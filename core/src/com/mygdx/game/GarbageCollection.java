package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.physics.bullet.extras.DillCreator;
import com.mygdx.Utils;
import com.mygdx.dialogues.DialogueLoader;
import com.mygdx.screens.MenuScreen;
import com.mygdx.screens.ScreensManager;
import com.mygdx.screens.ScreensManager.ScreenEnum;

public class GarbageCollection extends Game {
	
	@Override
	public void create () {
		Utils.setGame(this);
		setScreen(ScreensManager.getScreen(ScreenEnum.MENU_SCREEN));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {
		Utils.dispose();
	}
}
