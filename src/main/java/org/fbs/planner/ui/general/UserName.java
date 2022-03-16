package org.fbs.planner.ui.general;

import com.vaadin.flow.component.html.Span;

public class UserName extends Span
{
    public UserName(final String name, final String className)
    {
        this.setId("user-display-name");
        this.addClassName(className);
        this.setText(name);
    }
}
