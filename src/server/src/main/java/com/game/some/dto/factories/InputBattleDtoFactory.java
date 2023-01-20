package com.game.some.dto.factories;

import com.game.some.dto.BattleDto;
import com.game.some.dto.InputBattleDto;
import com.game.some.entities.Battle;

public class InputBattleDtoFactory {

    public static InputBattleDto makeBattleDto(Battle battle)
    {
        return InputBattleDto.builder()
                .description(battle.getDescription())
                .title(battle.getTitle())
                .owner(InputBattleClientDtoFactory
                        .makeBattleClientDto(battle.getOwner()))

                .members(InputBattleMemberDtoFactory
                        .makeBattleMemberDtoList(battle.getMembers()))
                .build();
    }
}
