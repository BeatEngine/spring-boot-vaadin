package org.fbs.planner.ui.general;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.dom.Element;

public class ComponentJS extends Component
{
    private final Element element;

    public ComponentJS(final String src)
    {
        Element that = new Element("script");
        that.setAttribute("type","text/javascript");
        that.setAttribute("src", src);
        element = that;
    }

    @Override
    public Element getElement()
    {
        return element;
    }
}
