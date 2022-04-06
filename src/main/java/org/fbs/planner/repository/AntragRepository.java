package org.fbs.planner.repository;

import org.fbs.planner.entity.Antrag;
import org.fbs.planner.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AntragRepository extends JpaRepository<Antrag, Long>
{

}
