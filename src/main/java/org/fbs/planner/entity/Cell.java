package org.fbs.planner.entity;

import org.hibernate.annotations.ForeignKey;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "x", "y", "antrag_id" }) })
public class Cell
{
    @Id
    @GeneratedValue
    private Long id;
    private long antrag_id;
    private String text;
    private int x;
    private int y;

				/**
				* Description
				* 
				*/
    public Cell()
    {

    }


    public Cell(Long id, long antrag_id, String text, int x, int y)
    {
        this.id = id;
        this.antrag_id = antrag_id;
        this.text = text;
        this.x = x;
        this.y = y;
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
				* Rückgabe von Antrag_id
				* 
				* @return (long) Antrag_id
				*/
    public long getAntrag_id()
    {
        return antrag_id;
    }

				/**
				* Setzen von Antrag_id
				* 
				* @param antrag_id Antrag_id
				*/
    public void setAntrag_id(long antrag_id)
    {
        this.antrag_id = antrag_id;
    }

				/**
				* Rückgabe von Text
				* 
				* @return (String) Text
				*/
    public String getText()
    {
        return text;
    }

				/**
				* Setzen von Text
				* 
				* @param text Text
				*/
    public void setText(String text)
    {
        this.text = text;
    }

				/**
				* Rückgabe von X
				* 
				* @return (int) X
				*/
    public int getX()
    {
        return x;
    }

				/**
				* Setzen von X
				* 
				* @param x X
				*/
    public void setX(int x)
    {
        this.x = x;
    }

				/**
				* Rückgabe von Y
				* 
				* @return (int) Y
				*/
    public int getY()
    {
        return y;
    }

				/**
				* Setzen von Y
				* 
				* @param y Y
				*/
    public void setY(int y)
    {
        this.y = y;
    }
}
