package com.example.marshall.gouldensays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity
{
    private int index;
    private Game game;
    private ArrayList<Instruction> instructionList;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);

        game = new Game();
        instructionList = new ArrayList<>();
        index = 0;

        addNextInstruction();
        playNextInstruction();
    }

    private void playNextInstruction()
    {
        if (index >= instructionList.size())
        {
            index = 0;
            return;
        }

        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.buttonsig);

        Toast.makeText(getApplicationContext(), String.valueOf(instructionList.get(index).getButton()), Toast.LENGTH_SHORT).show();

        switch ((instructionList.get(index)).getButton())
        {
            case 0: findViewById(R.id.btn0).startAnimation(shake);
                break;
            case 1: findViewById(R.id.btn1).startAnimation(shake);
                break;
            case 2: findViewById(R.id.btn2).startAnimation(shake);
                break;
            case 3: findViewById(R.id.btn3).startAnimation(shake);
        }

        shake.setAnimationListener(new Animation.AnimationListener(){
            public void onAnimationStart(Animation a){}
            public void onAnimationRepeat(Animation a){}
            public void onAnimationEnd(Animation a){
                index++;
                playNextInstruction();
            }
            });

    }

    private void addNextInstruction()
    {
        instructionList.add(new Instruction(game));
    }

    public void exitToMenu(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);

        startActivity(intent);
    }
}
