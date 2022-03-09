package org.fbs.planner.ui.table;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import org.fbs.planner.DefinitionPaths;

@CssImport(DefinitionPaths.CSS_DIR+"table.css")
public class Vertretungsplan extends Div
{
    private CustomTable table;

    private final int collumnSize = 7;

    public Vertretungsplan()
    {
        table = new CustomTable(7, 16, false);
        table.setWidth("90vw");
        table.setHeight("90vh");
        table.addHeadRow();
        table.addHeadRow();
        table.addHeadRow();
        for(int i = 0; i < collumnSize; i++)
        {
            table.addHeadColumn();
        }
        table.addClassName("main-table");
        add(table);
        initForm();
    }

    private void initForm()
    {
        for(int i = 0; i < 16; i++)
        {
            table.addBodyRow();
        }
        for(int i = 0; i < collumnSize; i++)
        {
            table.addBodyColumn();
        }
        int lineNumber = 1;
        for(int i = 0; i < 16; i++)
        {
            if(i < 11)
            {
                table.getBodyCell(0, i).setText(""+lineNumber);
                lineNumber++;
            }
            else if(i > 11)
            {
                table.getBodyCell(0, i).setText(""+lineNumber);
                lineNumber++;
            }
            else
            {
                table.getBodyCell(0, i).setText("Abendunterricht");
            }
        }


    }


}
