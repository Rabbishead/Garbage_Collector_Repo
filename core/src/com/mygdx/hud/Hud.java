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
import com.mygdx.dialogues.ComplexDialogue;
import com.mygdx.hud.actors.Fps;
import com.mygdx.hud.actors.HealthBar;
import com.mygdx.states.StateManager;

public class Hud implements Disposable{
    private final Stage stage;
    private final Fps fps;
    private ComplexDialogue complexDialogue;

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

        DelayManager.updateDelay(complexDialogue);

        if(StateManager.getState("pause").equals("true") && DelayManager.isDelayOver(complexDialogue)){

            int numberOfChoices = complexDialogue.getNumberOfChoices();
            if(Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)){
                if(numberOfChoices>0)
                    complexDialogue.chose(0);

                if(complexDialogue.canContinue())
                    complexDialogue.continueDialogue();

                else {
                    StateManager.updateState("pause", "false");
                    DelayManager.resetDelay(complexDialogue);
                    removeDialogue();
                    System.out.println("Destroy");
                }
            }
            if(Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
                if(complexDialogue.canContinue()){
                    if(numberOfChoices>0) complexDialogue.chose(1);
                    complexDialogue.continueDialogue();

                }
                else {
                    System.out.println("Destroy");
                    StateManager.updateState("pause", "false");
                    DelayManager.resetDelay(complexDialogue);
                    removeDialogue();
                }
            }
        }
    }

    @Override
    public void dispose() {
        stage.dispose();
        fps.dispose();
    }

    public void addComponent(Actor actor){
        stage.addActor(actor);
        if(actor instanceof ComplexDialogue){
            complexDialogue = (ComplexDialogue) actor;
            DelayManager.registerObject(complexDialogue, 100);
        } 
    }

    public void removeDialogue(){
        stage.getActors().forEach(actor -> {
            if(actor instanceof ComplexDialogue){
                actor.remove();
                return;
            }
        });
    }
}
