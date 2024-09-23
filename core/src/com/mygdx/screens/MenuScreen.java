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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.Utils;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.dialogues.DialogueLoader.Languages;
import com.mygdx.game.GarbageCollection;
import com.mygdx.resources.ResourceEnum;

public class MenuScreen extends ScreenAdapter {
    private final GarbageCollection game;
    
    private final Stage stage;
    private ImageButton engButton;

    private ImageButton itaButton;

    private ImageButton playButton;


    public MenuScreen(GarbageCollection game){
        /*Graphics.DisplayMode displayMode = Gdx.graphics.getDisplayMode();
        Gdx.graphics.setUndecorated(true);
        Gdx.graphics.setWindowedMode(displayMode.width, displayMode.height);*/
        this.game = game;
        stage = new Stage();
        Utils.setStage(stage);
        DialogueLoader.setLang(Languages.ITALIAN);

        
    }

    @Override
    public void show() {
        engButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ENGFLAG))));

        itaButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.ITAFLAG))));

        playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(Utils.getTexture(ResourceEnum.PLAYBUTTON))));


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
                game.setScreen(new MainScreen(game));
                return true;
            }
        });
        stage.addActor(playButton);
        Gdx.input.setInputProcessor(stage); 

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
}
