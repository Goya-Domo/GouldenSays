package com.example.marshall.gouldensays;

import java.util.Random;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.view.View;

/**
 * Created by Marshall on 10/15/2016.
 *
 * The whole point of managing the seed of the Random device is to be able to replicate games while also having RNG.
 * Hopefully that will work.
 *
 */

public class Game
{
    private long seed;

    private Random rng;
    public Game()
    {
        //create a random seed and save it.
        rng = new Random();
        seed = rng.nextLong();
        rng.setSeed(seed);
    }

    public Game(long seed)
    {
        this.seed = seed;
        rng = new Random(seed);
    }

    public Random getRng()
    {
        return rng;
    }

}
