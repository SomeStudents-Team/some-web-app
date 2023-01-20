package com.game.some.dto.factories;

import com.game.some.dto.InputBattleMemberDto;
import com.game.some.entities.BattleMember;

import java.util.ArrayList;
import java.util.List;

public class InputBattleMemberDtoFactory {
    public static InputBattleMemberDto makeBattleMemberDto(BattleMember member)
    {
        return InputBattleMemberDto.builder()
                .name(member.getName())
                .image(member.getImage())
                .score(member.getScore())
                .build();
    }

    public static List<InputBattleMemberDto> makeBattleMemberDtoList(List<BattleMember> members)
    {
        List<InputBattleMemberDto> out = new ArrayList<>();

        for (BattleMember member : members)
            out.add(makeBattleMemberDto(member));

        return out;
    }
}
