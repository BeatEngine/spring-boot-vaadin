package org.fbs.planner.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Antrag
{
    @Id
    @GeneratedValue
    private Long id;

    private Long user_id;

    public Long getUser_id()
    {
        return user_id;
    }

				/**
				* Setzen von User_id
				* 
				* @param user_id User_id
				*/
    public void setUser_id(Long user_id)
    {
        this.user_id = user_id;
    }

    private String name;
    private String klasse;
    private String reason;
    private String vermerk;
    private String abteilungsleiter;
    private String schulleitung;

				/**
				* Setzen von Entwurf
				* 
				* @param entwurf Entwurf
				*/
    public void setEntwurf(boolean entwurf)
    {
        this.entwurf = entwurf;
    }

    private boolean entwurf;

    private Timestamp von;
    private Timestamp bis;

				/**
				* Rückgabe von Von
				* 
				* @return (Timestamp) Von
				*/
    public Timestamp getVon()
    {
        return von;
    }

				/**
				* Setzen von Von
				* 
				* @param von Von
				*/
    public void setVon(Timestamp von)
    {
        this.von = von;
    }

				/**
				* Rückgabe von Bis
				* 
				* @return (Timestamp) Bis
				*/
    public Timestamp getBis()
    {
        return bis;
    }

				/**
				* Setzen von Bis
				* 
				* @param bis Bis
				*/
    public void setBis(Timestamp bis)
    {
        this.bis = bis;
    }

				/**
				* Description
				* 
				*/
    public Antrag()
    {
        entwurf = true;
    }


    public Antrag(Long id, long userId, String name, String klasse, String reason, String vermerk, String abteilungsleiter, String schulleitung, Timestamp von, Timestamp bis)
    {
        entwurf = true;
        this.id = id;
        this.user_id = userId;
        this.name = name;
        this.klasse = klasse;
        this.reason = reason;
        this.vermerk = vermerk;
        this.abteilungsleiter = abteilungsleiter;
        this.schulleitung = schulleitung;
        this.von = von;
        this.bis = bis;
    }

				/**
				* Rückgabe von Id
				* 
				* @return (Long) Id
				*/
    public Long getId()
    {
        return id;
    }

				/**
				* Setzen von Id
				* 
				* @param id Id
				*/
    public void setId(Long id)
    {
        this.id = id;
    }

				/**
				* Rückgabe von Name
				* 
				* @return (String) Name
				*/
    public String getName()
    {
        return name;
    }

				/**
				* Setzen von Name
				* 
				* @param name Name
				*/
    public void setName(String name)
    {
        this.name = name;
    }

				/**
				* Rückgabe von Klasse
				* 
				* @return (String) Klasse
				*/
    public String getKlasse()
    {
        return klasse;
    }

				/**
				* Setzen von Klasse
				* 
				* @param klasse Klasse
				*/
    public void setKlasse(String klasse)
    {
        this.klasse = klasse;
    }

				/**
				* Rückgabe von Reason
				* 
				* @return (String) Reason
				*/
    public String getReason()
    {
        return reason;
    }

				/**
				* Setzen von Reason
				* 
				* @param reason Reason
				*/
    public void setReason(String reason)
    {
        this.reason = reason;
    }

				/**
				* Rückgabe von Vermerk
				* 
				* @return (String) Vermerk
				*/
    public String getVermerk()
    {
        return vermerk;
    }

				/**
				* Setzen von Vermerk
				* 
				* @param vermerk Vermerk
				*/
    public void setVermerk(String vermerk)
    {
        this.vermerk = vermerk;
    }

				/**
				* Rückgabe von Abteilungsleiter
				* 
				* @return (String) Abteilungsleiter
				*/
    public String getAbteilungsleiter()
    {
        return abteilungsleiter;
    }

				/**
				* Setzen von Abteilungsleiter
				* 
				* @param abteilungsleiter Abteilungsleiter
				*/
    public void setAbteilungsleiter(String abteilungsleiter)
    {
        this.abteilungsleiter = abteilungsleiter;
    }

				/**
				* Rückgabe von Schulleitung
				* 
				* @return (String) Schulleitung
				*/
    public String getSchulleitung()
    {
        return schulleitung;
    }

				/**
				* Setzen von Schulleitung
				* 
				* @param schulleitung Schulleitung
				*/
    public void setSchulleitung(String schulleitung)
    {
        this.schulleitung = schulleitung;
    }
}
