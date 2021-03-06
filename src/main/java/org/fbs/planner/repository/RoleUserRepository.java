package org.fbs.planner.repository;

import org.fbs.planner.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser, Long>
{
    public List<RoleUser> findByUserId(long userId);
}
