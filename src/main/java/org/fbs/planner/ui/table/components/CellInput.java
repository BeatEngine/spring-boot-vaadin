package org.fbs.planner.ui.table.components;

import com.vaadin.flow.component.html.Input;

public class CellInput extends TextArea
{
    public CellInput(final String nameId, final String className, final String style)
    {
        this.setId(nameId);
        this.getElement().setAttribute("name", nameId);
        this.setClassName(className);
        if(!style.isBlank())
        {
            this.getElement().setAttribute("style", style);
        }
    }
    public CellInput(final String nameId, final String value, final String className, final String style)
    {
        this.setId(nameId);
        this.getElement().setAttribute("name", nameId);
        this.setValue(value);
        this.setClassName(className);
        if(!style.isBlank())
        {
            this.getElement().setAttribute("style", style);
        }
    }
}
