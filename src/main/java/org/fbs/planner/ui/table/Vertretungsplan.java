package org.fbs.planner.ui.table;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Input;
import com.vaadin.flow.component.html.Span;
import org.fbs.planner.DefinitionPaths;
import org.fbs.planner.ui.table.components.CellInput;
import org.fbs.planner.ui.table.components.Th;
import org.fbs.planner.utils.UtilService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

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
        final Div tableWrapperHead = new Div();
        tableWrapperHead.setClassName("table-scroll-head");
        tableWrapperHead.add(headTable);
        add(tableWrapperHead);
        final Div tableWrapper = new Div();
        tableWrapper.setClassName("table-scroll");
        tableWrapper.add(table);
        add(tableWrapper);
        initForm();
    }

    private void initForm()
    {
        LocalDate mondayOfCurrentWeek = LocalDate.now();
        mondayOfCurrentWeek = mondayOfCurrentWeek.minusDays(mondayOfCurrentWeek.getDayOfWeek().getValue()-1);
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
            if(i != 11)
            {
                for(int x = 1; x < 7; x++)
                {
                    table.getBodyCell(x, i).add(new CellInput("cell-"+x+"-"+i, "cell-input", "height: 20px;"));
                }
            }
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        ZoneId defaultZoneId = ZoneId.systemDefault();
        table.getHeadCell(0,0).setText("Stunden");
        table.getHeadCell(1,0).setText("Montag " + dateFormat.format(Date.from(mondayOfCurrentWeek.atStartOfDay(defaultZoneId).toInstant())));
        table.getHeadCell(2,0).setText("Dienstag " + dateFormat.format(Date.from(mondayOfCurrentWeek.plusDays(1).atStartOfDay(defaultZoneId).toInstant())));
        table.getHeadCell(3,0).setText("Mittwoch " + dateFormat.format(Date.from(mondayOfCurrentWeek.plusDays(2).atStartOfDay(defaultZoneId).toInstant())));
        table.getHeadCell(4,0).setText("Donnerstag " + dateFormat.format(Date.from(mondayOfCurrentWeek.plusDays(3).atStartOfDay(defaultZoneId).toInstant())));
        table.getHeadCell(5,0).setText("Freitag " + dateFormat.format(Date.from(mondayOfCurrentWeek.plusDays(4).atStartOfDay(defaultZoneId).toInstant())));
        table.getHeadCell(6,0).setText("Samstag " + dateFormat.format(Date.from(mondayOfCurrentWeek.plusDays(5).atStartOfDay(defaultZoneId).toInstant())));
        final String monday = mondayOfCurrentWeek.toString();
        headTable.getHeadRow(0).removeAll();
        Th title = new Th();
        title.setText("Vertretungsplan / Abwesenheitsmeldung");
        title.getStyle().set("font-size","3vm");
        title.setWidth("47%");
        headTable.getHeadRow(0).add(title);
        headTable.getHeadRow(1).removeAll();
        Th headreason = new Th();
        headreason.setWidth("47%");
        Span name = new Span();
        name.setText("Name:");
        name.setWidth("8.3333%");
        name.getStyle().set("display", "inline-block");
        name.getStyle().set("text-align","center");
        Input nameInp = new Input();
        nameInp.getElement().setAttribute("name", "name-antragsteller");
        nameInp.setId("name-antragsteller");
        nameInp.setClassName("form-input");
        Span von = new Span();
        von.setText("Von:");
        von.setWidth("8.3333%");
        von.getStyle().set("display", "inline-block");
        von.getStyle().set("text-align","center");
        Input vonInp = new Input();
        vonInp.getElement().setAttribute("name", "anfang-antrag");
        vonInp.setId("anfang-antrag");
        vonInp.setClassName("form-input");
        vonInp.setType("date");
        vonInp.setValue(monday);
        vonInp.getElement().setAttribute("min", monday);
        Span bis = new Span();
        bis.setText("Bis:");
        bis.setWidth("8.3333%");
        bis.getStyle().set("display", "inline-block");
        bis.getStyle().set("text-align","center");
        Input bisInp = new Input();
        bisInp.getElement().setAttribute("name", "ende-antrag");
        bisInp.setId("ende-antrag");
        bisInp.setClassName("form-input");
        bisInp.setType("date");
        bisInp.setValue(monday);
        bisInp.getElement().setAttribute("min", monday);
        headreason.add(name);
        headreason.add(nameInp);
        headreason.add(von);
        headreason.add(vonInp);
        headreason.add(bis);
        headreason.add(bisInp);
        headTable.getHeadRow(1).add(headreason);

        headTable.getHeadRow(2).removeAll();
        headTable.getHeadRow(2).add(new Th());
        headTable.getHeadRow(2).add(new Th());
        headTable.getHeadCell(0, 2).setWidth("50%");
        headTable.getHeadCell(0, 2).setText("Grund der Abwesenheit:\n\nKlasse: ");
        Input classInp = new Input();
        classInp.getElement().setAttribute("name", "klasse-antrag");
        classInp.setId("klasse-antrag");
        classInp.setClassName("form-input");
        Span restText = new Span();
        restText.setText("(Belege, wie Einladungen usw. anhängen)");
        headTable.getHeadCell(0, 2).add(classInp);
        headTable.getHeadCell(0, 2).add(restText);

        headTable.getHeadCell(0, 2).getStyle().set("white-space","pre-line");
        headTable.getHeadCell(0, 2).getStyle().set("width","47%");
        headTable.getHeadCell(0, 2).getStyle().set("text-align","left");
        headTable.getHeadCell(1, 2).getStyle().set("width","53%");
        Div vermerk = new Div();
        vermerk.setText("Genehmigungsvermerke mit Datum:");
        vermerk.setHeight("33.3333%");
        vermerk.getStyle().set("border", "solid 1p black");
        vermerk.getStyle().set("display", "inline-block");
        vermerk.getStyle().set("white-space","nowrap");
        vermerk.getStyle().set("width","100%");
        vermerk.getStyle().set("text-align","left");
        Input vermerkInp = new Input();
        vermerkInp.getElement().setAttribute("name", "vermerk-antrag");
        vermerkInp.setId("vermerk-antrag");
        vermerkInp.setClassName("form-input");
        Div abteilungsleiter = new Div();
        abteilungsleiter.setText("Abteilungsleiter/in:");
        abteilungsleiter.setHeight("33.3333%");
        abteilungsleiter.getStyle().set("border", "solid 1p black");
        abteilungsleiter.getStyle().set("display", "inline-block");
        abteilungsleiter.getStyle().set("white-space","nowrap");
        abteilungsleiter.getStyle().set("width","100%");
        abteilungsleiter.getStyle().set("text-align","left");
        Input abteilungsleiterInp = new Input();
        abteilungsleiterInp.getElement().setAttribute("name", "abteilungsleiter-antrag");
        abteilungsleiterInp.setId("abteilungsleiter-antrag");
        abteilungsleiterInp.setClassName("form-input");
        Div schulleitung = new Div();
        schulleitung.setText("Schulleitung:");
        schulleitung.setHeight("33.3333%");
        schulleitung.getStyle().set("border", "solid 1p black");
        schulleitung.getStyle().set("display", "inline-block");
        schulleitung.getStyle().set("white-space","nowrap");
        schulleitung.getStyle().set("width","100%");
        schulleitung.getStyle().set("text-align","left");
        Input schulleitungInp = new Input();
        schulleitungInp.getElement().setAttribute("name", "schulleitung-antrag");
        schulleitungInp.setId("schulleitung-antrag");
        schulleitungInp.setClassName("form-input");

        vermerk.add(vermerkInp);
        abteilungsleiter.add(abteilungsleiterInp);
        schulleitung.add(schulleitungInp);

        Th right = headTable.getHeadCell(1, 2);
        right.add(vermerk);
        right.add(abteilungsleiter);
        right.add(schulleitung);

        //System.out.println(UtilService.toHTML(table));
    }


}
