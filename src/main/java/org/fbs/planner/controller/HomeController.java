package org.fbs.planner.controller;

import com.vaadin.flow.router.Route;
import org.fbs.planner.ldap.LDAPauthenticator;
import org.fbs.planner.ldap.LDAPauthentificationData;
import org.fbs.planner.service.HomeService;
import org.fbs.planner.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
public class HomeController
{

    @Autowired
    HomeService service;

    @Autowired
    private Environment properties;


    /*@GetMapping("/")
    public ModelAndView index()
    {
        return service.home();
    }*/

    @PostMapping("/auth")
    public ModelAndView index(@RequestBody final String input)
    {
        Map<String, String> bodyValues = UtilService.processRequestValuesByStringSTD(input, true);
        LDAPauthentificationData result;
        try
        {


            result = LDAPauthenticator.authenticateProjectUser(bodyValues.get("username"), bodyValues.get("password"), properties.getProperty("ldap-server-address"),
                    properties.getProperty("ldap-server-password"), properties);

            return new ModelAndView("redirect:/home");
        }
        catch (Exception e)
        {
            String decode = URLDecoder.decode(e.getMessage(), StandardCharsets.UTF_8);
            return new ModelAndView("redirect:/login?reason=" + decode);
        }
    }


}
