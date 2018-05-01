package cr.ac.itcr.andreifuentes.flappybirdclase;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

/*
    This class contains the logic behind the game.
 */
public class FlappyBird extends ApplicationAdapter {
    private SpriteBatch batch;
    private Random random;
    private BitmapFont font;
    private Circle birdCircle;
    private Rectangle[] topPipes;
    private Rectangle[] bottomPipes;

    //Textures to show
    private Texture background;
    private Texture topTube;
    private Texture bottomTube;
    private TextureRegion[] birds;
    private Texture gameOver;

    //Variables to control the game
    private int birdState;
    private float gap;
    private float birdY;
    private float velocity;
    private float gravity;
    private int numberOfPipes = 4;
    private float pipeX[] = new float[numberOfPipes];
    private float pipeYOffset[] = new float[numberOfPipes];
    private float distance;
    private float pipeVelocity = 5;
    private float maxLine;
    private float minLine;
    private int score;
    private int pipeActivo;
    private int game_state;
    private int birdSize;
    private int lift;
    private float distance_rate;
    private int rotation;


    public FlappyBird(float gravity, int lift, float distance_rate) {
        this.gravity = gravity;
        this.lift = lift;
        this.distance_rate = distance_rate;
    }

    @Override
    public void create() {
        batch = new SpriteBatch();

        background = new Texture("bg.png");
        birds = new TextureRegion[2];
        birds[0] = new TextureRegion(new Texture("bird.png"));
        birds[1] = new TextureRegion(new Texture("bird2.png"));
        topTube = new Texture("toptube.png");
        bottomTube = new Texture("bottomtube.png");
        gameOver = new Texture("gameOverOriginal.png");

        birdCircle = new Circle();
        topPipes = new Rectangle[numberOfPipes];
        bottomPipes = new Rectangle[numberOfPipes];

        birdState = 0;
        game_state = 0;
        gap = 500;
        velocity = 0;
        random = new Random();
        distance = Gdx.graphics.getWidth() * distance_rate;
        maxLine = Gdx.graphics.getHeight() * 3 / 4;
        minLine = Gdx.graphics.getHeight() * 1 / 4;
        score = 0;
        pipeActivo = 0;
        birdSize = 0;

        rotation = 0;

        font = new BitmapFont();
        font.setColor(Color.WHITE);
        font.getData().setScale(10);

        startGame();
    }

    public void startGame() {
        birdY = Gdx.graphics.getHeight() / 2 - birds[birdState].getRegionHeight() / 2;
        for (int i = 0; i < numberOfPipes; i++) {
            pipeYOffset[i] = (random.nextFloat() * (maxLine - minLine) + minLine);
            pipeX[i] = Gdx.graphics.getWidth() / 2 - topTube.getWidth() + Gdx.graphics.getWidth() + distance * i;

            // Initialize all the shapes
            topPipes[i] = new Rectangle();
            bottomPipes[i] = new Rectangle();
        }
    }

    @Override
    public void render() {
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        // Before starts
        if (game_state == 0) {
            if (Gdx.input.justTouched()) {
                game_state = 1;
            }
        }
        // Playing
        else if (game_state == 1) {
            if (pipeX[pipeActivo] < Gdx.graphics.getWidth() / 2 - topTube.getWidth()) {
                score++;

                if (pipeActivo < numberOfPipes - 1) {
                    pipeActivo++;
                } else {
                    pipeActivo = 0;
                }
            }


            birdCircle.set(Gdx.graphics.getWidth() / 2, birdY + birds[birdState].getRegionHeight() / 2, birds[birdState].getRegionWidth() / 2);


            // Pipes positioning
            for (int i = 0; i < numberOfPipes; i++) {

                if (pipeX[i] < -topTube.getWidth()) {
                    pipeYOffset[i] = (random.nextFloat() * (maxLine - minLine) + minLine);
                    pipeX[i] += distance * (numberOfPipes);
                } else {
                    pipeX[i] = pipeX[i] - pipeVelocity;
                }

                batch.draw(topTube,
                        pipeX[i],
                        pipeYOffset[i] + gap / 2,
                        topTube.getWidth(),
                        topTube.getHeight());
                batch.draw(bottomTube,
                        pipeX[i],
                        pipeYOffset[i] - bottomTube.getHeight() - gap / 2,
                        bottomTube.getWidth(),
                        bottomTube.getHeight());

                topPipes[i] = new Rectangle(pipeX[i],
                        pipeYOffset[i] + gap / 2,
                        topTube.getWidth(),
                        topTube.getHeight());
                bottomPipes[i] = new Rectangle(pipeX[i],
                        pipeYOffset[i] - bottomTube.getHeight() - gap / 2,
                        bottomTube.getWidth(),
                        bottomTube.getHeight());


                if (Intersector.overlaps(birdCircle, topPipes[i])) {
                    game_state = 2;
                } else if (Intersector.overlaps(birdCircle, bottomPipes[i])) {
                    game_state = 2;
                }
            }

            if (Gdx.input.justTouched()) {
                velocity = velocity - lift;
            }

            birdState = birdState == 0 ? 1 : 0;


            velocity = velocity + gravity;

            if (birdY < 0 || birdY > Gdx.graphics.getHeight()) {
                game_state = 2;
            } else {
                birdY = birdY - velocity;
            }

            if (Math.round(velocity) == 0f) {
                rotation = 0;
            } else if (Math.round(velocity) < 0f) {
                rotation = 45;
            } else if (Math.round(velocity) > 0f) {
                rotation = 315;
            }


        }
        // game over
        else if (game_state == 2) {
            batch.draw(gameOver, Gdx.graphics.getWidth() / 2 - gameOver.getWidth() / 2, Gdx.graphics.getHeight() / 2 - gameOver.getHeight() / 2);
            if (Gdx.input.justTouched()) {
                game_state = 1;
                score = 0;
                pipeActivo = 0;
                velocity = 0;
                startGame();
            }
        }

        //Draw bird
        batch.draw(birds[birdState].getTexture(), Gdx.graphics.getWidth() / 2 - birds[birdState].getRegionWidth() / 2, birdY, birds[birdState].getRegionWidth() / 2, birds[birdState].getRegionHeight() / 2, birds[birdState].getRegionWidth(), birds[birdState].getRegionHeight(), 1, 1, rotation, birds[birdState].getRegionX(), birds[birdState].getRegionY(), birds[birdState].getRegionWidth(), birds[birdState].getRegionHeight(), false, false);

        //Draw score
        font.draw(batch, Integer.toString(score), Gdx.graphics.getWidth() * 1 / 8, Gdx.graphics.getHeight() * 9 / 10);
        batch.end();

    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
    }
}
