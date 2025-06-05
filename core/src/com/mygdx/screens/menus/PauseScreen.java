package com.mygdx.screens.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.generic.GuiScreen;

public class PauseScreen extends GuiScreen {

    public PauseScreen() {
        super();
    }

    @Override
    public void show() {
        super.show();

        var bg = new Image(Utils.getTexture(ResourceEnum.BACKGROUND_2));
        stage.getActors().insert(0, bg);

        var pauseLabel = new Label("PAUSED", Data.skin);
        table.add(pauseLabel).top();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    
}
