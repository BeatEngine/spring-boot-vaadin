package org.fbs.planner.ui.general;

import com.vaadin.flow.component.html.Div;

public class NavBarElement extends Div
{
    public NavBarElement(final String text)
    {
        this.setClassName("nav-bar-element");
        this.setText(text);
    }

    /**
     *
     * @param text innerText
     * @param onClick onclick="[onClick]"
     */
    public NavBarElement(final String text, final String onClick)
    {
        this.setClassName("nav-bar-element");
        this.setText(text);
        this.getElement().setAttribute("onclick", onClick);
    }
}
