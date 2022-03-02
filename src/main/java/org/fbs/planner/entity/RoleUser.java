<<<<<<< HEAD
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
}
=======
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
}
>>>>>>> eba9085661a0ced3e225ffd09e78b4989733b16a
