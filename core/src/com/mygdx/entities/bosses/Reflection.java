package com.mygdx.entities.bosses;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.messages.MsgManager;
import com.mygdx.controllers.messages.MsgManager.MSG;
import com.mygdx.entities.npcs.NPC;
import com.mygdx.entities.npcs.StateController;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;

import java.util.Random;

public class Reflection extends NPC {

    private StateController stateController;

    public Reflection(NPCBuilder npcBuilder) {
        super(npcBuilder);
        stateController = new StateController();
        stateController.setState(StateController.StateEnum.FOLLOW_PLAYER);
        Utils.subscribeToStageMsg(this, MSG.SHOT);
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
                    Action movement = Actions.moveTo(playerPos.x, playerPos.y, 5);
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

    @Override
    public boolean handleMessage(Telegram msg) {
        if(msg.message == MsgManager.codes.get(MSG.SHOT)){
            System.out.println("HEY");
        }
        return true;
    }

    public static class ReflectionBuilder extends NPCBuilder{

        public ReflectionBuilder coordinates(Vector2 coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public ReflectionBuilder size(Vector2 size) {
            this.size = size;
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

        public ReflectionBuilder story(ResourceEnum e) {
            this.story = Utils.getStory(e);
            return this;
        }

        public Reflection build() {
            return new Reflection(this);
        }
    }
}
