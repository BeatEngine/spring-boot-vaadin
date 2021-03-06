/* This is useful for tomcat applications server.xy/applicationname/home */
var applicationname = ".";

function link(url)
{
    document.location = url;
}

function selectAuto(elementId, value)
{
    var select = document.getElementById(elementId);
    if(select)
    {
        var opts = select.getElementsByTagName("option");
        for(var i = 0; i < opts.length; i++)
        {
            if(opts[i].value == value)
            {
                opts[i].setAttribute("selected", "");
                opts[i].selected = true;
                select.value = value;
            }
            else
            {
                opts[i].removeAttribute("selected");
                opts[i].selected = false;
            }
        }
    }
    else
    {
        console.log("Warnung: select nicht gefunden!");
    }
}

function selectAntrag()
{
    if(event.target.value == 0)
    {
        //Neu anlegen --> Felder leeren
        document.getElementById("name-antragsteller").value = "";
        document.getElementById("grund-antrag").value = "";
        document.getElementById("vermerk-antrag").value =  "";
        document.getElementById("anfang-antrag").value = "";
        document.getElementById("ende-antrag").value = "";
        //Tabelle leeren
        for(var y = 0; y < 16; y++)
        {
            for(var x = 1; x < 7; x++)
            {
                var cellBox = document.getElementById("cell-"+x+"-"+y);
                if(cellBox)
                {
                    cellBox.value = "";
                }
            }
        }
    }
    else
    {

        asyncRequest("POST","/antrag/"+event.target.value, "", function loadTable(){
            var res = JSON.parse(this.response);
            if(res)
            {
                if(res.antrag && res.cells)
                {
                    var antrag = res.antrag;
                    var istEntwurf = antrag.entwurf;
                    document.getElementById("name-antragsteller").value = antrag.name;
                    document.getElementById("grund-antrag").value = antrag.reason;
                    selectAuto("klasse-antrag", antrag.klasse);
                    document.getElementById("vermerk-antrag").value =  antrag.vermerk;
                    selectAuto("abteilungsleiter-antrag", antrag.abteilungsleiter);

                    if(antrag.schulleitung != "")
                    {
                        document.getElementById("schulleitung-antrag").value = antrag.schulleitung;
                    }

                    document.getElementById("anfang-antrag").value = antrag.von;
                    document.getElementById("ende-antrag").value = antrag.bis;

                    var zellen = res.cells;
                    for(var i = 0; i < zellen.length; i++)
                    {
                        var x = zellen[i].x;
                        var y = zellen[i].y;
                        var text = zellen[i].text;
                        var cellBox = document.getElementById("cell-"+x+"-"+y);
                        if(cellBox)
                        {
                            cellBox.value = text;
                        }
                    }
                }
                else
                {
                    alert("Interner Fehler!\nDaten wurden nicht korrekt verarbeitet!");
                }
            }
        });

   }
}

function updateAvailableAntraege()
{
    asyncRequest("POST", "/listeentwurf", "", function tmplistload(){
        var arr = JSON.parse(this.response);
        var select = document.getElementById("select-saved");
        select.innerHTML = "";
        var opt = document.createElement("option");
            opt.value = "0";
            opt.innerText = "[Neu anlegen]";
            select.appendChild(opt);
        for(var i = 0; i < arr.length; i++)
        {
            var option = document.createElement("option");
            option.value = arr[i].id;
            var klasse = arr[i].klasse;
            if(!klasse)
            {
                klasse = "null";
            }
            option.innerText = arr[i].name + "-" + klasse + "-" + arr[i].von;
            select.appendChild(option);
        }
        select.value = "0";
    });
}

function entwurfSpeichern()
{
    var inputs = document.getElementsByTagName("vaadin-vertical-layout")[0].getElementsByTagName("input");
    var textboxes = document.getElementsByTagName("vaadin-vertical-layout")[0].getElementsByTagName("textarea");
    var selects = document.getElementsByTagName("vaadin-vertical-layout")[0].getElementsByTagName("select");
    var body = "";
    var p = 0;
    for(var i = 0; i < inputs.length; i++)
    {
        var inp = inputs[i];
        if(i > 0)
        {
            body += "&";
            p++;
        }
        body += inp.name + "=" + encodeURIComponent(inp.value);
    }
    for(var i = 0; i < textboxes.length; i++)
    {
        var inp = textboxes[i];
        if(p > 0)
        {
            body += "&";
            p++;
        }
        body += inp.name + "=" + encodeURIComponent(inp.value);
    }
    for(var i = 0; i < selects.length; i++)
        {
            var inp = selects[i];
            if(p > 0)
            {
                body += "&";
                p++;
            }
            body += inp.name + "=" + encodeURIComponent(inp.value);
        }
    asyncRequest("POST", "/table/save", body, function tmp()
    {
        var id = document.getElementById("select-saved").value;
        updateAvailableAntraege();
        //Den aktuellen Plan ausw??hlen.
        selectAuto(select-saved, id);
        pushNotification("Entwurf gespeichert!", "Der Entwurf wurde gespeichert!");
    });
}

function entwurfBeantragen()
{
    entwurfSpeichern();
    updateAvailableAntraege();
}

function entwurfEntfernen()
{
    var select = document.getElementById("select-saved");
    asyncRequest("POST", "/antrag/"+select.value+"/remove", "", function(){
        console.log(this.response);
        updateAvailableAntraege();
    });
}


/**
 * HTML aus Response in gesuchtes Element einsetzen.
 * @Param dataResponse HTML-Response mit <... id="containerId"></...>
 * @Param containerId Id des Containers, der ersetzt werden soll.
 * */
function reloadContainerByResponse(dataResponse, containerId)
{
    var newContainer = dataResponse;
    //Baum ??berschreiben.
    if(newContainer.length > 0)
    {
        var htmlIndx = newContainer.indexOf("<html");

        if(htmlIndx >= 0)
        {
            while (i < newContainer.length && newContainer[htmlIndx] != '>')
            {
                htmlIndx++;
            }
            newContainer = newContainer.substring(htmlIndx, newContainer.indexOf("</html>"));
        }
        var index = newContainer.indexOf("id=\"" + containerId + "\"");
        if(index > 0 || containerId == "html")
        {
            if (containerId == "html") {
                var container = document.getElementsByTagName("html")[0];
                container.innerHTML = newContainer;
            } else {
                var dom = parseDOMelementExtractById(dataResponse, containerId);
                if (dom) {
                    var container = document.getElementById(containerId);
                    container.innerHTML = dom.innerHTML;
                } else {
                    var tagName = "";
                    var i = index;
                    while (i > 0 && newContainer[i] != '<') {
                        i--;
                    }
                    var a = i;
                    while (i < newContainer.length && newContainer[i] != ' ' && newContainer[i] != '>') {
                        i++;
                    }
                    tagName = newContainer.substring(a + 1, i);
                    while (i < newContainer.length && newContainer[i] != '>') {
                        i++;
                    }
                    a = i;
                    i = newContainer.indexOf("</" + tagName);
                    //Letztes
                    while (newContainer.indexOf("</" + tagName, i + 1) > i) {
                        i = newContainer.indexOf("</" + tagName, i + 1);
                    }
                    newContainer = newContainer.substring(a + 1, i - 1);
                    var container = document.getElementById(containerId);
                    container.innerHTML = newContainer;

                }
            }

        }
        else
        {
            console.log("Error reloadContainerByResponse");
        }
    }
    else
    {
        var updatedUrl = document.location.href.toString().replaceAll("#", "");
        if(updatedUrl.includes("?"))
        {
            updatedUrl = updatedUrl.substring(0, updatedUrl.indexOf("?"));
        }
        document.location.href = updatedUrl;
    }
}

/**
 * Ein Button mit type button verh??lt sich wie eine button submit, allerdings ??ber asynkronen Request.
 * Asyncron
 */
function executeFormByMemberEvent( that = null, callBackEvent = null)
{
    if(that) {
        //Button finden
        if (that && that.localName != "button") {
            while (that && that.localName != "button" && that.localName != "html") {
                that = that.parentElement;
            }
        }

        if (that == null || that.localName != "button") {
            console.log("Error executeFormByMemberEvent: Button not found!");
            return;
        }

        var parent = that.parentElement;
        //FORM finden
        while (parent && parent.localName != "form" && parent.localName != "html")
        {
            parent = parent.parentElement;
        }
        if(parent && parent.localName == "form")
        {
            var method = parent.method;
            var url = parent.action;
            //Trennen von relativen Teil-URLs
            var relative = url.indexOf(":");
            if(relative >= 0)
            {
                var parts = url.split(":");
                parts = parts[parts.length-1].split("/");
                url = "/";
                for(var i = 1; i < parts.length; i++)
                {
                    if(parts[i] != '.')
                    {
                        url += parts[i];
                        if (i + 1 < parts.length)
                        {
                            url += "/";
                        }
                    }
                }
            }
            if(url.startsWith("//"))
            {
                url = url.substr(1);
            }


            var inputs = parent.getElementsByTagName("input");
            var texts = parent.getElementsByTagName("textarea");
            var bodyParams = "";
            var attached = 0;
            //Inputs an body anh??ngen
            for(var i = 0; i < inputs.length; i++)
            {
                var inp = inputs[i];
                if (i > 0) {
                    bodyParams += "&";
                    attached++;
                }
                bodyParams += decodeURI(inp.name) + "=" + decodeURI(inp.value);
            }
            for(var i = 0; i < texts.length; i++)
            {
                var inp = texts[i];
                if(inp.name && inp.name.length > 0)
                {
                    if (attached > 0) {
                        bodyParams += "&";
                        attached++;
                    }
                    bodyParams += decodeURI(inp.name) + "=" + decodeURI(inp.innerText);
                }
            }
            if(callBackEvent)
            {
                showLoadingProgressSpinner(5);
                asyncRequest(method, url, bodyParams, callBackEvent);
            }
            else
            {
                asyncRequest(method, url, bodyParams);
            }

        }
        else
        {
            console.log("Error: executeFormByMemberEvent context is not inside a FORM-Element!");
        }
    }
    else
    {
        console.log("Warning: executeFormByMemberEvent parameter is null!");
    }
}

/**
 * Best??tigen, dass eine Funktion ausgef??hrt wird.
 * @param func function(){...}
 * @param confirmMsg Meldung-Text
 */
function askToExecute(func, confirmMsg)
{
    if(confirm(confirmMsg))
    {
        func();
    }
}

/**
 * Sleep
 * @param milliSeconds
 */
function sleepMilli(milliSeconds)
{
    var start = Date.now();
    var op = "";
    while ((Date.now()-start) < milliSeconds)
    {
        op = "abcde";
    }
}

/** Kopiert die Daten eines Eintrages der Auswahlliste in das Neue-Zeile-Formular. */
function copyValue_SelectionToEntry(Selection, idEntry) {
    var e = document.getElementById(idEntry);
    e.setAttribute("value", Selection.value);

}

/** Extrahiert ein html-Element, das wie ein document verwendet werden kann, per Id aus einem String*/
function parseDOMelementExtractById(htmlString, elementId)
{
    var parser = new DOMParser();
    var xml = parser.parseFromString(htmlString.toString(), 'text/html');
    return xml.getElementById(elementId);
}
/** Sorgt daf??r, dass alle <img>'s im Element mit der Id neu geladen werden*/
function reloadImagesInElement(elementId)
{
    var element = document.getElementById(elementId);
    if(element) {
        var imgs = element.getElementsByTagName('img');
        var customParam = "timestampforreloadimg";
        for(var i=0; i < imgs.length; i++)
        {
            var imgsrc = imgs[i].src;
            var timestampIndex = imgsrc.indexOf(customParam);
            if(timestampIndex >= 0)
            {
                //Gefunden
                //Herausschneiden des vorherigen customParam
                var d = timestampIndex;
                while (imgsrc[d] != '&')
                {
                    if(d+1 == imgsrc.length)
                    {
                        break;
                    }
                    d++;
                }
                if(d+1 == imgsrc.length)
                {
                    //Das timestampIndex-1 folgt aus &timestampIndex oder ?timestampIndex
                    imgsrc = imgsrc.substr(0, timestampIndex-1);
                }
                else
                {
                    var partA = imgsrc.substr(0, timestampIndex-1);
                    var partB = imgsrc.substr(d);
                    imgsrc = partA + partB;
                }
            }

            //Die src wird ver??ndert, damit sich das Bild neu geladen wird.
            if(imgsrc.indexOf('?') >= 0)
            {
                imgsrc += '&' + customParam + '=' + new Date().getTime();
            }
            else
            {
                imgsrc += '?' + customParam + '=' + new Date().getTime();
            }
            imgs[i].src = imgsrc;
        }
    }
}

function removeThisModal(element)
{
    var p = element.parentNode;
    var l = 0;
    while(!p.classList.contains("modal"))
    {
        p = p.parentNode;
        if(p == null || l > 20)
        {
            return;
        }
        l++;
    }
    p.remove();
}

function pushNotification(title, text, duration = 3000)
{
    var code = "<div aria-live=\"polite\" aria-atomic=\"true\" style=\"position: absolute; min-height: 200px;\">\n" +
        "  <div class=\"toast\" style=\"position: absolute; top: 0; right: 0; z-index: 99999;\">\n" +
        "    <div class=\"toast-header\">\n" +
        "      <img src=\"...\" class=\"rounded mr-2\" alt=\"...\">\n" +
        "      <strong class=\"mr-auto\">"+title+"</strong>\n" +
        "      <button type=\"button\" class=\"ml-2 mb-1 close\" data-dismiss=\"toast\" aria-label=\"Close\">\n" +
        "        <span aria-hidden=\"true\">&times;</span>\n" +
        "      </button>\n" +
        "    </div>\n" +
        "    <div class=\"toast-body\">\n" +
        text +
        "    </div>\n" +
        "  </div>\n" +
        "</div>";

    var notification = document.createElement("div");
    notification.innerHTML = code;
    notification.id = "pushNotification";
    document.getElementsByTagName("body")[0].insertBefore(notification, document.getElementsByTagName("body")[0].children[0]);
    window.setTimeout(function rem(){
        document.getElementById("pushNotification").remove();
    }, duration);
}
