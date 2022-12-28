package com.game.some.repositories;

import com.game.some.entities.BattleMember;
import org.springframework.data.repository.CrudRepository;

public interface BattleMemberRepository extends CrudRepository<BattleMember, Long> {
    // TODO: ?
}
