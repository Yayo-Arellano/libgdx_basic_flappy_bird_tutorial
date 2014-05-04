package com.tiarsoft.flappybasico.game;

import java.util.Iterator;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tiarsoft.flappybasico.objetos.Bird;
import com.tiarsoft.flappybasico.objetos.Contador;
import com.tiarsoft.flappybasico.objetos.Tuberia;
import com.tiarsoft.flappybasico.screens.Screens;

public class WorldGame {
	final float WIDTH = Screens.WORLD_WIDTH;
	final float HEIGHT = Screens.WORLD_HEIGHT;

	static final int STATE_RUNNING = 0;
	static final int STATE_GAMEOVER = 1;
	public int state;

	/**
	 * Tiempo en segundos entre una tuberia y otra, si se incrementa, el espacio entre tuberias incrementa.
	 */
	final float TIME_TO_SPAWN_PIPE = 1.5f;
	float timeToSpawnPipe;

	public World oWorldBox;
	public int score;

	/**
	 * Guarda la informacion de nuestro pajaro
	 */
	Bird oBird;

	/**
	 * Un arreglo con la informacion de cada una de las tuberias que estan en el juego
	 */
	Array<Tuberia> arrTuberias;

	/**
	 * Guarda todos los cuerpos (Box2d) que hay en el mundo (Box2d) esto incluye las tuberias, el pajaro y los objeto contador
	 */
	Array<Body> arrBodies;

	public WorldGame() {
		oWorldBox = new World(new Vector2(0, -13.0f), true);
		oWorldBox.setContactListener(new Colisiones());

		arrTuberias = new Array<Tuberia>();
		arrBodies = new Array<Body>();

		timeToSpawnPipe = 1.5f;

		createBird();
		createTecho();
		createPiso();

		state = STATE_RUNNING;

	}

	private void createBird() {
		oBird = new Bird(1.35f, 4.75f);

		BodyDef bd = new BodyDef();
		bd.position.x = oBird.position.x;
		bd.position.y = oBird.position.y;
		bd.type = BodyType.DynamicBody;

		Body oBody = oWorldBox.createBody(bd);

		CircleShape shape = new CircleShape();
		shape.setRadius(.25f);

		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.density = 8;
		oBody.createFixture(fixture);

		oBody.setFixedRotation(true);
		oBody.setUserData(oBird);
		oBody.setBullet(true);

		shape.dispose();
	}

	private void createTecho() {
		BodyDef bd = new BodyDef();
		bd.position.x = 0;
		bd.position.y = HEIGHT;
		bd.type = BodyType.StaticBody;
		Body oBody = oWorldBox.createBody(bd);

		EdgeShape shape = new EdgeShape();
		shape.set(0, 0, WIDTH, 0);

		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;

		oBody.createFixture(fixture);
		shape.dispose();
	}

	private void createPiso() {

		BodyDef bd = new BodyDef();
		bd.position.x = 0;
		bd.position.y = 1.1f;
		bd.type = BodyType.StaticBody;
		Body oBody = oWorldBox.createBody(bd);

		EdgeShape shape = new EdgeShape();
		shape.set(0, 0, WIDTH, 0);

		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;

		oBody.createFixture(fixture);
		shape.dispose();
	}

	public void agregarTuberias() {
		float x = WIDTH + 2.5f;
		float y = MathUtils.random() * (1.5f) + .4f;

		/**
		 * Agrego la tuberia de la parte de abajo
		 */
		agregarTuberia(x, y, false);

		/**
		 * Agrego la tuberia de la parte de arriba
		 */
		agregarTuberia(x, y + 2f + Tuberia.HEIGHT, true);

		/**
		 * Agrego el contador
		 */
		agregarContador(x, y + Contador.HEIGHT / 2f + Tuberia.HEIGHT / 2f + .1f);

	}

	private void agregarTuberia(float x, float y, boolean esArriba) {
		Tuberia obj;
		if (esArriba)
			obj = new Tuberia(x, y, Tuberia.TIPO_ARRIBA);
		else
			obj = new Tuberia(x, y, Tuberia.TIPO_ABAJO);

		BodyDef bd = new BodyDef();
		bd.position.x = x;
		bd.position.y = y;
		bd.type = BodyType.KinematicBody;
		Body oBody = oWorldBox.createBody(bd);
		oBody.setLinearVelocity(Tuberia.VELOCIDAD_X, 0);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Tuberia.WIDTH / 2f, Tuberia.HEIGHT / 2f);

		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;

		oBody.createFixture(fixture);
		oBody.setFixedRotation(true);
		oBody.setUserData(obj);
		arrTuberias.add(obj);
		shape.dispose();

	}

	private void agregarContador(float x, float y) {
		Contador obj = new Contador();
		BodyDef bd = new BodyDef();
		bd.position.x = x;
		bd.position.y = y;
		bd.type = BodyType.KinematicBody;
		Body oBody = oWorldBox.createBody(bd);
		oBody.setLinearVelocity(Contador.VELOCIDAD_X, 0);

		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Contador.WIDTH / 2f, Contador.HEIGHT / 2f);

		FixtureDef fixture = new FixtureDef();
		fixture.shape = shape;
		fixture.isSensor = true;

		oBody.createFixture(fixture);
		oBody.setFixedRotation(true);
		oBody.setUserData(obj);

		shape.dispose();
	}

	public void update(float delta, boolean jump) {
		oWorldBox.step(delta, 8, 4);

		eliminarObjetos();

		timeToSpawnPipe += delta;

		if (timeToSpawnPipe >= TIME_TO_SPAWN_PIPE) {
			timeToSpawnPipe -= TIME_TO_SPAWN_PIPE;
			agregarTuberias();
		}

		oWorldBox.getBodies(arrBodies);
		Iterator<Body> i = arrBodies.iterator();

		while (i.hasNext()) {
			Body body = i.next();

			if (body.getUserData() instanceof Bird) {
				updateBird(body, delta, jump);
			}
			else if (body.getUserData() instanceof Tuberia) {
				updateTuberias(body, delta);
			}
			else if (body.getUserData() instanceof Contador) {
				updateContador(body, delta);
			}
		}

		if (oBird.state == Bird.STATE_MUERTO)
			state = STATE_GAMEOVER;
	}

	private void updateBird(Body body, float delta, boolean jump) {

		oBird.update(delta, body);

		if (jump && oBird.state == Bird.STATE_NORMAL) {
			body.setLinearVelocity(0, Bird.VELOCIDAD_JUMP);
		}

	}

	private void updateTuberias(Body body, float delta) {
		if (oBird.state == Bird.STATE_NORMAL) {
			Tuberia obj = (Tuberia) body.getUserData();

			obj.update(delta, body);
			if (obj.position.x <= -5)
				obj.state = Tuberia.STATE_DESTRUIR;

		}
		else
			body.setLinearVelocity(0, 0);

	}

	private void updateContador(Body body, float delta) {
		if (oBird.state == Bird.STATE_NORMAL) {
			Contador obj = (Contador) body.getUserData();

			if (obj.position.x <= -5)
				obj.state = Contador.STATE_DESTRUIR;
		}
		else
			body.setLinearVelocity(0, 0);
	}

	private void eliminarObjetos() {
		oWorldBox.getBodies(arrBodies);
		Iterator<Body> i = arrBodies.iterator();

		while (i.hasNext()) {
			Body body = i.next();

			if (!oWorldBox.isLocked()) {

				if (body.getUserData() instanceof Tuberia) {
					Tuberia obj = (Tuberia) body.getUserData();
					if (obj.state == Tuberia.STATE_DESTRUIR) {
						arrTuberias.removeValue(obj, true);
						oWorldBox.destroyBody(body);
						continue;
					}
				}
				else if (body.getUserData() instanceof Contador) {
					Contador obj = (Contador) body.getUserData();
					if (obj.state == Contador.STATE_DESTRUIR) {
						oWorldBox.destroyBody(body);
						continue;
					}
				}
			}

		}
	}

	class Colisiones implements ContactListener {

		@Override
		public void beginContact(Contact contact) {
			Fixture a = contact.getFixtureA();
			Fixture b = contact.getFixtureB();

			if (a.getBody().getUserData() instanceof Bird)
				beginContactBirdOtraCosa(a, b);
			else if (b.getBody().getUserData() instanceof Bird)
				beginContactBirdOtraCosa(b, a);

		}

		private void beginContactBirdOtraCosa(Fixture bird, Fixture otraCosa) {

			Object oOtraCosa = otraCosa.getBody().getUserData();

			if (oOtraCosa instanceof Contador) {
				Contador obj = (Contador) oOtraCosa;
				if (obj.state == Contador.STATE_NORMAL) {
					obj.state = Contador.STATE_DESTRUIR;
					score++;

				}
			}
			else {
				if (oBird.state == Bird.STATE_NORMAL) {
					oBird.getHurt();

				}
			}
		}

		@Override
		public void endContact(Contact contact) {

		}

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
			
		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {

		}

	}

}
