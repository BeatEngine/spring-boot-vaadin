package org.fbs.planner.ui.general;

@com.vaadin.flow.component.Tag("option")
public class Option extends com.vaadin.flow.component.HtmlContainer implements com.vaadin.flow.component.ClickNotifier<com.vaadin.flow.component.html.Div>, com.vaadin.flow.component.HasOrderedComponents
{
    private String value = "";

    public Option(final String value, final String text)
    {
        this.value = value;
        this.setText(text);
    }

    @Override
    public String toString()
    {
        return "<option id=\""+this.getId()+"\" value=\""+ value + "\" >" + this.getText() + "</option>";
    }
}
