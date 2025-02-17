package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.hitboxes.Collider;
import com.mygdx.player.camera.CameraController;

public class Projectile extends Actor {
    protected Vector2 pos;
    protected Vector2 movement;
    protected Vector2 anchor;

    protected float speed, distance;

    protected Sprite s;

    protected Collider collider = new Collider();

    public Projectile(Texture t, float barrel, float speed, float distance, float rotation) {
        s = new Sprite(t);
        this.speed = speed;
        this.distance = distance;

        setWidth(s.getWidth());
        setHeight(s.getHeight());
        setTouchable(Touchable.enabled);

        pos = new Vector2(1, 0).scl(barrel);
        pos.set(pos.x - getWidth() / 2, pos.y - getHeight() / 2);

        Vector2 velocity = new Vector2(CameraController.getMouseDirection()).scl(speed);
        movement = new Vector2(velocity).scl(Gdx.graphics.getDeltaTime());

        anchor = new Vector2(Utils.getPlayer().center);
        pos.add(anchor.x, anchor.y);
        s.setOrigin(getWidth() / 2, getHeight() / 2);
        s.setRotation(rotation);

        setX(pos.x);
        setY(pos.y);

        collider = new Collider(getX(), getY(), getWidth(), getHeight(), rotation, "projectile");
        Utils.getHitboxHandler().registerCollider(collider);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        s.draw(batch);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(collider.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        pos.add(movement);
        setX(pos.x);
        setY(pos.y);

        if (collider.isCollided())
            delete();
        if (anchor.dst(pos) > distance)
            delete();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        s.setPosition(getX(), getY());
        collider.setPosition(getX(), getY());
    }

    protected void delete() {
        this.remove();
        Utils.getHitboxHandler().unRegisterCollider(collider);
    }
}
