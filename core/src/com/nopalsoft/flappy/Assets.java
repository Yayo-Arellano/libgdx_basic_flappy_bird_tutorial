package com.nopalsoft.flappy;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;

public class Assets {

    public static BitmapFont font;
    private static final GlyphLayout glyphLayout = new GlyphLayout();

    public static Animation<AtlasRegion> bird;

    public static TextureRegion fondo;
    public static TextureRegion gameOver;
    public static TextureRegion getReady;
    public static TextureRegion tap;
    public static TextureRegion tuberiaDown;
    public static TextureRegion tuberiaUp;

    public static void load() {

        /**
         * Creamos un objeto TextureAtlas a partir del texture atlas generado por el TexturePacker.
         */
        TextureAtlas atlas = new TextureAtlas(
                Gdx.files.internal("data/atlasMap.txt"));

        /**
         * Creamos cada una de las regiones, buscando por el nombre de la imagen en nuestro texture atlas.
         */
        fondo = atlas.findRegion("fondo");
        gameOver = atlas.findRegion("gameOver");
        getReady = atlas.findRegion("getReady");
        tap = atlas.findRegion("tap");
        tuberiaDown = atlas.findRegion("tuberiaDown");
        tuberiaUp = atlas.findRegion("tuberiaUp");

        /**
         * Creamos la animacion del pajaro con una duracion de .3 segundos
         */
        bird = new Animation<>(.3f, atlas.findRegion("bird1"),
                atlas.findRegion("bird2"), atlas.findRegion("bird3"));

        /**
         * Creamos el bitmap font por default e incrementamos su tamano
         */
        font = new BitmapFont();
        font.getData().scale(7f);
//		font.setScale(7f);
    }

    public static float getTextWidth(String text) {
        glyphLayout.setText(font, text);
        return glyphLayout.width;
    }
}
