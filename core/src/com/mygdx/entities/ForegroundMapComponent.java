package com.mygdx.entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.animations.MapComponentAnimationManager;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.resources.ResourceEnum;

public class ForegroundMapComponent extends GameActor {

    private Hitbox hitbox = new Hitbox();

    private final MapComponentAnimationManager animationManager;

    public ForegroundMapComponent(Vector2 coordinates) {

        setX(coordinates.x);
        setY(coordinates.y);

        setWidth(32);
        setHeight(32);
        
        //this.debug();

        setTouchable(Touchable.enabled);

        animationManager = new MapComponentAnimationManager(ResourceEnum.LAMP, 1, 2, 5f);

        hitbox = new Hitbox(getX() + getWidth() * 0.4f, getY(), 8, 24, 0, true, "enemy,npc");
        hitbox.setOnHit((hitbox, collider) -> {});
        
        Utils.getHitboxHandler().registerHitbox(hitbox);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        animationManager.setCurrentAnimation(0);
        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
    }

}
