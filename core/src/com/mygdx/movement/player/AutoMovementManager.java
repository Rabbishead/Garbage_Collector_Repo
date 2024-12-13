package com.mygdx.movement.player;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.entities.Player;

public class AutoMovementManager {
    
    private Player player;
    private boolean animationInProgress;
    private Vector2 destination;

    public AutoMovementManager(){
        this.player = Utils.getPlayer();
    }

    public boolean update(){
        if(destination == null) return false;
        Vector2 difference = player.getCoordinates().sub(destination);
        if(player.getCoordinates().dst(destination) < 1) {
            animationInProgress = false;
            destination = null;
            return false;
        }
        move();

        return true;
    }

    public boolean isAnimationInProgress(){
        return animationInProgress;
    }

    public void goTo(Vector2 coordinates){
        if(isAnimationInProgress()) return;
        animationInProgress = true;
        destination = coordinates;
    }

    public String getAnimationState(){
        return "";
    }

    private void move(){

    }
}
