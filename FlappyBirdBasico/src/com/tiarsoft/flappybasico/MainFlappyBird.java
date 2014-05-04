package com.tiarsoft.flappybasico;

import com.badlogic.gdx.Game;
import com.tiarsoft.flappybasico.game.GameScreen;

public class MainFlappyBird extends Game {

	@Override
	public void create() {
		Assets.load();
		setScreen(new GameScreen(this));
	}
}
