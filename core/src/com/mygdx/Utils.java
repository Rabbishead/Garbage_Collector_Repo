package com.mygdx;

import com.badlogic.gdx.graphics.Texture;
import com.mygdx.resources.ResourceEnum;
import com.mygdx.resources.ResourceManager;

public class Utils {

    private static final ResourceManager manager = new ResourceManager();

    public static Texture getTexture(ResourceEnum e){
        return manager.getTexture(e);
    }

    public static void dispose(){
        manager.dispose();
    }
}
