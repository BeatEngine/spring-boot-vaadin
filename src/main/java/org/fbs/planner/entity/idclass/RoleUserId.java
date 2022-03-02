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

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 31 * hash + (userId == null ? 0 : userId.hashCode());
        hash = 71 * hash + (roleId == null ? 0 : roleId.hashCode());
        return hash;
    }

    @Override
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
