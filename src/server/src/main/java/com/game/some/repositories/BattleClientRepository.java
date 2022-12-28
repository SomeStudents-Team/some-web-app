package com.game.some.repositories;

import com.game.some.entities.BattleClient;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BattleClientRepository extends CrudRepository<BattleClient, Long> {

    Optional<BattleClient> findByIpAndName(String ip, String name);
}
