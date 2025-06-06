package com.mygdx.hud;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.mygdx.Data;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.controllers.dialogues.ComplexDialogue;
import com.mygdx.hud.actors.DebugData;
import com.mygdx.hud.actors.Fps;
import com.mygdx.hud.actors.HealthBar;

public class Hud implements Disposable {
    private final Stage stage;
    private final Fps fps;
    private ComplexDialogue complexDialogue;

    private DebugData debugData;

    public Hud() {
        FitViewport viewport = new FitViewport(Data.VIEWPORT_X, Data.VIEWPORT_Y, new OrthographicCamera());
        stage = new Stage(viewport);
        fps = new Fps();
        stage.addActor(fps);
        HealthBar healthBar = new HealthBar();
        stage.addActor(healthBar);

        debugData = new DebugData();

        stage.addActor(debugData);
    }

    public void draw() {
        stage.draw();
    }

    public void update() {
        stage.act(Gdx.graphics.getDeltaTime());

        DelayManager.updateDelay(complexDialogue);
    }

    @Override
    public void dispose() {
        stage.dispose();
        fps.dispose();
    }

    public void addComponent(Actor actor) {
        stage.addActor(actor);
        if (actor instanceof ComplexDialogue) {
            complexDialogue = (ComplexDialogue) actor;
            DelayManager.registerObject(complexDialogue, 100f);
        }
    }

    public void removeDialogue() {
        stage.getActors().forEach(actor -> {
            if (actor instanceof ComplexDialogue) {
                actor.remove();
            }
        });
    }

    public void setDebugSting(String debugSting) {
        debugData.setText(debugSting);
    }

    public Stage getStage() {
        return stage;
    }
}
