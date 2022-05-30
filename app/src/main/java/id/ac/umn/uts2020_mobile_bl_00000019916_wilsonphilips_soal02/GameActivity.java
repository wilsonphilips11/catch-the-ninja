package id.ac.umn.uts2020_mobile_bl_00000019916_wilsonphilips_soal02;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GameActivity extends AppCompatActivity {
    ImageView           ninja, scratch;
    Button              buttonExit;
    ConstraintLayout    gameView;
    ProgressBar         hpBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ninja = findViewById(R.id.ninja);
        buttonExit = findViewById(R.id.buttonExit);
        buttonExit.setVisibility(View.GONE);
        scratch = findViewById(R.id.scratch);
        scratch.setVisibility(View.GONE);
        gameView = findViewById(R.id.gameView);
        hpBar = findViewById(R.id.hpBar);

        //Calculate Ninja Move Field
        final DisplayMetrics screenSize = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        final float height = screenSize.heightPixels - 430; //Diff with ninja height and width
        final float width  = screenSize.widthPixels - 120;

        //Random Ninja Start Position
        Random startPosition = new Random();
        ninja.setX(startPosition.nextFloat() * width);
        ninja.setY(Math.max(55, startPosition.nextFloat() * height));

        //Random Ninja Move Position
        final Timer timer = new Timer();
        final long start = System.currentTimeMillis();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Random position = new Random();
                        float dx = position.nextFloat() * width;
                        float dy = position.nextFloat() * height;
                        float destX;
                        float destY;

                        if((ninja.getX() + dx) > width)
                            destX = Math.max(0, ninja.getX() - dx);
                        else
                            destX = Math.min(width, ninja.getX() + dx);

                        if((ninja.getY() + dy) > height)
                            destY = Math.max(55, ninja.getY() - dy);
                        else
                            destY = Math.min(height, ninja.getY() + dy);

                        final AnimatorSet ninjaMoveXY = new AnimatorSet();
                        ObjectAnimator moveX = ObjectAnimator.ofFloat(ninja, "translationX", ninja.getX(), destX);
                        ObjectAnimator moveY = ObjectAnimator.ofFloat(ninja, "translationY", ninja.getY(), destY);
                        ninjaMoveXY.playTogether(moveX, moveY);
                        ninjaMoveXY.setInterpolator(new AccelerateInterpolator(5.0f * 20 / hpBar.getProgress()));
                        ninjaMoveXY.start();

                        ninja.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                MediaPlayer hitAudio = MediaPlayer.create(GameActivity.this,R.raw.hit);
                                hitAudio.start();
                                hitAudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                                    public void onCompletion(MediaPlayer hitAudio) {
                                        hitAudio.release();
                                    };
                                });
                                if(hpBar.getProgress() == 0) {
                                    ninjaMoveXY.cancel();
                                    Animation ninjaHitAnimation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
                                    ninja.startAnimation(ninjaHitAnimation);
                                    Toast.makeText(gameView.getContext(), "Finished in " + Long.toString((System.currentTimeMillis() - start)) + " ms", Toast.LENGTH_LONG).show();
                                    buttonExit.setVisibility(View.VISIBLE);
                                    timer.cancel();
                                    timer.purge();
                                    }
                                else {
                                    hpBar.incrementProgressBy(-20);
                                }
                            }
                        });
                    }
                });
            }
        }, 0, 1000);

        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentMainActivity = new Intent(GameActivity.this, MainActivity.class);
                startActivity(intentMainActivity);
            }
        });


        gameView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        float getTouchX = motionEvent.getX();
                        float getTouchY = motionEvent.getY();
                        scratch.setX(getTouchX);
                        scratch.setY(getTouchY);
                        scratch.setVisibility(View.VISIBLE);
                        Animation scratchAnimation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_out);
                        scratch.startAnimation(scratchAnimation);
                        MediaPlayer missAudio = MediaPlayer.create(GameActivity.this, R.raw.miss);
                        missAudio.start();
                        missAudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                            public void onCompletion(MediaPlayer missAudio) {
                                missAudio.release();
                            };
                        });
                        break;
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return true;
            }
        });
    }

}
