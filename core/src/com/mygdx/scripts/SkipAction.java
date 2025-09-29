package com.mygdx.scripts;

import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.mygdx.entities.ScriptableActor;

public class SkipAction implements ScriptAction{

    @Override
    public void perform(ScriptableActor actor) {
        actor.addAction(Actions.sequence(
            Actions.delay(0, //skips a frame
            Actions.run(() -> actor.proceed()))
        ));
    }
}