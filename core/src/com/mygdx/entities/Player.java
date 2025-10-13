package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.movement.PlayerMovement;

import com.mygdx.animations.AnimationManager;
import com.mygdx.controllers.camera.CameraController;
import com.mygdx.controllers.gunControls.GunController;
import com.mygdx.controllers.hitboxes.Collider;
import com.mygdx.controllers.hitboxes.Tags;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;
import com.mygdx.stage.GCStage;

public class Player extends ScriptableActor{

    private final PlayerMovement movement;
    private final AnimationManager animationManager;
    private Collider collider = new Collider();
    public static Vector2 center = new Vector2();
    private boolean fighting;


    public Player(Vector2 coordinates) {
        GCStage.get().setPlayer(this);
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

        animationManager = new AnimationManager(16, TextureEnum.PLAYER.getAnimationRate(), TextureEnum.PLAYER.getDelay(), TextureEnum.PLAYER);
            
        this.debug();

        movement = new PlayerMovement();
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
                autoMovementManager.update() ? 
                    ResourceEnum.valueOf("PLAYER_" + autoMovementManager.getOrientation()) : 
                    ResourceEnum.valueOf("PLAYER_" + movement.move()));

        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
        collider.setPosition();
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        if(msg.message == MSG.SWAP_FIGHT_STATE.code) swapFightingState();
        
        return true;
    }

    public boolean isFighting() {
        return fighting;
    }
    
    public void swapFightingState(){
        if(fighting) GunController.get().remove();
        else GCStage.get().addActor(GunController.get());


        fighting = !fighting;
    }
}