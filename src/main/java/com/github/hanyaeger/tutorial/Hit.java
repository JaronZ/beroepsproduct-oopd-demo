package com.github.hanyaeger.tutorial;

import com.github.hanyaeger.api.entities.Collided;
import com.github.hanyaeger.api.entities.Collider;
import javafx.geometry.Bounds;
import org.jetbrains.annotations.NotNull;

public interface Hit extends Collided {
    @Override
    default void onCollision(Collider collider) {
        CollisionSide side = getCollisionSide(this, collider);
        hit(collider, side);
    }

    default CollisionSide getCollisionSide(@NotNull Collided collided, @NotNull Collider collider) {
        // Player
        Bounds colliderBoundingBox = collider.getBoundingBox();
        // Block
        Bounds collidedBoundingBox = collided.getBoundingBox();

        double colliderHalfWidth = collider.getWidth() / 2;
        double colliderHalfHeight = collider.getHeight() / 2;
        double collidedHalfWidth = collided.getWidth() / 2;
        double collidedHalfHeight = collided.getHeight() / 2;

        // Calculate X and Y distance between collided and collider
        double differenceX = collidedBoundingBox.getCenterX() - colliderBoundingBox.getCenterX();
        double differenceY = collidedBoundingBox.getCenterY() - colliderBoundingBox.getCenterY();

        // Calculate minimal distance to separate collider and collided
        double minXDistance = colliderHalfWidth + collidedHalfWidth;
        double minYDistance = colliderHalfHeight + collidedHalfHeight;

        // Calculate collision depth
        // Add or subtract 1 to make sure depth can't be 0 at first collision
        double depthX = differenceX > 0 ? minXDistance - differenceX + 1 : -minXDistance - differenceX - 1;
        double depthY = differenceY > 0 ? minYDistance - differenceY + 1 : -minYDistance - differenceY - 1;

        // Math.abs makes negative numbers positive
        if (Math.abs(depthX) >= Math.abs(depthY)) {
            if (depthY > 0) {
                return CollisionSide.Top;
            }

            return CollisionSide.Bottom;
        }

        if (depthX > 0) {
            return CollisionSide.Left;
        }

        return CollisionSide.Right;
    }

    void hit(Collider collider, CollisionSide side);
}
