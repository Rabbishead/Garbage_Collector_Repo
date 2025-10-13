package com.mygdx.entities;


import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;

import com.mygdx.animations.AnimationManager;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;
import com.mygdx.stage.GCStage;

public class ScriptableActor extends GameActor{
    protected Script script;
    public MSG listeningMSG;
    protected AnimationManager animationManager;

    public void doScript(ResourceEnum s){
        script = new Script(s);
        script.proceed(this);
    }

    public void move(float x, float y, boolean relative){
        if(relative) moveTo(getCoords().add(new Vector2(x, y)));
        else moveTo(x, y);
    }

    public void proceed(){
        script.proceed(this);
    }

    public void changeAnimation(ResourceEnum e, float time){
        animationManager.setCurrentAnimation(e);
        script.proceed(this);
    }

    public void listen(MSG msg){
        GCStage.get().subscribe(this, msg);
        this.listeningMSG = msg;
    }

    @Override
    public boolean handleMessage(Telegram msg) {
        super.handleMessage(msg);
        if(listeningMSG != null)
            if(listeningMSG.code == msg.message){
                resetListen();
            } 
        
        return true;
    }

    public void resetListen(){
        GCStage.get().unSubscribe(this, listeningMSG);
        listeningMSG = null;
        proceed();
    }
    
    public boolean hasScript(){
        return script != null;
    }
}