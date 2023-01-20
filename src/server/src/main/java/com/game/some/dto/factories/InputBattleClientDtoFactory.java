package com.game.some.dto.factories;

import com.game.some.dto.BattleClientDto;
import com.game.some.dto.InputBattleClientDto;
import com.game.some.entities.BattleClient;

public class InputBattleClientDtoFactory {
    public static InputBattleClientDto makeBattleClientDto(BattleClient client)
    {
        return InputBattleClientDto.builder()
                .ip(client.getIp())
                .name(client.getName())
                .build();
    }

}
