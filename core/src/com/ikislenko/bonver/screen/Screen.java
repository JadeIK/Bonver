package com.ikislenko.bonver.screen;

import com.ikislenko.bonver.Bonver;

/**
 * Created by Kislenko on 18.08.2014.
 */
public abstract class Screen {
    Bonver game;

    public Screen(Bonver game) {
        this.game = game;
    }

    public abstract void update(float deltaTime);
    public abstract void present(float deltaTime);
    public abstract void pause();
    public abstract void resume();
    public abstract void dispose();
}
