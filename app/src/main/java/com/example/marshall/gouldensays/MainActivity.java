package com.example.marshall.gouldensays;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import java.util.ArrayList;
import android.view.animation.*;
import android.view.animation.Animation.AnimationListener;
import android.widget.LinearLayout.LayoutParams;
import  	android.os.Handler;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> mainButtons = new ArrayList<>();
    private ArrayList<TextView> mainText = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        mainButtons.add((ImageView)findViewById(R.id.start));
        mainButtons.add((ImageView)findViewById(R.id.how_to));
        mainButtons.add((ImageView)findViewById(R.id.settings));
        mainButtons.add((ImageView)findViewById(R.id.about));
        mainButtons.add((ImageView)findViewById(R.id.sounds));

        mainText.add((TextView)findViewById(R.id.start_text));
        mainText.add((TextView)findViewById(R.id.how_to_text));
        mainText.add((TextView)findViewById(R.id.settings_text));
        mainText.add((TextView)findViewById(R.id.about_text));
        mainText.add((TextView)findViewById(R.id.sounds_text));
    }

    public void startGame(View view)
    {
        //Create intent that the animation listenter will launch
        final Intent intent = new Intent(this, GameActivity.class);

        //Start animation w/ listener
        selectionAnimation().setAnimationListener(new AnimationListener()
        {
            @Override
            public void onAnimationEnd(Animation animation)
            {
                //Start game
                startActivity(intent);
            }

            @Override public void onAnimationRepeat(Animation animation) { }
            @Override public void onAnimationStart(Animation animation) { }
        });
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

    public TranslateAnimation selectionAnimation()
    {
        TranslateAnimation retAnim = null;
        ImageView image;
        TextView text;
        for(int i = 0; i < mainButtons.size(); ++i) {
            image = mainButtons.get(i);
            text = mainText.get(i);
            if (i < 1)
            {
                retAnim = new TranslateAnimation(0, 0, 0, 900);
                retAnim.setDuration(400);
                retAnim.setFillAfter(false);
                retAnim.setStartOffset(100);
                image.startAnimation(retAnim);
                text.startAnimation(retAnim);
            }
            else if (i < 3)
            {
                TranslateAnimation animation = new TranslateAnimation(0, -500, 0, 0);
                animation.setDuration(400);
                animation.setFillAfter(false);
                image.startAnimation(animation);
                text.startAnimation(animation);
            }
            else
            {
                TranslateAnimation anim = new TranslateAnimation(0, 500, 0, 0);
                anim.setDuration(400);
                anim.setFillAfter(false);
                image.startAnimation(anim);
                text.startAnimation(anim);

                //Anon listener so the buttons stay invisible instead of snapping back into place.
                anim.setAnimationListener(new AnimationListener()
                {
                    @Override
                    public void onAnimationEnd(Animation animation)
                    {
                        //Set buttons and text invisible
                        for (ImageView view : mainButtons)
                        {
                            view.setVisibility(View.INVISIBLE);
                        }
                        for (TextView text : mainText)
                        {
                            text.setVisibility(View.INVISIBLE);
                        }
                    }
                    @Override public void onAnimationStart(Animation animation) { }
                    @Override public void onAnimationRepeat(Animation animation) { }
                });
            }
        }
        return retAnim;
    }
}
