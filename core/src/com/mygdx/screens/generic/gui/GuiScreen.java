package com.mygdx.screens.generic.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.screens.generic.GenericScreen;

/**
 * generic class for every GUI
 */
public abstract class GuiScreen extends GenericScreen {

    protected Table table;

    public GuiScreen() {
        super();

        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true);
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        stage.act(delta);

        stage.draw();
    }
}
