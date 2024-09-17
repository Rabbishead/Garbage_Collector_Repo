package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.animations.PlayerAnimationManager;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.map.TileMapCollisionsManager;
import com.mygdx.resources.ResourceEnum;

public class TestActor extends Actor {
    private Hitbox hitbox;

    public TestActor(float x, float y) {
        setX(x + 100);
        setY(y + 100);
        setWidth(32);
        setHeight(32);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
        hitbox = new Hitbox(getX(), getY(), getWidth(), getHeight(), true, e -> {
            this.remove();
            Utils.getHitboxHandler().unRegisterHitbox(hitbox);
            System.out.println("touched");
        });
        Utils.getHitboxHandler().registerHitbox(hitbox);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(Utils.getTexture(ResourceEnum.TESTACTOR), getX(), getY(), 32, 32);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        if (TileMapCollisionsManager.canMove(getX() + 5, getY() + 5)) {
            setX(getX() + 1);
            setY(getY() + 1);
        }
        hitbox.setPosition(getX(), getY());
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }
}
