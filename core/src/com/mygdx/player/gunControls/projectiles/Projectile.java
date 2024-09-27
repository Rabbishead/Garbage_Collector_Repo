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

    protected float speed = 500;

    protected Sprite sprite;

    protected Collider collider = new Collider();

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, float speed, int time) {
        sprite = new Sprite(t);
        
        float mX = Gdx.input.getX(), mY = Gdx.input.getY();
        Vector3 tmp = Utils.getStage().getCamera().unproject(new Vector3(mX, mY, 0));

        touch.set(tmp.x, tmp.y);
        position.set(nozzleX, nozzleY);
        dir.set(touch).sub(position);
        float angle = MathUtils.radiansToDegrees * MathUtils.atan2(dir.x, dir.y);
        if (angle < 0) angle+= 360;
        dir.nor();
        velocity = new Vector2(dir).scl(speed);
        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());

        sprite.setOrigin(getWidth() / 2, getHeight() / 2);
        
        sprite.setRotation(angle);
        System.out.println(sprite.getRotation() + "\n" + dir.x + " - " + dir.y);
        position.add(movement);

        setX(position.x);
        setY(position.y);
        setWidth(width);
        setHeight(height);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);
        
        collider = new Collider(getX(), getY(), getWidth(), getHeight(), 0, "projectile");
        Utils.getHitboxHandler().registerCollider(collider);
        DelayManager.registerObject(this, time, e -> {
            delete();
        });
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, int time) {
        this(t, width, height, nozzleX, nozzleY, 500, time);
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY, float speed) {
        this(t, width, height, nozzleX, nozzleY, speed, 150);
    }

    public Projectile(Texture t, float width, float height, float nozzleX, float nozzleY) {
        this(t, width, height, nozzleX, nozzleY, 500, 150);
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

        velocity = new Vector2(dir).scl(speed);
        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());
        position.add(movement);
        setX(position.x);
        setY(position.y);

        if (collider.isCollided())
            delete();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setX(getX());
        sprite.setY(getY());
        collider.setPosition(getX(), getY());
    }

    protected void delete() {
        this.remove();
        Utils.getHitboxHandler().unRegisterCollider(collider);
    }
}
