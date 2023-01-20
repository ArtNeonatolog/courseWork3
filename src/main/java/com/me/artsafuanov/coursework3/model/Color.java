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

    private String color;

    Color(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @JsonCreator
    public static Color forValues(@JsonProperty("цвет") String value) {
        for (Color color : Color.values()) {
            if (color.color.equals(value)) {
                return color;
            }
        }
        return null;
    }


}
