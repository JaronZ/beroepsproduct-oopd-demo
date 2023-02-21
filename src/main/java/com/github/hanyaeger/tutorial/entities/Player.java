package com.github.hanyaeger.tutorial.entities;

import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.UpdateExposer;
import com.github.hanyaeger.api.entities.Collider;
import com.github.hanyaeger.api.entities.Direction;
import com.github.hanyaeger.api.entities.SceneBorderCrossingWatcher;
import com.github.hanyaeger.api.entities.SceneBorderTouchingWatcher;
import com.github.hanyaeger.api.entities.impl.DynamicRectangleEntity;
import com.github.hanyaeger.api.scenes.SceneBorder;
import com.github.hanyaeger.api.userinput.KeyListener;
import javafx.scene.input.KeyCode;

import java.util.Set;

public class Player extends DynamicRectangleEntity implements KeyListener, Collider, SceneBorderTouchingWatcher, SceneBorderCrossingWatcher, UpdateExposer {
    private final Coordinate2D initialLocation;
    private final int SPEED = 3;
    private final double GRAVITY = .5;
    protected boolean isGrounded = true;

    public Player(Coordinate2D initialLocation, Size size) {
        super(initialLocation, size);
        this.initialLocation = initialLocation;

    }

    public void setIsGrounded() {
        isGrounded = true;
    }

    @Override
    public void onPressedKeysChange(Set<KeyCode> pressedKeys) {
        if (pressedKeys.isEmpty()) {
            setMotion(GRAVITY,Direction.DOWN);
            return;
        }

        if (pressedKeys.contains(KeyCode.A)) {
            setMotion(SPEED, Direction.LEFT);
        }
        if (pressedKeys.contains(KeyCode.D)) {
            setMotion(SPEED, Direction.RIGHT);
        }

        if (pressedKeys.contains(KeyCode.S)) {
            setMotion(SPEED, Direction.DOWN);
        }

        if (pressedKeys.contains(KeyCode.W) && isGrounded) {
            isGrounded = false;

            setMotion(0, Direction.DOWN);
            double height = this.getAnchorLocation().getY();
            for (int i = 0; i < 30; i++) {
                height -= SPEED;
                setMotion(0, Direction.DOWN);
                this.setAnchorLocationY(height);
                setMotion(0, Direction.DOWN);
            }
        }
    }

    @Override
    public void explicitUpdate(long l) {
        // Gravity
        setMotion(GRAVITY, Direction.DOWN);
    }

    @Override
    public void notifyBoundaryCrossing(SceneBorder sceneBorder) {
        if (sceneBorder == SceneBorder.RIGHT) {
            setAnchorLocation(initialLocation);
        }
    }

    @Override
    public void notifyBoundaryTouching(SceneBorder sceneBorder) {
        if (sceneBorder == SceneBorder.LEFT) {
            setAnchorLocationX(0);
        }
    }
}
