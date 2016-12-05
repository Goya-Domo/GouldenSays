package com.example.marshall.gouldensays;

/**
 * Created by Marshall on 12/4/2016.
 */

public enum Song
{
    CAB (0, "Downtown Cab"),
    LINN (1, "Linn's Basket"),
    SKYDIVE (2, "Skydive"),
    FLYING (3, "Flying"),
    RUBENS (4, "Ruben's"),
    RANDOM (-1, "Random");

    public final int trackNum;
    public final String name;

    Song(int trackNum, String name) {
        this.trackNum = trackNum;
        this.name = name;
    }
}
