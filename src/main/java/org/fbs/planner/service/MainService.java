package org.fbs.planner.service;

import org.fbs.planner.ldap.LDAPauthenticator;
import org.fbs.planner.session.CookieSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.util.List;

@Service
public class MainService
{
    private static MainService INSTANCE = null;

    @Autowired
    private Environment properties;

    private CookieSessionManager sessions = new CookieSessionManager();

    public CookieSessionManager getSessions()
    {
        return sessions;
    }

				/**
				* Rückgabe von Instance
				* 
				* @return (MainService) Instance
				*/
    public static MainService getInstance()
    {
        return INSTANCE;
    }

    /**
     * Anmelden
     * @return Cookie-Session-Id
     */
    public String signIn(final HttpServletRequest request, final HttpServletResponse response, final String username, final String password, final String displayName)
    {
        if(INSTANCE == null)
        {
            INSTANCE = this;
        }

        sessions.maxCookieUpdateDelayMinutes = Integer
                .valueOf(properties.getProperty("cookie-session-maxCookieUpdateDelayMinutes"));
        sessions.maxCookieLifeTimeMinutes = Integer
                .valueOf(properties.getProperty("cookie-session-maxCookieLifeTimeMinutes"));

        final String usrnm = username;
        final String paswd = password;

        List<String> strings = LDAPauthenticator.queryUsers(properties.getProperty("ldap-server-address"),
                properties.getProperty("ldap-server-password"));

        String sid = sessions.newSession();
        //todo User implementieren!!!
        sessions.setAccount(username, sid, 0);
        sessions.logIn(sid);
        sessions.setDisplayName(displayName, sid);
        sessions.setPassword(password, sid);
        //Set Cookie

        final Cookie cookie = new Cookie("session", sid);
        System.out.println("Sessions on server: " + sessions.numberOfSessions());
        cookie.setDomain(request.getServerName());
        /* minutes --> seconds */
        cookie.setMaxAge(sessions.maxCookieLifeTimeMinutes * 60);
        response.addCookie(cookie);

        return sid;
    }
}
