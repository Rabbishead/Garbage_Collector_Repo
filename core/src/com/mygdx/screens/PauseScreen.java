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
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Utils;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.DialogueLoader.Languages;
import com.mygdx.player.camera.CameraController;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.screens.ScreensManager.ScreenEnum;

public class PauseScreen extends ScreenAdapter{
    private final Stage stage;
    private Viewport viewport;
    private OrthographicCamera camera;

    private ImageButton fullScreenButton;

    private ImageButton engButton;

    private ImageButton itaButton;

    private ImageButton playButton;


    public PauseScreen(){
        stage = new Stage();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Utils.VIEWPORT_X, Utils.VIEWPORT_Y, camera);
        stage.setViewport(viewport);
        camera.translate(Utils.VIEWPORT_X/2, Utils.VIEWPORT_Y/2, 0);
    }

    @Override
    public void show() {
        Utils.setStage(stage);
        CameraController.setGameCamera(camera);
        
        engButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ENGFLAG))));

        itaButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ITAFLAG))));

        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.PLAYBUTTON))));

        fullScreenButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ENGFLAG))));


        int row_height = Utils.VIEWPORT_X / 24;
        int col_width = Utils.VIEWPORT_Y / 24;
 
        engButton.setSize(col_width*4,row_height);
        engButton.setPosition(col_width*2, row_height);
        engButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.setLang(Languages.ENGLISH);
                return true;
            }
        });
        stage.addActor(engButton);

        itaButton.setSize(col_width*4,row_height);
        itaButton.setPosition(col_width*6,row_height);
        itaButton.addListener(new InputListener(){
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                DialogueLoader.setLang(Languages.ITALIAN);
                return true;
            }
        });
        //stage.addActor(itaButton);

        playButton.setSize(col_width*6,row_height);
        playButton.setPosition(col_width*24 - playButton.getWidth(), row_height * 12 - playButton.getHeight());
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
        fullScreenButton.setPosition(col_width * 32,row_height);
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
            Gdx.graphics.setWindowedMode(Utils.VIEWPORT_X, Utils.VIEWPORT_Y);
            Gdx.graphics.setUndecorated(false);
            return;
        }
        Gdx.graphics.setUndecorated(true);
        Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);
    }
}
