package com.mygdx.animations;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class MapComponentAnimationManager {

    private HashMap<Integer, Animation<TextureRegion>> animationMap = new HashMap<>();

    private int currentAnimation;
    
    private TextureRegion currentFrame;

    private float stateTime;

    public MapComponentAnimationManager(ResourceEnum e){
        Texture animationSheet = Utils.getTexture(e);

        int FRAME_COLS = animationSheet.getWidth()/32;
        int FRAME_ROWS = animationSheet.getHeight()/32;

        TextureRegion[][] tmp = TextureRegion.split(animationSheet,
				animationSheet.getWidth() / FRAME_COLS,
				animationSheet.getHeight() / FRAME_ROWS);

        //rotates matrix
        for(int i=0; i < tmp.length; i++)  {  
            for(int j = i; j < tmp.length; j++)  {  
                if(i == j) continue;  
                  
                TextureRegion temp = tmp[i][j];  
                tmp[i][j] = tmp[j][i];  
                tmp[j][i] = temp;  
            }
        }

        for (int i = 0; i < tmp.length; i++) {
            animationMap.put(i, new Animation<TextureRegion>(0.3f, tmp[i]));
        }

        currentAnimation = 0;

		stateTime = 0f;
    }

    /**
     * @return currant frame in the animation
     */
    public TextureRegion getCurrentFrame() {
        return currentFrame;
    }

    /**
     * updates currentFrame state
     * @param delta
     */
    public void updateAnimation(float delta){
        stateTime += delta;
        currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime, true);
    }

    public void setCurrentAnimation(int animationCode){
        currentAnimation = animationCode;
    }
}
