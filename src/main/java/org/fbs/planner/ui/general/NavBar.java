package org.fbs.planner.ui.general;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.html.Div;

import java.util.List;

public class NavBar extends Div
{
    public NavBar()
    {
        this.setClassName("nav-bar-container");

    }

    public NavBar(List<Component> elements)
    {
        this.setClassName("nav-bar-container");
        for(Component element : elements)
        {
            this.add(element);
        }
    }
}
