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

    public Long getPermissionId()
    {
        return permissionId;
    }

    public void setPermissionId(Long permissionId)
    {
        this.permissionId = permissionId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }
}
