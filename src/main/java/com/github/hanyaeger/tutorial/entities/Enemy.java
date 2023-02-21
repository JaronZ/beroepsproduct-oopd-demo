package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.impl.DynamicRectangleEntity;
import com.github.hanyaeger.tutorial.CollisionSide;
import com.github.hanyaeger.tutorial.Hit;

public class Enemy extends DynamicRectangleEntity implements Collider, Collided, UpdateExposer, Hit {
    protected final double speed = 2;

    public Enemy(Coordinate2D initialLocation, Size size) {
        super(initialLocation, size);
        setMotion(speed, Direction.LEFT);
    }

    public void kill() {
        remove();
    }

    @Override
    public void explicitUpdate(long l) {
//        setMotion(speed, getDirection());
    }

    @Override
    public void hit(Collider collider, CollisionSide side) {
        if (collider instanceof Player) {
            if (side == CollisionSide.Top) {
                kill();
            }
            return;
        }

        if (collider instanceof DynamicRectangleEntity c) {
            switch (side) {
                case Left -> {
                    c.setAnchorLocationX(this.getBoundingBox().getMinX() - c.getWidth() - 1);
                    c.setDirection(Direction.LEFT);
                    this.setDirection(Direction.RIGHT);
                }
                case Right -> {
                    c.setAnchorLocationX(this.getBoundingBox().getMaxX() + 1);
                    c.setDirection(Direction.RIGHT);
                    this.setDirection(Direction.LEFT);
                }
                case Top -> c.setAnchorLocationY(this.getBoundingBox().getMinY() - 1);
                case Bottom -> c.setAnchorLocationY(this.getBoundingBox().getMaxY() + c.getHeight() + 1);
            }
        }
    }
}
