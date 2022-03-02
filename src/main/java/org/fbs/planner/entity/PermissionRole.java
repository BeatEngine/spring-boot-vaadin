package org.fbs.planner.entity;

import org.fbs.planner.entity.idclass.PermissionRoleId;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
// Hilfsklasse für zusammengesetzten pKey
@IdClass(PermissionRoleId.class)
public class PermissionRole
{
    @Id
    private Long permissionId;
    @Id
    private Long roleId;

    public PermissionRole()
    {

    }

    public PermissionRole(Long permissionId, Long roleId)
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

}
