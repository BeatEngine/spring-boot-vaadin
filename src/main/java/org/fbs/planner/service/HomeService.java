package org.fbs.planner.service;

import elemental.json.JsonObject;
import org.fbs.planner.entity.Antrag;
import org.fbs.planner.entity.Cell;
import org.fbs.planner.repository.AntragRepository;
import org.fbs.planner.repository.CellRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.util.*;

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

    public ResponseEntity<byte[]> getTableData(final long antragId)
    {
        final JSONObject response = new JSONObject();

        Optional<Antrag> byId = antragRepo.findById(antragId);

        if(!byId.isEmpty())
        {
            final Antrag antrag = byId.get();

            List<Cell> allByAntragId = cellRepo.findAllByAntragId(antragId);

            allByAntragId.sort(Cell::compareTo);

            final JSONObject antragJSON = new JSONObject();

            antragJSON.put("id", antragId);
            antragJSON.put("user_id", antrag.getUser_id());
            antragJSON.put("name", antrag.getName());
            antragJSON.put("klasse", antrag.getKlasse());
            antragJSON.put("reason", antrag.getReason());
            antragJSON.put("vermerk", antrag.getVermerk());
            antragJSON.put("abteilungsleiter", antrag.getAbteilungsleiter());
            antragJSON.put("schulleitung", antrag.getSchulleitung());
            antragJSON.put("von", antrag.getVon().toLocalDateTime().toLocalDate());
            antragJSON.put("bis", antrag.getBis().toLocalDateTime().toLocalDate());
            antragJSON.put("entwurf", antrag.isEntwurf());
            response.put("antrag", antragJSON);

            final JSONArray cells = new JSONArray(allByAntragId);
            response.put("cells", cells);


        }

        return new ResponseEntity<byte[]>(response.toString().getBytes(),HttpStatus.OK);
    }

				/**
				* Description
				*
				* @param request
				* @param sessionId
				* @return (ModelAndView) 
				*/
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

        List<Antrag> allByUserId = antragRepo.findByUserId(currentUserId);

        Timestamp timestampAnfang = Timestamp.valueOf(LocalDate.parse(anfang).atStartOfDay());
        Timestamp timestampEnde = Timestamp.valueOf(LocalDate.parse(ende).atTime(23, 59));

        Antrag equal = antragRepo.findByUserIdAndKlasseAndVonAndBis(currentUserId, klasse, timestampAnfang, timestampEnde);

        final Antrag antrag;

        if(equal != null)
        {
            antrag = equal;
        }
        else
        {
            antrag = new Antrag(null,currentUserId, name, klasse, grund, vermerk, abteilungsleiter, schulleitung,
                    timestampAnfang, timestampEnde);
        }

        Antrag save = antragRepo.save(antrag);

        //Zellen
        List<Cell> cells = cellRepo.findAllByAntragId(save.getId());
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
                boolean find = false;
                for(int i = 0; i < cells.size(); i++)
                {
                    final Cell cell = cells.get(i);
                    if(cell.getX() == x && cell.getY() == y)
                    {
                        cells.get(i).setText(value);
                        find = true;
                        break;
                    }
                }
                if(!find)
                {
                    cells.add(new Cell(null, save.getId(), value, x, y));
                }
            }
        }
        try
        {

            cellRepo.saveAll(cells);
        }
        catch (final Exception e)
        {
            System.out.println("Problem beim Speichern: " + e.getMessage());
        }
        return new ModelAndView("home");
    }

}
