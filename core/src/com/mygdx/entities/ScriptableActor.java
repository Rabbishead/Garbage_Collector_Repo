package com.mygdx.entities;


import com.badlogic.gdx.ai.msg.Telegram;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.Utils;
import com.mygdx.animations.ActorAnimationManager;
import com.mygdx.controllers.messages.MSG;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.scripts.Script;

public abstract class ScriptableActor extends GameActor{
    protected Script script;
    public MSG listeningMSG;
    protected ActorAnimationManager animationManager;

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
        animationManager.setOtherAnimation(e, time, (int)getWidth(), (int)getHeight());
        script.proceed(this);
    }

    public void listen(MSG msg){
        Utils.subscribeToStageMsg(this, msg);
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
        Utils.getActiveScreen().unSubscribe(this, listeningMSG);
        listeningMSG = null;
        proceed();
    }
    
    public boolean hasScript(){
        return script != null;
    }
}