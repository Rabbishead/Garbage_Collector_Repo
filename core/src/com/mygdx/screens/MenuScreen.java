package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.Utils;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.DialogueLoader.Languages;
import com.mygdx.game.GarbageCollection;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.ScreensManager.ScreenEnum;

public class MenuScreen extends ScreenAdapter {
    
    private final Stage stage;
    private ImageButton engButton;

    private ImageButton itaButton;

    private ImageButton playButton;

    private ImageButton fullScreenButton;


    public MenuScreen(){
        stage = new Stage();
        Utils.setStage(stage);
        DialogueLoader.setLang(Languages.ITALIAN);
    }

    @Override
    public void show() {


        stage.setViewport(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getWidth()));
        stage.getCamera().translate(Gdx.graphics.getWidth()/2, Gdx.graphics.getWidth()/3, 0);

        engButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ENGFLAG))));

        itaButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ITAFLAG))));

        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.PLAYBUTTON))));

        fullScreenButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ENGFLAG))));


        int row_height = Gdx.graphics.getWidth() / 24;
        int col_width = Gdx.graphics.getWidth() / 24;
 
        engButton.setSize(col_width*4,row_height);
        engButton.setPosition(col_width,Gdx.graphics.getHeight()-row_height*17);
        engButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.setLang(Languages.ENGLISH);
                return true;
            }
        });
        stage.addActor(engButton);

        itaButton.setSize(col_width*4,row_height);
        itaButton.setPosition(col_width*4,Gdx.graphics.getHeight()-row_height*17);
        itaButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.setLang(Languages.ITALIAN);
                return true;
            }
        });
        stage.addActor(itaButton);

        playButton.setSize(col_width*6,row_height);
        playButton.setPosition(Gdx.graphics.getHeight()/2 - playButton.getHeight()/2,Gdx.graphics.getWidth()/2 - playButton.getWidth()/2);
        playButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.loadFile();
                Utils.getGame().setScreen(ScreensManager.getScreen(ScreenEnum.MAIN_SCREEN));
                return true;
            }
        });
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage); 

        fullScreenButton.setSize(col_width*4,row_height);
        fullScreenButton.setPosition(col_width * 20,Gdx.graphics.getHeight()-row_height*17);
        fullScreenButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                toggleFullScreen();
                return true;
            }
        });
        stage.addActor(fullScreenButton);

    }
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, .25f, 0, 1);
        ScreenUtils.clear(1,1,1,0);

        stage.act(Gdx.graphics.getDeltaTime());

        stage.draw();
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        stage.getViewport().update(width, height); 

    }

    private void toggleFullScreen(){
        Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        if(Gdx.graphics.getHeight() == displayMode.height){
            Gdx.graphics.setWindowedMode(640, 480);
            Gdx.graphics.setUndecorated(false);
            return;
        }
        Gdx.graphics.setUndecorated(true);
        Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
    }
}
