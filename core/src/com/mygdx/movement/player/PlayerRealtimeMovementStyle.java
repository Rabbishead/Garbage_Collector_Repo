package com.mygdx.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.movement.MovementStyle;
import java.util.Objects;

/**
 * RealtimeMovement for the overworld
 */
public class PlayerRealtimeMovementStyle extends MovementStyle {

    private final Actor player;
    private String lastDirection;

    public PlayerRealtimeMovementStyle() {
        this.player = Utils.getPlayer();
        lastDirection = "-";
    }

    /**
     * moves the player and returns the correct direction of the body
     */
    public String move() {
        String direction = "-";
        Vector2 finalPosition = new Vector2(0, 0);
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            finalPosition.x += 100;
            if (!Objects.equals(lastDirection, "wU") && !Objects.equals(lastDirection, "wD")) {
                direction = "wR";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            finalPosition.x -= 100;
            if (!Objects.equals(lastDirection, "wU") && !Objects.equals(lastDirection, "wD")) {
                direction = "wL";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            finalPosition.y += 100;
            if (!Objects.equals(lastDirection, "wR") && !Objects.equals(lastDirection, "wL")) {
                direction = "wU";
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            finalPosition.y -= 100;
            if (!Objects.equals(lastDirection, "wR") && !Objects.equals(lastDirection, "wL")) {
                direction = "wD";
            }
        }
        if (finalPosition.x == 0 && finalPosition.y == 0)
            direction = "-";
        if (direction.equals("-")) {
            direction = "i" + lastDirection.substring(1);
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
