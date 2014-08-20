package com.ikislenko.bonver;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.ikislenko.bonver.screen.Screen;

public class Bonver implements ApplicationListener {

    public Screen screen;

    @Override
    public void create() {
        screen = getStartScreen();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void render() {
        screen.update(Gdx.graphics.getDeltaTime());
        screen.present(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void pause() {
        screen.pause();
    }

    @Override
    public void resume() {
        screen.resume();
    }

    @Override
    public void dispose() {
        screen.dispose();
    }

    public void setScreen(Screen screen) {
        this.screen.pause();
        this.screen.dispose();
        this.screen = screen;
    }

    public Screen getStartScreen() {
        return new IntroScreen(this);
    }
}
