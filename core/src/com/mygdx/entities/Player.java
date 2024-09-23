package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.movement.MovementStyle;
import com.mygdx.Utils;
import com.mygdx.animations.PlayerAnimationManager;
import com.mygdx.gunControls.GunController;
import com.mygdx.gunControls.guns.Slingshot;
import com.mygdx.hitboxes.Collider;
import com.mygdx.movement.RealtimeMovementStyle;
import com.mygdx.movement.TiledMovementStyle;

/**
 * player class with collision managing
 */
public class Player extends Actor {

    private final PlayerAnimationManager playerAnimationManager;
    private MovementStyle movementStyle;
    private Collider collider = new Collider();

    public enum Styles {
        REALTIME, TILED
    }

    public Player(int x, int y) {
        setX(x + 16);
        setY(y + 16);
        setWidth(18);
        setHeight(32);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
        playerAnimationManager = new PlayerAnimationManager();
        collider = new Collider(getX(), getY(), getWidth(), getHeight(), 0, "player");
        Utils.getHitboxHandler().registerCollider(collider);
        Utils.player = this;
        GunController.get().loadGun(new Slingshot());
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
        playerAnimationManager.setCurrentAnimation(movementStyle.move());
        playerAnimationManager.updateAnimation(delta);
        GunController.get().act();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        collider.setPosition(getX(), getY());
    }
}