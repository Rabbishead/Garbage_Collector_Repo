package com.mygdx.game;

import com.badlogic.gdx.*;
import com.mygdx.Logger;
import com.mygdx.Utils;
import com.mygdx.savings.SavingsManager;
import com.mygdx.screens.ScreensManager;
import com.mygdx.states.StateEnum;
import com.mygdx.states.StateManager;

public class GarbageCollection extends Game {

	@Override
	public void create() {
		Utils.setGame(this);
		SavingsManager.load();
		StateManager.updateBoolState(StateEnum.IS_ENTERING, false);
		StateManager.updateBoolState(StateEnum.IS_EXITING, false);
		StateManager.updateStringState(StateEnum.DESTINATION, "");
		StateManager.updateBoolState(StateEnum.PAUSE, false);
		Logger.init();
		setScreen(ScreensManager.getScreen("MENU_SCREEN"));
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		Utils.dispose();
	}
}
