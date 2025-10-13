package com.mygdx.animations;

import java.util.EnumMap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.entities.GameActor;
import com.mygdx.resources.RM;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.TextureEnum;
import com.mygdx.stage.GCStage;

public class AnimationManager {
    private EnumMap<ResourceEnum, Animation<TextureRegion>> animationMap = new EnumMap<>(ResourceEnum.class);

    private ResourceEnum currentAnimation;

    private TextureRegion currentFrame;

    // Incremented each frame by delta
    // Allows you to pick the correct frame in an animation
    private float stateTime = 0f;

    private boolean paused;
    private boolean alreadyPausedOnce;

    private float defaultDelay;
    private float currentDelay;

    private GameActor pauser = new GameActor();

    public AnimationManager(int width, float animationRate, float delay, ResourceEnum... textures) {
        for (ResourceEnum e : textures) {
            Texture texture = RM.get().getTexture(e);
            int FRAME_COLS = texture.getWidth() / width;

            TextureRegion[][] matrix = TextureRegion.split(texture,
                    texture.getWidth() / FRAME_COLS,
                    texture.getHeight() / 1);

            animationMap.put(e, new Animation<>(e.animationRate != -1 ? e.animationRate : animationRate, matrix[0]));
            animationMap.get(e).setPlayMode(PlayMode.LOOP);

        }
        currentAnimation = textures[0];

        stateTime = 0f;

        defaultDelay = delay;
        currentDelay = currentAnimation.delay != -1 ? currentAnimation.delay : defaultDelay;

        paused = false;
        alreadyPausedOnce = false;

        GCStage.get().addActor(pauser);
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
        pauser.act(delta);
        
        if (paused)
            return;

        stateTime += delta; // Advances the animation
        // Conditions to pause:
        // currentDelay is not zero, the option to pause was selected
        // frame index is zero, we are on the first frame of the animation
        // we didn't already paused this animation
        if (currentDelay != 0 && animationMap.get(currentAnimation).getKeyFrameIndex(stateTime) == 0
                && !alreadyPausedOnce) {
            pauser.addAction(
                    Actions.sequence(
                            Actions.delay(currentDelay),
                            Actions.run(() -> {
                                paused = false;
                                stateTime = 0;
                            })));
            alreadyPausedOnce = true;
            paused = true;
        }

        // when we move on from frame on
        if (animationMap.get(currentAnimation).getKeyFrameIndex(stateTime) != 0)
            alreadyPausedOnce = false;

        currentFrame = animationMap.get(currentAnimation).getKeyFrame(stateTime, true);
    }

    public void setCurrentAnimation(ResourceEnum ani) {
        currentAnimation = ani;
        currentDelay = ani.delay != -1 ? ani.delay : defaultDelay;
        pauser.clearActions();
    }
}
