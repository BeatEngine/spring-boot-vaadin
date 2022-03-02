package org.fbs.planner.ui.table;

import com.vaadin.flow.component.Component;
import org.fbs.planner.ui.table.components.*;

import java.util.Iterator;
import java.util.stream.Stream;

@com.vaadin.flow.component.Tag("table")
public class CustomTable extends com.vaadin.flow.component.HtmlContainer implements com.vaadin.flow.component.ClickNotifier<com.vaadin.flow.component.html.Div>, com.vaadin.flow.component.HasOrderedComponents
{

    private TableHead head = new TableHead();

    private TableBody body = new TableBody();

    public CustomTable()
    {
        add(head);
        add(body);
    }

    public CustomTable(final int width, final int height, boolean head)
    {
        add(this.head);
        add(this.body);
        if(head)
        {
            addHeadRow();
        }
        for(int i = 0; i < height; i++)
        {
            addBodyRow();
        }
        for(int i = 0; i < width; i++)
        {
            if(head)
            {
                addHeadColumn();
            }
            addBodyColumn();
        }
    }

    public void addHeadRow()
    {
        head.add(new Tr());
    }

    public void addBodyRow()
    {
        body.add(new Tr());
    }

    public void addHeadColumn()
    {
        final Stream<Component> children = head.getChildren();
        final Iterator<Component> iterator = children.iterator();
        int idx = 0;
        while (iterator.hasNext())
        {
            final Component next = iterator.next();
            if(next instanceof Tr)
            {
                //head.remove(next);
                Tr cur = (Tr)next;
                cur.add(new Th());
                //head.addComponentAtIndex(idx, cur);
            }
            idx++;
        }
    }

    public void addBodyColumn()
    {
        final Stream<Component> children = body.getChildren();
        final Iterator<Component> iterator = children.iterator();
        int idx = 0;
        while (iterator.hasNext())
        {
            final Component next = iterator.next();
            if(next instanceof Tr)
            {
                //body.remove(next);
                Tr cur = (Tr)next;
                cur.add(new Td());
                //body.addComponentAtIndex(idx, cur);
            }
            idx++;
        }
    }

}
