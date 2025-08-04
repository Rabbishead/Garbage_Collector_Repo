package com.mygdx.movement.npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.movement.MovementStyle;

public class NPCScriptMovementStyle extends MovementStyle{
    private final Actor npc;
    private char lastDirection;
    private String path = "";
    private Vector2 finalCoordinates;

    public NPCScriptMovementStyle(Actor npc) {
        this.npc = npc;
        DelayManager.registerObject(this, 200f);
    }

    public String move(){
        if(finalCoordinates != null){
            return coordsMove();
        }
        if(path != ""){
            return pathMove();
        }
        return "-";
    }


    public void setPath(String path) {
        this.path = path;
    }
    public void setFinalCoordinates(Vector2 finalCoordinates) {
        this.finalCoordinates = finalCoordinates;
    }

    /**
     * moves the npc and returns the correct direction of the body
     */
    public String pathMove() {
        Vector2 finalPosition = new Vector2(0, 0);
        char currentDirection = path.charAt(0);
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

        DelayManager.resetDelay(this);

        return isWalking ? "w" + currentDirection : "i" + lastDirection;
    }

    /**
     * moves the npc and returns the correct direction of the body
     */
    public String coordsMove() {
        boolean isWalking = true;
        DelayManager.updateDelay(this);

        npc.addAction(Actions.moveTo(finalCoordinates.x, finalCoordinates.y, 50));

        return "-";
    }
}
