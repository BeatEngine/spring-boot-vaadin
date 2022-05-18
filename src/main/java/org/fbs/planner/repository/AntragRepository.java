package org.fbs.planner.repository;

import org.fbs.planner.entity.Antrag;
import org.fbs.planner.entity.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface AntragRepository extends JpaRepository<Antrag, Long>
{
    @Query(value = "select * from antrag where user_id = ?1", nativeQuery = true)
    List<Antrag> findByUserId(long userId);

    @Query(value = "select * from antrag where user_id = ?1 and klasse = ?2 and von = ?3 and bis = ?4", nativeQuery = true)
    Antrag findByUserIdAndKlasseAndVonAndBis(long userId, String klasse, Timestamp von, Timestamp bis);
}
