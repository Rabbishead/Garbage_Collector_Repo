package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.Utils;
import com.mygdx.resources.ResourceEnum;

/**
 * Class useful to manage actors' animations
 */
public class ActorAnimationManager {
    private final Animation<TextureRegion> idleDown;
    private final Animation<TextureRegion> idleRight;
    private final Animation<TextureRegion> idleLeft;
    private final Animation<TextureRegion> idleUp;
    private final Animation<TextureRegion> walkDownAnimation;
    private final Animation<TextureRegion> walkUpAnimation;
    private final Animation<TextureRegion> walkRightAnimation;
    private final Animation<TextureRegion> walkLeftAnimation;

    private Animation<TextureRegion> otherAnimation;
    private Animation<TextureRegion> currentAnimation;

    private TextureRegion currentFrame;

    private float stateTime; // changes with delta

    public ActorAnimationManager(ResourceEnum e) {
        Texture walkSheet = Utils.getTexture(e);

        int FRAME_COLS = 7;
        int FRAME_ROWS = 3;

        TextureRegion[][] tmp = TextureRegion.split(walkSheet,
                walkSheet.getWidth() / FRAME_COLS,
                walkSheet.getHeight() / FRAME_ROWS);

        TextureRegion[] idleDownFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] idleRightFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] idleLeftFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] idleUpFrames = new TextureRegion[1];

        TextureRegion[] walkDownFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkUpFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkRightFrames = new TextureRegion[FRAME_ROWS];
        TextureRegion[] walkLeftFrames = new TextureRegion[FRAME_ROWS];

        idleUpFrames[0] = tmp[0][4];
        for (int i = 0; i < FRAME_ROWS; i++) {
            idleDownFrames[i] = tmp[i][0];
            idleRightFrames[i] = tmp[i][1];
            idleLeftFrames[i] = tmp[i][2];

            walkDownFrames[i] = tmp[i][3];
            walkUpFrames[i] = tmp[i][4];
            walkRightFrames[i] = tmp[i][5];
            walkLeftFrames[i] = tmp[i][6];
        }

        idleDown = new Animation<>(0.3f, idleDownFrames);
        idleRight = new Animation<>(0.3f, idleRightFrames);
        idleLeft = new Animation<>(0.3f, idleLeftFrames);
        idleUp = new Animation<>(0.3f, idleUpFrames);
        walkDownAnimation = new Animation<>(0.2f, walkDownFrames);
        walkUpAnimation = new Animation<>(0.2f, walkUpFrames);
        walkRightAnimation = new Animation<>(0.2f, walkRightFrames);
        walkLeftAnimation = new Animation<>(0.2f, walkLeftFrames);

        currentAnimation = idleDown;

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
    public void updateAnimation(float delta) {
        stateTime += delta;
        currentFrame = currentAnimation.getKeyFrame(stateTime, true);
    }

    /**
     * @param direction iD: idleDown, iR:idleRight, iL: idleLeft, iU: idleUp, wD:
     *                  walkDown, wU: walkUp, wR: walkRight, wL: walkLeft
     */
    public void setWalkingAnimation(String direction) {
        otherAnimation = null;
        currentAnimation = switch (direction) {
            case "iD" -> idleDown;
            case "iR" -> idleRight;
            case "iL" -> idleLeft;
            case "iU" -> idleUp;
            case "wD" -> walkDownAnimation;
            case "wU" -> walkUpAnimation;
            case "wR" -> walkRightAnimation;
            case "wL" -> walkLeftAnimation;
            default -> idleDown;
        };
    }

    /**
     * sets an animation for the actor different from idle and walk
     * @param ani RESOURCE ENUM of the animation to play
     * @param duration interval between frames, adapt as needed
     * @param width of the actor as int
     * @param height of the actor as int 
     */
    public void setOtherAnimation(ResourceEnum ani, float duration, int width, int height) {
        Texture aniSheet = Utils.getTexture(ani);

        int FRAME_COLS = aniSheet.getWidth() / width;
        int FRAME_ROWS = aniSheet.getHeight() / height;

        TextureRegion[][] tmp = TextureRegion.split(aniSheet,
                aniSheet.getWidth() / FRAME_COLS,
                aniSheet.getHeight() / FRAME_ROWS);

        otherAnimation = new Animation<>(duration, tmp[0]);
        currentAnimation = otherAnimation;
    }

    /**
     * Just as the other with a default duration
     * @param ani
     * @param width
     * @param height
     */
    public void setOtherAnimation(ResourceEnum ani, int width, int height) {
        setOtherAnimation(ani, 0.3f, width, height);
    }
}
