package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.Utils;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.controllers.messages.MsgManager;
import com.mygdx.resources.ResourceEnum;

public abstract class GameActor extends Actor implements Telegraph {

    public GameActor(){

    }

    public GameActor(MSG... msgs){
        for (MSG msg : msgs) {
            Utils.getActiveScreen().subscribe(this, msg);
        }
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        return true;
    }

    public abstract void move(float x, float y);
    public abstract void changeAnimation(ResourceEnum e);
    public void send(MSG... msgs){
        for (MSG msg : msgs) {
            MsgManager.sendStageMsg(msg);
        }
    }
    public abstract void wait(float time);
}
