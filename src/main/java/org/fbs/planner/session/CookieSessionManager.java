/**
 * Title: CookieSessionManager.
 * Description: Verwaltet die Nutzersessions
 * Copyright EDAG Engineering GmbH 2020
 *
 * @author dg79696
 */
package org.fbs.planner.session;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Hier werden alle Nutzersessions verwaltet und hier sind alle nutzerbezogenen Methoden gesammelt.
 */

//@Component
public class CookieSessionManager
{

	//private final static CookieSessionManager INSTANCE                 = new CookieSessionManager();
	public int maxCookieLifeTimeMinutes = 300;

	public  int                 maxCookieUpdateDelayMinutes = 15;
	/**
	 * Konstruktor der Klasse CookieSessionManager.
	 */

	private List<CookieSession> sessions;

	public CookieSessionManager()
	{
		sessions = new ArrayList<CookieSession>();
	}

	//public static CookieSessionManager GetInstance()
	//{
	//return this;
	//return INSTANCE;
	//}

	/**
	 * UUDI
	 *
	 * @return UUID
	 */
	private static String generateSessionId()
	{
		return UUID.randomUUID().toString();
	}

	/**
	 * Eine Sitzung anlegen, per UUID
	 *
	 * @param id UUID muss eindeutig sein.
	 */
	private void addUniqeSession(String id)
	{
		sessions.add(new CookieSession(id));
	}

	/**
	 * Update timestamps and removes expired sessions
	 * Part of the CookieSessionManager garbage system.
	 */
	private void updateCookiesLife(String currentCookieId)
	{
		LocalTime now        = LocalTime.now();
		int       currentMin = now.getHour() * 60 + now.getMinute();
		for (int i = 0; i < sessions.size(); i++)
		{
			int diffMin = currentMin -
							  sessions.get(i).getCreatedAt().getHour() * 60 -
							  sessions.get(i).getCreatedAt().getMinute();
			int diffMinUpd = currentMin -
								  sessions.get(i).getUpdatedAt().getHour() * 60 -
								  sessions.get(i).getUpdatedAt().getMinute();

			if (sessions.get(i).getId().equals(currentCookieId)) /* For users, triggering update actions */
			{
				if (diffMinUpd > maxCookieUpdateDelayMinutes || /* No useraction for some time */
					 diffMin > maxCookieLifeTimeMinutes ||         /* Cookie reached lifetime */
					 sessions.get(i).isSignedIn() == false)         /* User logged out */
				{
					sessions.remove(i);
					i--;
				}
				else
				{
					sessions.get(i).update();
				}
			}
			else                                             /* For all other users*/
			{
				if (diffMin > maxCookieLifeTimeMinutes)      /* Cookie reached lifetime */
				{
					sessions.remove(i);
					i--;
				}
			}
		}
	}

	public int numberOfSessions()
	{
		return this.sessions.size();
	}

	/**
	 * Remove a certain session by id.
	 */
	private void removeSession(String id)
	{
		for (int i = 0; i < sessions.size(); i++)
		{
			if (sessions.get(i).getId().equals(id))
			{
				sessions.remove(i);
				break;
			}
		}
	}

	/**
	 * Removes all sessions with certain user.
	 */
	private void removeSessionsOfUser(String user)
	{
		for (int i = sessions.size() - 1; i >= 0; i--)
		{
			if (sessions.get(i).getUsername().equals(user))
			{
				sessions.remove(i);
			}
		}
	}

	/**
	 * Adds a new unique Session and reurns its id.
	 *
	 * @return sessionId
	 */
	public String newSession()
	{
		final String id = generateSessionId();
		addUniqeSession(id);
		return id;
	}

	/**
	 * Returns session with certain id
	 *
	 * @return CookieSession containing userdata
	 */
	private CookieSession getById(String id)
	{
		updateCookiesLife(id);
		for (int i = 0; i < sessions.size(); i++)
		{
			if (sessions.get(i).getId().equals(id))
			{
				return sessions.get(i);
			}
		}
		return null;
	}

	/**
	 * @param id    SessionId of the cookie.
	 * @param state true == loggedIn
	 * @return false means the session doesn't exist.
	 */
	private boolean setLoggedInById(String id, boolean state)
	{
		if(id == null || id.isBlank())
		{
			return true;
		}
		if (state == false)
		{
			String tmpUser = getUser(id);
			if (tmpUser.length() > 0)
			{
				removeSessionsOfUser(tmpUser);
			}
			else
			{
				removeSession(id);
			}
			return false;
		}
		for (int i = 0; i < sessions.size(); i++)
		{
			if (sessions.get(i).getId().equals(id))
			{
				sessions.get(i).setSignedIn(state);
				return true;
			}
		}
		return false;
	}

	/**
	 * Set user LoggedIn
	 *
	 * @return false means the session doesn't exist.
	 */
	public boolean logIn(String id)
	{
		return setLoggedInById(id, true);
	}

	/**
	 * Set user LoggedOut and remove the session
	 *
	 * @return false means the session doesn't exist.
	 */
	public void logOut(String id)
	{
		setLoggedInById(id, false);
	}

	/**
	 * Change or Set the authentication parameters of the user with id at runtime.
	 *
	 * @return false means the session doesn't exist.
	 */
	public boolean setAccount(String user, String id)
	{
		for (int i = 0; i < sessions.size(); i++)
		{
			if (id.equals(sessions.get(i).getId()))
			{
				sessions.get(i).setUsername(user);
				return true;
			}
		}
		return false;
	}

	/**
	 * @return Returns the 'full' name of the user.
	 */
	public String getDisplayName(String id)
	{
		CookieSession tmp = getById(id);
		if (tmp != null)
		{
			return tmp.getDisplayName();
		}
		return "";
	}

	/**
	 * @return false means the session doesn't exist.
	 */
	public boolean setDisplayName(String displayName, String id)
	{
		for (int i = 0; i < sessions.size(); i++)
		{
			if (id.equals(sessions.get(i).getId()))
			{
				sessions.get(i).setDisplayName(displayName);
				return true;
			}
		}
		return false;
	}

	/**
	 * @return returns the username or userId. In the case of faile "" is returned.
	 */
	public String getUser(String id)
	{
		CookieSession tmp = getById(id);
		if (tmp != null)
		{
			return tmp.getUsername();
		}
		return "";
	}

	/**
	 * Also updates the session(s) life circle!
	 */
	public boolean isLoggedIn(String id)
	{
		updateCookiesLife(id);
		CookieSession tmp = getById(id);
		if (tmp != null)
		{
			return tmp.isSignedIn();
		}
		return false;
	}

	public String getPassword(String id)
	{
		for (int i = 0; i < sessions.size(); i++)
		{
			if (id.equals(sessions.get(i).getId()))
			{
				return sessions.get(i).getPassword();
			}
		}
		return null;
	}

	public void setPassword(String password, String id)
	{
		for (int i = 0; i < sessions.size(); i++)
		{
			if (id.equals(sessions.get(i).getId()))
			{
				sessions.get(i).setEncPwd(password);
			}
		}
	}

	public long getUserId()
	{
		//todo implement
		return 0;
	}
}
