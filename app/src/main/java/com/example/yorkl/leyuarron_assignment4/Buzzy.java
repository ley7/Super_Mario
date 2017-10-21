package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by yorkL on 2017/6/9.
 */

public class Buzzy extends Enemy{
    String state;

    public Buzzy(){
        this.state = "buzzy_normal";
        this.alive = true;
        this.reward = 200;
        this.direction = "right";
    }

}
