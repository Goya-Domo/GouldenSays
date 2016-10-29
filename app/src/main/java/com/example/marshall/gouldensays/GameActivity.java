package com.example.marshall.gouldensays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import java.util.ArrayList;

public class GameActivity extends AppCompatActivity
{
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);

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

    public void exitToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }

    public void hexClick(View view) { game.respond(view); }

    public void debugAddHex(View view)
    {
        game.addHex();
    }

}
