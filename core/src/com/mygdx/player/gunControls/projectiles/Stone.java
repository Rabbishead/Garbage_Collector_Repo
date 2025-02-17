package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Stone extends Projectile {

    public Stone(float barrel) {
        super(Utils.getTexture(ResourceEnum.STONE), barrel, 500f, 150f, 0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        sprite.rotate(10);
    }
}
