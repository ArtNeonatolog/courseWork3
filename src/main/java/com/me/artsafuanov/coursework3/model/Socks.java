package com.me.artsafuanov.coursework3.model;
import java.util.Objects;

public class Socks {

    private Color color;

    private Size size;

    private Integer cottonPart;


    public Socks(Color color, Size size, Integer cottonPart) {
        this.color = color;
        this.size = size;
        this.cottonPart = cottonPart;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Integer getCottonPart() {
        return cottonPart;
    }

    public void setCottonPart(Integer cottonPart) {
        this.cottonPart = cottonPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Socks socks = (Socks) o;
        return color == socks.color && size == socks.size && cottonPart.equals(socks.cottonPart);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color, size, cottonPart);
    }
}
