package cr.ac.itcr.andreifuentes.flappybirdclase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private float gravity;	 //facil  0.2		//medio 0.6		//dificil 1
    private int lift;       //facil  10		//medio 20		//dificil 30
    private float distance_rate;    //facil  0.8		//medio 0.6		//dificil 0.45
    MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.AudioFile1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void setEasy(View view){
        gravity = 0.2f;
        lift = 10;
        distance_rate = 0.8f;
        empezar_juego();
    }

    public void setNormal(View view){
        gravity = 0.6f;
        lift = 20;
        distance_rate = 0.6f;
        empezar_juego();
    }

    public void setHard(View view){
        gravity = 1f;
        lift = 30;
        distance_rate = 0.45f;
        empezar_juego();
    }

    public void setSound(View view){

    }

    public void empezar_juego(){
        Intent i = new Intent(this, AndroidLauncher.class);
        i.putExtra("gravity", gravity);
        i.putExtra("lift", lift);
        i.putExtra("distance_rate", distance_rate);
        startActivity(i);
    }
}
