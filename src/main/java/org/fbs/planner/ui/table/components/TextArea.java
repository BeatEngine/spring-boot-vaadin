package org.fbs.planner.ui.table.components;

@com.vaadin.flow.component.Tag("textarea")
public class TextArea extends com.vaadin.flow.component.HtmlContainer implements com.vaadin.flow.component.ClickNotifier<com.vaadin.flow.component.html.Div>, com.vaadin.flow.component.HasOrderedComponents
{
    public TextArea()
    {

    }

    public void setValue(final String value)
    {
        this.getElement().setAttribute("value", value);
    }

    public String getValue()
    {
        return this.getElement().getAttribute("value");
    }
}
