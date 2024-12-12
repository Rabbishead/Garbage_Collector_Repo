package com.mygdx.movement.player;

import java.util.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.entities.Player;
import com.mygdx.map.TileMapCollisionsManager;

public class AutoMovementManager {
    
    private Player player;
    private boolean animationInProgress;
    private Vector2 destination;

    public AutoMovementManager(){
        this.player = Utils.getPlayer();
    }

    public boolean update(){
        if(destination == null) return false;
        float distance = player.getCoordinates().dst(destination);
        Vector2 difference = player.getCoordinates().sub(destination);

        System.out.println(difference);
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
    }
}
