package org.fbs.planner.ui.general;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.dom.Element;

@com.vaadin.flow.component.Tag("option")
public class Option extends com.vaadin.flow.component.HtmlContainer implements com.vaadin.flow.component.ClickNotifier<com.vaadin.flow.component.html.Div>, com.vaadin.flow.component.HasOrderedComponents
{
    private String value = "";

    final Element option = new CustomElement("option");

    public Option(final String value, final String text)
    {
        this.value = value;
        this.setText(text);
        option.setAttribute("value", value);
        option.setText(text);
    }

    @Override
    public String toString()
    {
        return "\n<option id=\""+this.getId().toString()+"\" value=\""+ value + "\" >" + this.getText() + "</option>";
    }

    @Override
    public Element getElement()
    {
        if(option == null)
        {
            return new CustomElement("option");
        }
        return option;
    }
}
