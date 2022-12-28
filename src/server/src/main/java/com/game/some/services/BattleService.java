package com.game.some.services;

import com.game.some.entities.Battle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Optional;

public interface BattleService {

    Iterable<Battle> getAll();

    Optional<Battle> getLast();

    Optional<Battle> getInProgress(int status);

    Optional<Battle> getBattleByDate(LocalDate date);

    Page<Battle> findAll(Pageable pageable);

    Battle write(Battle battle);

    Optional<Battle> findById(Long id);

    void deleteAll();

    void deleteById(Long id);
}
