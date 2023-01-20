package com.me.artsafuanov.coursework3.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Size {
    XS (36),
    S (38),
    M (40),
    L (42),
    XL (44),
    XXL (46);

    private final Integer intValue;
    Size(Integer size) {

        this.intValue = size;
    }

    @JsonValue
    public Integer getIntValue() {
        return this.intValue;
    }
    @JsonCreator
    public static Size forValues(Integer value) {
        for (Size size : Size.values()) {
            if (size.getIntValue().equals(value)) {
                return size;
            }
        }
        return null;
    }
}
