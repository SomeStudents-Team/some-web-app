package com.game.some.services.implementations;

import com.game.some.entities.Battle;
import com.game.some.repositories.BattleRepository;
import com.game.some.services.BattleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_= @Autowired)
public class BattleServiceImpl implements BattleService {

    BattleRepository battleRepository;

    @Override
    public Iterable<Battle> getAll() {
        return battleRepository.findAll();
    }

    @Override
    public Optional<Battle> getLast() {
        return battleRepository.findLast();
    }

    @Override
    public Optional<Battle> getInProgress(int status) {
        return battleRepository.findBattleByState(status);
    }

    @Override
    public Optional<Battle> getBattleByDate(LocalDate date) {
        return battleRepository.findBattleByDateStart(date);
    }

    @Override
    public Page<Battle> findAll(Pageable pageable) {
        return battleRepository.findAll(pageable);
    }

    @Override
    public Battle write(Battle battle) {
        return battleRepository.save(battle);
    }

    @Override
    public Optional<Battle> findById(Long id) {
        return battleRepository.findById(id);
    }

    @Override
    public List<Battle> getBattlesAfterDate(LocalDate date) {
        return battleRepository.getBattlesByDateStartAfterOrderByDateStartDesc(date);
    }

    @Override
    public Long getBattlesCount() {
        return battleRepository.getBattlesCount();
    }

    @Override
    public void deleteAll() {
        battleRepository.deleteAll();
    }

    @Override
    public void deleteById(Long id) {
        battleRepository.deleteById(id);
    }

}
