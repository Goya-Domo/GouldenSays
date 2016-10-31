package com.example.marshall.gouldensays;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import java.util.ArrayList;
import android.view.animation.*;
public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageView> mainButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* mainButtons.add((ImageView)findViewById(R.id.start));
        mainButtons.add((ImageView)findViewById(R.id.about));
        mainButtons.add((ImageView)findViewById(R.id.how_to));
        mainButtons.add((ImageView)findViewById(R.id.settings));
        mainButtons.add((ImageView)findViewById(R.id.sounds));*/
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

    //setting up transition animations, pardon the dust

    /*public void selectionAnimation()
    {
        ImageView image;
        for(int i = 0; i < mainButtons.size(); ++i) {
            image = mainButtons.get(i);
            if (i < 1)
            {
                TranslateAnimation animation = new TranslateAnimation(0, 50, 0, 100);
                animation.setDuration(1000);
                animation.setFillAfter(false);
                animation.setAnimationListener(new MyAnimationListener());

                image.startAnimation(animation);
            }
            else
            {

            }
        }
    }

    private class MyAnimationListener implements AnimationListener{

        @Override
        public void onAnimationEnd(Animation animation) {
            image.clearAnimation();
            LayoutParams lp = new LayoutParams(image.getWidth(), image.getHeight());
            lp.setMargins(50, 100, 0, 0);
            image.setLayoutParams(lp);
        }

        @Override
        public void onAnimationRepeat(Animation animation) {
        }

        @Override
        public void onAnimationStart(Animation animation) {
        }

    }*/
}
