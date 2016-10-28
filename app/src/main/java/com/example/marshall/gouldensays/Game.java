package com.example.marshall.gouldensays;

import java.util.ArrayList;
import java.util.Random;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.Animation;
import android.view.View;
import android.widget.ImageView;

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
    private int numButtons;

    private int instIndex; //Iterates through instructions
    private int responseIndex; //Increments while user inputs responses
    private boolean animating; //To prevent input while showing instructions
    private boolean animationAlternator; //To alternate hex spinning while showing instructions
    private ArrayList<Instruction> instructionList;
    private ArrayList<ImageView> buttons;

    private Random rng;
    public Game(ArrayList<ImageView> buttons)
    {
        //create a random seed and save it.
        this.buttons = buttons;
        numButtons = 4;
        rng = new Random();
        seed = rng.nextLong();
        rng.setSeed(seed);

        instructionList = new ArrayList<>();
        instIndex = 0;
        responseIndex = 0;
        animationAlternator = true;
    }

    public Game(long seed)
    {
        numButtons = 4;
        this.seed = seed;
        rng = new Random(seed);
    }


    public void newGame()
    {
        rng = new Random();
        numButtons = 4;
        buttons.get(4).setVisibility(View.INVISIBLE);
        buttons.get(5).setVisibility(View.INVISIBLE);
        instructionList = new ArrayList<>();
        addNextInstruction();
        addNextInstruction();
        playNextInstruction();
    }

    private void addNextInstruction() { instructionList.add(new Instruction(this)); }

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
        RotateAnimation ra;

        ra = rotation(buttons.get(instructionList.get(instIndex).getButton()), animationAlternator, 6);
        animationAlternator = !animationAlternator;

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

    public boolean respond(View view)
    {
        boolean correct = true;
        if (!animating)
        {
            rotation(view, animationAlternator);
            animationAlternator = !animationAlternator;
            try
            {
                int hexNum = Integer.parseInt((String) view.getTag());

                if (instructionList.get(responseIndex).getButton() == hexNum)
                {
                    correct = true;
                    if (++responseIndex == instructionList.size())
                    {
                        if (responseIndex == 4)
                        {
                            addHex();

                        }
                        else
                        {
                            if (responseIndex == 6)
                            {
                                addHex();
                            }
                        }
                        responseIndex = 0;
                        addNextInstruction();
                        playNextInstruction();
                    }
                }
                else
                {
                    correct = false;
                    animating = true;
                    for (ImageView iv : buttons)
                    {
                        if (iv.getVisibility() == View.VISIBLE)
                        {
                            animationAlternator = !animationAlternator;

                            if (iv == buttons.get(numButtons - 1))
                            {

                                rotation(iv, animationAlternator, 6).setAnimationListener(new Animation.AnimationListener()
                                {
                                    public void onAnimationStart(Animation a) {}

                                    public void onAnimationRepeat(Animation a) {}

                                    public void onAnimationEnd(Animation a)
                                    {
                                        newGame();
                                    }
                                });
                            }
                            else
                            {
                                rotation(iv, animationAlternator, 6);
                            }
                        }
                    }
                }
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
                correct = false;
            }
        }

        return correct;
    }

    public void addHex()
    {
        if (buttons.get(4).getVisibility() == View.INVISIBLE)
        {
            buttons.get(4).setVisibility(View.VISIBLE);
            addNumButtons(1);
        }
        else
        {
            if (buttons.get(5).getVisibility() == View.INVISIBLE)
            {
                buttons.get(5).setVisibility(View.VISIBLE);
                addNumButtons(1);
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
        //anim.setStartOffset(100);

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
        anim.setStartOffset(100);

        view.startAnimation(anim);
        return anim;
    }

    public int getNumButtons()
    { return numButtons; }

    public void addNumButtons(int addThisMany)
    {
        if (numButtons + addThisMany < 0 || numButtons + addThisMany > 6)
        {
            return;
        }

        numButtons += addThisMany;
    }

    public Random getRng()
    {
        return rng;
    }

}
