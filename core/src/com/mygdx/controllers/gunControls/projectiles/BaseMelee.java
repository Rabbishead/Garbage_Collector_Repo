package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.hitboxes.Collider;

public class BaseMelee extends Actor {
    protected Sprite s;
    protected Vector2 pos, origin;
    protected float angle, arc, speed, end;
    private boolean flipped;
    public Vector2 center = new Vector2();
    protected Collider collider = new Collider();

    public BaseMelee(Texture t, Vector2 origin, float angle, float arc, float speed, boolean flipped) {
        s = new Sprite(t);

        this.origin = origin;
        this.angle = angle;
        this.arc = arc;
        this.speed = speed;
        this.flipped = flipped;

        if (flipped) {
            arc = arc * -1;
            this.speed = speed * -1;
        }
        end = angle + arc;

        setSize(s.getWidth(), s.getHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);

        pos = new Vector2(0, 0);
        s.setOrigin(-pos.x, -pos.y);

        collider = new Collider(origin, getWidth(), getHeight(), angle, "projectile");
        collider.register();

        collider.setOrigin(-pos.x, -pos.y);

        pos.set(pos.x - getWidth() / 2, pos.y - getHeight() / 2);
        this.debug();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        s.draw(batch);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(collider.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        setPosition(origin.x + pos.x, origin.y + pos.y);

        angle += speed * delta;

        s.setRotation(angle);
        collider.setRotation(angle);

        if (collider.isCollided())
            delete();

        if (flipped) {
            if (angle < end)
                delete();
        } else if (angle > end)
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

    public void setOffset(Vector2 offset) {
        setOffset(offset.x, offset.y);
    }

    public void setOffset(float x, float y) {
        pos = new Vector2(x, y);
        pos.set(pos.x - getWidth() / 2, pos.y - getHeight() / 2);
        s.setOrigin(-pos.x, -pos.y);
        collider.setOffset(x, y);
    }
}
