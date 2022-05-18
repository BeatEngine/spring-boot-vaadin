/**
 * Title: LDAPauthenticator.
 * Description:
 *
 * @author David
 */
package org.fbs.planner.ldap;

import org.springframework.core.env.Environment;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class LDAPauthenticator
{

	public static String queryUserData(int userPNUM, String ldapServer, String ldapPassword, Environment properties)
	{
		String                    result   = null;
		final String              ldapBase = "cn=public,dc=fbs,dc=org";
		Hashtable<String, Object> env      = new Hashtable<String, Object>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		if (ldapBase != null)
		{
			env.put(Context.SECURITY_PRINCIPAL, ldapBase);
		}
		if (ldapPassword != null)
		{
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		}
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapServer);
		//ensures that objectSID attribute values
		//will be returned as a byte[] instead of a String
		env.put("java.naming.ldap.attributes.binary", "objectSID");
		try
		{
			/* Die erste Verbindung zum LDAP-Server prüft das LDAP passwort */
			LdapContext                     ctx         = new InitialLdapContext(env, null);
			NamingEnumeration<SearchResult> useringroup = null;
			try
			{
				useringroup = ctx.search("ou=people,ou=de,dc=fbs,dc=org", "(uidnumber=" + userPNUM + ")",
												 getSimpleSearchControls());
			}
			catch (Exception e)
			{
				return null;
			}

			if (useringroup.hasMore())
			{
				SearchResult tmp = useringroup.next();

				Attributes attributes = tmp.getAttributes();

				result = "{" +
							"\"sn\":\"" +
							attributes.get("sn").get().toString() +
							"\",\"givenname\":\"" +
							attributes.get("givenname").get().toString() +
							"\",\"uidnumber\":\"" +
							attributes.get("uidnumber").get().toString() +
							"\",\"businesscategory\":\"" +
							attributes.get("businesscategory").get().toString() +
							"\",\"memberof\":\"" +
							"cn=" +
							attributes.get("oeid").get().toString() +
							tmp.getNameInNamespace().substring(tmp.getName().length()) +
							"\",\"mail\":\"" +
							attributes.get("mail").get().toString() +
							"\"}";
			}

			int dbg = 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	* Description
	* 
	* @param user
	* @param ldapServer
	* @param ldapPassword
	* @param properties
	* @return (String) 
	*/
	public static String queryUserData(String user, String ldapServer, String ldapPassword, Environment properties)
	{
		String                    result   = null;
		final String              ldapBase = "cn=public,dc=fbs,dc=org";
		Hashtable<String, Object> env      = new Hashtable<String, Object>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		if (ldapBase != null)
		{
			env.put(Context.SECURITY_PRINCIPAL, ldapBase);
		}
		if (ldapPassword != null)
		{
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		}
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapServer);
		//ensures that objectSID attribute values
		//will be returned as a byte[] instead of a String
		env.put("java.naming.ldap.attributes.binary", "objectSID");
		try
		{
			/* Die erste Verbindung zum LDAP-Server prüft das LDAP passwort */
			LdapContext                     ctx         = new InitialLdapContext(env, null);
			NamingEnumeration<SearchResult> useringroup = null;
			try
			{
				useringroup = ctx.search("ou=people,ou=de,dc=fbs,dc=org", "(uid=" + user + ")",
												 getSimpleSearchControls());
			}
			catch (Exception e)
			{
				return null;
			}

			if (useringroup.hasMore())
			{
				SearchResult tmp = useringroup.next();

				Attributes attributes = tmp.getAttributes();
				result = "{" +
								 "\"sn\":\"" +
								 attributes.get("sn").get().toString() +
								 "\",\"givenname\":\"" +
								 attributes.get("givenname").get().toString() +
								 "\",\"uidnumber\":\"" +
								 attributes.get("uidnumber").get().toString() +
								 "\",\"uid\":\"" +
								 attributes.get("uid").get().toString() +
								 "\"";
				try
				{
					result += ",\"businesscategory\":\"" +
							 attributes.get("businesscategory").get().toString() +
							 "\",\"memberof\":\"" +
							 "cn=" +
							 attributes.get("oeid").get().toString() +
							 tmp.getNameInNamespace().substring(tmp.getName().length()) +
							 "\",\"mail\":\"" +
							 attributes.get("mail").get().toString() +
							 "\"";
				}catch (Exception ex)
				{

				}

				result += "}";
			}

			int dbg = 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
		return result;
	}

	/**
	* Description
	* 
	* @param ldapServer
	* @param ldapPassword
	* @param properties
	* @return (List<String>) 
	*/
	public static List<String> queryUsers(String ldapServer, String ldapPassword, Environment properties)
	{
		List<String>              result   = new ArrayList<String>();
		final String              ldapBase = "cn=public,dc=fbs,dc=org";
		Hashtable<String, Object> env      = new Hashtable<String, Object>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		if (ldapBase != null)
		{
			env.put(Context.SECURITY_PRINCIPAL, ldapBase);
		}
		if (ldapPassword != null)
		{
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		}
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapServer);
		//ensures that objectSID attribute values
		//will be returned as a byte[] instead of a String
		env.put("java.naming.ldap.attributes.binary", "objectSID");
		try
		{
			/* Die erste Verbindung zum LDAP-Server prüft das LDAP passwort */
			LdapContext                     ctx         = new InitialLdapContext(env, null);
			NamingEnumeration<SearchResult> useringroup = null;
			try
			{
				useringroup = ctx.search("ou=people,ou=de,dc=fbs,dc=org", "(uidnumber>=0)",
												 getSimpleSearchControls());
			}
			catch (Exception e)
			{
				return result;
			}



			while (useringroup.hasMore())
			{
				try
				{
					SearchResult tmp = useringroup.next();

					Attributes attributes = tmp.getAttributes();

					String res = "{" +
									 "\"sn\":\"" +
									 attributes.get("sn").get().toString() +
									 "\",\"givenname\":\"" +
									 attributes.get("givenname").get().toString() +
									 "\",\"uidnumber\":\"" +
									 attributes.get("uidnumber").get().toString() +
									 "\",\"uid\":\"" +
											 attributes.get("uid").get().toString() +
											 "\"";
					try
					{
						res += ",\"businesscategory\":\"" +
								 attributes.get("businesscategory").get().toString() +
								 "\",\"memberof\":\"" +
								 "cn=" +
								 attributes.get("oeid").get().toString() +
								 tmp.getNameInNamespace().substring(tmp.getName().length()) +
								 "\",\"mail\":\"" +
								 attributes.get("mail").get().toString() +
								 "\"";
					}catch (Exception ex)
					{

					}

					 res += "}";
					result.add(res);
				}
				catch (Exception e)
				{
					//System.out.println("LDAP query users error! " + e.getMessage());
				}
			}

			int dbg = 1;
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return result;
		}
		return result;
	}

	/**
	 * Authentification mode is 'simple'.
	 *
	 * @param ldapAdServer address to server - use LDAPS address if possible!!!.
	 * @param ldapPassword passwort to access ldap server.
	 * @param username     EDAG system user.
	 * @param password     EDAG system password.
	 *                     Changes of the LDAP tree requires changes in this method.
	 * @param properties
	 * @return LDAPauthentificationData Daten zum User
	 */
	public static LDAPauthentificationData authenticateProjectUser(final String username, final String password,
																						final String ldapAdServer, final String ldapPassword,
																						final Environment properties)
	{
		final LDAPauthentificationData returnData = new LDAPauthentificationData(false, "", "");
		/* Von rechts nach links --> den Baum absteigen*/
		final String ldapBase = "cn=public"; /* ,dc=fbs,dc=org */

		Hashtable<String, Object> env = new Hashtable<String, Object>();
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		if (ldapBase != null)
		{
			env.put(Context.SECURITY_PRINCIPAL, ldapBase);
		}
		if (ldapPassword != null)
		{
			env.put(Context.SECURITY_CREDENTIALS, ldapPassword);
		}

		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, ldapAdServer);

		//ensures that objectSID attribute values
		//will be returned as a byte[] instead of a String
		env.put("java.naming.ldap.attributes.binary", "objectSID");

		try
		{
			/* Die erste Verbindung zum LDAP-Server prüft das LDAP passwort */
			final LdapContext               ctx         = new InitialLdapContext(env, null);
			NamingEnumeration<SearchResult> useringroup = null;

			NamingEnumeration<SearchResult> useringroup2 = null;

			returnData.setSuccessfullyAuthenticated(true);
			return returnData;
			/*try
			{
				useringroup = ctx
					.search("ou=people", "(uid=" + username + ")", getSimpleSearchControls());

				// Die zweite Verbindung zum LDAP-Server prüft ob der User in der Gruppe ist und ob diese Existiert
				returnData.setSuccessfullyAuthenticated(false);
				final SearchResult result     = useringroup.next();
				final Attributes   attributes = result.getAttributes();
				final String       ldapGroup  = properties.getProperty("ldap-project");
				useringroup2 = ctx.search("cn=" + ldapGroup + ",ou=jedwin,ou=de,dc=edag,dc=com", "objectClass=*",
												  getSimpleSearchControls());

				if (useringroup2.hasMore())
				{
					final SearchResult tmpResult     = useringroup2.next();
					final String       members       = tmpResult.getAttributes().get("uniquemember").toString();
					final String       correctFilter = "uidNumber=" + attributes.get("uidNumber").get();
					if (!members.contains(correctFilter))
					{
						returnData.setReasonForFail(
							attributes.get("displayName").get().toString() + " isn't a member of " + ldapGroup);
						ctx.close();
						return returnData;
					}
				}
				else
				{
					returnData.setReasonForFail("\"" + ldapGroup + "\" doesn't exist");
					ctx.close();
					return returnData;
				}

				//Setzen der User-Infos
				returnData.setSuccessfullyAuthenticated(true);
				returnData.setFullUserName(
					attributes.get("givenname").get().toString() + " " + attributes.get("sn").get().toString());
				returnData.setBusinesscategory(attributes.get("businesscategory").get().toString());
				returnData.setTitle(attributes.get("title").get().toString());
				ctx.close();
				return returnData;
			}
			catch (final Exception e)
			{
				returnData.setReasonForFail("user not in LDAP group \"people\" or wrong password");
				ctx.close();
				return returnData;
			}*/
		}
		catch (final NamingException e)
		{
			e.printStackTrace();
			returnData.setReasonForFail(e.getMessage());
			return returnData;
		}
	}

	/**
	 * Der einfachste Standart.
	 */
	private static SearchControls getSimpleSearchControls()
	{
		final SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
		searchControls.setTimeLimit(3000);
		return searchControls;
	}




}
