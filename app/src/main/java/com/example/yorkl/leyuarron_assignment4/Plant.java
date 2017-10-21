package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by aaron on 6/7/2017.
 */

public class Plant extends Enemy{
    int plant_flag;
    public Plant(){
        this.setReward(1000);
        this.alive = true;
        plant_flag=0;
        this.reward = 200;
    }
}

