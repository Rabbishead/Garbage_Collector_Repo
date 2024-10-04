package com.mygdx.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.mygdx.delay.DelayManager;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.player.camera.CameraController;

import java.util.HashSet;
import java.util.Set;

public class TiledMovementStyle extends MovementStyle {

    private final Set<Character> inputs;
    private final Actor player;
    private String lastDirection;

    public TiledMovementStyle(Actor player) {
        inputs = new HashSet<>();
        this.player = player;
        lastDirection = "-";
        DelayManager.registerObject(this, 14);
    }

    public String move() {
        DelayManager.updateDelay(this);

        if (Gdx.input.isKeyPressed(Input.Keys.W))
            inputs.add('W');
        if (Gdx.input.isKeyPressed(Input.Keys.S))
            inputs.add('S');
        if (Gdx.input.isKeyPressed(Input.Keys.A))
            inputs.add('A');
        if (Gdx.input.isKeyPressed(Input.Keys.D))
            inputs.add('D');

        float angle = CameraController.getMouseAngle();
        String dir = "-";

        if (angle > 55 && angle <= 125)
            dir = "wW";
        else if (angle > 125 && angle <= 235)
            dir = "wA";
        else if (angle > 235 && angle <= 305)
            dir = "wS";
        else if (angle > 305 || angle <= 55)
            dir = "wD";
        lastDirection = "i" + dir.substring(1);

        if (inputs.isEmpty())
            return lastDirection;

        if (DelayManager.isDelayOver(this)) {
            float x = 0, y = 0;

            for (Character c : inputs) {
                switch (c) {
                    case 'W' -> y += 32;
                    case 'A' -> x -= 32;
                    case 'S' -> y -= 32;
                    case 'D' -> x += 32;
                }
            }

            inputs.clear();
            DelayManager.resetDelay(this);

            if (x == 0 && y == 0)
                return lastDirection;

            if (TileMapCollisionsManager.canMove(player.getX() + x, player.getY() + y)) {
                MoveByAction mba = new MoveByAction();
                mba.setAmount(x, y);
                mba.setDuration(0.1f);
                player.addAction(mba);
            }
        }

        return dir;
    }
}
