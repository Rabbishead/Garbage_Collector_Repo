package com.mygdx.animations;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.mygdx.Utils;
import com.mygdx.delay.DelayManager;
import com.mygdx.resources.ResourceEnum;

public class MapComponentAnimationManager {

    private final HashMap<Integer, Animation<TextureRegion>> animationMap = new HashMap<>();

    private int currentAnimation;

    private TextureRegion currentFrame;

    private float stateTime;

    private  float prevAnimation;

   private boolean paused;
   private boolean alreadyPausedOnce;

    public MapComponentAnimationManager(ResourceEnum e, int width, int height, float animationRate, float delay) {
        Texture animationSheet = Utils.getTexture(e);

        int FRAME_COLS = animationSheet.getWidth() / 32 / width;
        int FRAME_ROWS = animationSheet.getHeight() / 32 / height;

        TextureRegion[][] matrix = TextureRegion.split(animationSheet,
                animationSheet.getWidth() / FRAME_COLS,
                animationSheet.getHeight() / FRAME_ROWS);

        for (int i = 0; i < matrix.length; i++) {
            animationMap.put(i, new Animation<>(animationRate, matrix[i]));
            animationMap.get(i).setPlayMode(PlayMode.LOOP);
        }

        currentAnimation = 0;

        stateTime = 0f;

        prevAnimation = 0;

        DelayManager.registerObject(this, delay);

        paused = false;
    }
    /**
     * @return currant frame in the animation
     */
    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    /**
     * updates currentFrame state
     */
    public void updateAnimation(float delta) {
        prevAnimation = animationMap.get(currentAnimation).getKeyFrameIndex(stateTime);
        stateTime += delta;

        if(paused){
            stateTime -= delta;
            DelayManager.updateDelay(this);
            alreadyPausedOnce = true;

            if(DelayManager.isDelayOver(this)){
                paused = false;
            }
            return;
        }

        if(animationMap.get(currentAnimation).getKeyFrameIndex(stateTime) == 0 && prevAnimation != 0 && !alreadyPausedOnce){
            currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime, true);
            DelayManager.resetDelay(this);
            paused = true;
            stateTime = 0;
            return;
        }

        if(animationMap.get(currentAnimation).getKeyFrameIndex(stateTime) != 0){
            paused = false;
            alreadyPausedOnce = false;
        } 
        
        currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime, true);
    }

    public void setCurrentAnimation(int animationCode) {
        currentAnimation = animationCode;
    }
}
