package com.example.marshall.gouldensays;

/**
 * Created by Marshall on 12/4/2016.
 */

public enum GameSpeed{
    SLOW (200, 100),
    MED  (150, 80),
    FAST (100, 60);

    public final int animSpeed;
    public final int pauseLength;

    GameSpeed(int animSpeed, int pauseLength) {
        this.animSpeed = animSpeed;
        this.pauseLength = pauseLength;
    }
}