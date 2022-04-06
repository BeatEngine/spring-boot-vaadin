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

    public void setEntwurf(boolean entwurf)
    {
        this.entwurf = entwurf;
    }

    private boolean entwurf;

    private Timestamp von;
    private Timestamp bis;

    public Timestamp getVon()
    {
        return von;
    }

    public void setVon(Timestamp von)
    {
        this.von = von;
    }

    public Timestamp getBis()
    {
        return bis;
    }

    public void setBis(Timestamp bis)
    {
        this.bis = bis;
    }

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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getKlasse()
    {
        return klasse;
    }

    public void setKlasse(String klasse)
    {
        this.klasse = klasse;
    }

    public String getReason()
    {
        return reason;
    }

    public void setReason(String reason)
    {
        this.reason = reason;
    }

    public String getVermerk()
    {
        return vermerk;
    }

    public void setVermerk(String vermerk)
    {
        this.vermerk = vermerk;
    }

    public String getAbteilungsleiter()
    {
        return abteilungsleiter;
    }

    public void setAbteilungsleiter(String abteilungsleiter)
    {
        this.abteilungsleiter = abteilungsleiter;
    }

    public String getSchulleitung()
    {
        return schulleitung;
    }

    public void setSchulleitung(String schulleitung)
    {
        this.schulleitung = schulleitung;
    }
}
