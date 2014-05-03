package com.tiarsoft.flappybasico.screens;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.tiarsoft.flappybasico.MainFlappyBird;

public abstract class Screens extends InputAdapter implements Screen {
	public static final float SCREEN_WIDTH = 480;
	public static final float SCREEN_HEIGHT = 800;

	public static final float WORLD_SCREEN_WIDTH = 4.8f;
	public static final float WORLD_SCREEN_HEIGHT = 8;

	public MainFlappyBird game;

	public OrthographicCamera oCam;
	public SpriteBatch batcher;
	public Stage stage;

	Random oRan;

	public Screens(MainFlappyBird game) {
		this.stage = game.stage;
		this.stage.clear();
		this.batcher = game.batcher;
		this.game = game;

		oCam = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);
		oCam.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);

		InputMultiplexer input = new InputMultiplexer(this, stage);
		Gdx.input.setInputProcessor(input);

	}

	@Override
	public void render(float delta) {

		update(delta);
		stage.act(delta);

		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		draw(delta);

		stage.draw();

		Table.drawDebug(stage);
	}

	public abstract void draw(float delta);

	public abstract void update(float delta);

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {
		stage.dispose();
		batcher.dispose();
	}

}
