package com.tiarsoft.flappybasico.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Tuberia {
	public static int TIPO_ARRIBA = 0;// Indica si es una tuberia de la parte superior
	public static int TIPO_ABAJO = 1;// Indica si es una tuberia de la parte inferior
	public int tipo;// Guarda el tipo de tuberia (Superior o inferior).

	public static float WIDTH = .85f;
	public static float HEIGHT = 4f;

	public static int STATE_NORMAL = 0;
	public static int STATE_DESTRUIR = 1;
	public int state;

	/*
	 * Velocidad con la que se mueven de derecha a izquierda
	 */
	public static float VELOCIDAD_X = -2f;

	public Vector2 position;
	public float stateTime;

	public Tuberia(float x, float y, int tipo) {
		position = new Vector2(x, y);
		stateTime = 0;
		state = STATE_NORMAL;
		this.tipo = tipo;
	}

	public void update(float delta, Body body) {
		position.x = body.getPosition().x;
		position.y = body.getPosition().y;
		stateTime += delta;

	}

}
