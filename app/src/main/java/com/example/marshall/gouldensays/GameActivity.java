package com.example.marshall.gouldensays;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;
import java.util.Random;


public class GameActivity extends AppCompatActivity
{
    private Game game;
    private MediaPlayer player1, player2, player3, player4, player5;
    private ArrayList<MediaPlayer> list = new ArrayList<>();
    private static int trackNumber = Settings.song.trackNum;
    private static boolean playPause = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);


        Random rng = new Random();
        if (Settings.randSong) {
            trackNumber = rng.nextInt(5);
        }
        else
        {
            if (Settings.song != null)
                trackNumber = Settings.song.trackNum;
        }
        setPlayers();

        player1.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer)
            {
                mediaPlayer.stop();
                try
                {
                    mediaPlayer.prepare();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                trackNumber++;
                player2.start();
            }
        });
        player2.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer)
            {
                mediaPlayer.stop();
                try
                {
                    mediaPlayer.prepare();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                trackNumber++;
                player3.start();
            }
        });
        player3.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer)
            {
                mediaPlayer.stop();
                try
                {
                    mediaPlayer.prepare();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                trackNumber++;
                player4.start();
            }
        });
        player4.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer)
            {
                mediaPlayer.stop();
                try
                {
                    mediaPlayer.prepare();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                trackNumber++;
                player5.start();
            }
        });
        player5.setOnCompletionListener(new MediaPlayer.OnCompletionListener()
        {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer)
            {
                mediaPlayer.stop();
                try
                {
                    mediaPlayer.prepare();
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
                trackNumber=0;
                player1.start();
            }
        });

        ArrayList<ImageView> buttons = new ArrayList<>();
        buttons.add((ImageView)findViewById(R.id.yellow_hex));
        buttons.add((ImageView)findViewById(R.id.red_hex));
        buttons.add((ImageView)findViewById(R.id.blue_hex));
        buttons.add((ImageView)findViewById(R.id.green_hex));
        buttons.add((ImageView)findViewById(R.id.black_hex));
        buttons.add((ImageView)findViewById(R.id.white_hex));
        game = new Game(buttons, findViewById(R.id.currentscore));

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
        list.get(trackNumber).stop();
        try
        {
            list.get(trackNumber).prepare();
        }
        catch (Exception ex)
        {

        }
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void hexClick(View view) { game.respond(view); }

    //media methods
    public void playPause(View view) {

        if (playPause)
        {
            game.rotation(view,true,6,35);
            findViewById(R.id.play).setBackgroundResource(R.drawable.play);
            playPause = false;
            list.get(trackNumber).pause();
        }
        else
        {
            game.rotation(view,true,6,35);
            findViewById(R.id.play).setBackgroundResource(R.drawable.pause);
            playPause = true;
            list.get(trackNumber).start();
        }
    }



    public void seek(View view) {
        game.rotation(view,true,6,35);
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
        findViewById(R.id.play).setBackgroundResource(R.drawable.pause);
    }

    public static boolean getPlayPause ()
    {
        return playPause;
    }
}
