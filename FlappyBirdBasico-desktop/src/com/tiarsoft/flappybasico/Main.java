package com.tiarsoft.flappybasico;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "FlappyBirdBasico";
		cfg.width = 480;
		cfg.height = 800;

		new LwjglApplication(new MainFlappyBird(), cfg);
	}
}
