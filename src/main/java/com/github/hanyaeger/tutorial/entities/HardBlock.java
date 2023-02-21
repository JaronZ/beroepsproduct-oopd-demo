package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import javafx.scene.paint.Color;

public class HardBlock extends Block {
    public HardBlock(Coordinate2D location) {
        super(location);
        this.setFill(Color.BLUE);
    }
}
