package com.game.some.repositories;

import com.game.some.entities.Battle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface BattleRepository extends CrudRepository<Battle, Long> {

    Page<Battle> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "SELECT * FROM battles ORDER BY battles.time_start DESC LIMIT 1")
    Optional<Battle> findLast();

    Optional<Battle> findBattleByState(int status);

    Optional<Battle> findBattleByDateStart(LocalDate date);

    List<Battle> getBattlesByDateStartAfterOrderByDateStartDesc(LocalDate date);

    @Query(nativeQuery = true, value = "SELECT COUNT(*) FROM battles")
    Long getBattlesCount();
}

