package com.mygdx.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.animations.MapComponentAnimationManager;
import com.mygdx.hitboxes.Hitbox;
import com.mygdx.resources.ResourceEnum;

public class ForegroundMapComponent extends GameActor {

    private Hitbox hitbox = new Hitbox();

    private final MapComponentAnimationManager animationManager;

    public ForegroundMapComponent(ForegroundMapComponentBuilder builder) {
        super();
        setX(builder.coordinates.x);
        setY(builder.coordinates.y);

        // this.debug();

        animationManager = new MapComponentAnimationManager(builder.textureEnum, builder.singlePieceWidth,
                builder.singlePieceHeight, 5f);

        // hitbox = new Hitbox(getX() + getWidth() * 0.4f, getY(), 8, 24, 0, true,
        // "enemy,npc");
        // hitbox.setOnHit((hitbox, collider) -> {});

        hitbox.register();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(animationManager.getCurrentFrame(), getX(), getY());
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(hitbox.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        animationManager.setCurrentAnimation(0);
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

        public ForegroundMapComponent build() {
            return new ForegroundMapComponent(this);
        }
    }
}
