package com.me.artsafuanov.coursework3.model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.me.artsafuanov.coursework3.components.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Operation {
    private final TypeOfOperation type;
    private final LocalDateTime localDateTime;
    private final Integer quantity;
    private final Socks socks;

    public Operation(TypeOfOperation type, LocalDateTime localDateTime, Integer quantity, Socks socks) {
        this.type = type;
        this.localDateTime = localDateTime;
        this.quantity = quantity;
        this.socks = socks;
    }

    public TypeOfOperation getType() {
        return type;
    }

    @JsonSerialize(using = LocalDateTimeSerializer.class)
    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Socks getSocks() {
        return socks;
    }

}
