package com.mygdx.game;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.Data;

// Please note that on macOS your application needs to wbe started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(60);
		config.setTitle("Garbage Collection");
		config.setWindowedMode(Data.VIEWPORT_X, Data.VIEWPORT_Y);
		config.setWindowIcon("assets/testactor.png");
		new Lwjgl3Application(new GarbageCollection(), config);
	}
}