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

    public Long getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(Long permissionId)
    {
        this.permissionId = permissionId;
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
        hash = 31 * hash + (permissionId == null ? 0 : permissionId.hashCode());
        hash = 71 * hash + (roleId == null ? 0 : roleId.hashCode());
        return hash;
    }

    @Override
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
