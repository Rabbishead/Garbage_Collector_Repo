package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Stone extends Projectile {

    public Stone(float nozzleX, float nozzleY) {
        super(Utils.getTexture(ResourceEnum.STONE), 6, 6, nozzleX, nozzleY, 500f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.rotate(10);
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
