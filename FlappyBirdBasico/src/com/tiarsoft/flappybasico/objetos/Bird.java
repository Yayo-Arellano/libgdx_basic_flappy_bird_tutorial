package com.tiarsoft.flappybasico.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Bird {
	public static float VELOCIDAD_JUMP = 5;

	public static int STATE_NORMAL = 0;
	public static int STATE_MUERTO = 1;

	public Vector2 position;

	public int state;
	public float stateTime;

	public Bird(float x, float y) {
		position = new Vector2(x, y);
		state = STATE_NORMAL;
	}

	public void update(float delta, Body body) {

		position.x = body.getPosition().x;
		position.y = body.getPosition().y;

		stateTime += delta;

	}

	public void getHurt() {
		state = STATE_MUERTO;
		stateTime = 0;
	}

}
