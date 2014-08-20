package com.ikislenko.bonver;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.ikislenko.bonver.screen.Screen;
import com.ikislenko.bonver.tween.SpriteAccessor;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

/**
 * Created by Kislenko on 18.08.2014.
 */
public class IntroScreen extends Screen {

    public OrthographicCamera guiCam;
    public Texture texture;
    public Texture texturep;
    public Texture textureb;
    public SpriteBatch batch;
    public Sprite splash;
    public Sprite present;
    public Sprite bonver_logo;

    private TweenManager tweenManager;

    int SCREEN_HEIGHT, SCREEN_WIDTH, SCREEN_X, SCREEN_Y;

    public IntroScreen(final Bonver game) {
        super(game);

        guiCam = new OrthographicCamera(480, 320);
        guiCam.position.set(480/2, 320/2, 0);

        SCREEN_HEIGHT = Gdx.graphics.getHeight();
        SCREEN_WIDTH = (int) (SCREEN_HEIGHT * 1.5f) - 20;
        SCREEN_X = (Gdx.graphics.getWidth() - SCREEN_WIDTH) / 2;
        SCREEN_Y = (Gdx.graphics.getHeight() - SCREEN_HEIGHT) / 2;

        texture = new Texture(Gdx.files.internal("veliant-logo-and.png"));
        texturep = new Texture(Gdx.files.internal("veliantp-logo-and.png"));
        textureb = new Texture(Gdx.files.internal("bonver-logo.png"));
        splash = new Sprite(texture);
        present = new Sprite(texturep);
        bonver_logo = new Sprite(textureb);
        batch = new SpriteBatch();

        tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());

        Tween.set(splash, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.set(present, SpriteAccessor.ALPHA).target(0).start(tweenManager);
        Tween.set(bonver_logo, SpriteAccessor.ALPHA).target(0).start(tweenManager);

        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(1).start(tweenManager);
        Tween.to(present, SpriteAccessor.ALPHA, 2).target(1).delay(2).start(tweenManager);
        Tween.to(bonver_logo, SpriteAccessor.ALPHA, 2).target(1).delay(6).start(tweenManager);

        Tween.to(splash, SpriteAccessor.ALPHA, 2).target(0).delay(4).start(tweenManager);
        Tween.to(present, SpriteAccessor.ALPHA, 2).target(0).delay(4).start(tweenManager);
        Tween.to(bonver_logo, SpriteAccessor.ALPHA, 2).target(0).delay(10).start(tweenManager).setCallback( new TweenCallback() {
            @Override
            public void onEvent(int type, BaseTween<?> source) {
                game.setScreen(new MenuScreen(game));
            }
        });
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void present(float deltaTime) {
        Gdx.gl.glClearColor(0, 0,0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        guiCam.update();
        tweenManager.update(deltaTime);

        batch.setProjectionMatrix(guiCam.combined);
        Gdx.gl.glViewport(SCREEN_X, SCREEN_Y, SCREEN_WIDTH, SCREEN_HEIGHT);
        batch.begin();
        splash.draw(batch);
        present.draw(batch);
        bonver_logo.draw(batch);
        batch.end();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
