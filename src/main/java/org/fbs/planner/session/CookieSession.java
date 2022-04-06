/**
 * Title: CookieSession.
 * Description: Eine Nutzersession mit den Daten personalnummer und vollständiger Name
 * Copyright EDAG Engineering GmbH 2020
 *
 * @author dg79696
 */
package org.fbs.planner.session;

import java.time.LocalTime;

/**
 * Die Klasse CookieSession beschreibt die Informationen, auf die nur der Benutzer zugreifen kann.
 */

public class CookieSession
{

	public static byte[] encryptBlockwise(byte[] key, byte[] data)
	{
		byte[] newd = new byte[data.length];
		int    k    = 0;
		byte[] tmpk = new byte[key.length];
		tmpk = key.clone();
		for (int i = 0; i < data.length; i++)
		{
			if (k >= key.length)
			{
				k = 0;
			}
			newd[i] = (byte) (data[i] ^ tmpk[k]);
			tmpk[k] = data[i];
			k++;
		}
		return newd;
	}

	public static byte[] decryptBlockwise(byte[] key, byte[] data)
	{
		byte[] newd = new byte[data.length];
		int    k    = 0;
		byte[] tmpk = new byte[key.length];
		tmpk = key.clone();
		for (int i = 0; i < data.length; i++)
		{
			if (k >= key.length)
			{
				k = 0;
			}
			newd[i] = (byte) (data[i] ^ tmpk[k]);
			tmpk[k] = newd[i];
			k++;
		}
		return newd;
	}

	public static String byteArrayToString(byte[] array)
	{
		String ret = "";
		for (int i = 0; i < array.length; i++)
		{
			ret += (char) array[i];
		}
		return ret;
	}

	/* Wird im Manager benötigt */
	private String  id;
	private boolean signedIn = false;

	private String username;

	private Long userId;

	public void setUserId(Long userId)
	{
		this.userId = userId;
	}

	private String displayName = "";

	private LocalTime createdAt;
	private LocalTime updatedAt;

	private String encPwd;

	public String getPassword()
	{
		return byteArrayToString(decryptBlockwise(id.getBytes(), encPwd.getBytes()));
	}

	public void setEncPwd(final String pwd)
	{
		this.encPwd = byteArrayToString(encryptBlockwise(id.getBytes(), pwd.getBytes()));
	}

	public CookieSession(String id)
	{
		this.id = id;
		username = "";
		createdAt = LocalTime.now();
		updatedAt = LocalTime.now();
	}

	public String getId()
	{
		return id;
	}

	public void setId(final String id)
	{
		this.id = id;
	}

	public boolean isSignedIn()
	{
		return signedIn;
	}

	public void setSignedIn(final boolean signedIn)
	{
		this.signedIn = signedIn;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(final String username)
	{
		this.username = username;
	}

	public String getDisplayName()
	{
		return displayName;
	}

	public void setDisplayName(final String displayName)
	{
		this.displayName = displayName;
	}

	public LocalTime getCreatedAt()
	{
		return createdAt;
	}

	public void setCreatedAt(final LocalTime createdAt)
	{
		this.createdAt = createdAt;
	}

	public LocalTime getUpdatedAt()
	{
		return updatedAt;
	}

	public void setUpdatedAt(final LocalTime updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	public void update()
	{
		updatedAt = LocalTime.now();
	}

	public Long getUserId()
	{
		return userId;
	}
}
