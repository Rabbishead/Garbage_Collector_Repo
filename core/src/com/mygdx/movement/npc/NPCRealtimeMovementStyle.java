package com.mygdx.movement.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.controllers.delay.DelayManager;
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

    public NPCRealtimeMovementStyle(Actor npc, String... path) {
        this.npc = npc;
        this.path = path;
        lastDirection = '-';
        DelayManager.registerObject(this, 200f);
        innerIndex = 0;
        outerIndex = (int) (path.length * Math.random());
        startingCoordinates = new Vector2(npc.getX(), npc.getY());
    }

    public NPCRealtimeMovementStyle(Actor npc) {
        this(npc, "-" );
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
            case 'R' -> {
                finalPosition.x += 60;
            }
            case 'L' -> {
                finalPosition.x -= 60;
            }
            case 'U' -> {
                finalPosition.y += 60;
            }
            case 'D' -> {
                finalPosition.y -= 60;
            }
            case '-' -> {
                isWalking = false;
            }
        }

        finalPosition.scl(Gdx.graphics.getDeltaTime());

        float newX = npc.getX() + finalPosition.x, newY = npc.getY() + finalPosition.y;
        if (TileMapCollisionsManager.canMove(newX, newY)) {
            npc.setPosition(newX, newY);
        }

        if (!DelayManager.isDelayOver(this))
            return isWalking ? "w" + currentDirection : "i" + lastDirection;

        lastDirection = currentDirection;

        innerIndex++;

        if (innerIndex >= path[outerIndex].length())
            innerIndex = 0;
        DelayManager.resetDelay(this);

        if (npc.getX() == startingCoordinates.x && npc.getY() == startingCoordinates.y) {
            outerIndex = (int) (path.length * Math.random());
        }
        return isWalking ? "w" + currentDirection : "i" + lastDirection;
    }
}
