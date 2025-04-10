package com.mygdx.player.gunControls.projectiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class Stone extends BaseBullet {

    public Stone(Vector2 origin, float barrel) {
        super(Utils.getTexture(ResourceEnum.STONE), origin, barrel, 500f, 150f, 0f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        s.rotate(10);
    }
}
