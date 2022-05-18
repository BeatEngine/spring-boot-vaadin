package org.fbs.planner.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Permission
{
    @Id
    private Long permissionId;
    private String name;
    private String description;

    public Permission(Long id, String name, String description)
    {
        this.permissionId = id;
        this.name = name;
        this.description = description;
    }

				/**
				* Rückgabe von PermissionId
				* 
				* @return (Long) PermissionId
				*/
    public Long getPermissionId()
    {
        return permissionId;
    }

				/**
				* Setzen von PermissionId
				* 
				* @param permissionId PermissionId
				*/
    public void setPermissionId(Long permissionId)
    {
        this.permissionId = permissionId;
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
				* Rückgabe von Description
				* 
				* @return (String) Description
				*/
    public String getDescription()
    {
        return description;
    }

				/**
				* Setzen von Description
				* 
				* @param description Description
				*/
    public void setDescription(String description)
    {
        this.description = description;
    }
}
