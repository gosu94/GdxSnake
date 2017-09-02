package com.mygdx.game;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

/**
 * @author Tomasz Pilarczyk
 */
class InputReader implements InputProcessor {
    private Player player;
    private Player player2;

    InputReader(Player player){
        this.player=player;
    }

    InputReader(Player player, Player player2){
        this.player=player;
        this.player2=player2;
    }

    @Override
    public boolean keyDown(int keycode) {
        if(keycode== Input.Keys.UP && player.velY==0 && !player.isDead()){
            player.velX=0;
            player.velY=1;
        }
        if(keycode== Input.Keys.DOWN && player.velY==0 && !player.isDead()){
            player.velX=0;
            player.velY=-1;
        }
        if(keycode== Input.Keys.LEFT && player.velX==0 && !player.isDead()){
            player.velX=-1;
            player.velY=0;
        }
        if(keycode== Input.Keys.RIGHT && player.velX==0 && !player.isDead()){
            player.velX=1;
            player.velY=0;
        }
        if(keycode == Input.Keys.NUMPAD_3){
            player.addSquare();
        }

        if(player2!=null) {
            if (keycode == Input.Keys.W && player2.velY == 0 && !player2.isDead()) {
                player2.velX = 0;
                player2.velY = 1;
            }
            if (keycode == Input.Keys.S && player2.velY == 0 && !player2.isDead()) {
                player2.velX = 0;
                player2.velY = -1;
            }
            if (keycode == Input.Keys.A && player2.velX == 0 && !player2.isDead()) {
                player2.velX = -1;
                player2.velY = 0;
            }
            if (keycode == Input.Keys.D && player2.velX == 0 && !player2.isDead()) {
                player2.velX = 1;
                player2.velY = 0;
            }

        }
        if(keycode == Input.Keys.ENTER && player.isDead()){
            player.reset(50,300);
            player2.reset(50,200);
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
