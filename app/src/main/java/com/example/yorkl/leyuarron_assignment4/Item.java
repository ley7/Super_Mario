package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by aaron on 6/5/2017.
 */

public class Item implements GameItems {

    int value;
    boolean alive;
    int x;
    int y;
    int reward;
    String type;
    public Item(){
        this.alive = true;
        this.reward = 200;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    public void SetCoordinate(int posX, int posY){
        this.x = posX;
        this.y = posY;
    }
    public int getValue(){
        return this.value;
    }
    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }


}