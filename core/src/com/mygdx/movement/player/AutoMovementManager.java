package com.mygdx.movement.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.entities.Player;

public class AutoMovementManager {
    
    private Player player;
    private boolean animationInProgress;
    private Vector2 destination;
    private Vector2 velocity = new Vector2(2,2);
    private Vector2 movement = new Vector2();
    private Vector2 direction;
    private String orientation = "-";

    public AutoMovementManager(){
        this.player = Utils.getPlayer();
    }

    public boolean update(){
        if(destination == null) return false;

        player.setCoordinates(player.getCoordinates().add(movement));
        player.getStage().getCamera().translate(movement.x, movement.y, 0);

        if(player.getCoordinates().dst(destination) < 1) {
            animationInProgress = false;
            destination = null;
            return false;
        }
        return true;
    }

    public boolean isAnimationInProgress(){
        return animationInProgress;
    }

    public void goTo(Vector2 coordinates){
        if(isAnimationInProgress()) return;
        animationInProgress = true;
        destination = coordinates;
        System.out.println(destination);
        direction = calculateAngle(coordinates);
        velocity = new Vector2(direction).scl(50);
        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());
        System.out.println(direction);
        if(direction.x > 0 && direction.x >= direction.y) orientation = "wD";
        if(direction.x < 0 && direction.x <= direction.y) orientation = "wS";
        if(direction.y > 0 && direction.y > direction.x) orientation = "wW";
        if(direction.y < 0 && direction.y < direction.x) orientation = "wS";
        System.out.println(orientation);

    }

    public String getOrientation() {
        return orientation;
    }

    private Vector2 calculateAngle(Vector2 coordinates){
        Vector2 dir = new Vector2();
        dir.set(coordinates).sub(player.getCoordinates()).nor();
        return dir;
    }
}
