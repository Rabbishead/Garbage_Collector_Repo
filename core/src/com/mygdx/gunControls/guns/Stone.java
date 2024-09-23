package com.mygdx.gunControls.guns;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Stone extends Actor {
    // Declared as fields, so they will be reused
    private Vector2 position = new Vector2();
    private Vector2 velocity = new Vector2();
    private Vector2 movement = new Vector2();

    private Vector2 touch = new Vector2();
    private Vector2 dir = new Vector2();

    // On touch events, set the touch vector, then do this to get the direction
    // vector
    private Vector2 trajectory;
    private Vector2 playerVector;
    private Texture t = Utils.getTexture(ResourceEnum.STONE);

    public Stone(Vector2 playerVector, int mouseX, int mouseY) {
        touch.set(mouseX, mouseY);
        this.playerVector = playerVector;

        dir.set(touch).sub(position).nor();
        velocity = new Vector2(dir).scl(2);
        movement.set(velocity).scl(1);
        position.add(movement);
        trajectory = new Vector2();
        trajectory.setAngleDeg(playerVector.angleDeg(trajectory));
        setX(playerVector.x);
        setY(playerVector.y);

        setWidth(8);
        setHeight(8);
        setBounds(getX(), getY(), getWidth(), getHeight());
        setTouchable(Touchable.enabled);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(t, getX(), getY());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        trajectory.scl(1.2f);
        setX(trajectory.x);
        setY(trajectory.y);
        System.out.println(trajectory.x + " " + trajectory.y);

        if (trajectory.dst(playerVector) > 5000)
            this.remove();
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }
}
