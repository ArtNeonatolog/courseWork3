package com.me.artsafuanov.coursework3.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Objects;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Color {
    WHITE ("белый"),
    RED ("красный"),
    BLUE ("синий"),
    GREEN ("зеленый"),
    BLACK ("черный");

    private final String strValue;

    Color(String color) {

        this.strValue = color;
    }

    @JsonValue
    public String getStrValue() {

        return this.strValue;
    }

    @JsonCreator
    public static Color forValues(String value) {
        for (Color color : Color.values()) {
            if (color.getStrValue().equals(value)) {
                return color;
            }
        }
        return null;
    }

}
