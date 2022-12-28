package com.game.some.dto.factories;

import com.game.some.dto.BattleClientDto;
import com.game.some.entities.BattleClient;

public class BattleClientDtoFactory {

    public static BattleClientDto makeBattleClientDto(BattleClient client)
    {
        return BattleClientDto.builder()
                .id(client.getId())
                .ip(client.getIp())
                .name(client.getName())
                .build();
    }

}
