package org.fbs.planner.repository;

import org.fbs.planner.entity.idclass.RoleUser;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  RoleUserRepository
{
    public List<RoleUser> findByUserId(long userId);
}
