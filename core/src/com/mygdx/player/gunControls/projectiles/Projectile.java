package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.hitboxes.Collider;

public class Projectile extends Actor {
    protected Vector2 pos;
    protected Vector2 movement;
    protected Vector2 anchor;
    protected float distance, angle;
    protected Sprite s;
    protected Collider collider = new Collider();

    public Projectile(Texture t, float barrel, float speed, float distance, float rotation) {
        s = new Sprite(t);
        this.distance = distance;
        this.angle = rotation;
        anchor = new Vector2(Utils.getPlayer().center);

        setWidth(s.getWidth());
        setHeight(s.getHeight());

        pos = new Vector2(barrel, 0).setAngleDeg(rotation);
        pos.set(anchor.x + pos.x - getWidth() / 2, anchor.y + pos.y - getHeight() / 2);
        setPosition(anchor.x + pos.x, anchor.y + pos.y);

        Vector2 velocity = new Vector2(speed, 0).setAngleDeg(rotation);
        movement = new Vector2(velocity).scl(Gdx.graphics.getDeltaTime());
        
        s.setCenter(anchor.x, anchor.y);
        s.setOrigin(getWidth() / 2, getHeight() / 2);
        s.setRotation(rotation - 90);

        collider = new Collider(getX(), getY(), getWidth(), getHeight(), rotation - 90, "projectile");
        collider.register();
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
        collider.unregister();
    }

    public void flip(boolean x, boolean y) {
        s.flip(x, y);
    }

    public void setOffset(Vector2 offset) {
        setOffset(offset.x, offset.y);
    }

    public void setOffset(float x, float y) {
        Vector2 tmp = new Vector2(1, 0).setAngleDeg(angle).scl(x, y);
        pos.add(tmp);
        setPosition(pos.x, pos.y);
    }
}
