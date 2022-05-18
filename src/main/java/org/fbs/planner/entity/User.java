package org.fbs.planner.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User
{
    @Id
    private Long userId;

    private String firstname;
    private String lastname;

    private String email;

    public User()
    {

    }


    public User(Long id, String firstname, String lastname, String email)
    {
        this.userId = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
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

				/**
				* Setzen von UserId
				* 
				* @param userId UserId
				*/
    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

				/**
				* Rückgabe von Firstname
				* 
				* @return (String) Firstname
				*/
    public String getFirstname()
    {
        return firstname;
    }

				/**
				* Setzen von Firstname
				* 
				* @param firstname Firstname
				*/
    public void setFirstname(String firstname)
    {
        this.firstname = firstname;
    }

				/**
				* Rückgabe von Lastname
				* 
				* @return (String) Lastname
				*/
    public String getLastname()
    {
        return lastname;
    }

				/**
				* Setzen von Lastname
				* 
				* @param lastname Lastname
				*/
    public void setLastname(String lastname)
    {
        this.lastname = lastname;
    }

				/**
				* Rückgabe von Email
				* 
				* @return (String) Email
				*/
    public String getEmail()
    {
        return email;
    }

				/**
				* Setzen von Email
				* 
				* @param email Email
				*/
    public void setEmail(String email)
    {
        this.email = email;
    }
}
