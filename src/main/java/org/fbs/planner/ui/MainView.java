package org.fbs.planner.ui;

import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.dom.Element;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.fbs.planner.DefinitionPaths;
import org.fbs.planner.ui.general.ComponentJS;
import org.fbs.planner.ui.general.NavBar;
import org.fbs.planner.ui.general.NavBarElement;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@PageTitle("FBS - Vertretungsplaner")
@Route("home")
@CssImport(DefinitionPaths.CSS_DIR+"main.css")
public class MainView extends AppLayout
{

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
    private List<NavBarElement> defineNavBarContent()
    {
        final ArrayList<NavBarElement> elements = new ArrayList<>();
        //Definition der Abschnitte in der Navigationsleiste

        elements.add(new NavBarElement("Home","link('/home')"));
        elements.add(new NavBarElement("Tab 2","link('/tab2')"));
        elements.add(new NavBarElement("Tab 3 ...","link('/tab3')"));


        return elements;
    }

    /**
     * Main-Konstruktor
     */
    public MainView()
    {
        defineScript();
        // Use the drawer for the menu
        setPrimarySection(Section.DRAWER);
        addToNavbar(new NavBar(defineNavBarContent()));
    }


}
