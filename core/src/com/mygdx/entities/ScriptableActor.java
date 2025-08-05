package com.mygdx.entities;

import com.badlogic.gdx.ai.msg.Telegram;
import com.mygdx.Utils;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;

public abstract class ScriptableActor extends GameActor{
    protected Script script;
    protected MSG listeningMSG;

    public void doScript(ResourceEnum s){
        script = new Script(s);
        script.proceed(this);
    }

    public void move(float x, float y){
        moveTo(x, y);
    }

    public void proceed(){
        script.proceed(this);
    }

    public abstract void changeAnimation(ResourceEnum e);

    public void listen(MSG msg){
        Utils.subscribeToStageMsg(this, msg);
        this.listeningMSG = msg;
        System.out.println(listeningMSG);
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        super.handleMessage(msg);
        System.out.println(msg.message);
        if(listeningMSG != null)
            if(listeningMSG.code == msg.message){
                
                proceed();
            } 
        
        return true;
    }
    
}