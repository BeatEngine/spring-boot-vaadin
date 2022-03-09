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

    public Tr getHeadRow(int index)
    {
        final Stream<Component> children = head.getChildren();
        final Iterator<Component> iterator = children.iterator();
        for(int i = 0; i <= index && iterator.hasNext(); i++)
        {
            final Component next = iterator.next();
            if(i == index)
            {
                return (Tr)next;
            }
        }
        return null;
    }

    public Tr getBodyRow(int index)
    {
        final Stream<Component> children = body.getChildren();
        final Iterator<Component> iterator = children.iterator();
        for(int i = 0; i <= index && iterator.hasNext(); i++)
        {
            final Component next = iterator.next();
            if(i == index)
            {
                return (Tr)next;
            }
        }
        return null;
    }

    public Td getBodyCell(int x, int y)
    {
        final Stream<Component> children = body.getChildren();
        final Iterator<Component> iterator = children.iterator();
        for(int i = 0; i <= y && iterator.hasNext(); i++)
        {
            final Component next = iterator.next();
            if(i == y)
            {
                final Tr row = (Tr)next;
                final Stream<Component> cells = row.getChildren();
                final Iterator<Component> iter = cells.iterator();

                for(int c = 0; c <= x && iter.hasNext(); c++)
                {
                    final Component nxt = iter.next();
                    if(c == x)
                    {
                        return (Td)nxt;
                    }
                }
                return null;
            }
        }
        return null;
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
                final Tr cur = (Tr)next;
                cur.add(new Th());
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
                final Tr cur = (Tr)next;
                cur.add(new Td());
            }
            idx++;
        }
    }

}
