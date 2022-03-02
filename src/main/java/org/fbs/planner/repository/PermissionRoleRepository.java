package org.fbs.planner.repository;

import org.fbs.planner.entity.PermissionRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRoleRepository extends JpaRepository<PermissionRole, Long>
{

    public long[] findPermissionIdByRoleId(long roleId);
}
