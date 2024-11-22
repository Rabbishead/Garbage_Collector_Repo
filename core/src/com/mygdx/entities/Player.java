package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.movement.MovementStyle;
import com.mygdx.Utils;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.hitboxes.Collider;
import com.mygdx.movement.player.PlayerRealtimeMovementStyle;
import com.mygdx.movement.player.PlayerTiledMovementStyle;
import com.mygdx.player.camera.CameraController;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.guns.Sniper;
import com.mygdx.resources.ResourceEnum;

/**
 * player class with collision managing
 */
public class Player extends Actor {

    private final ActorAnimationManager animationManager;
    private MovementStyle movementStyle;
    private Collider collider = new Collider();
    public Vector2 center = new Vector2();

    public enum Styles {
        REALTIME, TILED
    }

    public Player(float playerX, float playerY) {
        setX(playerX + 16);
        setY(playerY + 16);
        setWidth(17);
        setHeight(32);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setTouchable(Touchable.enabled);
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
        animationManager = new ActorAnimationManager(ResourceEnum.PLAYER);
        collider = new Collider(getX(), getY(), getWidth(), getHeight(), 0, "player", "npc");
        Utils.getHitboxHandler().registerCollider(collider);
        GunController.get().loadGun(new Sniper());
        CameraController.calculateMouseAngle(center);
        this.debug();
    }
    
    public Player(Vector2 coordinates) {
        setX(coordinates.x + 16);
        setY(coordinates.y + 16);
        setWidth(17);
        setHeight(32);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setTouchable(Touchable.enabled);
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
        animationManager = new ActorAnimationManager(ResourceEnum.PLAYER);
        collider = new Collider(getX(), getY(), getWidth(), getHeight(), 0, "player", "npc");
        Utils.getHitboxHandler().registerCollider(collider);
        GunController.get().loadGun(new Sniper());
        CameraController.calculateMouseAngle(center);
        this.debug();
    }

    /**
     * sets player's movement style between REALTIME and TILED
     * 
     * @param s
     */
    public void setMovementStyle(Styles s) {
        switch (s) {
            case REALTIME:
                movementStyle = new PlayerRealtimeMovementStyle();
                break;
            case TILED:
                movementStyle = new PlayerTiledMovementStyle();
                break;
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
        //if (collider.isCollided()) return;

        CameraController.calculateMouseAngle(center);
        animationManager.setCurrentAnimation(movementStyle.move());
        animationManager.updateAnimation(delta);
        GunController.get().act();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        collider.setPosition(getX(), getY());
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
    }
}