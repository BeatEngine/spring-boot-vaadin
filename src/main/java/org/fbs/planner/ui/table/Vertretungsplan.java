package org.fbs.planner.ui.table;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import org.fbs.planner.DefinitionPaths;

import java.time.LocalDate;

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
        table.addHeadRow();
        for(int i = 0; i < collumnSize; i++)
        {
            table.addHeadColumn();
        }
        table.addClassName("main-table");
        add(table);
        initForm(LocalDate.now().toString());
    }

    private void initForm(final String date)
    {
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
        table.getHeadCell(0,3).setText("Stunden");
        table.getHeadCell(1,3).setText("Montag " + date);
        table.getHeadCell(2,3).setText("Dienstag " + date);
        table.getHeadCell(3,3).setText("Mittwoch " + date);
        table.getHeadCell(4,3).setText("Donnerstag " + date);
        table.getHeadCell(5,3).setText("Freitag " + date);



    }


}
