package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by aaron on 6/7/2017.
 */


public class Koopa extends Enemy{
    boolean stomped;
    String state;
    String direction;
    public Koopa(){
        this.state = "koopa_fly";
        this.alive = true;
        this.direction = "up";
        this.reward = 200;
    }
}
