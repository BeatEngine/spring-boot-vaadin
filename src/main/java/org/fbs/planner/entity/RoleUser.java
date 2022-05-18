package org.fbs.planner.entity;

import org.fbs.planner.entity.idclass.RoleUserId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
// Hilfsklasse für zusammengesetzten pKey
@IdClass(RoleUserId.class)
public class RoleUser
{
    @Id
    private Long userId;
    @Id
    private Long roleId;

    public RoleUser()
    {

    }


    public RoleUser(Long userId, Long roleId)
    {
        this.userId = userId;
        this.roleId = roleId;
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
}
