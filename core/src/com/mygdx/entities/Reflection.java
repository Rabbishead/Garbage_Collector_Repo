package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.Utils;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.controllers.gunControls.projectiles.Projectile;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.controllers.states.StateController;
import com.mygdx.map.TileMapCollisionsManager;

import java.util.Random;

public class Reflection extends NPC {
    private Scope scope;

    private StateController stateController;

    public Reflection(ReflectionBuilder npcBuilder) {
        super(npcBuilder);
        stateController = new StateController();
        stateController.setMovementState(StateController.MovementState.FOLLOW_PLAYER);
        Utils.subscribeToStageMsg(this, MSG.SHOT);
        scope = new Scope(getCoords());
        DelayManager.registerObject(scope, 100f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(Utils.getPlayer().isFighting()) scope.draw(batch, parentAlpha);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if(Utils.getPlayer().isFighting())  scope.act(delta);
        DelayManager.updateDelay(scope);
        Vector2 playerPos = Utils.getPlayer().getCoords();

        if (!Utils.getPlayer().isFighting()) {
            getActions().clear();
            return;
        }

        switch (stateController.getMovState()) {
            case STILL -> {
                getActions().clear();
            }
            case FOLLOW_PLAYER -> {
                if (TileMapCollisionsManager.canMove(playerPos.x, playerPos.y)) {
                    Action movement = Actions.moveTo(playerPos.x, playerPos.y, 5);
                    addAction(movement);
                }
                if (Utils.getPlayer().getCoords().dst(getCoords()) < 100) {
                    stateController.setMovementState(StateController.MovementState.FLEE);
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
                    stateController.setMovementState(StateController.MovementState.FOLLOW_PLAYER);
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
        if(scope.getCoords().dst(playerPos) < 20 && DelayManager.isDelayOver(scope)){
            System.out.println("HEYHEYHEY");
            getStage().addActor(new Projectile(center, 0, CameraController.getAngle(getCoords(), scope.getCoords()), false));
            DelayManager.resetDelay(scope);
        } 
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        if(msg.message == MSG.SHOT.code){
            System.out.println("HEY");
        }
        return true;
    }

    public static class ReflectionBuilder extends NPCBuilder{    

        public Reflection build() {
            return new Reflection(this);
        }

        @Override
        public ReflectionBuilder getThis() {
            return this;
        }
    }
}
