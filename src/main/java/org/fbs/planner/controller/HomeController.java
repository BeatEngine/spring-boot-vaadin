package org.fbs.planner.controller;

import org.fbs.planner.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController
{

    @Autowired
    HomeService service;

    @GetMapping("/")
    public ModelAndView index()
    {
        return service.home();
    }


}
