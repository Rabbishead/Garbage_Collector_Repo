package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.Utils;
import com.mygdx.hitboxes.Collider;
import com.mygdx.resources.ResourceEnum;

public class Stone extends Bullet {

    private Collider collider = new Collider();

    public Stone(float nozzleX, float nozzleY) {
        super(Utils.getTexture(ResourceEnum.STONE), 8, 8, nozzleX, nozzleY, 500);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.rotate(10);
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
