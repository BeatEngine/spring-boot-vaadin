package org.fbs.planner.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Role
{
    @Id
    private Long roleId;
    private String name;

    public Role()
    {

    }


    public Role(Long id, String name)
    {
        this.roleId = id;
        this.name = name;
    }

				/**
				* Rückgabe von RoleId
				* 
				* @return (Long) RoleId
				*/
    public Long getRoleId()
    {
        return roleId;
    }

				/**
				* Setzen von RoleId
				* 
				* @param roleId RoleId
				*/
    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
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
}
