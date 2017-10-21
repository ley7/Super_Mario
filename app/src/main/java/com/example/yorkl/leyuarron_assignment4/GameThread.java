package com.example.yorkl.leyuarron_assignment4;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;

import java.util.Calendar;

/**
 * Created by yorkL on 2017/6/5.
 */

public class GameThread extends Thread {
    private GameView gameView;
    int buzzy_counter = 0;
    boolean plant_increase_flag;
    int level = 1;
    public GameThread(GameView gameView) {
        this.gameView = gameView;
    }

    public void run() {
        SurfaceHolder sh = gameView.getHolder();
        while (true) {
            Canvas canvas = sh.lockCanvas();
            if (canvas != null) {
                buzzy_counter++;
                int q = gameView.pixel2-gameView.mario.xPos+gameView.start;

                processInput();


                q = gameView.pixel2-gameView.mario.xPos+gameView.start;
                for (int i = 0; i < gameView.gameMap.plant.length; i++) {
                    if (gameView.gameMap.plant[i].alive) {
                        if (gameView.gameMap.plant[i].plant_flag >= 20) {
                           // System.out.println("1:"+gameView.gameMap.plant[i].getX());
                           // System.out.println("1:"+gameView.gameMap.plant[i].getX()+" 2:"+q+" "+(q == gameView.gameMap.plant[i].getX()));
                            if (q == gameView.gameMap.plant[i].getX() && gameView.mario.yPos == gameView.gameMap.plant[i].getY()) {
                                //System.out.println("{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{{");
                                gameView.mario.alive = false;
                                die(gameView);
                            } else gameView.gameMap.plant[i].plant_flag++;
                        } else {
                            if (q == gameView.gameMap.plant[i].getX() && gameView.mario.yPos == gameView.gameMap.plant[i].getY()) {
                                //do nothing
                            } else gameView.gameMap.plant[i].plant_flag++;
                        }
                        if(gameView.gameMap.plant[i].plant_flag>=30){
                            gameView.gameMap.plant[i].plant_flag = 0;
                        }
                    }
                }
                System.out.println(gameView.mario.direction);
                if(gameView.mario.direction.equals("left") || gameView.mario.direction.equals("stand_towards_left"))
                gameView.mario.setdirection("stand_towards_left");
                else if(gameView.mario.direction.equals("right") || gameView.mario.direction.equals("stand_towards_right"))
                    gameView.mario.setdirection("stand_towards_right");
                else gameView.mario.setdirection("stand_towards_left");

                q = gameView.pixel2-gameView.mario.xPos+gameView.start;
                if(q == gameView.gameMap.level_switch.x){
                    if(gameView.current_level == 1) {
                        gameView.gameMap.loadmap(gameView.gameMap.level.getlevel2(),gameView.gameMap.map);
                        gameView.mario.xPos = 2*gameView.pixel;
                        gameView.mario.yPos = 7*gameView.pixel/10;
                        gameView.start = 0;
                        int end = gameView.pixel;
                        gameView.double_jump_flag = false;
                        gameView.plant_counter = 0;
                        gameView.current_level = 2;
                    }
                    else if(gameView.current_level == 2) {
                        gameView.gameMap.loadmap(gameView.gameMap.level.getlevel3(),gameView.gameMap.map);
                        gameView.mario.xPos = 2*gameView.pixel;
                        gameView.mario.yPos = 7*gameView.pixel/10;
                        gameView.start = 0;
                        int end = gameView.pixel;
                        gameView.double_jump_flag = false;
                        gameView.plant_counter = 0;
                        gameView.current_level = 3;
                    }
                    else if(gameView.current_level == 3) {
                        gameView.game_over_flag = true;
                    }
                }
                for(int i=0;i<gameView.gameMap.pipe.length;i++) {
                    for (int k = 0; k < gameView.fireball.size(); k++) {
                        q = gameView.pixel2 - gameView.fireball.get(k).getX() + gameView.start;
                        if ((gameView.gameMap.pipe[i].getX() == q || gameView.gameMap.pipe[i].getX() == q - 1 || gameView.gameMap.pipe[i].getX() == q + 1) && gameView.gameMap.pipe[i].getY() == gameView.fireball.get(k).getY()) {
                            gameView.fireball.remove(k);
                        }
                        else if(gameView.gameMap.plant[i].alive) {
                            if (gameView.gameMap.plant[i].plant_flag >= 20 && (gameView.gameMap.plant[i].getX() == q || gameView.gameMap.plant[i].getX() == q - 1 || gameView.gameMap.plant[i].getX() == q + 1) && gameView.gameMap.plant[i].getY() == gameView.fireball.get(k).getY()) {
                                gameView.gameMap.plant[i].alive = false;
                                gameView.gameMap.map[gameView.gameMap.plant[i].posx][gameView.gameMap.plant[i].posy] = "nothing";
                                gameView.mario.set_rewards(gameView.gameMap.plant[i].reward);
                                gameView.fireball.remove(k);
                            }
                        }
                    }
                }
                gameView.draw(canvas);
                sh.unlockCanvasAndPost(canvas);
                /*
                Paint p = new Paint(Color.RED);
                p.setAntiAlias(true);
                p.setTextSize(10);  // Set text size
                String s = "Score:";  // The text content
                s += String.valueOf(gameView.mario.gethealth());
                p.setTextAlign(Paint.Align.LEFT);
                canvas.drawText(s, 0, 20, p);  //draw the score
                */
            }
            try{
                sleep(250);
            }
            catch(InterruptedException e) {
                System.out.println("Exception occured");
            }

        }
    }

    public void processInput() {

        if(!gameView.mario.alive) {
            die(gameView);

        }
        if (gameView.mario.getdirection() == "right") {
            gameView.mario.moveRight(gameView);
        }
        else if (gameView.mario.getdirection() == "left") {
            gameView.mario.moveLeft(gameView);
        }
       // System.out.println("1:"+gameView.gameMap.coins[0].alive);
        if(gameView.hasdown(gameView.mario.xPos,gameView.mario.yPos)){
            if(gameView.mario.yPos>=gameView.pixel-2) {
                gameView.mario.alive=false;

            }
            else{
                gameView.mario.yPos++;
            }
        }
        //System.out.println("2:"+gameView.gameMap.coins[0].alive);
        if (gameView.mario.jump_flag) {
            gameView.mario.jump(gameView);
        }
        if(gameView.mario.xPos<gameView.pixel2/2){
            if(gameView.mario.direction.equals("left") ) {
                gameView.start++;
                gameView.mario.xPos+=1;
            }
        }

        for(int k = 0;k<gameView.gameMap.itemreward.length;k++) {
                if (gameView.gameMap.itemreward[k].flag == "mushroom") {
                    if (gameView.mario.yPos == gameView.gameMap.itemreward[k].getY() - 1 && gameView.pixel2 - gameView.mario.xPos +gameView.start == gameView.gameMap.itemreward[k].getX()) {
                        gameView.gameMap.itemreward[k].flag = "before";
                        gameView.mario.state = "mature";
                        gameView.mario.set_rewards(1000);
                    }
                }
                if (gameView.gameMap.itemreward[k].flag == "flower") {
                    if (gameView.mario.yPos == gameView.gameMap.itemreward[k].getY() - 1 && gameView.pixel2 - gameView.mario.xPos +gameView.start == gameView.gameMap.itemreward[k].getX()) {
                        gameView.gameMap.itemreward[k].flag = "before";
                        gameView.mario.state = "fire";
                        gameView.mario.set_rewards(1000);
                    }
                }
        }
       for(int i=0;i<gameView.fireball.size();i++) {
           if(gameView.fireball.get(i).move()){
               gameView.fireball.remove(i);
           }
        }

        gameView.buzzy_move();
        for(int i = 0;i<gameView.gameMap.buzzy.length;i++) {
            if (gameView.gameMap.buzzy[i].alive) {
                int q = gameView.pixel2 - gameView.mario.xPos + gameView.start;
                if (gameView.gameMap.buzzy[i].getX() == q && gameView.gameMap.buzzy[i].getY() == gameView.mario.yPos){
                    gameView.mario.alive = false;
                }
                if(!gameView.double_jump_flag) {
                    if (gameView.gameMap.buzzy[i].getX() == q && gameView.gameMap.buzzy[i].getY() == gameView.mario.yPos + 1 && gameView.gameMap.buzzy[i].state.equals("buzzy_shell")) {
                        gameView.mario.set_rewards(gameView.gameMap.buzzy[i].reward);
                        gameView.gameMap.buzzy[i].state = "buzzy_shell_run";
                    }
                }

                if (gameView.gameMap.buzzy[i].getX() == q && gameView.gameMap.buzzy[i].getY() == gameView.mario.yPos + 1 && gameView.gameMap.buzzy[i].state.equals("buzzy_normal")){
                    gameView.mario.set_rewards(gameView.gameMap.buzzy[i].reward);
                    gameView.gameMap.buzzy[i].state = "buzzy_shell";
                    gameView.double_jump_flag = true;
                }
            }
        }
        gameView.koopa_move();
        koopa_detection(gameView);
        //System.out.println("4:"+gameView.gameMap.coins[0].alive);
       // gameView.collect_coin();
    }

public void die(GameView gameview) {

        gameView.mario.decreasehealth();
    if (gameView.mario.gethealth() == 0) {
        gameview.game_over_flag = true;

    }
    gameView.mario.alive = true;
        gameView.mario.state = "child";
        gameView.mario.xPos = 4 * gameView.pixel2 / 5;
        gameView.mario.yPos = 7 * gameView.pixel / 10;
        while (gameView.hasdown(gameView.mario.xPos, gameView.mario.yPos)) {
            gameView.mario.xPos--;
        }

}
public void koopa_detection(GameView gameView) {
    for (int i = 0; i < gameView.gameMap.koopa.length; i++) {
        if (gameView.gameMap.koopa[i].alive) {

            int q = gameView.pixel2 - gameView.mario.xPos + gameView.start;


            if (!gameView.double_jump_flag) {
                if (gameView.gameMap.koopa[i].getX() == q && gameView.gameMap.koopa[i].getY() == gameView.mario.yPos + 1 && gameView.gameMap.koopa[i].state.equals("koopa_shell")) {
                    gameView.mario.yPos-=1;
                    gameView.gameMap.koopa[i].state = "koopa_shell_run";
                    gameView.mario.set_rewards(gameView.gameMap.koopa[i].getReward());
                    gameView.gameMap.koopa[i].direction="right";
                }
            }

            if (gameView.gameMap.koopa[i].getX() == q && gameView.gameMap.koopa[i].getY() == gameView.mario.yPos + 1 && gameView.gameMap.koopa[i].state.equals("koopa_normal") && gameView.gameMap.koopa[i].getY() == 14) {
                gameView.gameMap.koopa[i].state = "koopa_shell";
                gameView.mario.set_rewards(gameView.gameMap.koopa[i].getReward());
                gameView.gameMap.koopa[i].direction="right";
                gameView.double_jump_flag = true;
            }
            if ((gameView.mario.jump_flag==true)&& gameView.gameMap.koopa[i].getX() == q && (gameView.gameMap.koopa[i].getY() == gameView.mario.yPos + 1 || gameView.gameMap.koopa[i].getY() == gameView.mario.yPos ||gameView.gameMap.koopa[i].getY() == gameView.mario.yPos-1)&& gameView.gameMap.koopa[i].state.equals("koopa_fly")) {
                gameView.mario.yPos-=3;
                if(gameView.gameMap.koopa[i].direction.equals("up")) {
                   // gameView.mario.yPos-=2;
                }
                gameView.mario.set_rewards(gameView.gameMap.koopa[i].getReward());
                gameView.gameMap.koopa[i].state = "koopa_normal";
                gameView.gameMap.koopa[i].direction="right";
                gameView.double_jump_flag = true;
                return;
            }
            if ((gameView.gameMap.koopa[i].getX() == q && gameView.gameMap.koopa[i].getY() == gameView.mario.yPos)&&(gameView.mario.jump_flag==false)&&gameView.mario.jump_counter==0) {
                gameView.mario.alive = false;
            }
        }
    }
}

}

