package com.mygdx.controllers.gunControls.projectiles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.controllers.hitboxes.Collider;
import com.mygdx.movement.BaseMovement;

public class BaseMelee extends Actor {
    protected Sprite s;
    protected BaseMovement movement;
    protected float angle, speed, end;
    private boolean flipped;
    public Vector2 center = new Vector2();
    protected Collider collider = new Collider();

    public BaseMelee(Texture t, Vector2 origin, float angle, float arc, float speed, boolean flipped) {
        s = new Sprite(t);

        this.angle = angle;
        this.speed = speed;
        this.flipped = flipped;

        if (flipped) {
            s.flip(false, true);
            arc = arc * -1;
            this.speed = speed * -1;
        }
        end = angle + arc;

        setSize(s.getWidth(), s.getHeight());
        setOrigin(getWidth() / 2, getHeight() / 2);

        movement = new BaseMovement(getOriginX(), getOriginY());
        movement.anchor(origin);
        s.setOrigin(movement.origin.x, movement.origin.y);

        collider = new Collider(origin, getWidth(), getHeight(), angle, "projectile");
        collider.register();
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
        Vector2 worldCoords = movement.getWorldCoords();
        setPosition(worldCoords.x, worldCoords.y);

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
        movement.x = getX();
        movement.y = getY();
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
        movement.offset(new Vector2(x, y));
        s.setOrigin(movement.origin.x, movement.origin.y);
        collider.setOffset(x, y);
    }
}
