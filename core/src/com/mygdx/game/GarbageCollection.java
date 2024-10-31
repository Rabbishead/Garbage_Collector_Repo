package com.mygdx.game;

import com.badlogic.gdx.*;
import com.mygdx.Utils;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.ScreensManager;

public class GarbageCollection extends Game {
	
	@Override
	public void create () {
		Utils.setGame(this);
		SavingsManager.load();
		setScreen(ScreensManager.getScreen("MENU_SCREEN"));
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
