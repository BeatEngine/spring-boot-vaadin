package org.fbs.planner.controller;

import com.vaadin.flow.router.Route;
import org.fbs.planner.ldap.LDAPauthenticator;
import org.fbs.planner.ldap.LDAPauthentificationData;
import org.fbs.planner.service.HomeService;
import org.fbs.planner.service.MainService;
import org.fbs.planner.utils.UtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestController
public class HomeController
{

    @Autowired
    MainService service;

    @Autowired
    HomeService homeService;

    @Autowired
    private Environment properties;


    /*@GetMapping("/")
    public ModelAndView index()
    {
        return service.home();
    }*/

    @PostMapping("/table/save")
    public ModelAndView save(@RequestBody final String input, @CookieValue("session") String session)
    {
        return homeService.save(UtilService.processRequestValuesByStringSTD(input, true), session);
    }

				/**
				* Description
				* 
				* @param "/auth"
				*/
    @PostMapping("/auth")
    public ModelAndView index(@RequestBody final String input, final HttpServletRequest request, final HttpServletResponse response)
    {
        Map<String, String> bodyValues = UtilService.processRequestValuesByStringSTD(input, true);
        LDAPauthentificationData result;
        try
        {
            final String user = bodyValues.get("username");
            final String pass = bodyValues.get("password");

            //result = LDAPauthenticator.authenticateProjectUser(user, pass, properties.getProperty("ldap-server-address"),
            //        properties.getProperty("ldap-server-password"), properties);

            if(true /*result.isSuccessfullyAuthenticated()*/)
            {
                service.signIn(request, response, user, pass, user);
            }

            return new ModelAndView("redirect:/home");
        }
        catch (Exception e)
        {
            String decode = URLDecoder.decode(e.getMessage(), StandardCharsets.UTF_8);
            return new ModelAndView("redirect:/login?reason=" + decode);
        }
    }


}
