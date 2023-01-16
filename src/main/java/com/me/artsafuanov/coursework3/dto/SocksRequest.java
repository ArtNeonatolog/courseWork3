package com.me.artsafuanov.coursework3.dto;

import com.me.artsafuanov.coursework3.model.Color;
import com.me.artsafuanov.coursework3.model.Size;

public class SocksRequest {
    private Color color;

    private Size size;

    private Integer cottonPart;

    private Integer quantity;

    public SocksRequest(Color color, Size size, Integer cottonPart, Integer quantity) {
        this.color = color;
        this.size = size;
        this.cottonPart = cottonPart;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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

}
