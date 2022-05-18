package org.fbs.planner.repository;

import org.fbs.planner.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long>
{
    @Query(value = "select * from cell where antrag_id = ?1", nativeQuery = true)
    List<Cell> findAllByAntragId(long id);
}
