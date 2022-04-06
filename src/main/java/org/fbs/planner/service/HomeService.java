package org.fbs.planner.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Service
public class HomeService
{

    public ModelAndView home()
    {
        final ModelAndView view = new ModelAndView("home");


        return view;
    }

    public ModelAndView save(final Map<String, String> request)
    {

        return new ModelAndView("home");
    }

}
