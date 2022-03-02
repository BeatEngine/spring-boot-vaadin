package org.fbs.planner.ui;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.fbs.planner.DefinitionPaths;
import org.fbs.planner.ui.general.InfoLine;
import org.fbs.planner.ui.general.NavBar;

@PageTitle("Login | FBS - Vertretungsplaner")
@Route("login")
@CssImport(DefinitionPaths.CSS_DIR + "login.css")
public class LoginView extends VerticalLayout implements BeforeEnterObserver
{
    private void defineScript()
    {
        var ui = UI.getCurrent();
        var page = ui.getPage();
        page.addJavaScript(DefinitionPaths.JS_DIR + "login.js");

    }

    private final LoginForm login = new LoginForm();

    public LoginView()
    {
        defineScript();
        addClassName("login-view");
        setSizeFull();




        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("auth");


        add(new H1("FBS - Vertretungsplan"), login);

        this.add(new InfoLine("redirect"));
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent)
    {
        // inform the user about an authentication error
        if (beforeEnterEvent.getLocation()


                .getQueryParameters()
                .getParameters()
                .containsKey("error"))
        {
            login.setError(true);
        }
    }

}
