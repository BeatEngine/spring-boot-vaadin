package org.fbs.planner.ui;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.fbs.planner.DefinitionPaths;
import org.fbs.planner.service.MainService;
import org.fbs.planner.ui.general.ComponentJS;
import org.fbs.planner.ui.general.NavBar;
import org.fbs.planner.ui.general.NavBarElement;
import org.fbs.planner.ui.general.UserName;
import org.fbs.planner.ui.table.Vertretungsplan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.swing.*;
import java.util.*;
import java.util.stream.Stream;

@PageTitle("FBS - Vertretungsplaner")
@Route("home")
@CssImport(DefinitionPaths.CSS_DIR+"main.css")
@SpringComponent
@UIScope
public class MainView extends VerticalLayout
{
    private MainService service;

    /**
     * Laden des JavaScripts für Alle Seiten
     */
    private void defineScript()
    {
        var ui = UI.getCurrent();
        var page = ui.getPage();
        page.addJavaScript(DefinitionPaths.JS_DIR + "main.js");

    }

    /**
     * Navigationsleiste Konstruieren
     * @return Liste der Elemente in der Leiste
     */
    private List<Component> defineNavBarContent()
    {
        final ArrayList<Component> elements = new ArrayList<>();
        //Definition der Abschnitte in der Navigationsleiste

        elements.add(new NavBarElement("Home","link('/home')"));
        elements.add(new NavBarElement("Tab 2","link('/tab2')"));
        elements.add(new NavBarElement("Tab 3 ...","link('/tab3')"));

        Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();

        for(Cookie cookie : cookies)
        {
            if("session".equals(cookie.getName()))
            {
                elements.add(new UserName(service.getSessions().getUser(cookie.getValue()),"user-name"));
                break;
            }
        }

        return elements;
    }

    /**
     * Main-Konstruktor
     */
    public MainView()
    {
        service = MainService.getInstance();
        defineScript();

        add(new NavBar(defineNavBarContent()));

        add(new Vertretungsplan());
    }


}
