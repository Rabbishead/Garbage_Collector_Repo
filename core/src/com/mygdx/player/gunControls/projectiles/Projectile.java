package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.hitboxes.Collider;

public class Projectile extends Actor {
    protected Vector2 position = new Vector2();
    protected Vector2 velocity = new Vector2();
    protected Vector2 movement = new Vector2();

    protected Vector2 touch = new Vector2();
    protected Vector2 dir = new Vector2();

    protected float speed;

    protected Sprite sprite;

    protected Collider collider = new Collider();

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, float speed, int time, float rotation) {
        sprite = new Sprite(t);
        this.speed = speed;

        setWidth(width);
        setHeight(height);
        setTouchable(Touchable.enabled);

        float mX = Gdx.input.getX(), mY = Gdx.input.getY();
        Vector3 tmp = Utils.getStage().getViewport().unproject(new Vector3(mX, mY, 0));

        touch.set(tmp.x, tmp.y);
        position.set(nozzleX, nozzleY);
        dir.set(touch).sub(position).nor();
        velocity = new Vector2(dir).scl(speed);
        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());
        position.add(movement);

        float angle = MathUtils.radiansToDegrees * MathUtils.atan2(dir.y, dir.x) + rotation;
        sprite.setOrigin(getWidth() / 2, getHeight() / 2);
        sprite.setRotation(angle);

        setX(position.x);
        setY(position.y);
        setBounds(getX(), getY(), getWidth(), getHeight());

        collider = new Collider(getX(), getY(), getWidth(), getHeight(), angle, "projectile");
        Utils.getHitboxHandler().registerCollider(collider);
        DelayManager.registerObject(this, time, e -> {
            delete();
        });
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, int time, float rotation) {
        this(t, width, height, nozzleX, nozzleY, 500, time, rotation);
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, float speed, float rotation) {
        this(t, width, height, nozzleX, nozzleY, speed, 150, rotation);
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, int time) {
        this(t, width, height, nozzleX, nozzleY, 500, time, 0);
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, float speed) {
        this(t, width, height, nozzleX, nozzleY, speed, 150, 0);
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY) {
        this(t, width, height, nozzleX, nozzleY, 500, 150, 0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(collider.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        DelayManager.updateDelay(this);

        position.add(movement);
        setX(position.x);
        setY(position.y);

        if (collider.isCollided())
            delete();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setPosition(getX(), getY());
        collider.setPosition(getX(), getY());
    }

    protected void delete() {
        this.remove();
        Utils.getHitboxHandler().unRegisterCollider(collider);
    }
}
