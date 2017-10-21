package com.example.yorkl.leyuarron_assignment4;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Calendar;

import static android.R.attr.columnWidth;
import static android.R.attr.direction;

/**
 * Created by yorkL on 2017/6/5.
 */

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private Bitmap mario_bitmap,left_bitmap,right_bitmap,jump_bitmap,attack_bitmap,dirt_bitmap,brick_bitmap,itemblock,mushroom_bitmap,flower_bitmap,mario_child_bitmap,fire_mario_bitmap,coin_bitmap;
    private Bitmap fire_ball_bitmap,buzzy_bitmap,buzzy_shell_bitmap,koopa_bitmap,koopa_shell_bitmap,koopa_fly_bitmap,pipe_bitmap,plant_bitmap,flag_bitmap;
    GameMap gameMap;
    public SuperMarioVisitorImpl mario = new SuperMarioVisitorImpl();
    int start;
    int pixel = 20;
    int pixel2;
    ArrayList<FireBall> fireball = new ArrayList<>();
    boolean double_jump_flag;
    int plant_counter;
    int current_level;
    boolean game_over_flag;

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        int width = getWidth();
        int height = 9*getHeight()/10;
        int row = pixel/2;
        int column = pixel/2;
        gameMap = new GameMap();
        mario.xPos = 2*pixel;
        mario.yPos = 7*pixel/10;
        start = 0;
        int end = pixel;
        double_jump_flag = false;
        plant_counter = 0;
        current_level = 1;
        game_over_flag = false;
    }

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);
        int width = getWidth();
        int height = 9*getHeight()/10;
        Rect rect = new Rect();
        int rowHeight = getHeight() / pixel;
        int columnWidth = getHeight() / pixel;
        pixel2 = getWidth()/columnWidth + 1;
        int left_icon_x = 0;
        int right_icon_x = 2*pixel2/10;
        int attack_icon_x = 7*pixel2/10;
        int jump_icon_x = 9*pixel2/10;
      //  System.out.println(7*pixel/10);
        if(game_over_flag) System.exit(0);
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        String s = "Health:";  // The text content
        // paint.setTextAlign(Paint.Align.CENTER);
        s+=String.valueOf(mario.gethealth());
        canvas.drawText(s, 10, 50, paint);
        paint.setColor(Color.WHITE);
        paint.setTextSize(50);
        String str = "Rewards:";  // The text content
        // paint.setTextAlign(Paint.Align.CENTER);
        str+=String.valueOf(mario.get_rewards());
        canvas.drawText(str, 1440, 50, paint);
        str = "Level:";
        str+=String.valueOf(current_level);
        canvas.drawText(str, 740, 50, paint);

        rect.set(0, height,  (0+3)*rowHeight, getHeight());
        canvas.drawBitmap(left_bitmap, null, rect, null);
        rect.set((right_icon_x)* columnWidth, height, (right_icon_x+3)*rowHeight,getHeight());
        canvas.drawBitmap(right_bitmap, null, rect, null);
        rect.set((attack_icon_x)*rowHeight, height, (attack_icon_x+3)*rowHeight,getHeight());
        canvas.drawBitmap(attack_bitmap, null, rect, null);
        rect.set((jump_icon_x)*rowHeight, height, (jump_icon_x+3)*rowHeight,getHeight());
        canvas.drawBitmap(jump_bitmap, null, rect, null);
        if(mario.state == "child") {
            rect.set((mario.xPos) * columnWidth, (mario.yPos) * rowHeight, (mario.xPos + 1) * columnWidth, (mario.yPos + 1) * columnWidth);
            canvas.drawBitmap(mario_child_bitmap, null, rect, null);
        }
        else if(mario.state == "mature") {
            rect.set((mario.xPos) * columnWidth, (mario.yPos) * rowHeight, (mario.xPos + 1) * columnWidth, (mario.yPos + 1) * columnWidth);
            canvas.drawBitmap(mario_bitmap, null, rect, null);
        }
        else if(mario.state == "fire"){
            rect.set((mario.xPos) * columnWidth, (mario.yPos) * rowHeight, (mario.xPos + 1) * columnWidth, (mario.yPos + 1) * columnWidth);
            canvas.drawBitmap(fire_mario_bitmap, null, rect, null);
        }
        //System.out.println(pixel2);
        for (int i = 0; i < pixel2; i++) {
            for (int j = 7*pixel/10+1; j <  9*pixel/10; j++) {
                int x = i-start;
                if(gameMap.map[pixel2-i+start][j] == "dirt") {
                    rect.set((i) * columnWidth, (j) * rowHeight, (i + 1) * columnWidth, (j + 1) * rowHeight);
                    canvas.drawBitmap(dirt_bitmap, null, rect, null);
                }
            }
        }

        for (int i = 0;i < fireball.size();i++){
                rect.set((fireball.get(i).getX()) * columnWidth, (fireball.get(i).getY()) * rowHeight, (fireball.get(i).getX() + 1) * columnWidth, (fireball.get(i).getY() + 1) * rowHeight);
                canvas.drawBitmap(fire_ball_bitmap, null, rect, null);
        }
        for (int i = 0; i < pixel2; i++) {   //draw the board
            for (int j = 0*pixel/10+1; j <=  7*pixel/10; j++) {
                if(gameMap.map[pixel2-i+start][j] == "brick") {
                    rect.set((i) * columnWidth, (j) * rowHeight, (i + 1) * columnWidth, (j + 1) * rowHeight);
                    canvas.drawBitmap(brick_bitmap, null, rect, null);
                }
                if(gameMap.map[pixel2-i+start][j] == "itemreward") {
                    rect.set((i) * columnWidth, (j) * rowHeight, (i + 1) * columnWidth, (j + 1) * rowHeight);
                    canvas.drawBitmap(itemblock, null, rect, null);
                }
                if(gameMap.map[pixel2-i+start][j] == "after_dirt") {
                    rect.set((i) * columnWidth, (j) * rowHeight, (i + 1) * columnWidth, (j + 1) * rowHeight);
                    canvas.drawBitmap(dirt_bitmap, null, rect, null);
                }
                if(gameMap.map[pixel2-i+start][j] == "coin") {
                    rect.set((i) * columnWidth, (j) * rowHeight, (i + 1) * columnWidth, (j + 1) * rowHeight);
                    canvas.drawBitmap(coin_bitmap, null, rect, null);
                }
                if(gameMap.map[pixel2-i+start][j] == "pipe") {
                    rect.set((i) * columnWidth, (j) * rowHeight, (i + 1) * columnWidth, (j + 1) * rowHeight);
                    canvas.drawBitmap(pipe_bitmap, null, rect, null);
                }

            }
        }

        for(int k = 0;k<gameMap.itemreward.length;k++) {
                int j =gameMap.itemreward[k].getY();
                if (gameMap.itemreward[k].flag == "mushroom") {
                   // System.out.println("k:"+k);
                    rect.set((pixel2 - gameMap.itemreward[k].getX() + start) * columnWidth, (j - 1) * rowHeight, ((pixel2 + start - gameMap.itemreward[k].getX() + 1)) * columnWidth, (j) * rowHeight);
                    canvas.drawBitmap(mushroom_bitmap, null, rect, null);
                }
                if (gameMap.itemreward[k].flag == "flower") {
                    rect.set((pixel2 - gameMap.itemreward[k].getX() + start) * columnWidth, (j - 1) * rowHeight, ((pixel2 + start - gameMap.itemreward[k].getX() + 1)) * columnWidth, (j) * rowHeight);
                    canvas.drawBitmap(flower_bitmap, null, rect, null);
                }
        }

        for(int i=0;i<gameMap.buzzy.length;i++) {
            if(gameMap.buzzy[i].alive) {
                int p = pixel2 - gameMap.buzzy[i].getX() + start;
                int q = gameMap.buzzy[i].getY();
                if (gameMap.buzzy[i].state == "buzzy_normal") {
                    rect.set((p) * columnWidth, (q) * rowHeight, (p + 1) * columnWidth, (q + 1) * rowHeight);
                    canvas.drawBitmap(buzzy_bitmap, null, rect, null);
                }
                if (gameMap.buzzy[i].state.equals("buzzy_shell") || gameMap.buzzy[i].state.equals("buzzy_shell_run")) {
                    rect.set((p) * columnWidth, (q) * rowHeight, (p + 1) * columnWidth, (q + 1) * rowHeight);
                    canvas.drawBitmap(buzzy_shell_bitmap, null, rect, null);
                }
            }
        }

        for(int i=0;i<gameMap.koopa.length;i++) {
            if (gameMap.koopa[i].alive) {
                int p = pixel2 - gameMap.koopa[i].getX() + start;
                int q = gameMap.koopa[i].getY();

                if (gameMap.koopa[i].state.equals("koopa_normal")) {
                    rect.set((p) * columnWidth, (q) * rowHeight, (p + 1) * columnWidth, (q + 1) * rowHeight);
                    canvas.drawBitmap(koopa_bitmap, null, rect, null);
                }
                if (gameMap.koopa[i].state.equals("koopa_fly")) {
                    rect.set((p) * columnWidth, (q) * rowHeight, (p + 1) * columnWidth, (q + 1) * rowHeight);
                    canvas.drawBitmap(koopa_fly_bitmap, null, rect, null);
                }
                if (gameMap.koopa[i].state.equals("koopa_shell") || gameMap.koopa[i].state.equals("koopa_shell_run")) {
                    rect.set((p) * columnWidth, (q) * rowHeight, (p + 1) * columnWidth, (q + 1) * rowHeight);
                    canvas.drawBitmap(koopa_shell_bitmap, null, rect, null);
                }
            }
        }

        for(int i=0;i<gameMap.plant.length;i++){
            if(gameMap.plant[i].alive){
                int p = pixel2 - gameMap.plant[i].getX() + start;
                int q = gameMap.plant[i].getY();
                if(gameMap.plant[i].plant_flag >=20) {
                    rect.set((p) * columnWidth, (q) * rowHeight, (p + 1) * columnWidth, (q + 1) * rowHeight);
                    canvas.drawBitmap(plant_bitmap, null, rect, null);
                }
            }
        }
        int q = pixel2 - gameMap.level_switch.getX() + start;
            rect.set((q) * columnWidth, (gameMap.level_switch.getY()) * rowHeight, (q + 1) * columnWidth, (gameMap.level_switch.getY() + 1) * rowHeight);
            canvas.drawBitmap(flag_bitmap, null, rect, null);
    }



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

       // setWillNotDraw(false);
        mario_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mario_icon);
        left_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.left_icon);
        right_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.right_icon);
        jump_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.jump_icon);
        attack_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.attack_icon);
        dirt_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dirt_icon);
        brick_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.brick_icon);
        itemblock = BitmapFactory.decodeResource(getResources(), R.drawable.itemblock);
        mushroom_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mushroom_icon);
        flower_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fireflower);
        mario_child_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mario_child_icon);
        fire_mario_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fire_mario);
        coin_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        fire_ball_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fire_ball);
        buzzy_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buzzy_icon);
        buzzy_shell_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.buzzy_shell);
        koopa_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_icon);
        koopa_shell_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_shell);
        koopa_fly_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.koopa_fly_icon);
        plant_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.plant_icon);
        pipe_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.pipe_icon);
       flag_bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flag_icon);
        GameThread gameThread = new GameThread(this);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // System.out.println("Touch event occured")
        int width = getWidth();
        int height = 9*getHeight()/10;;
        int rowHeight = getHeight() / pixel;
        int columnWidth = getHeight() / pixel;
        pixel2 = getWidth()/columnWidth + 1;
        int left_icon_x = 0;
        int right_icon_x = 2*pixel2/10;
        int attack_icon_x = 7*pixel2/10;
        int jump_icon_x = 9*pixel2/10;
        int currX;
        int currY;
        int prevX=0;
        int prevY=0;
        int endRowNum = 0;
        int endColNum = 0;

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            prevX = (int) event.getX();
            prevY = (int) event.getY();
          if(prevX<=3*columnWidth){
              mario.direction = "left";
            }
            else if(prevX>(right_icon_x)*columnWidth && prevX<=(right_icon_x+3)*columnWidth){
              mario.direction = "right";
          }
         // else mario.direction = "stand";

          if(prevX>(attack_icon_x * columnWidth)&& prevX<=(attack_icon_x+3) * columnWidth){
              if(mario.state == "fire") {
                  mario.attack_flag = true;
                  FireBall ball = new FireBall(mario);
                  System.out.println(mario.direction);
                 if(mario.direction.equals("stand_towards_left")) ball.direction = "left";
                  if(mario.direction.equals("stand_towards_right")) ball.direction = "right";
                  fireball.add(ball);
              }
          }
          else if(prevX>(jump_icon_x)*columnWidth && prevX<=(jump_icon_x+3)*columnWidth){
              if(!mario.jump_flag) {
                  mario.jump_flag = true;
                  mario.jump_counter = 4;
                  double_jump_flag = false;
              }
          }

        } else if (event.getAction() == MotionEvent.ACTION_UP) {
            currX = (int) event.getX();
            currY = (int) event.getY();

            }

        invalidate();
        return true;
    }

    public boolean hasdown(int x, int y){
        if(y+2<=20){
            if (gameMap.map[41-x+start][y + 1]=="nothing" || gameMap.map[41-x+start][y + 1].equals("koopa_fly") || gameMap.map[41-x+start][y + 1].equals("plant")) {
                return true;
            }

            for(int k=0;k<gameMap.coins.length;k++) {
                if (gameMap.map[41 - x + start][y + 1].equals("coin")) {
                    gameMap.coins[k].alive = false;
                    mario.set_rewards(gameMap.coins[k].reward);
                    gameMap.map[41 - x + start][y + 1] = "nothing";
                    return true;
                }
            }

        }
        return false;
    }

    public boolean hasup(int x, int y){
        if(y-2>=0) {
            for(int i=0;i<gameMap.brick.length;i++) {
                if(gameMap.brick[i].alive) {
                    if ((!gameMap.map[41-x+start][y-2].equals("nothing"))) {
                        if(gameMap.map[41-x+start][y-2].equals("brick") && (mario.state.equals("mature") || mario.state.equals("fire"))){
                            gameMap.brick[i].alive = false;
                            mario.set_rewards(gameMap.brick[i].reward);
                            gameMap.map[41-x+start][y-2] = "nothing";
                            return false;
                        }
                        if(gameMap.map[41-x+start][y-2].equals("brick") && mario.state.equals("child")){
                            gameMap.brick[i].alive = true;
                            return false;
                        }
                    }
                }
            }

            for(int k=0;k<gameMap.coins.length;k++) {
                if (gameMap.map[41 - x + start][y - 2].equals("coin")) {
                    gameMap.coins[k].alive = false;
                    mario.set_rewards(gameMap.coins[k].reward);
                    gameMap.map[41 - x + start][y - 2] = "nothing";
                    return true;
                }
            }

                if ((!gameMap.map[41-x+start][y-2].equals("nothing"))) {
                    for(int i = 0;i<gameMap.itemreward.length;i++) {
                        if (gameMap.itemreward[i].flag == "before") {
                            if (41 - x + start == gameMap.itemreward[i].getX()) {
                                if (gameMap.map[41 - x + start][y - 2].equals("itemreward") && mario.state.equals("child")) {
                                    //System.out.println("here" + i);
                                    gameMap.itemreward[i].flag = "mushroom";
                                    gameMap.itemreward[i].mark = x;
                                    gameMap.map[41 - x + start][y - 2] = "after_dirt";
                                }
                                if (gameMap.map[41 - x + start][y - 2].equals("itemreward") && mario.state.equals("mature")) {
                                   // System.out.println("here" + i);
                                    gameMap.itemreward[i].flag = "flower";
                                    gameMap.map[41 - x + start][y - 2] = "after_dirt";
                                }
                                if (gameMap.map[41 - x + start][y - 2].equals("itemreward") && mario.state.equals("fire")) {
                                   //System.out.println("here" + i);
                                    gameMap.itemreward[i].flag = "flower";
                                    gameMap.map[41 - x + start][y - 2] = "after_dirt";
                                }
                            }
                        }
                    }
                    return false;
            }

        }
        return true;
    }
    public void buzzy_move() {
        //System.out.println(mario.jump_counter);
        //System.out.println(gameMap.buzzy[0].getX());
        for (int i = 0; i < gameMap.buzzy.length; i++) {
            if(gameMap.buzzy[i].posx<=start+1 || gameMap.buzzy[i].getX()>=218){
                gameMap.buzzy[i].alive= false;
                gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "nothing";
                return;
            }
            if (gameMap.buzzy[i].alive && gameMap.buzzy[i].state == "buzzy_normal") {
                if (gameMap.buzzy[i].posx > start ) {
                    if(gameMap.buzzy[i].getY()>=17 || gameMap.buzzy[i].getX()>=219) {
                        gameMap.buzzy[i].alive= false;
                        gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "nothing";
                        return;
                    }
                    if(hasdown(pixel2 - gameMap.buzzy[i].getX() + start,gameMap.buzzy[i].getY())){
                        gameMap.buzzy[i].posy++; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!remember
                        gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy-1] = "nothing";
                        gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "buzzy_normal";
                        return;
                    }
                   // System.out.println("1:"+(gameMap.buzzy[i].posx + 1));
                   // System.out.println(gameMap.buzzy[i].posy);
                    if(gameMap.map[gameMap.buzzy[i].posx + 1][gameMap.buzzy[i].posy].equals("pipe")) {
                        gameMap.buzzy[i].direction = "right";
                    }
                    else if(gameMap.map[gameMap.buzzy[i].posx - 1][gameMap.buzzy[i].posy].equals("pipe")) {
                        gameMap.buzzy[i].direction = "left";
                    }
                    if(gameMap.buzzy[i].direction.equals("right")) {
                        gameMap.buzzy[i].posx--;
                        gameMap.map[gameMap.buzzy[i].posx + 1][gameMap.buzzy[i].posy] = "nothing";
                        gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "buzzy_normal";
                    }
                    else if(gameMap.buzzy[i].direction.equals("left")){
                        gameMap.buzzy[i].posx++;
                        gameMap.map[gameMap.buzzy[i].posx - 1][gameMap.buzzy[i].posy] = "nothing";
                        gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "buzzy_normal";
                    }
                    for (int k = 0; k < fireball.size(); k++) {
                        //System.out.println("1:" + (pixel2 - fireball.get(k).getX() + start));
                       // System.out.println("2:" + gameMap.buzzy[i].getX());
                        int q = pixel2 - fireball.get(k).getX() + start; //about immuning to the fireball !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                        if ((gameMap.buzzy[i].getX() == q || gameMap.buzzy[i].getX() == q - 1 || gameMap.buzzy[i].getX() == q + 1) && gameMap.buzzy[i].getY() == fireball.get(k).getY()) {
                            //System.out.println(gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy]);
                          //  gameMap.buzzy[i].alive = false;
                            fireball.remove(k);
                          //  gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "nothing";
                           // gameMap.buzzy[i].setX(0);
                        }
                    }

                }
            } else if (gameMap.buzzy[i].alive && gameMap.buzzy[i].state.equals("buzzy_shell_run")) {
                //System.out.println(gameMap.buzzy[i].state);
                if(hasdown(pixel2 - gameMap.buzzy[i].getX() + start,gameMap.buzzy[i].getY())){
                    gameMap.buzzy[i].posy++; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!remember
                    gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy-1] = "nothing";
                    gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "buzzy_shell";
                    return;
                }
                if((!double_jump_flag) && mario.jump_counter == 0) {
                    if (gameMap.buzzy[i].posx > start) {
                        if(gameMap.map[gameMap.buzzy[i].posx + 1][gameMap.buzzy[i].posy].equals("pipe")) {
                            gameMap.buzzy[i].direction = "right";
                        }
                        else if(gameMap.map[gameMap.buzzy[i].posx - 1][gameMap.buzzy[i].posy].equals("pipe")) {
                            gameMap.buzzy[i].direction = "left";
                        }
                        if(gameMap.buzzy[i].direction.equals("right")) {
                            gameMap.buzzy[i].posx--;
                            gameMap.map[gameMap.buzzy[i].posx + 1][gameMap.buzzy[i].posy] = "nothing";
                            gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "buzzy_shell";
                        }
                        else if(gameMap.buzzy[i].direction.equals("left")){
                            gameMap.buzzy[i].posx++;
                            gameMap.map[gameMap.buzzy[i].posx - 1][gameMap.buzzy[i].posy] = "nothing";
                            gameMap.map[gameMap.buzzy[i].posx][gameMap.buzzy[i].posy] = "buzzy_shell";
                        }
                    }

            }
        }
        }
    }

    public void koopa_move(){
        for (int i = 0; i < gameMap.koopa.length; i++) {
            if (gameMap.koopa[i].alive && gameMap.koopa[i].state == "koopa_fly") {
                if (gameMap.koopa[i].posx > start) {
                    if(gameMap.koopa[i].direction.equals("up")) {
                        if(gameMap.koopa[i].getY() <= 11) gameMap.koopa[i].direction = "down";
                        gameMap.koopa[i].posy--; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!remember
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy+1] = "nothing";
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_fly";
                        for (int k = 0; k < fireball.size(); k++) {
                            int q = pixel2 - fireball.get(k).getX() + start; //about immuning to the fireball !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            if ((gameMap.koopa[i].getX() == q || gameMap.koopa[i].getX() == q - 1 || gameMap.koopa[i].getX() == q + 1) && gameMap.koopa[i].getY() == fireball.get(k).getY()) {
                                //System.out.println(gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy]);
                                gameMap.koopa[i].alive = false;
                                mario.set_rewards(gameMap.koopa[i].reward);
                                fireball.remove(k);
                                gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "nothing";
                                gameMap.koopa[i].setX(0);
                            }
                        }
                    }
                    else if(gameMap.koopa[i].direction.equals("down")) {
                        if(gameMap.koopa[i].getY() >= 13) gameMap.koopa[i].direction = "up";
                        gameMap.koopa[i].posy++; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!remember
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy-1] = "nothing";
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_fly";
                        for (int k = 0; k < fireball.size(); k++) {
                            int q = pixel2 - fireball.get(k).getX() + start; //about immuning to the fireball !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            if ((gameMap.koopa[i].getX() == q || gameMap.koopa[i].getX() == q - 1 || gameMap.koopa[i].getX() == q + 1) && gameMap.koopa[i].getY() == fireball.get(k).getY()) {
                                gameMap.koopa[i].alive = false;
                                mario.set_rewards(gameMap.koopa[i].reward);
                                fireball.remove(k);
                                gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "nothing";
                                gameMap.koopa[i].setX(0);
                            }
                        }
                    }
                }
            } else if (gameMap.koopa[i].alive && gameMap.koopa[i].state.equals("koopa_shell_run")) {
                if((!double_jump_flag) && mario.jump_counter == 0) {
                    if (gameMap.koopa[i].posx > start) {
                        if(gameMap.map[gameMap.koopa[i].posx + 1][gameMap.koopa[i].posy].equals("pipe")) {
                            gameMap.koopa[i].direction = "right";
                        }
                        else if(gameMap.map[gameMap.koopa[i].posx - 1][gameMap.koopa[i].posy].equals("pipe")) {
                            gameMap.koopa[i].direction = "left";
                        }
                        if(gameMap.koopa[i].direction.equals("right")) {
                            gameMap.koopa[i].posx--;
                            gameMap.map[gameMap.koopa[i].posx + 1][gameMap.koopa[i].posy] = "nothing";
                            gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_shell";
                        }
                        else if(gameMap.koopa[i].direction.equals("left")){
                            gameMap.koopa[i].posx++;
                            gameMap.map[gameMap.koopa[i].posx - 1][gameMap.koopa[i].posy] = "nothing";
                            gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_shell";
                        }
                    }
                }
            }
            else if(gameMap.koopa[i].alive && gameMap.koopa[i].state.equals("koopa_normal")){
                if (gameMap.koopa[i].posx > start) {
                    if(gameMap.koopa[i].getY()>=17) {
                        gameMap.koopa[i].alive= false;
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "nothing";
                        return;
                    }/*
                    if(hasdown(pixel2 - gameMap.koopa[i].getX() + start,gameMap.koopa[i].getY())){
                        gameMap.koopa[i].posy++; //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!remember
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy-1] = "nothing";
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_normal";
                        return;
                    }
                    */
                    if(gameMap.koopa[i].posy != 14){
                        gameMap.koopa[i].posy++;
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy-1] = "nothing";
                        gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_normal";
                    }
                    else {
                        if(gameMap.map[gameMap.koopa[i].posx + 1][gameMap.koopa[i].posy].equals("pipe")) {
                            gameMap.koopa[i].direction = "right";
                        }
                        else if(gameMap.map[gameMap.koopa[i].posx - 1][gameMap.koopa[i].posy].equals("pipe")) {
                            gameMap.koopa[i].direction = "left";
                        }
                        if(gameMap.koopa[i].direction.equals("right")) {
                            gameMap.koopa[i].posx--;
                            gameMap.map[gameMap.koopa[i].posx + 1][gameMap.koopa[i].posy] = "nothing";
                            gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_normal";
                        }
                        else if(gameMap.koopa[i].direction.equals("left")){
                            gameMap.koopa[i].posx++;
                            gameMap.map[gameMap.koopa[i].posx - 1][gameMap.koopa[i].posy] = "nothing";
                            gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "koopa_normal";
                        }
                        for (int k = 0; k < fireball.size(); k++) {
                            int q = pixel2 - fireball.get(k).getX() + start; //about immuning to the fireball !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                            if ((gameMap.koopa[i].getX() == q || gameMap.koopa[i].getX() == q - 1 || gameMap.koopa[i].getX() == q + 1) && gameMap.koopa[i].getY() == fireball.get(k).getY()) {
                                gameMap.koopa[i].alive = false;
                                fireball.remove(k);
                                mario.set_rewards(gameMap.koopa[i].reward);
                                gameMap.map[gameMap.koopa[i].posx][gameMap.koopa[i].posy] = "nothing";
                                gameMap.koopa[i].setX(0);
                            }
                        }
                    }
                }
            }
        }
    }

    public void collect_coin(){
        for(int i = 0;i<gameMap.coins.length;i++) {
            if (gameMap.coins[i].alive) {
                int q = pixel2 - mario.xPos + start;
                if (gameMap.coins[i].getX() == q && mario.yPos == gameMap.coins[i].getY()){
                    mario.set_rewards(gameMap.coins[i].reward);
                    gameMap.coins[i].alive = false;
                    gameMap.map[gameMap.coins[i].getX()][mario.yPos] = "nothing";
                }
            }
        }
    }
}

