package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.Utils;
import com.mygdx.hitboxes.Collider;
import com.mygdx.resources.ResourceEnum;

public class Bullet extends Projectile {

    private Collider collider = new Collider();

    public Bullet(float nozzleX, float nozzleY) {
        super(Utils.getTexture(ResourceEnum.BULLET), 6, 6, nozzleX, nozzleY, 1000f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
    }

    public void drawDebug(ShapeRenderer shapeRenderer) {
        shapeRenderer.polygon(collider.getTransformedVertices());
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    protected void positionChanged() {
        super.positionChanged();
    }
}
