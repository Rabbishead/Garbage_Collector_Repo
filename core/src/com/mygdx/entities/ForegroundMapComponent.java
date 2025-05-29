package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.animations.MapComponentAnimationManager;
import com.mygdx.controllers.hitboxes.Hitbox;
import com.mygdx.resources.ResourceEnum;

public class ForegroundMapComponent extends GameActor {

    private Hitbox hitbox = new Hitbox();
    private float fade = 1;

    private final MapComponentAnimationManager animationManager;

    public ForegroundMapComponent(ForegroundMapComponentBuilder builder) {
        super();
        setX(builder.coordinates.x);
        setY(builder.coordinates.y);

        animationManager = new MapComponentAnimationManager(builder.textureEnum, builder.singlePieceWidth,
                builder.singlePieceHeight, builder.animationRate, builder.delay);

        animationManager.setCurrentAnimation(builder.startingAnimationCode);

        if (builder.fade) {
            
            hitbox = new Hitbox(
                    new Vector2(getX() + builder.singlePieceWidth * 16, getY() + builder.singlePieceHeight * 16),
                    builder.singlePieceWidth * 32, builder.singlePieceHeight * 32, 0, "building", true);
            hitbox.setOnHit((hitbox, collider) -> {
                fade = 0.2f;
            });
            hitbox.setOnLeave((hitbox, collider) -> {
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
        protected ResourceEnum textureEnum;
        protected int singlePieceWidth;
        protected int singlePieceHeight;
        protected float animationRate = 1;
        protected float delay = 0;
        protected int startingAnimationCode = 0;
        protected boolean fade = false;

        public ForegroundMapComponentBuilder coordinates(Vector2 coordinates) {
            this.coordinates = coordinates;
            return this;
        }

        public ForegroundMapComponentBuilder texture(ResourceEnum texture) {
            this.textureEnum = texture;
            return this;
        }

        public ForegroundMapComponentBuilder singlePieceWidth(int singlePieceWidth) {
            this.singlePieceWidth = singlePieceWidth;
            return this;
        }

        public ForegroundMapComponentBuilder singlePieceHeight(int singlePieceHeight) {
            this.singlePieceHeight = singlePieceHeight;
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

        public ForegroundMapComponentBuilder startingAnimationCode(int startingAnimationCode) {
            this.startingAnimationCode = startingAnimationCode;
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
