package com.example.marshall.gouldensays;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.util.ArrayList;



public class GameActivity extends AppCompatActivity
{
    private Game game;
    private MediaPlayer player1, player2, player3, player4, player5;
    private ArrayList<MediaPlayer> list = new ArrayList<>();
    private int trackNumber = 0;
    private boolean playPause = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);

        setPlayers();


        ArrayList<ImageView> buttons = new ArrayList<>();
        buttons.add((ImageView)findViewById(R.id.yellow_hex));
        buttons.add((ImageView)findViewById(R.id.red_hex));
        buttons.add((ImageView)findViewById(R.id.blue_hex));
        buttons.add((ImageView)findViewById(R.id.green_hex));
        buttons.add((ImageView)findViewById(R.id.black_hex));
        buttons.add((ImageView)findViewById(R.id.white_hex));
        game = new Game(buttons);

        game.newGame();
    }

    public void setPlayers()
    {
        player2 = MediaPlayer.create(GameActivity.this, R.raw.song1);
        player1 = MediaPlayer.create(GameActivity.this, R.raw.song2);
        player3 = MediaPlayer.create(GameActivity.this, R.raw.song3);
        player4 = MediaPlayer.create(GameActivity.this, R.raw.song4);
        player5 = MediaPlayer.create(GameActivity.this, R.raw.song5);

        list.add(player1);
        list.add(player2);
        list.add(player3);
        list.add(player4);
        list.add(player5);

        list.get(trackNumber).start();
    }

    public void exitToMenu(View view)
    {
        list.get(trackNumber).pause();
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void hexClick(View view) { game.respond(view); }

    public void debugAddHex(View view)
    {
        game.addHex();
    }

    //media methods
    public void playPause(View view) {
        if (playPause)
        {
            playPause = false;
            list.get(trackNumber).pause();
        }
        else
        {
            playPause = true;
            list.get(trackNumber).start();
        }
    }

    public void seek(View view) {
        list.get(trackNumber).stop();
        try
        {
            list.get(trackNumber).prepare();
        }
        catch (Exception ex)
        {

        }
        trackNumber++;
        if (trackNumber == list.size())
        {
            trackNumber = 0;
        }
        list.get(trackNumber).start();
        playPause = true;
    }
}
