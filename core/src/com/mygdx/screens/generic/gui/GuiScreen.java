package com.mygdx.screens.generic.gui;

import com.badlogic.gdx.Gdx;
import com.mygdx.Data;
import com.mygdx.screens.generic.GenericScreen;

/**
 * generic class for every GUI  
 */
public abstract class GuiScreen extends GenericScreen{

    public GuiScreen(){
        super();
        camera.translate((float) Data.VIEWPORT_X /2, (float) Data.VIEWPORT_Y /2, 0);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
}
