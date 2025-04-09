package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.messages.MsgManager;
import com.mygdx.movement.MovementStyle;
import com.mygdx.Utils;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.hitboxes.Collider;
import com.mygdx.movement.player.AutoMovementManager;
import com.mygdx.movement.player.PlayerRealtimeMovementStyle;
import com.mygdx.movement.player.PlayerTiledMovementStyle;
import com.mygdx.player.camera.CameraController;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.resources.ResourceEnum;

/**
 * player class with collision managing
 */
public class Player extends GameActor {

    private final ActorAnimationManager animationManager;
    private MovementStyle movementStyle;
    private Collider collider = new Collider();
    public Vector2 center = new Vector2();
    private final AutoMovementManager autoMovementManager;

    public enum Styles {
        REALTIME, TILED
    }

    public Player(Vector2 coordinates) {
        Utils.setPlayer(this);
        setTouchable(Touchable.enabled);

        setSize(16, 32);
        setOrigin(getWidth() / 2, getHeight() / 2);

        collider = new Collider(center, getWidth(), getHeight(), 0, "player", "npc");
        collider.register();
        setPosition(coordinates.x, coordinates.y);

        GunController.get().loadGuns();
        CameraController.calculateMouseAngle(center);

        animationManager = new ActorAnimationManager(ResourceEnum.PLAYER);
        autoMovementManager = new AutoMovementManager();

        debug();
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
            MsgManager.sendStageMsg(MsgManager.MSG.BLOCK_WALLS);
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

        animationManager.setCurrentAnimation(
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

    public void moveTo(Vector2 coords) {
        autoMovementManager.goTo(coords);
    }

    public Vector2 getCoords() {
        return new Vector2(getX(), getY());
    }

    public void setCoords(Vector2 coords) {
        setX(coords.x);
        setY(coords.y);
    }

    public boolean isAutoWalking() {
        return autoMovementManager.isAnimationInProgress();
    }

    public boolean isTiledWalking() {
        return movementStyle instanceof PlayerTiledMovementStyle;
    }
}