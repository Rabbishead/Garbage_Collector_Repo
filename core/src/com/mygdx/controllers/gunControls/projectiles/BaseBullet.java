package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.hitboxes.Collider;

public class BaseBullet extends Actor {
    protected Vector2 pos;
    protected Vector2 movement;
    protected Vector2 origin;
    public Vector2 center = new Vector2();
    protected float distance, angle;
    protected Sprite s;
    protected Collider collider = new Collider();

    public BaseBullet(Texture t, Vector2 origin, float barrel, float speed, float distance, float rotation) {
        s = new Sprite(t);
        this.distance = distance;
        this.angle = rotation;
        this.origin = origin;

        setSize(s.getWidth(), s.getHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);

        pos = new Vector2(barrel, 0).setAngleDeg(rotation);
        pos.set(origin.x + pos.x - getOriginX(), origin.y + pos.y - getOriginY());

        Vector2 velocity = new Vector2(speed, 0).setAngleDeg(rotation);
        movement = new Vector2(velocity).scl(Gdx.graphics.getDeltaTime());

        s.setCenter(origin.x, origin.y);
        s.setOrigin(getOriginX(), getOriginY());
        s.setRotation(rotation);

        collider = new Collider(center, getWidth(), getHeight(), rotation, "projectile");
        collider.register();
        setPosition(pos.x, pos.y);
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
        if (origin.dst(pos) > distance)
            delete();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        s.setPosition(getX(), getY());
        center.x = getX() + getOriginX();
        center.y = getY() + getOriginY();
        collider.setPosition();
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
