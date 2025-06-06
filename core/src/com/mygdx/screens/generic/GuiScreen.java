package com.mygdx.screens.generic;

import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * generic class for every GUI
 */
public abstract class GuiScreen extends GenericScreen {

    protected Table table;

    public GuiScreen() {
        super();

        table = new Table();
        table.setFillParent(true);
        table.defaults().expand();
        stage.addActor(table);

        //table.setDebug(true);
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
