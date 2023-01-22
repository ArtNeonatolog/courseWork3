package com.me.artsafuanov.coursework3.model;
import java.time.LocalDateTime;

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
