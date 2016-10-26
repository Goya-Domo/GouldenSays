package com.example.marshall.gouldensays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity
{
    private int instIndex;
    private int responseIndex;
    private boolean animating;
    private Game game;
    private ArrayList<Instruction> instructionList;
    //private ArrayList<View> images;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_layout);

        game = new Game();
        instructionList = new ArrayList<>();
        instIndex = 0;
        responseIndex = 0;

        addNextInstruction();
        addNextInstruction();
        playNextInstruction();
    }

    private void playNextInstruction()
    {
        if (instIndex >= instructionList.size())
        {
            instIndex = 0;
            animating = false;
            return;
        }
        animating = true;

        //Rotate to display instruction
        RotateAnimation ra = null;
        switch ((instructionList.get(instIndex)).getButton())
        {
            case 0: ra = rotation(findViewById(R.id.yellow_hex), true, 6);
                break;
            case 1: ra = rotation(findViewById(R.id.red_hex), true, 6);
                break;
            case 2: ra = rotation(findViewById(R.id.blue_hex), true, 6);
                break;
            case 3: ra = rotation(findViewById(R.id.green_hex), true, 6);
        }

        //When done rotating, rotate the next button
        if (ra != null)
        {
            ra.setAnimationListener(new Animation.AnimationListener()
            {
                public void onAnimationStart(Animation a) {}

                public void onAnimationRepeat(Animation a) {}

                public void onAnimationEnd(Animation a)
                {
                    instIndex++;
                    playNextInstruction();
                }
            });
        }
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
        if (!animating)
        {
            rotation(view, true);
            respond(view);
        }
    }

    private boolean respond(View view)
    {
        boolean correct = false;
        if (view.getTag() instanceof Integer)
        {
            Integer hexNum = (Integer)view.getTag();

            if (instructionList.get(responseIndex).equals(hexNum))
            {
                correct = true;
                if (++responseIndex == instructionList.size())
                {
                    responseIndex = 0;
                    addNextInstruction();
                    playNextInstruction();
                }
            }
            else
            {
                exitToMenu(view);
            }
        }
        else
        {
            exitToMenu(view);
        }

        return correct;
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

    public void debugAddHex(View view)
    {
        if (findViewById(R.id.black_hex).getVisibility() == View.INVISIBLE)
        {
            findViewById(R.id.black_hex).setVisibility(View.VISIBLE);
        }
        else
        {
            if (findViewById(R.id.white_hex).getVisibility() == View.INVISIBLE)
            {
                findViewById(R.id.white_hex).setVisibility(View.VISIBLE);
            }
        }
    }

    public void rotation(View view, boolean clockwise)
    {
        RotateAnimation anim;
        if(clockwise)
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

    public RotateAnimation rotation(View view, boolean clockwise, int nTimes)
    {
        RotateAnimation anim;
        if(clockwise)
        {
            anim = new RotateAnimation(0.0f, (float)(60.0 * nTimes), Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        else
        {
            anim = new RotateAnimation((float)(nTimes * 60.0), 0.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        }
        anim.setInterpolator(new LinearInterpolator());

        anim.setDuration(200 * nTimes); //Put desired duration per anim cycle here, in milliseconds

        view.startAnimation(anim);
        return anim;
    }


}
