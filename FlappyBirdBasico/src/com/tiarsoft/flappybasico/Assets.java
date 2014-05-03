package com.tiarsoft.flappybasico;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {

	public static BitmapFont font;

	public static Animation bird;

	public static TextureRegion fondo;

	public static TextureRegion gameOver;
	public static TextureRegion getReady;
	public static TextureRegion tap;

	public static TextureRegion tuberiaDown;
	public static TextureRegion tuberiaUp;

	public static void load() {
		TextureAtlas atlas = new TextureAtlas(
				Gdx.files.internal("data/atlasMap.txt"));

		font = new BitmapFont();
		font.setScale(7f);

		bird = new Animation(.3f, atlas.findRegion("bird1"),
				atlas.findRegion("bird2"), atlas.findRegion("bird3"));

		fondo = atlas.findRegion("fondo");

		gameOver = atlas.findRegion("gameOver");
		getReady = atlas.findRegion("getReady");
		tap = atlas.findRegion("tap");
		tuberiaDown = atlas.findRegion("tuberiaDown");
		tuberiaUp = atlas.findRegion("tuberiaUp");

	}
}
