<<<<<<< HEAD
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

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
=======
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

    public Long getRoleId()
    {
        return roleId;
    }

    public void setRoleId(Long roleId)
    {
        this.roleId = roleId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }
}
>>>>>>> eba9085661a0ced3e225ffd09e78b4989733b16a
