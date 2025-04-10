package com.mygdx.movement.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.delay.DelayManager;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.movement.MovementStyle;

import java.util.HashSet;
import java.util.Set;

public class NPCTiledMovementStyle extends MovementStyle {

    private final Set<Character> inputs;
    private final Actor player;
    private String movingDir;

    public NPCTiledMovementStyle(Actor player) {
        inputs = new HashSet<>();
        this.player = player;
        movingDir = "-";
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
        String idleDir = "-";

        if (angle > 55 && angle <= 125)
            idleDir = "iW";
        else if (angle > 125 && angle <= 235)
            idleDir = "iA";
        else if (angle > 235 && angle <= 305)
            idleDir = "iS";
        else if (angle > 305 || angle <= 55)
            idleDir = "iD";
        movingDir = "w" + idleDir.substring(1);

        if (inputs.isEmpty())
            return idleDir;

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
                return idleDir;

            if (TileMapCollisionsManager.canMove(player.getX() + x, player.getY() + y)) {
                MoveByAction mba = new MoveByAction();
                mba.setAmount(x, y);
                mba.setDuration(0.1f);
                player.addAction(mba);
            }
            return movingDir;
        }

        return "";
    }
}
