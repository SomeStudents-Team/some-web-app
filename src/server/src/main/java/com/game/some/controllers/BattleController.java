package com.game.some.controllers;

import com.game.some.dto.*;
import com.game.some.dto.factories.BattleDtoFactory;
import com.game.some.entities.Battle;
import com.game.some.entities.BattleClient;
import com.game.some.entities.BattleMember;
import com.game.some.services.BattleClientService;
import com.game.some.services.BattleMemberService;
import com.game.some.services.BattleService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/battles")
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor(onConstructor_= @Autowired)
public class BattleController {

    BattleService battleService;

    BattleClientService battleClientService;

    BattleMemberService battleMemberService;

    @PostMapping
    public BattleResponseEntity<BattleDto> create(@RequestBody InputBattleDto input) {
        BattleResponseEntityBuilder<BattleDto> builder =
                new BattleResponseEntityBuilder<>();

        Optional<BattleClient> client = battleClientService.findByIpAndName(
                input.getOwner().getIp(),
                input.getOwner().getName());

        if(client.isEmpty())
            client = Optional.of(
                    battleClientService.create(BattleClient.builder()
                        .ip(input.getOwner().getIp())
                        .name(input.getOwner().getName())
                        .build()));

        List<BattleMember> memberList = new ArrayList<>();
        for(int i = 0; i < input.getMembers().size(); i++)
        {
            memberList.add(battleMemberService.create(BattleMember.builder()
                    .dateCreate(LocalDate.now())
                    .name(input.getMembers().get(i).getName())
                    .score(0L)
                    .image(input.getMembers().get(i).getImage())
                    .build()));

            System.out.println(memberList.get(i).getId());
        }

        Optional<Battle> lastBattle = battleService.getLast();

        Battle battle = battleService.write(Battle.builder()
                        .title(input.getTitle())
                        .description(input.getDescription())
                        .owner(client.get())
                        .members(memberList)
                        .state(BattleState.kWait.ordinal())
                        .dateStart(lastBattle.map(value -> value.getDateStart().plusDays(1L))
                                .orElseGet(LocalDate::now))
                        .build());

        return builder
                .isSuccess(true)
                .code(HttpStatus.CREATED)
                .data(BattleDtoFactory.makeBattleDto(battleService.write(battle)))
                .build();
    }

    @GetMapping
    public BattleResponseEntity<Page<BattleDto>> getBattleList(
            @RequestParam(defaultValue = "0", required = false) Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @RequestParam(required = false, defaultValue = "id") String sort)
    {
        BattleResponseEntityBuilder<Page<BattleDto>> builder =
                new BattleResponseEntityBuilder<>();

        try
        {
            builder
                .isSuccess(true)
                .code(HttpStatus.OK)
                .data(battleService.findAll(PageRequest.of(page, size, Sort.by(sort).ascending()))
                        .map(BattleDtoFactory::makeBattleDto));
        }
        catch (Exception e)
        {
            builder
                .data(null)
                .isSuccess(false)
                .code(HttpStatus.BAD_REQUEST)
                .error(e.getMessage());
        }

        return builder.build();
    }

    @GetMapping("/{id}")
    public BattleResponseEntity<Optional<BattleDto>> getBattle(@PathVariable("id") Long id) {

        Optional<Battle> battle;

        BattleResponseEntityBuilder<Optional<BattleDto>> builder =
                new BattleResponseEntityBuilder<>();

        if((battle = battleService.findById(id)).isPresent())
            builder.data(Optional.of(BattleDtoFactory.makeBattleDto(battle.get())));


        return builder
                .code((battle.isPresent()) ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .isSuccess(battle.isPresent())
                .build();
    }

    @GetMapping("/now")
    public BattleResponseEntity<Optional<BattleDto>> getToDayBattle()
    {
        BattleResponseEntityBuilder<Optional<BattleDto>> entity
                = new BattleResponseEntityBuilder<>();

        Optional<Battle> battleInProgress =
                battleService.getInProgress(BattleState.kInProgress.ordinal());

        Optional<Battle> mustBeInProgress =
                battleService.getBattleByDate(LocalDate.now());

        if(battleInProgress.isEmpty() && mustBeInProgress.isEmpty())
            return entity
                    .code(HttpStatus.OK)
                    .isSuccess(false)
                    .error("No one battle in progress")
                    .data(Optional.empty())
                    .build();



        if(battleInProgress.isEmpty())
        {
            mustBeInProgress.get()
                    .setState(BattleState.kInProgress.ordinal());
            battleService.write(mustBeInProgress.get());

            return entity
                    .code(HttpStatus.OK)
                    .isSuccess(true)
                    .data(Optional.of(BattleDtoFactory
                            .makeBattleDto(mustBeInProgress.get())))
                    .build();
        }

        if(mustBeInProgress.isEmpty())
            mustBeInProgress = battleInProgress;

        if(mustBeInProgress.get().getDateStart() != battleInProgress.get().getDateStart())
        {
            battleInProgress.get().setState(BattleState.kFinished.ordinal());
            battleService.write(battleInProgress.get());
        }

        if(mustBeInProgress.get().getState() != BattleState.kInProgress.ordinal())
        {
            mustBeInProgress.get().setState(BattleState.kInProgress.ordinal());
            battleService.write(mustBeInProgress.get());
        }

        return entity
                .data(Optional.of(BattleDtoFactory.makeBattleDto(mustBeInProgress.get())))
                .isSuccess(true)
                .code(HttpStatus.OK)
                .build();
    }
}
