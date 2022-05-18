package org.fbs.planner.entity.idclass;

import java.io.Serializable;

public class RoleUserId implements Serializable
{
    private Long userId;
    private Long roleId;

    public RoleUserId()
    {

    }


    public RoleUserId(Long userId, Long roleId)
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

    @Override
				/**
				* Description
				* 
				* @return (int) 
				*/
    public int hashCode()
    {
        int hash = 7;
        hash = 31 * hash + (userId == null ? 0 : userId.hashCode());
        hash = 71 * hash + (roleId == null ? 0 : roleId.hashCode());
        return hash;
    }

    @Override
				/**
				* Description
				* 
				* @param obj
				* @return (boolean) 
				*/
    public boolean equals(Object obj)
    {
        if(obj instanceof RoleUserId)
        {
           final RoleUserId other = (RoleUserId) obj;
           return userId == other.getUserId() && roleId == other.getRoleId();
        }
        return false;
    }
}
