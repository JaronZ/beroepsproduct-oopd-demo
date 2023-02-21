package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.DynamicRectangleEntity;
import com.github.hanyaeger.api.entities.impl.RectangleEntity;
import com.github.hanyaeger.tutorial.CollisionSide;
import com.github.hanyaeger.tutorial.Hit;

public abstract class Block extends RectangleEntity implements Collided, Hit {
    public Block(Coordinate2D location) {
        super(location);
    }

    public void setSize(double size) {
        this.setWidth(size);
        this.setHeight(size);
    }

    @Override
    public void hit(Collider collider, CollisionSide side) {
        if (collider instanceof Player player) {
            switch (side) {
                case Left -> player.setAnchorLocationX(this.getBoundingBox().getMinX() - player.getWidth() - 1);
                case Right -> player.setAnchorLocationX(this.getBoundingBox().getMaxX() + 1);
                case Top -> {
                    player.setAnchorLocationY(this.getBoundingBox().getMinY() - 1);
                    player.setIsGrounded();
                }
                case Bottom -> player.setAnchorLocationY(this.getBoundingBox().getMaxY() + player.getHeight() + 1);
            }
        }

        if (!(collider instanceof Player) && collider instanceof DynamicRectangleEntity c) {
            switch (side) {
                case Left -> {
                    c.setAnchorLocationX(this.getBoundingBox().getMinX() - c.getWidth() - 1);
                    c.setDirection(Direction.LEFT);
                }
                case Right -> {
                    c.setAnchorLocationX(this.getBoundingBox().getMaxX() + 1);
                    c.setDirection(Direction.RIGHT);
                }
                case Top -> c.setAnchorLocationY(this.getBoundingBox().getMinY() - 1);
                case Bottom -> c.setAnchorLocationY(this.getBoundingBox().getMaxY() + c.getHeight() + 1);
            }
        }
    }
}
