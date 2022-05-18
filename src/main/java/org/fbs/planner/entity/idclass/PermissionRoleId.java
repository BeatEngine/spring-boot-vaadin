package org.fbs.planner.entity.idclass;

import java.io.Serializable;

public class PermissionRoleId implements Serializable
{
    private Long permissionId;
    private Long roleId;

    public PermissionRoleId()
    {

    }


    public PermissionRoleId(Long permissionId, Long roleId)
    {
        this.permissionId = permissionId;
        this.roleId = roleId;
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
        hash = 31 * hash + (permissionId == null ? 0 : permissionId.hashCode());
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
        if(obj instanceof PermissionRoleId)
        {
            final PermissionRoleId other = (PermissionRoleId) obj;
            return permissionId == other.getPermissionId() && roleId == other.getRoleId();
        }
        return false;
    }

}
