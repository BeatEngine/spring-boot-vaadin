/**
 * Title: LDAPauthentificationData.
 * Description:
 *
 * @author David
 */
package org.fbs.planner.ldap;

public class LDAPauthentificationData
{

	/**
	 * Konstruktor der Klasse LDAPauthentificationData.
	 */

	private boolean successfullyAuthenticated = false;
	private String  reasonForFail;
	private String  fullUserName;
	private String  businesscategory;
	private String  title;

	/**
	 * @return Kategorie
	 */
	public String getBusinesscategory()
	{
		return businesscategory;
	}

	/**
	 * @param businesscategory Kategorie
	 */
	public void setBusinesscategory(final String businesscategory)
	{
		this.businesscategory = businesscategory;
	}

	/**
	 * @param successfullyAuthenticated Nur wahr, wenn alle Bedingungen für eintritt zum OTM erfüllt sind.
	 * @param reasonForFail             Felermeldung.
	 * @param fullUserName              Name des Benutzers
	 */
	public LDAPauthentificationData(final boolean successfullyAuthenticated, final String reasonForFail,
											  final String fullUserName)
	{
		this.successfullyAuthenticated = successfullyAuthenticated;
		this.reasonForFail = reasonForFail;
		this.fullUserName = fullUserName;
	}

	/**
	 * @return Nur wahr, wenn alle Bedingungen für eintritt zum OTM erfüllt sind.
	 */
	public boolean isSuccessfullyAuthenticated()
	{
		return successfullyAuthenticated;
	}

	/**
	 * @return Fehlermeldung.
	 */
	public String getReasonForFail()
	{
		return reasonForFail;
	}

	/**
	 * @return Benutzername, wenn gesetzt.
	 */
	public String getFullUserName()
	{
		return fullUserName;
	}

	/**
	 * @return Titel des Benutzers, wie Job-Title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * Setzen der Authentifikation.
	 *
	 * @param successfullyAuthenticated Nur wahr, wenn alle Bedingungen für eintritt zum OTM erfüllt sind.
	 */
	public void setSuccessfullyAuthenticated(final boolean successfullyAuthenticated)
	{
		this.successfullyAuthenticated = successfullyAuthenticated;
	}

	/**
	 * @param reasonForFail Fehlermeldung.
	 */
	public void setReasonForFail(final String reasonForFail)
	{
		this.reasonForFail = reasonForFail;
	}

	/**
	 * @param fullUserName Benutzernamen "Max Müller"
	 */
	public void setFullUserName(final String fullUserName)
	{
		this.fullUserName = fullUserName;
	}

	/**
	 * @param title z.B. "Fachinformatiker für Anwendungsentwicklung"
	 */
	public void setTitle(final String title)
	{
		this.title = title;
	}
}
