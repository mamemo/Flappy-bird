package cr.ac.itcr.andreifuentes.flappybirdclase;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

/*
    This class contains the game menu, the user can choose the difficulty and if they want to play sound.
 */

public class MainActivity extends AppCompatActivity {

    private float gravity;     //easy  0.2		//normal 0.6		//hard 1
    private int lift;       //easy  10		//normal 20		//hard 30
    private float distance_rate;    //easy  0.8		//normal 0.6		//hard 0.45
    private MediaPlayer mp;
    private boolean music_on;

    //Creates the mediaplayer
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mp = MediaPlayer.create(getApplicationContext(), R.raw.shootingstars);
        mp.setLooping(true);
        mp.start();
        music_on = true;
    }

    //Set game on easy
    public void setEasy(View view) {
        gravity = 0.2f;
        lift = 10;
        distance_rate = 0.8f;
        start_game();
    }

    //Set game on normal
    public void setNormal(View view) {
        gravity = 0.6f;
        lift = 20;
        distance_rate = 0.6f;
        start_game();
    }

    //Set game on hard
    public void setHard(View view) {
        gravity = 1f;
        lift = 30;
        distance_rate = 0.45f;
        start_game();
    }

    //Calls the game activity
    public void start_game() {
        Intent i = new Intent(this, AndroidLauncher.class);
        i.putExtra("gravity", gravity);
        i.putExtra("lift", lift);
        i.putExtra("distance_rate", distance_rate);
        startActivity(i);
    }

    //Controls the sound (play/stop)
    public void setSound(View view) {
        if (music_on) {
            mp.pause();
            music_on = false;
        } else {
            mp.start();
            music_on = true;
        }
    }

}
