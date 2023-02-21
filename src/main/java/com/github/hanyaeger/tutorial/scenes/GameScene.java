package com.github.hanyaeger.tutorial.scenes;

import com.github.hanyaeger.api.AnchorPoint;
import com.github.hanyaeger.api.Coordinate2D;
import com.github.hanyaeger.api.Size;
import com.github.hanyaeger.api.scenes.DynamicScene;
import com.github.hanyaeger.tutorial.entities.*;
import javafx.scene.paint.Color;

public class GameScene extends DynamicScene {

    @Override
    public void setupScene() {
        setBackgroundColor(Color.DARKGREEN);
    }

    @Override
    public void setupEntities() {
        int GRID_SIZE = 40;
        Platform platform = new Platform(
                new Coordinate2D(0, getHeight() - GRID_SIZE),
                new Coordinate2D(getWidth(), getHeight())
        );
        addEntity(platform);

        Player player = new Player(
                new Coordinate2D(GRID_SIZE, platform.getAnchorLocation().getY()),
                new Size(GRID_SIZE, GRID_SIZE)
        );
        player.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
        addEntity(player);

        Enemy enemy = new Enemy(
                new Coordinate2D(GRID_SIZE * 20, platform.getAnchorLocation().getY()),
                new Size(GRID_SIZE, GRID_SIZE)
        );
        enemy.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
        addEntity(enemy);

        Enemy enemy2 = new Enemy(
                new Coordinate2D(GRID_SIZE * 26, platform.getAnchorLocation().getY()),
                new Size(GRID_SIZE, GRID_SIZE)
        );
        enemy2.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
        addEntity(enemy2);

        for (int x = 0; x < 5; x++) {
            for (int y = 0; y < 5; y++) {
                if (y > x) continue;
                Block block = new HardBlock(
                        new Coordinate2D(GRID_SIZE * (5 + x), platform.getAnchorLocation().getY() - GRID_SIZE * y)
                );
                block.setSize(GRID_SIZE);
                block.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
                addEntity(block);
            }
        }

        for (int y = 0; y < 2; y++) {
            Block block = new HardBlock(
                    new Coordinate2D(GRID_SIZE * 10, platform.getAnchorLocation().getY() - GRID_SIZE * y)
            );
            block.setSize(GRID_SIZE);
            block.setAnchorPoint(AnchorPoint.BOTTOM_LEFT);
            addEntity(block);
        }
    }
}
