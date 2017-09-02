package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


/**
 * @author Tomasz Pilarczyk
 */
public class PowerUp extends Point {
    enum Type {SPEED, DOUBLE, SHORTER}

    int TIME_TO_RESPAWN = 10000;
    int PROCENTAGE_CHANCE_OF_RESPAWN= 50;
    int TIME_OF_ACTIVE = 10000;

    private Type type;
    private boolean isActive;
    private boolean activated;
    private boolean deactivated;
    private TimeUtil activeTimer;
    private TimeUtil placeTimer;
    private int squaresRemoved;
    private int squaresAdded;
    private int whichPlayer;

    PowerUp(){
        super();
        x=-50;
        y=-50;
        isActive = false;
        activated = false;
        deactivated = false;
        type = Type.DOUBLE;
        squaresAdded = 0;
        squaresRemoved = 0;
        activeTimer = new TimeUtil();
        placeTimer = new TimeUtil();
    }

    void draw(ShapeRenderer shapeRenderer){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        switch (type){
            case SPEED:
                shapeRenderer.setColor(Color.CYAN);
                break;
            case DOUBLE:
                shapeRenderer.setColor(Color.GOLD);
                break;
            case SHORTER:
                shapeRenderer.setColor(Color.PINK);
                break;

        }
        shapeRenderer.rect(x, y, width, width);
        shapeRenderer.end();
    }

    void checkIfActive(Player player, Player player2) {
        if (isActive) {
            if (activeTimer.countdown(TIME_OF_ACTIVE)) {
                deactivated=true;
            }

            if (activated) {
                if(whichPlayer==1) activate(player);
                else activate(player2);
                activated=false;
            }
            if (deactivated) {
                if(whichPlayer==1) deactivate(player);
                else deactivate(player2);
                deactivated = false;
                isActive=false;
            }
        }

    }

    private void activate(Player player) {
        switch (type) {
            case SPEED:
                player.setSpeed(player.getSpeed() - 20);
                break;
            case DOUBLE:
                int howMuch=player.getSquares().size();
                for (int i=0; i<howMuch;i++) {
                    player.addSquare();
                    squaresAdded++;
                }
                break;
            case SHORTER:
                while (player.getSquares().size() > 3) {
                    player.delSquare();
                    squaresRemoved++;
                }
                break;
        }
    }

    private void deactivate(Player player) {
        switch (type) {
            case SPEED:
                player.setSpeed(player.getSpeed() + 20);
                break;
            case DOUBLE:
                for (int i=0; i< squaresAdded; i++) {
                    player.delSquare();
                }
                squaresAdded=0;
                break;
            case SHORTER:
                for(int i=0; i< squaresRemoved; i++) {
                    player.addSquare();
                }
                squaresRemoved=0;
                break;
        }
    }

    void placeInRandomTime() {
        if(!isActive) {
            if(placeTimer.countdown(TIME_TO_RESPAWN)) {
                int procentage = generator.nextInt(100);
                if(procentage>=PROCENTAGE_CHANCE_OF_RESPAWN) {
                    int whichType = generator.nextInt(3);
                    switch (whichType){
                        case 0:
                            type=Type.SPEED;
                            break;
                        case 1:
                            type=Type.DOUBLE;
                            break;
                        case 2:
                            type=Type.SHORTER;
                            break;
                    }
                    placeRandom();
                }
            }
        }
    }


    @Override
    void checkIfTaken(Player player) {
        if (GameUtil.collision(this, player)) {
            //player.setPoints(10);
            x=-50;
            y=-50;
            isActive=true;
            activated=true;
            if(player.getColor()== Color.RED)whichPlayer=1;
            else whichPlayer=2;

        }
    }

    String getType(){
        if(type!=null) {
                switch (type) {
                    case SPEED:
                        return "Speed";
                    case DOUBLE:
                        return "Double";
                    case SHORTER:
                        return "Shorter";
                    default:
                        return "nic";
                }
        }
        else return "nic";
    }

}

