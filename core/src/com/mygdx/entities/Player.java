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
import com.mygdx.movement.player.AutoMovementManager;
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
    private AutoMovementManager autoMovementManager;

    public enum Styles {
        REALTIME, TILED
    }

    public Player(Vector2 coordinates) {
        Utils.setPlayer(this);

        setX(coordinates.x + 16);
        setY(coordinates.y + 16);

        setWidth(17);
        setHeight(32);

        setOrigin(getWidth() / 2, getHeight() / 2);

        setTouchable(Touchable.enabled);

        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();

        collider = new Collider(getX(), getY(), getWidth(), getHeight(), 0, "player", "npc");
        Utils.getHitboxHandler().registerCollider(collider);
        collider.setOnHit((collider, hitbox) -> {
                moveTo(new Vector2(100, 100));
            });


        GunController.get().loadGun(new Sniper());

        CameraController.calculateMouseAngle(center);

        animationManager = new ActorAnimationManager(ResourceEnum.PLAYER);

        autoMovementManager = new AutoMovementManager();

        debug();
    }

    /**
     * sets player's movement style between REALTIME and TILED
     * 
     * @param s
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

        autoMovementManager.update();

        animationManager.setCurrentAnimation(autoMovementManager.update() ? autoMovementManager.getAnimationState() : movementStyle.move());
        
        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        collider.setPosition(getX(), getY());
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
    }

    public void moveTo(Vector2 coordinates){
        autoMovementManager.goTo(coordinates);
    }

    public Vector2 getCoordinates(){
        return new Vector2(getX(), getY());
    }
}