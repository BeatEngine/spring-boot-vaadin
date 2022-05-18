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

}
