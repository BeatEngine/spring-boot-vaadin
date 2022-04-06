/**
 * Title: RequestFilter.
 * Description: URL-Zugriffsvalidierung
 * Copyright EDAG Engineering GmbH 2022
 *
 * @author dg79696
 */
package org.fbs.planner.session;

import org.fbs.planner.repository.PermissionRepository;
import org.fbs.planner.repository.PermissionRoleRepository;
import org.fbs.planner.repository.RoleUserRepository;
import org.fbs.planner.security.UserPermissionsPathMatcher;
import org.fbs.planner.service.MainService;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Configuration
public class SessionCookieRequestFilter implements Filter
{

	private final MainService               service;
	private final RoleUserRepository userRoles;
	private final PermissionRepository permissions;
	private final PermissionRoleRepository permissionRoles;

	/**
	 * Konstruktor der Klasse RequestFilter.
	 */
	public SessionCookieRequestFilter(final MainService service, final RoleUserRepository userRoles,
									  final PermissionRepository permissions,
									  final PermissionRoleRepository permissionRoles)
	{
		this.service = service;
		this.userRoles = userRoles;
		this.permissions = permissions;
		this.permissionRoles = permissionRoles;
	}

	/**
	 * Setup
	 *
	 * @return Setup
	 */
	@Bean
	public FilterRegistrationBean<SessionCookieRequestFilter> loggingFilter()
	{
		final FilterRegistrationBean<SessionCookieRequestFilter> registrationBean =
			new FilterRegistrationBean<SessionCookieRequestFilter>();
		registrationBean.setFilter(new SessionCookieRequestFilter(service, userRoles, permissions, permissionRoles));
		registrationBean.addUrlPatterns("/*");
		registrationBean.addUrlPatterns();
		registrationBean.setOrder(1);
		return registrationBean;
	}

	/**
	 * Filter Anwenden
	 *
	 * @param request  HTTP-Anfrage
	 * @param response HTTP-Antwoort vorbereitet.
	 * @param chain    Filterbedingung
	 * @throws IOException
	 * @throws ServletException
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws
																																					IOException,
																																					ServletException
	{
		boolean                  valideSession      = false;
		final HttpServletRequest httpServletRequest = (HttpServletRequest) (request);
		final Cookie[]           cookies            = httpServletRequest.getCookies();
		if (cookies != null)
		{
			for (final Cookie cookie : cookies)
			{
				if ("session".equals(cookie.getName()))
				{
					final CookieSessionManager sessionManager = service.getSessions();
					final String               sessionId      = cookie.getValue();
					valideSession = sessionManager.isLoggedIn(sessionId);
					break;
				}
			}
		}

		String contextPath = httpServletRequest.getServletPath();
		if (contextPath == null || contextPath.isBlank())
		{
			contextPath = "/";
		}
		//todo LOGGING bei level info
		if (contextPath.startsWith("/favicon.ico") || /* Icon */
				contextPath.startsWith("/VAADIN") || /* Vaadin Requests */
			contextPath.startsWith("/vaadinServlet") || /* Vaadin Requests */
			"/".equals(contextPath) && "POST".equals(httpServletRequest.getMethod().toUpperCase()) ||
			"/auth".equals(contextPath) && "POST".equals(httpServletRequest.getMethod().toUpperCase()) ||
			"/logout".equals(contextPath) ||
			"/login".equals(contextPath) ||
		 	"/favicon.ico".equals(contextPath) ||
		 	contextPath.startsWith("/js/") ||
		 	contextPath.startsWith("/css/") )
		{
			//Statischer Kontent
			chain.doFilter(request, response);
		}
		else if (valideSession)
		{
			if("/".equals(contextPath))
			{
				redirectHome(httpServletRequest, (HttpServletResponse) response, "Seite nicht gefunden!");
			}
			else
			{
				//todo implementieren!!!
				final long currentUserId = 0;
				//Prüfung der Rechte des Users --> Auf welche URLs darf er zugreifen bzw. nicht zugreifen?
				final UserPermissionsPathMatcher pathPermissions = new UserPermissionsPathMatcher(currentUserId, userRoles,
						permissions,
						permissionRoles);
				if (pathPermissions.userHasPermissionFor(contextPath))
				{
					chain.doFilter(request, response);
				}
				else
				{
					//Umleitung mit Begründung
					redirectHome(httpServletRequest, (HttpServletResponse) response, "Nicht genügend Rechte!");
				}
			}
		}
		else
		{
			//Auf Anmeldeseite umleiten
			redirectWrongSession(httpServletRequest, (HttpServletResponse) response);
		}
	}

	/**
	 * Auf Login-Seite umleiten.
	 *
	 * @param httpServletRequest HTTP-Anfrage.
	 * @param httpResponse       HTTP-Antwort.
	 */
	private void redirectWrongSession(final HttpServletRequest httpServletRequest,
												 final HttpServletResponse httpResponse)
	{
		httpResponse.reset();
		final String requestURI = httpServletRequest.getRequestURI();
		final String redirect   = URLEncoder.encode(requestURI, StandardCharsets.UTF_8);
		final String msg        = "Error: Sitzung ist abgelaufen!";
		try
		{
			httpResponse
				.sendRedirect("/login?reason=" + URLEncoder.encode(msg, StandardCharsets.UTF_8));
			//httpResponse.setStatus(HttpServletResponse.SC_OK);
			httpResponse.getOutputStream().print(msg);
			httpResponse.getOutputStream().flush();
			//Wichtig, damit nicht noch irgendetwas drauf geschrieben wird, wie beim Test vorgefallen.
			httpResponse.getOutputStream().close();
		}
		catch (final IOException e)
		{
			System.out.println("Error redirect: " + e.getMessage());
		}
	}

	/**
	 * Auf Start-Seite umleiten.
	 *
	 * @param httpServletRequest HTTP-Anfrage.
	 * @param httpResponse       HTTP-Antwort.
	 */
	private void redirectHome(final HttpServletRequest httpServletRequest, final HttpServletResponse httpResponse,
									  final String info)
	{
		httpResponse.reset();
		final String requestURI = httpServletRequest.getRequestURI();
		final String redirect   = URLEncoder.encode(requestURI, StandardCharsets.UTF_8);
		try
		{
			httpResponse.sendRedirect("/home?reason=" + URLEncoder.encode(info, StandardCharsets.UTF_8));
			//httpResponse.setStatus(HttpServletResponse.SC_OK);
			httpResponse.getOutputStream().print(info);
			httpResponse.getOutputStream().flush();
			//Wichtig, damit nicht noch irgendetwas drauf geschrieben wird, wie beim Test vorgefallen.
			httpResponse.getOutputStream().close();
		}
		catch (final IOException e)
		{
			System.out.println("Error redirect: " + e.getMessage());
		}
	}

}
