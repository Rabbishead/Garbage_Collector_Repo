package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.movement.MovementStyle;
import com.mygdx.Utils;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.hitboxes.Collider;
import com.mygdx.controllers.hitboxes.Tags;
import com.mygdx.movement.player.PlayerRealtimeMovementStyle;
import com.mygdx.movement.player.PlayerTiledMovementStyle;
import com.mygdx.resources.ResourceEnum;

public class Player extends ScriptableActor{

    private final ActorAnimationManager animationManager;
    private MovementStyle movementStyle;
    private Collider collider = new Collider();
    public static Vector2 center = new Vector2();
    

    public enum Styles {
        REALTIME, TILED
    }

    public Player(Vector2 coordinates) {
        Utils.setPlayer(this);
        setTouchable(Touchable.enabled);

        setSize(16, 32);
        setOrigin(getWidth() / 2, getHeight() / 2);

        collider = new Collider(center, getWidth(), getHeight());
        collider.setTags(Tags.PLAYER);
        collider.setSearchTags(Tags.NPC, Tags.BUILDING);
        collider.register();
        setPosition(coordinates.x, coordinates.y);

        GunController.get().loadGuns();
        CameraController.calculateMouseAngle(center);

        animationManager = new ActorAnimationManager(ResourceEnum.PLAYER);
        this.debug();
    }

    /**
     * sets player's movement style between REALTIME and TILED
     */
    public void setMovementStyle(Styles s) {
        switch (s) {
            case REALTIME: {
                movementStyle = new PlayerRealtimeMovementStyle();
                GunController.get().remove();
                break;
            }
            case TILED: {
                movementStyle = new PlayerTiledMovementStyle();
                Utils.getStage().addActor(GunController.get());
                break;
            }
        }
    }

    public void swapMovementStyle() {
        if (movementStyle instanceof PlayerRealtimeMovementStyle) {
            movementStyle = new PlayerTiledMovementStyle();
            Utils.getStage().addActor(GunController.get());
            moveTo(new Vector2(((int) (getCoords().x / 32) * 32) + 8, ((int) (getCoords().y / 32) * 32) + 8));
        } else {
            movementStyle = new PlayerRealtimeMovementStyle();
            GunController.get().remove();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(collider.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        CameraController.calculateMouseAngle(center);

        animationManager.setWalkingAnimation(
                autoMovementManager.update() ? autoMovementManager.getOrientation() : movementStyle.move());

        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
        collider.setPosition();
    }

    

    

    

    

    public boolean isTiledWalking() {
        return movementStyle instanceof PlayerTiledMovementStyle;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        if(msg.message == MSG.CHANGE_MOV_STYLE.code) swapMovementStyle();
        
        return true;
    }
}