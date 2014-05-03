package com.tiarsoft.flappybasico;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.tiarsoft.flappybasico.game.GameScreen;
import com.tiarsoft.flappybasico.screens.Screens;

public class MainFlappyBird extends Game {

	public Stage stage;
	public SpriteBatch batcher;

	@Override
	public void create() {
		stage = new Stage(new StretchViewport(Screens.SCREEN_WIDTH,
				Screens.SCREEN_HEIGHT));
		batcher = new SpriteBatch();
		Assets.load();

		setScreen(new GameScreen(this));
	}
}
