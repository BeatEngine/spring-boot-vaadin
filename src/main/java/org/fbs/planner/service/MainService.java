package org.fbs.planner.service;

import org.fbs.planner.session.CookieSessionManager;
import org.springframework.stereotype.Service;

@Service
public class MainService
{

    CookieSessionManager sessions;

    public CookieSessionManager getSessions() {
        return sessions;
    }
}
