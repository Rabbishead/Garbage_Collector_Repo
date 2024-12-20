package com.mygdx.animations;

import java.util.HashMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

public class MapComponentAnimationManager {

    private final HashMap<Integer, Animation<TextureRegion>> animationMap = new HashMap<>();

    private int currentAnimation;
    
    private TextureRegion currentFrame;

    private float stateTime;

    public MapComponentAnimationManager(ResourceEnum e, int width, int height, float animationRate){
        Texture animationSheet = Utils.getTexture(e);

        int FRAME_COLS = animationSheet.getWidth()/32 / width;
        int FRAME_ROWS = animationSheet.getHeight()/32 / height;

        TextureRegion[][] matrix = TextureRegion.split(animationSheet,
				animationSheet.getWidth() / FRAME_COLS,
				animationSheet.getHeight() / FRAME_ROWS);

        for (int i = 0; i < matrix.length; i++) {
            animationMap.put(i, new Animation<>(animationRate, matrix[i]));
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
     */
    public void updateAnimation(float delta){
        stateTime += delta;
        currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime, true);
    }

    public void setCurrentAnimation(int animationCode){
        currentAnimation = animationCode;
    }
}
