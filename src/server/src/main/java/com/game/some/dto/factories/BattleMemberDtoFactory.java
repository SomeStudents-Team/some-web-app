package com.game.some.dto.factories;

import com.game.some.dto.BattleMemberDto;
import com.game.some.entities.BattleMember;

import java.util.ArrayList;
import java.util.List;

public class BattleMemberDtoFactory {

    public static BattleMemberDto makeBattleMemberDto(BattleMember member)
    {
        return BattleMemberDto.builder()
                .id(member.getId())
                .name(member.getName())
                .dateCreate(member.getDateCreate().toString())
                .image(member.getImage())
                .score(member.getScore())
                .build();
    }

    public static List<BattleMemberDto> makeBattleMemberDtoList(List<BattleMember> members)
    {
        List<BattleMemberDto> out = new ArrayList<>();

        for (BattleMember member : members)
            out.add(makeBattleMemberDto(member));

        return out;
    }
}
