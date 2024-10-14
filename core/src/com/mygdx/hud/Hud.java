package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Disableable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.entities.TestActor;
import com.mygdx.hud.actors.Fps;
import com.mygdx.hud.actors.HealthBar;

public class Hud implements Disposable{
    private Stage stage;
    private FitViewport viewport;
    private Fps fps;
    private HealthBar healthBar;

    public Hud(){
        viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, new OrthographicCamera());
        stage = new Stage(viewport);
        fps = new Fps();
        stage.addActor(fps);
        healthBar = new HealthBar();
        stage.addActor(healthBar);
    }
    public void draw(){
        stage.draw();
    }

    public void update() {
        stage.act();
    }

    @Override
    public void dispose() {
        stage.dispose();
        fps.dispose();
    }
}
