package org.fbs.planner.ui.table;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import org.fbs.planner.DefinitionPaths;

@CssImport(DefinitionPaths.CSS_DIR+"table.css")
public class Vertretungsplan extends Div
{
    private CustomTable table;
    public Vertretungsplan()
    {
        table = new CustomTable(7, 16, false);
        table.setWidth("90vw");
        table.setHeight("90vh");
        table.addHeadRow();
        table.addHeadRow();
        table.addHeadRow();
        for(int i = 0; i < 7; i++)
        {
            table.addHeadColumn();
        }
        table.addClassName("main-table");
        add(table);
    }
}
