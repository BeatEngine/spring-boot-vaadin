package org.fbs.planner.repository;

import org.fbs.planner.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface  PermissionRepository extends JpaRepository<Permission, Long>
{
    public List<Permission> findByPermissionIdIn(Set<Long> permissionIds);
}
