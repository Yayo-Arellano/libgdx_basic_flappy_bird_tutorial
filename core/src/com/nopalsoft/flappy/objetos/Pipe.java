package com.nopalsoft.flappy.objetos;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;

public class Pipe {
    public static int TYPE_UP = 0;
    public static int TYPE_DOWN = 1;
    public int type;

    public static float WIDTH = .85f;
    public static float HEIGHT = 4f;

    public static int STATE_NORMAL = 0;
    public static int STATE_REMOVE = 1;
    public int state;

    /**
     * Speed of pipes. Pipes will move from right to left
     */
    public static float SPEED_X = -2f;

    public Vector2 position;
    public float stateTime;

    public Pipe(float x, float y, int type) {
        position = new Vector2(x, y);
        stateTime = 0;
        state = STATE_NORMAL;
        this.type = type;
    }

    public void update(float delta, Body body) {
        position.x = body.getPosition().x;
        position.y = body.getPosition().y;
        stateTime += delta;
    }

}
