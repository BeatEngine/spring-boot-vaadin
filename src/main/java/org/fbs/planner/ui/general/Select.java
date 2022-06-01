package org.fbs.planner.ui.general;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@com.vaadin.flow.component.Tag("select")
public class Select extends com.vaadin.flow.component.HtmlContainer implements com.vaadin.flow.component.ClickNotifier<com.vaadin.flow.component.html.Div>, com.vaadin.flow.component.HasOrderedComponents
{
    private String name = "";
    private List<Option> options = new ArrayList<>();

    public Select()
    {

    }

    public void addOption(final Option opt)
    {
        options.add(opt);
    }

    public Select(final String id, final String name)
    {
        this.setId(id);
        this.getElement().setAttribute("name", name);
    }

    @Override
    public String toString()
    {
        String inner = "";
        for(int i = 0; i < options.size(); i++)
        {
            inner += options.toString();
        }
        return "<select id=\""+this.getId()+"\" name=\""+ name + "\" >" + inner + "</select>";
    }
}
