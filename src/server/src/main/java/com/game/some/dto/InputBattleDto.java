package com.game.some.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InputBattleDto {

    String title;

    String description;

    InputBattleClientDto owner;

    List<InputBattleMemberDto> members;
}
