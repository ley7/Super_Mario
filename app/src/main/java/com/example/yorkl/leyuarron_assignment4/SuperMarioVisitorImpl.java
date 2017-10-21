package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by yashaswiniamaresh on 5/24/17.
 */

public class SuperMarioVisitorImpl implements SuperMarioVisitor {
    String direction;
    private int reward;
    boolean attack_flag;
    boolean jump_flag;
    public int xPos,yPos;
    public int jump_counter;
    private int health;
    boolean alive;
    public String state;

    public SuperMarioVisitorImpl(){
    this.health = 3;
    this.direction = "stand";
    jump_counter = 0;
    this.alive = true;
    this.state="child"; //=++++++++++++++++++++++++++++
    this.reward = 0;
    }
    public int get_rewards(){
        return this.reward;
    }
    public void set_rewards(int rewards){
        this.reward = this.reward+rewards;
    }
    public int gethealth(){
        return this.health;
    }
    public void decreasehealth(){
        this.health--;
    }

    public void setdirection(String dir){
        this.direction = dir;
    }

    public String getdirection(){
        return this.direction;
    }

    @Override
    public int visit(Enemy enemy){
        if((enemy.getX()==this.xPos ||enemy.getX()==this.xPos-1 || enemy.getX()==this.xPos+1) && enemy.getY()==this.yPos+1) {
            enemy.alive = false;
            return enemy.getReward();
        }
        else if((enemy.getX()==this.xPos ||enemy.getX()==this.xPos-1 || enemy.getX()==this.xPos+1) && enemy.getY()==this.yPos) {
            this.alive = false;
            return 0;
        }
            return 0;

    }



    @Override
    public int visit(Item item){
        return item.getValue();
    }


    public void moveLeft(GameView gameView) {
        if(this.xPos-1==0){
            this.direction="stand";
        }
        else {
            int p = gameView.pixel2 - this.xPos + gameView.start;
            if(gameView.gameMap.map[p+1][this.yPos].equals("pipe")){
                this.direction = "stand";
            }
            else this.xPos -= 1;

        }
    }

    public void moveRight(GameView gameView) {
        if(this.xPos+1>=41){
            this.direction="stand";
        }
        else {
            int p = gameView.pixel2 - this.xPos + gameView.start;
            if(gameView.gameMap.map[p-1][this.yPos].equals("pipe")){
                this.direction = "stand";
            }
            else this.xPos += 1;
        }
    }

    public void jump(GameView game) {
        if(this.jump_counter>0) {
            //System.out.println("Mario:"+ this.xPos);
            if(game.hasup(game.mario.xPos,game.mario.yPos)) {

                this.yPos -= 2;
                //System.out.println("Mario y:"+ this.yPos);
                this.jump_counter--;
            }
            else {
                this.jump_counter=0;
               // this.yPos += 1;
            }
        }
        else{
            if(game.hasdown(game.mario.xPos,game.mario.yPos)){
                this.yPos+=1;
            }
            else this.jump_flag = false;
        }
    }

    public void moveDown() {

        if (this.yPos + 1 == 20) {
            this.direction = "stand";
        } else this.yPos += 1;
        }
    }