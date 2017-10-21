package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by yorkL on 2017/6/9.
 */

public class FireBall extends Item{
    String direction = "none";
    public FireBall(SuperMarioVisitorImpl mario){
        this.x = mario.xPos;
        this.y =  mario.yPos;
        this.direction = "left";
    }
    public boolean move(){
        if(this.direction == "left"){
            if(this.x>=2) {
                this.x -= 2;
                return false;
            }
            else return true;
        }
        if(this.direction == "right"){
            if(this.x<=39) {
                this.x += 2;
                return false;
            }
            else return true;
        }
        return false;
    }
}
