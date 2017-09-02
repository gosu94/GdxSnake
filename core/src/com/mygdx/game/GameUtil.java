package com.mygdx.game;
/**
 * @author Tomasz Pilarczyk
 */
class GameUtil {

    static boolean collision(Square s1, Square s2){
        return s1.x + s1.width > s2.x && s1.x < s2.x + s2.width &&
                s1.y + s1.width > s2.y && s1.y < s2.y + s2.width;
    }

}
