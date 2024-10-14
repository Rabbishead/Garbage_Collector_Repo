package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.movement.MovementStyle;
import com.mygdx.Utils;
import com.mygdx.animations.PlayerAnimationManager;
import com.mygdx.hitboxes.Collider;
import com.mygdx.movement.RealtimeMovementStyle;
import com.mygdx.movement.TiledMovementStyle;
import com.mygdx.player.camera.CameraController;
import com.mygdx.player.gunControls.GunController;
import com.mygdx.player.gunControls.guns.Sniper;

/**
 * player class with collision managing
 */
public class Player extends Actor {

    private final PlayerAnimationManager playerAnimationManager;
    private MovementStyle movementStyle;
    private Collider collider = new Collider();
    public Vector2 center = new Vector2();

    public enum Styles {
        REALTIME, TILED
    }

    public Player(int x, int y) {
        setX(x + 16);
        setY(y + 16);
        setWidth(18);
        setHeight(32);
        setOrigin(getWidth() / 2, getHeight() / 2);
        setTouchable(Touchable.enabled);
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
        playerAnimationManager = new PlayerAnimationManager();
        collider = new Collider(getX(), getY(), getWidth(), getHeight(), 0, "player");
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
                movementStyle = new RealtimeMovementStyle(this);
                break;
            case TILED:
                movementStyle = new TiledMovementStyle(this);
                break;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(playerAnimationManager.getCurrentFrame(), getX(), getY());
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(collider.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (collider.isCollided()) return;

        CameraController.calculateMouseAngle(center);
        playerAnimationManager.setCurrentAnimation(movementStyle.move());
        playerAnimationManager.updateAnimation(delta);
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