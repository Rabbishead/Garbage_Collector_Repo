package com.mygdx.screens.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.Data;
import com.mygdx.Utils;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.DialogueLoader.Languages;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.generic.gui.GuiScreen;

public class PauseScreen extends GuiScreen {

    public PauseScreen() {
        super();
    }

    @Override
    public void show() {
        super.show();

        ImageButton engButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ENGFLAG))));

        ImageButton itaButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ITAFLAG))));

        ImageButton playButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.PLAYBUTTON))));

        ImageButton fullScreenButton = new ImageButton(
                new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ENGFLAG))));

        int row_height = Data.VIEWPORT_X / 24;
        int col_width = Data.VIEWPORT_Y / 24;

        engButton.setSize(col_width * 4, row_height);
        engButton.setPosition(col_width * 2, row_height);
        engButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.setLang(Languages.ENGLISH);
                return true;
            }
        });
        stage.addActor(engButton);

        itaButton.setSize(col_width * 4, row_height);
        itaButton.setPosition(col_width * 6, row_height);
        itaButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.setLang(Languages.ITALIAN);
                return true;
            }
        });
        // stage.addActor(itaButton);

        playButton.setSize(col_width * 6, row_height);
        playButton.setPosition(col_width * 24 - playButton.getWidth(), row_height * 12 - playButton.getHeight());
        playButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.loadFile();
                Utils.setScreen(ScreensManager.getScreen(ScreensManager.getLastPlayableActiveScreen()));
                return true;
            }
        });
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage);

        fullScreenButton.setSize(col_width * 4, row_height);
        fullScreenButton.setPosition(col_width * 32, row_height);
        fullScreenButton.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                toggleFullScreen();
                return true;
            }
        });
        stage.addActor(fullScreenButton);
    }

    @Override
    public void render(float delta) {
        super.render(delta);
    }

    private void toggleFullScreen() {
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        if (Gdx.graphics.getHeight() == displayMode.height) {
            Gdx.graphics.setWindowedMode(Data.VIEWPORT_X, Data.VIEWPORT_Y);
            Gdx.graphics.setUndecorated(false);
            return;
        }
        Gdx.graphics.setUndecorated(true);
        Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
    }
}
