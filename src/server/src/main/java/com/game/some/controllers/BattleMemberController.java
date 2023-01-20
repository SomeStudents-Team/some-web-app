package com.game.some.controllers;

import com.game.some.dto.BattleMemberDto;
import com.game.some.dto.BattleResponseEntity;
import com.game.some.dto.BattleResponseEntityBuilder;
import com.game.some.dto.factories.BattleMemberDtoFactory;
import com.game.some.entities.BattleMember;
import com.game.some.services.BattleClientService;
import com.game.some.services.BattleMemberService;
import com.game.some.services.BattleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/members")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_= @Autowired)
public class BattleMemberController {

    BattleService battleService;

    BattleClientService battleClientService;

    BattleMemberService battleMemberService;

    @PutMapping
    public BattleResponseEntity<BattleMemberDto> update(@RequestBody BattleMemberDto input) {
        BattleResponseEntityBuilder<BattleMemberDto> builder =
                new BattleResponseEntityBuilder<>();

        BattleMember member = battleMemberService.create(BattleMember.builder()
                .id(input.getId())
                .name(input.getName())
                .score(input.getScore())
                .dateCreate(LocalDate.parse(input.getDateCreate()))
                .build());

        BattleMemberDto output = BattleMemberDtoFactory.makeBattleMemberDto(member);

        return builder
                .isSuccess(input != output)
                .data(output)
                .build();
    }

}
