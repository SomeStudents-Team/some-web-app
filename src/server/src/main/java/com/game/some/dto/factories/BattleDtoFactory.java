package com.game.some.dto.factories;

import com.game.some.dto.BattleDto;
import com.game.some.entities.Battle;

import java.util.List;

public class BattleDtoFactory {

    public static BattleDto makeBattleDto(Battle battle)
    {
        return BattleDto.builder()
                .id(battle.getId())
                .description(battle.getDescription())
                .title(battle.getTitle())
                .owner(BattleClientDtoFactory
                        .makeBattleClientDto(battle.getOwner()))
                .leader((battle.getLeader() != null) ? BattleMemberDtoFactory
                        .makeBattleMemberDto(battle.getLeader()) : null)

                .members(BattleMemberDtoFactory
                        .makeBattleMemberDtoList(battle.getMembers()))

                .dateStart(battle.getDateStart().toString())
                .state(battle.getState())
                .build();
    }

    public static List<BattleDto> makeBattleDtoListFromBattleList(List<Battle> battles) {
        return battles.stream().map(BattleDtoFactory::makeBattleDto).toList();
    }
}
