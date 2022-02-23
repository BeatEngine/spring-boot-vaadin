package org.fbs.planner.repository;

import org.fbs.planner.entity.Permission;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface  PermissionRoleRepository <T, ID extends Serializable> extends Repository<T, ID>
{

    public List<Permission> findPermissionIdByRoleId();

}
