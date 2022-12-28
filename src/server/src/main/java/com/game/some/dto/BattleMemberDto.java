package com.game.some.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BattleMemberDto {

    Long id;

    String name;

    @JsonProperty("date_create")
    String dateCreate;

    Long score;

    String image;
}
