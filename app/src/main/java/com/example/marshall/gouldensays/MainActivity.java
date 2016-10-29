package com.example.marshall.gouldensays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startGame(View view)
    {
        Intent intent = new Intent(this, GameActivity.class);

        startActivity(intent);
    }

    public void aboutClick(View view)
    {
        setContentView(R.layout.about);
    }

    public void howToClick(View view)
    {
        setContentView(R.layout.how_to);
    }

    public void exitToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
