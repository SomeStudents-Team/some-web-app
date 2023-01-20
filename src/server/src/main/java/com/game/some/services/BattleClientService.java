package com.game.some.services;

import com.game.some.entities.BattleClient;

import java.util.Optional;


public interface BattleClientService {

    Iterable<BattleClient> getAll();

    BattleClient create(BattleClient battle);

    Optional<BattleClient> findById(Long id);

    Optional<BattleClient> findByIpAndName(String ip, String name);

    void deleteAll();

    void deleteById(Long id);
}
