package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.delay.DelayManager;
import com.mygdx.dialogues.BossDialogue;
import com.mygdx.hud.actors.Fps;
import com.mygdx.hud.actors.HealthBar;
import com.mygdx.states.StateManager;

public class Hud implements Disposable{
    private final Stage stage;
    private final Fps fps;
    private BossDialogue bossDialogue;

    public Hud(){
        FitViewport viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, new OrthographicCamera());
        stage = new Stage(viewport);
        fps = new Fps();
        stage.addActor(fps);
        HealthBar healthBar = new HealthBar();
        stage.addActor(healthBar);
    }
    public void draw(){
        stage.draw();
    }

    public void update() {
        stage.act();

        DelayManager.updateDelay(bossDialogue);

        if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) && StateManager.getState("pause").equals("true") && DelayManager.isDelayOver(bossDialogue)){
            StateManager.updateState("pause", "false");
            DelayManager.resetDelay(bossDialogue);
            removeDialogue();
        }
        if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT) && StateManager.getState("pause").equals("true") && DelayManager.isDelayOver(bossDialogue)){
            StateManager.updateState("pause", "false");
            DelayManager.resetDelay(bossDialogue);
            removeDialogue();
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        fps.dispose();
    }

    public void addComponent(Actor actor){
        stage.addActor(actor);
        if(actor instanceof BossDialogue){
            bossDialogue = (BossDialogue) actor;
            DelayManager.registerObject(bossDialogue, 100);  
        } 
    }

    public void removeDialogue(){
        stage.getActors().forEach(actor -> {
            if(actor instanceof BossDialogue){
                actor.remove();
                return;
            }
        });
    }
}
