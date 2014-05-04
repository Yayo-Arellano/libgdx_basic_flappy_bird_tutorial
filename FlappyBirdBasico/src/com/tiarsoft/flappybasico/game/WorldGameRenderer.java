package com.tiarsoft.flappybasico.game;

import java.util.Iterator;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.tiarsoft.flappybasico.Assets;
import com.tiarsoft.flappybasico.objetos.Bird;
import com.tiarsoft.flappybasico.objetos.Tuberia;
import com.tiarsoft.flappybasico.screens.Screens;

public class WorldGameRenderer {

	final float WIDTH = Screens.WORLD_WIDTH;
	final float HEIGHT = Screens.WORLD_HEIGHT;

	SpriteBatch batcher;
	WorldGame oWorld;
	OrthographicCamera oCam;

	Box2DDebugRenderer renderBox;

	public WorldGameRenderer(SpriteBatch batcher, WorldGame oWorld) {

		this.oCam = new OrthographicCamera(WIDTH, HEIGHT);
		this.oCam.position.set(WIDTH / 2f, HEIGHT / 2f, 0);
		this.batcher = batcher;
		this.oWorld = oWorld;
		this.renderBox = new Box2DDebugRenderer();
	}

	public void render(float delta) {

		oCam.update();
		batcher.setProjectionMatrix(oCam.combined);

		batcher.begin();
		batcher.disableBlending();
		drawBackGround(delta);
		batcher.enableBlending();
		drawTuberia(delta);
		drawBird(delta);

		batcher.end();

		renderBox.render(oWorld.oWorldBox, oCam.combined);
	}

	private void drawBackGround(float delta) {
		batcher.draw(Assets.fondo, 0, 0, WIDTH, HEIGHT);

	}

	private void drawTuberia(float delta) {
		Iterator<Tuberia> i = oWorld.arrTuberias.iterator();

		while (i.hasNext()) {
			Tuberia obj = i.next();
			if (obj.tipo == Tuberia.TIPO_ABAJO)
				batcher.draw(Assets.tuberiaDown, obj.position.x - .5f,
						obj.position.y - 2f, 1f, 4);
			else
				batcher.draw(Assets.tuberiaUp, obj.position.x - .5f,
						obj.position.y - 2f, 1f, 4);
		}

	}

	private void drawBird(float delta) {
		Bird obj = oWorld.oBird;
		TextureRegion keyFrame;

		if (obj.state == Bird.STATE_NORMAL) {
			keyFrame = Assets.bird.getKeyFrame(obj.stateTime, true);
		}
		else {
			keyFrame = Assets.bird.getKeyFrame(obj.stateTime, false);
		}
		batcher.draw(keyFrame, obj.position.x - .3f, obj.position.y - .25f,
				.6f, .5f);
	}

}
