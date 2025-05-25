package com.mygdx.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.resources.LangEnum;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.generic.gui.GuiScreen;

public class SettingsScreen extends GuiScreen {

    private final int row_height = Data.VIEWPORT_X / 24;
    private final int col_width = Data.VIEWPORT_Y / 24;

    public SettingsScreen() {
        super();
    }

    @Override
    public void show() {
        super.show();

        Image bg = new Image(Utils.getTexture(ResourceEnum.BACKGROUND_2));
        stage.addActor(bg);


        


        ImageButton playButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.PLAY_BUTTON))));
        ImageButton settingsButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.SETTINGS_BUTTON))));
        ImageButton quitButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.QUIT_BUTTON))));

        playButton.setPosition(Data.VIEWPORT_X - col_width * 2 -  playButton.getWidth(), row_height * 7);
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Utils.setScreen(SavingsManager.getLastRoom());
                return true;
            }
        });
        stage.addActor(playButton);

        settingsButton.setPosition(Data.VIEWPORT_X - col_width * 2 - settingsButton.getWidth(), row_height * 4);
        settingsButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Utils.setScreen(SavingsManager.getLastRoom());
                return true;
            }
        });
        stage.addActor(settingsButton);

        quitButton.setPosition(Data.VIEWPORT_X - col_width * 2 - quitButton.getWidth(), row_height);
        quitButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return true;
            }
        });
        stage.addActor(quitButton);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }
}
