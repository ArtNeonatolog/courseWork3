package com.me.artsafuanov.coursework3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Size {
    XS (36),
    S (38),
    M (40),
    L (42),
    XL (44),
    XXL (46);

    private Integer size;
    Size(Integer size) {
        this.size = size;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
    @JsonCreator
    public static Size forValues(@JsonProperty("размер") Integer value) {
        for (Size size : Size.values()) {
            if (value == size.size) {
                return size;
            }
        }
        return null;
    }
}
