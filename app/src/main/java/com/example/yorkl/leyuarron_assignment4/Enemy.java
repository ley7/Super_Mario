package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by aaron on 6/5/2017.
 */

public abstract class Enemy implements GameItems {
    int reward;
    int posx;
    int posy;
    String direction;
    boolean alive;
    public void setReward(int x){
        this.reward = x;
    }
    public int getReward(){
        return this.reward;
    }
    public String getDirection(){
        return this.direction;
    }
    public int getX(){
        return this.posx;
    }
    public int getY(){
        return this.posy;
    }
    public void setdirection(String direction){
        this.direction = direction;
    }
    public void setX(int x){
        this.posx = x;
    }
    public void setY(int y){
        this.posy = y;
    }
    public void SetCoordinate(int posX, int posY){
        this.posx = posX;
        this.posy = posY;
    }
    public int getPoints(SuperMarioVisitor visitor){
        return visitor.visit(this);
    }


}
