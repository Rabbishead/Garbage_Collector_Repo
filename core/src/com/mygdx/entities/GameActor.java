package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.ai.msg.Telegraph;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class GameActor extends Actor implements Telegraph {

    @Override
    public boolean handleMessage(Telegram msg) {
        System.out.println(msg.message);
        return true;
    }
}
