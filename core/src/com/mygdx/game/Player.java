package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tomasz Pilarczyk
 */
class Player extends Square {

    private int speed=30;    //Time to move in milliseconds
    private Color color;
    private List<Square> squares = new ArrayList<Square>();
    private int points;
    int velX = 1, velY = 0;
    private boolean move = false;
    private boolean dead;
    private TimeUtil timeUtil;

    Player(int x, int y,Color color) {
        reset(x,y);
        move = false;
        this.color=color;
        timeUtil = new TimeUtil();

    }

    void draw(ShapeRenderer shapeRenderer) {
        if(timeUtil.countdown(speed) && !isDead())move=true;
        for (Square square : squares) {
                square.draw(shapeRenderer, color);
        }
    }

    void movement() {
        if (move) {
            outOfBounds();
            moveSquares();
            move = false;
        }
    }

    void selfCollision(){
        for (int i=1;i<squares.size();i++){
            if (GameUtil.collision(this,squares.get(i))){
                death();
            }
        }
    }

    void enemyCollision(Player player){
        for(int i=1;i<player.squares.size();i++){
            if(GameUtil.collision(this,player.squares.get(i))){
                if(!isDead())player.setPoints(player.getPoints()+1);
                death();
                player.death();
            }
        }
    }

    private void death(){
        velY=0;velX=0;
        move=false;
        dead =true;
    }

    void reset(int x, int y){
        dead =false;
        this.x=x;this.y=y;
        velX=1;velY=0;
        if(!squares.isEmpty())squares.clear();
        addDefaultSquares();
        //points=0;             //TODO: for singleplayer
    }

    private void addDefaultSquares(){
        squares.add(new Square(x, y));
        addSquare();
        addSquare();
    }

    private void outOfBounds(){
        if(x+width> MyGdxGame.screenWidth)x=0;
        if(x<0)x=MyGdxGame.screenWidth;
        if(y+width>MyGdxGame.screenHeight)y=0;
        if(y<0)y=MyGdxGame.screenHeight;
    }

    private void moveSquares(){
        int tmpX = squares.get(0).x;
        int tmpY = squares.get(0).y;

        x = x + velX * width;
        squares.get(0).x=x;
        y = y + velY * width;
        squares.get(0).y=y;
        for (int i = 1; i < squares.size(); i++) {
            int tmpX2 = squares.get(i).x;
            int tmpY2 = squares.get(i).y;
            squares.get(i).x=tmpX;
            squares.get(i).y=tmpY;
            tmpX = tmpX2;
            tmpY = tmpY2;
        }
    }

    void addSquare(){
        int howMuchAlreadyAre=squares.size();
        Square last=squares.get(howMuchAlreadyAre-1);
        squares.add(new Square(last.x-(velX*width), last.y-(velY*width) ));
    }
    void delSquare(){
        int howMuchAlreadyAre=squares.size();
        Square last=squares.get(howMuchAlreadyAre-1);
        if(howMuchAlreadyAre>1){
            squares.remove(last);
        }
    }

    int getX() {
        return x;
    }

    int getY() {
        return y;
    }

    int getPoints() {
        return points;
    }

    void setPoints(int points) {
        this.points = points;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public List<Square> getSquares() {
        return squares;
    }

    public Color getColor() {
        return color;
    }
}
