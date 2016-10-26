package com.example.marshall.gouldensays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity
{
    private int instIndex;
    private int responseIndex;
    private Game game;
    private ArrayList<Instruction> instructionList;
    //private ArrayList<View> images;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);

       /* images.add(findViewById(R.id.yellow_hex));
        images.add(findViewById(R.id.red_hex));
        images.add(findViewById(R.id.green_hex));
        images.add(findViewById(R.id.blue_hex));*/

        game = new Game();
        instructionList = new ArrayList<>();
        instIndex = 0;
        responseIndex = 0;

        addNextInstruction();
        playNextInstruction();
    }

    private void playNextInstruction()
    {
        if (instIndex >= instructionList.size())
        {
            instIndex = 0;
            return;
        }

        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.buttonsig);

        Toast.makeText(getApplicationContext(), String.valueOf(instructionList.get(instIndex).getButton()), Toast.LENGTH_SHORT).show();

        switch ((instructionList.get(instIndex)).getButton())
        {
            case 0: findViewById(R.id.red_hex).startAnimation(shake);
                break;
            case 1: findViewById(R.id.blue_hex).startAnimation(shake);
                break;
            case 2: findViewById(R.id.green_hex).startAnimation(shake);
                break;
            case 3: findViewById(R.id.yellow_hex).startAnimation(shake);
        }

        shake.setAnimationListener(new Animation.AnimationListener(){
            public void onAnimationStart(Animation a){}
            public void onAnimationRepeat(Animation a){}
            public void onAnimationEnd(Animation a){
                instIndex++;
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

    public void hexClick(View view)
    {
        rotation(view, 1);
    }

    /*public void addButton5()
    {
        images.add(findViewById(R.id.black_hex));
        foreach (View img: images)
        {

        }
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(img.getLayoutParams());
        lp.setMargins(50, 100, 0, 0);
        img.setLayoutParams(lp);

    }*/

    public void rotation(View view, int direction)
    {
        RotateAnimation anim;
        if(direction > 1)
        {
            anim = new RotateAnimation(0.0f, 60.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        else
        {
            anim = new RotateAnimation(60.0f, 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        anim.setInterpolator(new LinearInterpolator());

        anim.setDuration(200); //Put desired duration per anim cycle here, in milliseconds

        view.startAnimation(anim);
    }


}
