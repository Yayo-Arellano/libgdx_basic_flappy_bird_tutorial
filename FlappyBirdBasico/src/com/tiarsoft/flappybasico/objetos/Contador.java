package com.tiarsoft.flappybasico.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Contador {

	public static float WIDTH = .1f;// Ancho del contador
	public static float HEIGHT = 1.85f;// Altura del contador

	public static int STATE_NORMAL = 0;// Indica que su estado es normal
	public static int STATE_DESTRUIR = 1;// Indica que su estado es destruir
	public int state;// Guarda el estado actual

	/*
	 * Se mueve con la misma velocidad que las tuberias
	 */
	public static float VELOCIDAD_X = Tuberia.VELOCIDAD_X;

	public Vector2 position;// Almacena la poscion

	/**
	 * Esta variable acumula el tiempo en el que el objeto se encuentra en el mismo estado.
	 */
	public float stateTime;

	public Contador() {
		position = new Vector2();
		stateTime = 0;
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

}
