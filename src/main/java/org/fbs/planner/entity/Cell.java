package org.fbs.planner.entity;

import org.hibernate.annotations.ForeignKey;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "x", "y" }) })
public class Cell
{
    @Id
    @GeneratedValue
    private Long id;
    private long antrag_id;
    private String text;
    private int x;
    private int y;

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

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public long getAntrag_id()
    {
        return antrag_id;
    }

    public void setAntrag_id(long antrag_id)
    {
        this.antrag_id = antrag_id;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public int getY()
    {
        return y;
    }

    public void setY(int y)
    {
        this.y = y;
    }
}
