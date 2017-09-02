package com.mygdx.game;



import java.util.Random;

/**
 * @author Tomasz Pilarczyk
 */
class Point extends Square {
    private int points = 10;
    Random generator;

    Point(){
        generator = new Random();
    }

    Point(int x, int y) {
        super(x, y);
        generator = new Random();
    }

    void placeRandom(){
        int possibleXs= MyGdxGame.screenWidth/width;
        int possibleYs= MyGdxGame.screenHeight/width;

        int randomX = generator.nextInt(possibleXs);
        int randomY = generator.nextInt(possibleYs);

        x=randomX*width;
        y=randomY*width;
    }

    void checkIfTaken(Player player){
        if(GameUtil.collision(player,this)){
            //player.setPoints(player.getPoints()+10);      //TODO: singleplayer
            player.addSquare();
            placeRandom();
        }

    }

}
