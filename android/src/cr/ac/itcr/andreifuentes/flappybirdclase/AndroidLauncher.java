package cr.ac.itcr.andreifuentes.flappybirdclase;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import cr.ac.itcr.andreifuentes.flappybirdclase.FlappyBird;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Bundle b = getIntent().getExtras();
		float gravity = b.getFloat("gravity");
		int lift= b.getInt("lift");
		float distance_rate= b.getFloat("distance_rate");
		System.out.println(Float.toString(gravity));
		System.out.println(Integer.toString(lift));
		System.out.println(Float.toString(distance_rate));
		initialize(new FlappyBird(gravity, lift, distance_rate), config);
	}
}
