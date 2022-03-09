package org.fbs.planner.ui.table;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import org.fbs.planner.DefinitionPaths;
import org.fbs.planner.ui.table.components.Th;
import org.fbs.planner.utils.UtilService;

import java.time.LocalDate;

@CssImport(DefinitionPaths.CSS_DIR+"table.css")
public class Vertretungsplan extends Div
{
    private CustomTable table;

    private CustomTable headTable;

    private final int collumnSize = 7;

    public Vertretungsplan()
    {
        table = new CustomTable(7, 16, false);
        headTable = new CustomTable(0, 0, false);
        headTable.addHeadRow();
        headTable.addHeadRow();
        headTable.addHeadRow();
        table.addHeadRow();
        for(int i = 0; i < collumnSize; i++)
        {
            table.addHeadColumn();
            headTable.addHeadColumn();
        }
        table.addClassName("main-table");
        headTable.addClassName("head-table");
        add(headTable);
        add(table);
        initForm(LocalDate.now());
    }

    private void initForm(final LocalDate date)
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
        table.getHeadCell(0,0).setText("Stunden");
        table.getHeadCell(1,0).setText("Montag " + date.toString());
        table.getHeadCell(2,0).setText("Dienstag " + date.plusDays(1).toString());
        table.getHeadCell(3,0).setText("Mittwoch " + date.plusDays(2).toString());
        table.getHeadCell(4,0).setText("Donnerstag " + date.plusDays(3).toString());
        table.getHeadCell(5,0).setText("Freitag " + date.plusDays(4).toString());
        table.getHeadCell(6,0).setText("Samstag " + date.plusDays(5).toString());

        headTable.getHeadRow(0).removeAll();
        Th title = new Th();
        title.setText("Vertretungsplan / Abwesenheitsmeldung");
        title.getStyle().set("font-size","24pt");
        title.setWidth("100%");
        headTable.getHeadRow(0).add(title);
        headTable.getHeadRow(1).removeAll();
        Th headreason = new Th();
        headreason.setWidth("100%");
        Span name = new Span();
        name.setText("Name:");
        name.setWidth("33.3333%");
        name.getStyle().set("display", "inline-block");
        Span von = new Span();
        von.setText("Von:");
        von.setWidth("33.3333%");
        von.getStyle().set("display", "inline-block");
        Span bis = new Span();
        bis.setText("Bis:");
        bis.setWidth("33.3333%");
        bis.getStyle().set("display", "inline-block");
        headreason.add(name);
        headreason.add(von);
        headreason.add(bis);
        headTable.getHeadRow(1).add(headreason);

        headTable.getHeadRow(2).removeAll();
        headTable.getHeadRow(2).add(new Th());
        headTable.getHeadRow(2).add(new Th());
        headTable.getHeadCell(0, 2).setWidth("70%");
        headTable.getHeadCell(0, 2).setText("Grund der Abwesenheit:\n\nKlasse:          (Belege, wie Einladungen usw. anhängen)");
        headTable.getHeadCell(0, 2).getStyle().set("white-space","pre-line");
        headTable.getHeadCell(0, 2).getStyle().set("width","100%");
        headTable.getHeadCell(0, 2).getStyle().set("text-align","left");
        Div vermerk = new Div();
        vermerk.setText("Genehmigungsvermerke mit Datum:");
        vermerk.setHeight("33.3333%");
        vermerk.getStyle().set("border", "solid 1p black");
        vermerk.getStyle().set("display", "inline-block");
        vermerk.getStyle().set("white-space","nowrap");
        vermerk.getStyle().set("width","100%");
        vermerk.getStyle().set("text-align","left");
        Div abteilungsleiter = new Div();
        abteilungsleiter.setText("Abteilungsleiter/in:");
        abteilungsleiter.setHeight("33.3333%");
        abteilungsleiter.getStyle().set("border", "solid 1p black");
        abteilungsleiter.getStyle().set("display", "inline-block");
        abteilungsleiter.getStyle().set("white-space","nowrap");
        abteilungsleiter.getStyle().set("width","100%");
        abteilungsleiter.getStyle().set("text-align","left");
        Div schulleitung = new Div();
        schulleitung.setText("Schulleitung:");
        schulleitung.setHeight("33.3333%");
        schulleitung.getStyle().set("border", "solid 1p black");
        schulleitung.getStyle().set("display", "inline-block");
        schulleitung.getStyle().set("white-space","nowrap");
        schulleitung.getStyle().set("width","100%");
        schulleitung.getStyle().set("text-align","left");

        Th right = headTable.getHeadCell(1, 2);
        right.add(vermerk);
        right.add(abteilungsleiter);
        right.add(schulleitung);

        //System.out.println(UtilService.toHTML(table));
    }


}
