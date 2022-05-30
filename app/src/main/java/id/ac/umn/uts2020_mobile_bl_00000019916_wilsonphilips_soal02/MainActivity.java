package id.ac.umn.uts2020_mobile_bl_00000019916_wilsonphilips_soal02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button  btnStart, btnProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStart);
        btnProfile = findViewById(R.id.btnProfile);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaPlayer startAudio = MediaPlayer.create(MainActivity.this, R.raw.start);
                startAudio.start(); // no need to call prepare(); create() does that for you
                startAudio.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer startAudio) {
                        startAudio.release();
                    };
                });
                Intent intentGameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intentGameActivity);
            }
        });

        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentProfileActivity = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intentProfileActivity);
            }
        });
    }

}
