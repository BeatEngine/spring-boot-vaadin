package org.fbs.planner.ui.table.components;

import com.vaadin.flow.component.Component;

import java.util.Iterator;
import java.util.stream.Stream;

@com.vaadin.flow.component.Tag("tbody")
public class TableBody extends com.vaadin.flow.component.HtmlContainer implements com.vaadin.flow.component.ClickNotifier<com.vaadin.flow.component.html.Div>, com.vaadin.flow.component.HasOrderedComponents
{
    public TableBody()
    {

    }

    @Override
				/**
				* Description
				* 
				* @return (String) 
				*/
    public String toString()
    {
        String intern = "";

        final Stream<Component> children = this.getChildren();
        final Iterator<Component> iterator = children.iterator();

        while (iterator.hasNext())
        {
            final Tr next = (Tr) iterator.next();

            Stream<Component> ch = next.getChildren();
            Iterator<Component> iter = ch.iterator();
            intern += "<tr>";
            while (iter.hasNext())
            {
                Component nxt = iter.next();
                if(nxt instanceof Td)
                {
                    final Td td = (Td) nxt;
                    intern += "<td>" + td.getText() + "</td>";
                }
                else if(nxt instanceof  Th)
                {
                    final Th th = (Th) nxt;
                    intern += "<th>" + th.getText() + "</th>";
                }
                else
                {
                    intern += "<"+nxt.getElement().getTag()+">" + nxt.getElement().getText() + "</"+nxt.getElement().getTag()+">";
                }
            }
            intern += "</tr>\n";
        }

        return "<tbody>\n" + intern + "</tbody>";
    }
}
