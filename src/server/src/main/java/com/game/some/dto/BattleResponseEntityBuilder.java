package com.game.some.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class BattleResponseEntityBuilder<T> {
    BattleResponseEntity<T> entity;

    public BattleResponseEntityBuilder()
    {
        entity = new BattleResponseEntity<>();
    }

    public BattleResponseEntityBuilder<T> code(HttpStatus code) {
        entity.setCode(code);
        return this;
    }

    public BattleResponseEntityBuilder<T> error(String error) {

        entity.setError(error);
        return this;
    }

    public BattleResponseEntityBuilder<T> data(T data)
    {
        entity.setData(data);
        return this;
    }

    public BattleResponseEntityBuilder<T> isSuccess(boolean success)
    {
        entity.setIsSuccess(success);
        return this;
    }

    public BattleResponseEntity<T> build()
    {
        return entity;
    }
}
