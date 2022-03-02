function lastPath()
{
    var redirectInp = document.getElementById("redirect");
    var lastLoc = window.location.href;
    if (lastLoc.indexOf("logout") < 0) {
        //Relativer Pfad *:*:*[/...]
        lastLoc = lastLoc.substr(lastLoc.indexOf("/", lastLoc.indexOf(":", lastLoc.indexOf(":") + 1) + 1));
        //Reason herausnehmen
        var qidx = lastLoc.indexOf("redirect=");
        if (qidx > 0)
        {
            lastLoc = lastLoc.substr(qidx + 9);
            var andIdx = lastLoc.indexOf("&");
            if (andIdx > 0)
            {
                lastLoc = lastLoc.substr(0, lastLoc.indexOf("&"))
            }
            lastLoc = decodeURI(lastLoc);
        }
        redirectInp.value = lastLoc;
    } else {
        redirectInp.value = "/" + applicationname + "/";
    }
}
document.getElementsByTagName("body")[0].setAttribute("onload", "lastPath();");

