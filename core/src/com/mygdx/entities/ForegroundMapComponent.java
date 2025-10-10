package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.animations.AnimationManager;
import com.mygdx.controllers.hitboxes.Hitbox;
import com.mygdx.controllers.hitboxes.Tags;
import com.mygdx.resources.ResourceEnum;

public class ForegroundMapComponent extends GameActor {

    private Hitbox hitbox = new Hitbox();
    private float fade = 1;

    private final AnimationManager animationManager;

    public ForegroundMapComponent(ForegroundMapComponentBuilder builder) {
        super();
        setX(builder.coordinates.x);
        setY(builder.coordinates.y);

        animationManager = new AnimationManager(builder.width * 32, builder.animationRate, builder.delay, builder.textureEnum);

        if (builder.fade) {

            hitbox = new Hitbox(
                    new Vector2(getX() + builder.width * 16, getY() + 16 + builder.height * 16),
                    builder.width * 32, (builder.height - 1) * 32, true);
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

    public static class ForegroundMapComponentBuilder {
        protected Vector2 coordinates;
        protected ResourceEnum[] textureEnum;
        protected int width;
        protected int height;
        protected float animationRate = 0.1f;
        protected float delay = 0;
        protected ResourceEnum startingAnimation;
        protected boolean fade = false;

        public ForegroundMapComponentBuilder coordinates(Vector2 coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public ForegroundMapComponentBuilder texture(ResourceEnum... texture) {
            this.textureEnum = texture;
            return this;
        }

        public ForegroundMapComponentBuilder width(int width) {
            this.width = width;
            return this;
        }
        public ForegroundMapComponentBuilder height(int height) {
            this.height = height;
            return this;
        }

        public ForegroundMapComponentBuilder animationRate(float animationRate) {
            this.animationRate = animationRate;
            return this;
        }

        public ForegroundMapComponentBuilder delay(float delay) {
            this.delay = delay;
            return this;
        }

        public ForegroundMapComponentBuilder startingAnimation(ResourceEnum startingAnimation) {
            this.startingAnimation = startingAnimation;
            return this;
        }

        public ForegroundMapComponentBuilder fade() {
            this.fade = true;
            return this;
        }

        public ForegroundMapComponent build() {
            return new ForegroundMapComponent(this);
        }
    }
}
