package com.mygdx.entities.mapComponents;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.AnimationManager;
import com.mygdx.entities.helpers.GameActor;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.hitboxes.Tags;

public class MapComponent extends GameActor {

    private Hitbox hitbox = new Hitbox();
    private float fade = 1;

    private final AnimationManager animationManager;

    public MapComponent(MapComponentBuilder builder) {
        super();
        setX(builder.coordinates.x);
        setY(builder.coordinates.y);

        animationManager = new AnimationManager((int)builder.width, builder.animationRate, builder.delay, false, builder.textureEnum);

        if (builder.fade) {

            hitbox = new Hitbox(
                    new Vector2(getX() + builder.width * 0.5f, getY() + 16 + builder.height * 0.5f),
                    builder.width, builder.height - 32, true);
            hitbox.setTags(Tags.BUILDING);
            hitbox.setOnHit((collider) -> {
                fade = 0.2f;
            });
            hitbox.setOnLeave((collider) -> {
                fade = 1;
            });
            hitbox.register();
            this.debug();
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.setColor(1, 1, 1, fade);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
        batch.setColor(1, 1, 1, 1);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        animationManager.updateAnimation(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
        hitbox.setPosition(getX(), getY());
    }
}
