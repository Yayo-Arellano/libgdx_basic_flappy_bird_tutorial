package com.tiarsoft.flappybasico.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Bird {
	public static float VELOCIDAD_JUMP = 5;

	public static int STATE_NORMAL = 0;// Indica que su estado es normal
	public static int STATE_MUERTO = 1;// Indica que su estado muerto
	public int state;// /Guarda el esado actual

	public Vector2 position;
	public float stateTime;

	public Bird(float x, float y) {
		position = new Vector2(x, y);
		state = STATE_NORMAL;
	}

	/**
	 * Actualiza las posiciones del objeto para que concuerden con las posiciones del cuerpo (Body) asi como acumular el stateTime
	 */
	public void update(float delta, Body body) {
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		stateTime += delta;
	}

	/**
	 * Esta funcion sera llamada cuando el pajaro colisiona con una tuberia Su state cambia a muerto y el stateTime se pone en 0.
	 */
	public void getHurt() {
		state = STATE_MUERTO;
		stateTime = 0;
	}

}
