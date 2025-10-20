package com.mygdx.controllers.dialogues;

import com.bladecoder.ink.runtime.Story;
import com.mygdx.Money;
import com.mygdx.entities.ScriptableActor;
import com.mygdx.resources.ResourceEnum;

public class GameStory {
    private Story story;
    private ScriptableActor currentActor;

    public GameStory(String inkJsonText) {
        try {
            story = new Story(inkJsonText);
            story.bindExternalFunction("DO", (Object[] args) -> {
                if (currentActor == null)
                    return null;
                String name = String.valueOf(args[0]);
                currentActor.doScript(ResourceEnum.valueOf(name));
                return null;
            });
            story.bindExternalFunction("GAIN", (Object[] args) -> {
                Money.gain(((int)args[0]));
                return null;
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setActor(ScriptableActor actor) {
        currentActor = actor;
    }

    public ScriptableActor getCurrentActor() {
        return currentActor;
    }

    public Story getStory() {
        return story;
    }

}
