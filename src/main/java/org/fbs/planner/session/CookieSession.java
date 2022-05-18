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

	/**
	* Description
	* 
	* @param key
	* @param data
	* @return (byte[]) 
	*/
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

	/**
	* Description
	* 
	* @param array
	* @return (String) 
	*/
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

	/**
	* Setzen von EncPwd
	* 
	* @param pwd EncPwd
	*/
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

	/**
	* Rückgabe von Id
	* 
	* @return (String) Id
	*/
	public String getId()
	{
		return id;
	}

	/**
	* Setzen von Id
	* 
	* @param id Id
	*/
	public void setId(final String id)
	{
		this.id = id;
	}

	/**
	* Abfragen von SignedIn
	* 
	* @return (boolean) 
	*/
	public boolean isSignedIn()
	{
		return signedIn;
	}

	/**
	* Setzen von SignedIn
	* 
	* @param signedIn SignedIn
	*/
	public void setSignedIn(final boolean signedIn)
	{
		this.signedIn = signedIn;
	}

	/**
	* Rückgabe von Username
	* 
	* @return (String) Username
	*/
	public String getUsername()
	{
		return username;
	}

	/**
	* Setzen von Username
	* 
	* @param username Username
	*/
	public void setUsername(final String username)
	{
		this.username = username;
	}

	/**
	* Rückgabe von DisplayName
	* 
	* @return (String) DisplayName
	*/
	public String getDisplayName()
	{
		return displayName;
	}

	/**
	* Setzen von DisplayName
	* 
	* @param displayName DisplayName
	*/
	public void setDisplayName(final String displayName)
	{
		this.displayName = displayName;
	}

	/**
	* Rückgabe von CreatedAt
	* 
	* @return (LocalTime) CreatedAt
	*/
	public LocalTime getCreatedAt()
	{
		return createdAt;
	}

	/**
	* Setzen von CreatedAt
	* 
	* @param createdAt CreatedAt
	*/
	public void setCreatedAt(final LocalTime createdAt)
	{
		this.createdAt = createdAt;
	}

	/**
	* Rückgabe von UpdatedAt
	* 
	* @return (LocalTime) UpdatedAt
	*/
	public LocalTime getUpdatedAt()
	{
		return updatedAt;
	}

	/**
	* Setzen von UpdatedAt
	* 
	* @param updatedAt UpdatedAt
	*/
	public void setUpdatedAt(final LocalTime updatedAt)
	{
		this.updatedAt = updatedAt;
	}

	/**
	* Description
	* 
	*/
	public void update()
	{
		updatedAt = LocalTime.now();
	}

	/**
	* Rückgabe von UserId
	* 
	* @return (Long) UserId
	*/
	public Long getUserId()
	{
		return userId;
	}
}
