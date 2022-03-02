package org.fbs.planner.service;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service
public class HomeService
{

    public ModelAndView home()
    {
        final ModelAndView view = new ModelAndView("home");


        return view;
    }

}
