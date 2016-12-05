package com.example.marshall.gouldensays;

/**
 * Created by Marshall on 12/4/2016.
 */

public class Settings
{
    public static boolean musicPlay = true;
    public static boolean soundPlay = true;
    public static boolean randSong = true;
    public static Song song = Song.RANDOM;

    public static GameSpeed gameSpeed = GameSpeed.SLOW;

    public static int highScore = 0;

    private Settings(){}
}
