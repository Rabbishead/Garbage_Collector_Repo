package com.mygdx.movement.npc;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.delay.DelayManager;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.movement.MovementStyle;

/**
 * RealtimeMovement for the overworld
 */
public class NPCRealtimeMovementStyle extends MovementStyle {
    
    private final Actor npc;
    private char lastDirection;
    private final String[] path;
    private int innerIndex;
    private int outerIndex;
    private final Vector2 startingCoordinates;

    public NPCRealtimeMovementStyle(Actor npc, String[] path) {
        this.npc = npc;
        this.path = path;
        lastDirection = '-';
        DelayManager.registerObject(this, 200);
        innerIndex = 0;
        outerIndex = (int) (path.length * Math.random());
        startingCoordinates = new Vector2(npc.getX(), npc.getY());
    }

    public NPCRealtimeMovementStyle(Actor npc){
        this(npc, new String[]{"-"});
    }

    /**
     * moves the npc and returns the correct direction of the body
     */
    public String move() {
        Vector2 finalPosition = new Vector2(0, 0);
        char currentDirection = path[outerIndex].charAt(innerIndex);
        boolean isWalking = true;
        DelayManager.updateDelay(this);

        switch (currentDirection) {
            case 'D' -> {
                finalPosition.x += 0.75F;
            }
            case 'A' -> {
                finalPosition.x -= 0.75F;
            }
            case 'W' -> {
                finalPosition.y += 0.75F;
            }
            case 'S' -> {
                finalPosition.y -= 0.75F;
            }
            case '-' -> {
                isWalking = false;
            }
        }

        if (TileMapCollisionsManager.canMove(npc.getX() + finalPosition.x, npc.getY() + finalPosition.y)) {
            npc.setX(npc.getX() + finalPosition.x);
            npc.setY(npc.getY() + finalPosition.y);
        }

        if(!DelayManager.isDelayOver(this)) return isWalking ? "w" + currentDirection : "i" + lastDirection;
        
        lastDirection = currentDirection;

        innerIndex++;

        if(innerIndex >= path[outerIndex].length()) innerIndex = 0;
        DelayManager.resetDelay(this);

        if(npc.getX() == startingCoordinates.x && npc.getY() == startingCoordinates.y){
            outerIndex = (int) (path.length * Math.random());
        }
        return isWalking ? "w" + currentDirection : "i" + lastDirection;
    }
}
