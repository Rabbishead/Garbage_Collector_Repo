package com.mygdx.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.stage.GCStage;

/**
 * RealtimeMovement for the overworld
 */
public class PlayerMovement {

    private final Actor player;
    private String lastDirection;

    public PlayerMovement() {
        this.player = GCStage.get().getPlayer();
        lastDirection = "IDLE_DOWN";
    }

    /**
     * moves the player and returns the correct direction of the body
     */
    public String move() {
        String direction = "IDLE_DOWN";
        Vector2 finalPosition = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            finalPosition.x += 100;
            if (!lastDirection.equals("WALK_UP") && !lastDirection.equals("WALK_DOWN")) {
                direction = "WALK_RIGHT";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            finalPosition.x -= 100;
            if (!lastDirection.equals("WALK_UP") && !lastDirection.equals("WALK_DOWN")) {
                direction = "WALK_LEFT";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            finalPosition.y += 100;
            if (!lastDirection.equals("WALK_RIGHT") && !lastDirection.equals("WALK_LEFT")) {
                direction = "WALK_UP";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            finalPosition.y -= 100;
            if (!lastDirection.equals("WALK_RIGHT") && !lastDirection.equals("WALK_LEFT")) {
                direction = "WALK_DOWN";
            }
        }
        if (finalPosition.x == 0 && finalPosition.y == 0) {
            direction = "IDLE_" + lastDirection.split("_")[1];
        }
        if (TileMapCollisionsManager.getCurrentTileProprieties() == null)
            return "";

        if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT))
            finalPosition.scl(10);

        finalPosition.scl(Gdx.graphics.getDeltaTime());
        if (TileMapCollisionsManager.canMove(player.getX() + finalPosition.x, player.getY() + finalPosition.y)) {
            player.setX(player.getX() + finalPosition.x);
            player.setY(player.getY() + finalPosition.y);
        }

        lastDirection = direction;
        return direction;
    }
}
