package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.paint.Color;

public class Platform extends Block {
    public Platform(Coordinate2D locationFrom, Coordinate2D locationTo) {
        super(locationFrom);
        this.setFill(Color.BROWN);
        this.setWidth(locationTo.getX() - locationFrom.getX());
        this.setHeight(locationTo.getY() - locationFrom.getY());
    }
}
