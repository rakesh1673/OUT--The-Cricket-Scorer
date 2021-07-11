package com.example.out;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnContinue;

    private ImageView tossView;

    private Button btnStart;

    private Spinner spnPlayerNum;
    private Spinner spnOver;

    private AnimationDrawable tossAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tossView = findViewById(R.id.btn_toss);
        btnContinue = findViewById(R.id.btn_continue);
        btnStart = findViewById(R.id.btn_start);

        spnOver = findViewById(R.id.spn_over);
        spnPlayerNum = findViewById(R.id.spn_player_number);

        btnContinue.setOnClickListener(this);
        btnStart.setOnClickListener(this);

        tossView.setBackgroundResource(R.drawable.toss_animation_tail);

        tossView.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.coinflip);
                player.start();
                // Pass our animation drawable to our custom drawable class
                final CustomAnimationDrawableNew cad = new CustomAnimationDrawableNew(
                        (AnimationDrawable) tossView.getBackground()) {
                    @Override
                    void onAnimationFinish() {

                        Random random = new Random();
                        int r = random.nextInt();
                        if (r % 2 == 0) {
                            v.setBackgroundResource(R.drawable.toss_animation_tail);
                        } else {
                            v.setBackgroundResource(R.drawable.toss_animation_head);
                        }
                    }
                };

                // Set the views drawable to our custom drawable
                v.setBackgroundDrawable(cad);

                // Start the animation
                cad.start();
            }
        });

        List<String> overs = new ArrayList<>();
        for (int i = 1; i <= 50; i++) {
            overs.add(String.valueOf(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, overs);
        spnOver.setAdapter(adapter);

        List<String> players = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            players.add(String.valueOf(i));
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, players);
        spnPlayerNum.setAdapter(adapter);
        spnPlayerNum.setSelection(11);
        spnOver.setSelection(12);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onClick(View v) {
        if (v == btnContinue) {
            Intent intent = new Intent(this, ScoreUpdateActivity.class);
            intent.putExtra("CONTINUE", true);
            intent.putExtra("START", false);
            Datasource db = new Datasource(this);
            db.open();
            try {
                db.getTeamScore(1);
                startActivity(intent);
            } catch (Exception e) {
                Toast.makeText(this, "No match is running. Start match first", Toast.LENGTH_SHORT).show();
            }
        } else if (v == btnStart) {
            Intent intent = new Intent(this, ScoreUpdateActivity.class);
            intent.putExtra("OVERS", Integer.parseInt(spnOver.getSelectedItem().toString()));
            intent.putExtra("PLAYERS", Integer.parseInt(spnPlayerNum.getSelectedItem().toString()));
            intent.putExtra("START", true);
            startActivity(intent);
        }
    }

    public abstract static class CustomAnimationDrawableNew extends AnimationDrawable {


        Handler mAnimationHandler;

        public CustomAnimationDrawableNew(AnimationDrawable aniDrawable) {
            /* Add each frame to our animation drawable */
            for (int i = 0; i < aniDrawable.getNumberOfFrames(); i++) {
                this.addFrame(aniDrawable.getFrame(i), aniDrawable.getDuration(i));
            }
        }

        @Override
        public void start() {
            super.start();
            /*
             * Call super.start() to call the base class start animation method.
             * Then add a handler to call onAnimationFinish() when the total
             * duration for the animation has passed
             */
            mAnimationHandler = new Handler();
            mAnimationHandler.postDelayed(new Runnable() {

                public void run() {
                    onAnimationFinish();
                }
            }, getTotalDuration());

        }

        /**
         * Gets the total duration of all frames.
         *
         * @return The total duration.
         */
        public int getTotalDuration() {

            int iDuration = 0;

            for (int i = 0; i < this.getNumberOfFrames(); i++) {
                iDuration += this.getDuration(i);
            }

            return iDuration;
        }

        /**
         * Called when the animation finishes.
         */
        abstract void onAnimationFinish();
    }
}