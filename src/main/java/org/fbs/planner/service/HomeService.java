package org.fbs.planner.service;

import org.fbs.planner.entity.Antrag;
import org.fbs.planner.entity.Cell;
import org.fbs.planner.repository.AntragRepository;
import org.fbs.planner.repository.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class HomeService
{
    @Autowired
    MainService main;

    @Autowired
    AntragRepository antragRepo;

    @Autowired
    CellRepository cellRepo;

    public ModelAndView home()
    {
        final ModelAndView view = new ModelAndView("home");


        return view;
    }

    public ModelAndView save(final Map<String, String> request, final String sessionId)
    {

        final String name = request.get("name-antragsteller");
        final String abteilungsleiter = request.get("abteilungsleiter-antrag");
        final String ende = request.get("ende-antrag");
        final String anfang = request.get("anfang-antrag");
        final String klasse = request.get("klasse-antrag");
        final String grund = request.get("grund-antrag");
        final String schulleitung = request.get("schulleitung-antrag");
        final String vermerk = request.get("vermerk-antrag");
        Long currentUserId = main.getSessions().getUserId(sessionId);
        Antrag antrag = new Antrag(null,currentUserId, name, klasse, grund, vermerk, abteilungsleiter, schulleitung,  Timestamp.valueOf(LocalDate.parse(anfang).atStartOfDay()), Timestamp.valueOf(LocalDate.parse(ende).atTime(23,59)));

        Antrag save = antragRepo.save(antrag);

        //Zellen
        List<Cell> cells = new ArrayList<>();
        final Iterator<Map.Entry<String, String>> iterator = request.entrySet().iterator();
        while (iterator.hasNext())
        {
            final Map.Entry<String, String> next = iterator.next();
            final String key = next.getKey();
            final String value = next.getValue();
            if(key.startsWith("cell-"))
            {
                final String[] split = key.substring(5).split("-");
                final int x = Integer.parseInt(split[0]);
                final int y = Integer.parseInt(split[1]);
                cells.add(new Cell(null, save.getId(), value, x, y));
            }
        }
        cellRepo.saveAll(cells);
        return new ModelAndView("home");
    }

}
