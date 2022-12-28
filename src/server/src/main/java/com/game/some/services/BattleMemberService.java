package com.game.some.services;

import com.game.some.entities.BattleClient;
import com.game.some.entities.BattleMember;

import java.util.Optional;

public interface BattleMemberService {

    Iterable<BattleMember> getAll();

    BattleMember create(BattleMember battle);

    Optional<BattleMember> findById(Long id);

    void deleteAll();

    void deleteById(Long id);
}
