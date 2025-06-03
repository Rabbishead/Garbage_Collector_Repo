package com.mygdx.screens.generic.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.mygdx.screens.generic.GenericScreen;

/**
 * generic class for every GUI
 */
public abstract class GuiScreen extends GenericScreen {

    protected Table table;
    protected Skin skin;

    public GuiScreen() {
        super();

        table = new Table();
        table.setFillParent(true);
        table.defaults().expand();
        stage.addActor(table);

        skin = new Skin(Gdx.files.internal("uiskin.json"));

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
