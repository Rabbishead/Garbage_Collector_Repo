package com.mygdx.movement.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.delay.DelayManager;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.movement.MovementStyle;

import java.util.Objects;

/**
 * RealtimeMovement for the overworld
 */
public class NPCRealtimeMovementStyle extends MovementStyle {
    
    private final Actor npc;
    private String lastDirection;
    private String[] path = {"wD", "wW" , "wW" , "wA", "wS" , "wS"};
    private int index;

    public NPCRealtimeMovementStyle(Actor npc) {
        this.npc = npc;
        lastDirection = "-";
        DelayManager.registerObject(this, 200);
    }

    /**
     * moves the npc and returns the correct direction of the body
     */
    public String move() {
        String direction = "-";
        Vector2 finalPosition = new Vector2(0, 0);
        String currentDirection = path[index];
        DelayManager.updateDelay(this);

        switch (currentDirection) {
            case "wD" -> {
                finalPosition.x += 0.75;
            }
            case "wA" -> {
                finalPosition.x -= 0.75;
            }
            case "wW" -> {
                finalPosition.y += 0.75;

            }
            case "wS" -> {
                finalPosition.y -= 0.75;
            }
        }

        if(TileMapCollisionsManager.getCurrentTileProprieties() == null) return "";
        if (TileMapCollisionsManager.canMove(npc.getX() + finalPosition.x, npc.getY() + finalPosition.y)) {
            npc.setX(npc.getX() + finalPosition.x);
            npc.setY(npc.getY() + finalPosition.y);
        }

        if(!DelayManager.isDelayOver(this)) return currentDirection;
       
        if(finalPosition.x == 0 && finalPosition.y == 0) direction = "-";
        if (direction.equals("-")) {
            direction = "i" + lastDirection.substring(1);
        }

        
        lastDirection = direction;
        index++;
        if(index >= path.length) index = 0;
        DelayManager.resetDelay(this);
        return currentDirection;
    }
}
