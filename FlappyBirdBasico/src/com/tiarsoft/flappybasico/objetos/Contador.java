package com.tiarsoft.flappybasico.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Contador {
	public static float WIDTH = .1f;
	public static float HEIGHT = 1.85f;

	public static int STATE_NORMAL = 0;
	public static int STATE_DESTRUIR = 1;

	/*
	 * Se mueve con la misma velocidad que las tuberias
	 */
	public static float VELOCIDAD_X = Tuberia.VELOCIDAD_X;

	public Vector2 position;
	public float stateTime;

	public int state;

	public Contador() {
		position = new Vector2();
		stateTime = 0;
		state = STATE_NORMAL;
	}

	public void update(float delta, Body body) {
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		stateTime += delta;

	}

}
