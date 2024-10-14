package com.mygdx.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.Data;

public class GenericScreen extends ScreenAdapter{
    protected Stage stage;
    protected Viewport viewport;
    protected OrthographicCamera camera;

    protected GenericScreen(){
        stage = new Stage();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, camera);
        stage.setViewport(viewport);
    }
    @Override
    public void show() {}
    @Override
    public void render(float delta) {}
    @Override
    public void dispose() {}
    @Override
    public void resize(int width, int height) {}
}
