package com.mygdx.animations;

import java.util.EnumMap;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Utils;
import com.mygdx.controllers.delay.DelayManager;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;

/**
 * Class useful to manage actors' animations
 */
public class AnimationManager {
    private EnumMap<ResourceEnum, Animation<TextureRegion>> animationMap = new EnumMap<>(ResourceEnum.class);

    private ResourceEnum currentAnimation;

    private TextureRegion currentFrame;

    private float stateTime = 0f; // changes with delta

    private float prevAnimation;

    private boolean paused;
    private boolean alreadyPausedOnce;

    public AnimationManager(int width, float animationRate, float delay, ResourceEnum... textures) {
        for (ResourceEnum e : textures) {
            Texture texture = Utils.getTexture(e);
            int FRAME_COLS = texture.getWidth() / width;

            TextureRegion[][] matrix = TextureRegion.split(texture,
                    texture.getWidth() / FRAME_COLS,
                    texture.getHeight() / 1);

            animationMap.put(e, new Animation<>(animationRate, matrix[0]));
            animationMap.get(e).setPlayMode(PlayMode.LOOP);

        }
        currentAnimation = textures[0];

        stateTime = 0f;

        prevAnimation = 0;

        DelayManager.registerObject(this, delay);

        paused = false;
    }

    public AnimationManager(int width, float animationRate, float delay, TextureEnum textures) {
        this(width, animationRate, delay, textures.getResourceList());
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

        if (paused) {
            stateTime -= delta;
            DelayManager.updateDelay(this);
            alreadyPausedOnce = true;

            if (DelayManager.isDelayOver(this)) {
                paused = false;
            }
            return;
        }

        if (animationMap.get(currentAnimation).getKeyFrameIndex(stateTime) == 0 && prevAnimation != 0
                && !alreadyPausedOnce) {
            currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime, true);
            DelayManager.resetDelay(this);
            paused = true;
            stateTime = 0;
            return;
        }

        if (animationMap.get(currentAnimation).getKeyFrameIndex(stateTime) != 0) {
            paused = false;
            alreadyPausedOnce = false;
        }

        currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime, true);
    }

    public void setCurrentAnimation(ResourceEnum ani) {
        currentAnimation = ani;
    }
}
