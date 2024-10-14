package com.mygdx.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.map.TileMapCollisionsManager;

import java.util.Objects;

/**
 * RealtimeMovement for the overworld
 */
public class RealtimeMovementStyle extends MovementStyle {
    
    private final Actor player;
    private String lastDirection;

    public RealtimeMovementStyle(Actor player) {
        this.player = player;
        lastDirection = "-";
    }

    /**
     * moves the player and returns the correct direction of the body
     */
    public String move() {
        String direction = "-";
        Vector2 finalPosition = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            finalPosition.x += 2;
            if (!Objects.equals(lastDirection, "wW") && !Objects.equals(lastDirection, "wS")) {
                direction = "wD";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            finalPosition.x -= 2;
            if (!Objects.equals(lastDirection, "wW") && !Objects.equals(lastDirection, "wS")) {
                direction = "wA";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            finalPosition.y += 2;
            if (!Objects.equals(lastDirection, "wD") && !Objects.equals(lastDirection, "wA")) {
                direction = "wW";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            finalPosition.y -= 2;
            if (!Objects.equals(lastDirection, "wD") && !Objects.equals(lastDirection, "wA")) {
                direction = "wS";
            }
        }
        if (direction.equals("-")) {
            direction = "i" + lastDirection.substring(1);
        }
        if(TileMapCollisionsManager.getCurrentTileProprieties() == null) return "";
        if (TileMapCollisionsManager.canMove(player.getX() + finalPosition.x, player.getY() + finalPosition.y)) {
            player.setX(player.getX() + finalPosition.x);
            player.setY(player.getY() + finalPosition.y);
            player.getStage().getCamera().translate(finalPosition.x, finalPosition.y, 0);
        }

        lastDirection = direction;
        return direction;
    }
}
