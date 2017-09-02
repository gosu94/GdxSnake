package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class MyGdxGame extends ApplicationAdapter {
	private SpriteBatch batch;
	private ShapeRenderer shapeRenderer;
    private BitmapFont regularFont;
	private Texture img;
    private Player player;
    private Player player2;
    private Point point;
    private PowerUp powerUp;
	static int screenWidth;
    static int screenHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        regularFont = new BitmapFont();
        player = new Player(50,300,Color.RED);
        player2 = new Player(50,200,Color.BLUE);
		img = new Texture("badlogic.jpg");
        Gdx.input.setInputProcessor(new InputReader(player,player2));
        screenWidth = Gdx.app.getGraphics().getWidth();
        screenHeight = Gdx.app.getGraphics().getHeight();
        point = new Point();
        powerUp = new PowerUp();
        point.placeRandom();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
        player.draw(shapeRenderer);
        player2.draw(shapeRenderer);
        point.draw(shapeRenderer,Color.WHITE);
        powerUp.draw(shapeRenderer);
		batch.end();

        drawHUD();
        player.movement();
        player.enemyCollision(player2);
        player2.movement();
        player2.enemyCollision(player);
        point.checkIfTaken(player);
        point.checkIfTaken(player2);

        powerUp.placeInRandomTime();
        powerUp.checkIfTaken(player);
        powerUp.checkIfTaken(player2);
        powerUp.checkIfActive(player,player2);


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
        shapeRenderer.dispose();
	}

	private void drawHUD(){
        batch.begin();
        regularFont.setColor(Color.WHITE);
        regularFont.draw(batch, "Player1: "+Integer.toString(player.getPoints()), screenWidth-80, 475);
        regularFont.draw(batch, "Player2: "+Integer.toString(player2.getPoints()), 10, 475);
        batch.end();
    }

}
