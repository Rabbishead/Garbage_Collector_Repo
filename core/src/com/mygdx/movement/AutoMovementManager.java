package com.mygdx.movement;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.entities.GameActor;
import com.mygdx.entities.ScriptableActor;
import com.mygdx.map.TileMapCollisionsManager;

public class AutoMovementManager {

    private final GameActor actor;
    private boolean animationInProgress;
    private Vector2 destination;
    private String orientation = "-";

    public AutoMovementManager(GameActor a) {
        this.actor = a;
    }

    public boolean update() {

        if (destination == null)
            return false;

        Vector2 direction = new Vector2(destination).sub(actor.getCoords()).nor();
        Vector2 movement = new Vector2(direction).scl(100 * Gdx.graphics.getDeltaTime());

        if (!TileMapCollisionsManager.canMove(actor.getX() + movement.x, actor.getY() + movement.y)
                || actor.getCoords().dst(destination) < 1) {
            reset();
            return false;
        }

        if (movement.len() > actor.getCoords().dst(destination)) {
            actor.setCoords(destination);
            reset();
            return false;
        }

        actor.setCoords(actor.getCoords().add(movement));

        return true;
    }

    public boolean isAnimationInProgress() {
        return animationInProgress;
    }

    public void goTo(Vector2 coordinates) {
        if (isAnimationInProgress()){
            
            return;
        }
            
        animationInProgress = true;
        destination = coordinates.cpy();

        Vector2 direction = new Vector2(destination).sub(actor.getCoords()).nor();

        float EPSILON = 1e-1f;

        if (Math.abs(direction.x) < EPSILON) direction.x = 0f; //round up values too small
        if (Math.abs(direction.y) < EPSILON) direction.y = 0f;

        if (direction.x > 0 && direction.x >= direction.y)
            orientation = "wR";
        if (direction.x < 0 && direction.x <= direction.y)
            orientation = "wL";
        if (direction.y > 0 && direction.y > direction.x)
            orientation = "wU";
        if (direction.y < 0 && direction.y < direction.x)
            orientation = "wD";
    }

    public String getOrientation() {
        return orientation;
    }

    public void reset(){
        animationInProgress = false;
        destination = null;
        orientation = "i" + orientation.substring(1);

        if(actor instanceof ScriptableActor sc){
            if(sc.hasScript())
                sc.proceed();
        }
    }
}
