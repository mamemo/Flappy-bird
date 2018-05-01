package cr.ac.itcr.andreifuentes.flappybirdclase;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/*
	This class contains the call to the gdx class to play the game.
 */
public class AndroidLauncher extends AndroidApplication {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();

        //Gets details on difficulty
        Bundle b = getIntent().getExtras();
        float gravity = b.getFloat("gravity");
        int lift = b.getInt("lift");
        float distance_rate = b.getFloat("distance_rate");

        initialize(new FlappyBird(gravity, lift, distance_rate), config);
    }

}
