package com.mygdx.entities.bosses;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.Logger;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.delay.DelayManager;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.entities.npcs.StateController;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;

import javax.swing.plaf.nimbus.State;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;

public class Reflection extends NPC {

    private Action movement;
    private StateController stateController;

    public Reflection(NPCBuilder npcBuilder) {
        super(npcBuilder);
        stateController = new StateController();
        stateController.setState(StateController.StateEnum.FOLLOW_PLAYER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Vector2 playerPos = Utils.getPlayer().getCoords();

        if (!Utils.getPlayer().isTiledWalking()) {
            getActions().clear();
            return;
        }
        switch (stateController.getState()) {
            case STILL -> {
                getActions().clear();
            }
            case FOLLOW_PLAYER -> {
                if (TileMapCollisionsManager.canMove(playerPos.x, playerPos.y)) {
                    movement = Actions.moveTo(playerPos.x, playerPos.y, 5);
                    addAction(movement);
                }
                if (Utils.getPlayer().getCoords().dst(getCoords()) < 100) {
                    stateController.setState(StateController.StateEnum.FLEE);
                    getActions().clear();
                }
            }
            case CIRLE_AROUND -> {
                if (TileMapCollisionsManager.canMove(playerPos.x, playerPos.y)) {

                }
                System.out.println("CIRCLING");
            }
            case FLEE -> {
                CameraController.calculateThowardsPos(playerPos, getCoords());
                float angle = CameraController.getXAngle();
                float rand = new Random().nextFloat() % 0.8f;
                Vector2 mov = new Vector2(1, rand).setAngleDeg(angle).scl(2);

                if (TileMapCollisionsManager.canMove(getX() + mov.x, getY() + mov.y)) {
                    setX(getX() + mov.x);
                    setY(getY() + mov.y);
                }

                if (Utils.getPlayer().getCoords().dst(getCoords()) > 500) {
                    stateController.setState(StateController.StateEnum.FOLLOW_PLAYER);
                    getActions().clear();
                }
            }
            case WANDER -> {
                System.out.println("WANDERING");
            }
            default -> {
                System.out.println("NOT IN A STATE");
            }
        }
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }

    public static class ReflectionBuilder extends NPCBuilder {

        public ReflectionBuilder coordinates(Vector2 coordinates) {
            super.coordinates(coordinates);
            return this;
        }

        public ReflectionBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }

        public ReflectionBuilder path(String[] path) {
            this.path = path;
            return this;
        }

        public ReflectionBuilder complexDialoguePath(String path) {
            this.complexDialoguePath = path;
            return this;
        }

        public Reflection build() {
            return new Reflection(this);
        }
    }
}
