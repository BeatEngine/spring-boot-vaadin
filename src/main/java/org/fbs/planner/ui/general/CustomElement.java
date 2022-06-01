package org.fbs.planner.ui.general;

import com.vaadin.flow.dom.Element;
import com.vaadin.flow.dom.ElementStateProvider;
import com.vaadin.flow.internal.StateNode;

public class CustomElement extends Element
{
    private String tag = "";

    public CustomElement(final String tag)
    {
        super(tag);
        this.tag = tag;
    }

    @Override
    public String getTag()
    {
        return tag;
    }

    @Override
    public String toString()
    {
        String inner = "";
        for(final Element c : this.getChildren().toList())
        {
            inner += c.toString() + "\n";
        }
        String atts = "";
        for(final String key : this.getAttributeNames().toList())
        {
            atts += key + "=\"" + this.getAttribute(key) + "\" ";
        }
        return "\n<" + tag + " " + atts +">\n" + inner + "</" + tag + ">";
    }
}
