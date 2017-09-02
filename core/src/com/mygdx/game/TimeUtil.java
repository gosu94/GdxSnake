package com.mygdx.game;
/**
 * @author Tomasz Pilarczyk
 */
class TimeUtil {
    private boolean countDownStart;
    private long startTime;

    TimeUtil(){
        countDownStart=true;
        startTime=0;
    }

    //When time passes method returns true (time in milliseconds)
    boolean countdown(long millis){

        if(countDownStart){startTime=System.currentTimeMillis();
            countDownStart =false;}

        if(System.currentTimeMillis()-startTime > millis){
            countDownStart=true;
            startTime=0;
            return true;
        }
        else return false;

    }
}
