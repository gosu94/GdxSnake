package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * @author Tomasz Pilarczyk
 */
class Square {
    int x,y;
    int width=15;
    Square(){
        this.x=0;
        this.y=0;
    }
    Square(int x,int y){
        this.x=x;
        this.y=y;
    }

    void draw(ShapeRenderer shapeRenderer,Color color){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(color);
        shapeRenderer.rect(x, y, width, width);
        shapeRenderer.end();
    }


}
