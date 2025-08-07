package com.mygdx.scripts;

import com.mygdx.entities.ScriptableActor;
import com.mygdx.resources.ResourceEnum;

public class AniAction implements ScriptAction{
    ResourceEnum e;


    public AniAction(ResourceEnum e) {
        this.e = e;
    }


    @Override
    public void perform(ScriptableActor actor) {
        actor.changeAnimation(e, 0.3f);
    }
}
