package com.mygdx.gunControls.guns;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.resources.ResourceEnum;

public class Stone extends Actor {
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 movement = new Vector2();

    private Vector2 touch = new Vector2();
    private Vector2 dir = new Vector2();

    private float speed = 500;

    private Vector2 playerVector;
    private Texture t = Utils.getTexture(ResourceEnum.STONE);
    private Sprite sprite = new Sprite(t);

    public Stone(Vector2 playerVector, int mouseX, int mouseY) {
        Vector3 tmp = Utils.getStage().getCamera().unproject(new Vector3(mouseX, mouseY, 0));
        touch.set(tmp.x, tmp.y);
        position.set(playerVector.x, playerVector.y);
        dir.set(touch).sub(position).nor();

        this.playerVector = playerVector;

        setX(playerVector.x);
        setY(playerVector.y);

        setWidth(8);
        setHeight(8);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);

        sprite.setOrigin(getWidth()/2, getHeight()/2);

        DelayManager.registerObject(this, 150, e -> {
            this.remove();
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.draw(batch);
        sprite.rotate(10);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        velocity = new Vector2(dir).scl(speed);
        movement.set(velocity).scl(Gdx.graphics.getDeltaTime());
        position.add(movement);
        setX(position.x);
        setY(position.y);

        DelayManager.updateDelay(this);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        sprite.setX(getX());
        sprite.setY(getY());
    }
}
