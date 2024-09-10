package com.mygdx.game;

import com.badlogic.gdx.*;
import com.mygdx.Utils;
import com.mygdx.screens.MenuScreen;

public class GarbageCollection extends Game {
	
	@Override
	public void create () {
		setScreen(new MenuScreen(this));
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
