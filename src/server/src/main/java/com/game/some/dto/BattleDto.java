package com.game.some.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BattleDto {
    Long id;

    String title;

    String description;

    @JsonProperty("date_start")
    String dateStart;

    Integer state;

    BattleClientDto owner;

    BattleMemberDto leader;

    List<BattleMemberDto> members;
}
