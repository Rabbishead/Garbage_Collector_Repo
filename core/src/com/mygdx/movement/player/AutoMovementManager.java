package com.mygdx.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;

public class AutoMovementManager {

    private final Player player;
    private boolean animationInProgress;
    private Vector2 destination;
    private final Vector2 movement = new Vector2();
    private String orientation = "-";

    public AutoMovementManager() {
        this.player = Utils.getPlayer();
    }

    public boolean update() {
        if (destination == null)
            return false;
        if (!TileMapCollisionsManager.canMove(player.getX() + movement.x, player.getY() + movement.y)
                || player.getCoords().dst(destination) < 1) {
            animationInProgress = false;
            destination = null;
            return false;
        }
        player.setCoords(player.getCoords().add(movement));
        player.getStage().getCamera().translate(movement.x, movement.y, 0);
        return true;
    }

    public boolean isAnimationInProgress() {
        return animationInProgress;
    }

    public void goTo(Vector2 coordinates) {
        if (isAnimationInProgress())
            return;
        animationInProgress = true;

        destination = coordinates;

        Vector2 direction = calculateAngle(coordinates);

        Vector2 velocity = new Vector2(direction).scl(100);

        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());

        if (direction.x > 0 && direction.x >= direction.y)
            orientation = "wD";
        if (direction.x < 0 && direction.x <= direction.y)
            orientation = "wA";
        if (direction.y > 0 && direction.y > direction.x)
            orientation = "wW";
        if (direction.y < 0 && direction.y < direction.x)
            orientation = "wS";
    }

    public String getOrientation() {
        return orientation;
    }

    private Vector2 calculateAngle(Vector2 coordinates) {
        Vector2 dir = new Vector2();
        dir.set(coordinates).sub(player.getCoords()).nor();
        return dir;
    }
}
