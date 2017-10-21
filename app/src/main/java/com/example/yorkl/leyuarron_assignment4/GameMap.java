package com.example.yorkl.leyuarron_assignment4;

/**
 * Created by yorkL on 2017/6/7.
 */


public class GameMap {
    public String map[][];
    int counter;
    Brick[] brick;
    ItemBlock[] itemreward;
    Item[] coins;
    Buzzy[] buzzy;
    Koopa[] koopa;
    Plant[] plant;
    Pipe[] pipe;
    Item level_switch;
    Levels level;

    public GameMap(){
        level = new Levels();
        level_switch = new Item();
        map = new String[200][20];
        brick = new Brick[25];
        itemreward = new ItemBlock[25];
        coins = new Item[35];
        buzzy = new Buzzy[20];
        koopa = new Koopa[20];
        plant = new Plant[20];
        pipe = new  Pipe[20];
        for(int i = 0;i<brick.length;i++){
            brick[i] = new Brick();
        }
        for(int i = 0;i<itemreward.length;i++){
            itemreward[i] = new ItemBlock();
        }

        for(int i = 0;i<coins.length;i++){
            coins[i] = new Item();
        }

        for(int i = 0;i<buzzy.length;i++){
            buzzy[i] = new Buzzy();
        }

        for(int i = 0;i<koopa.length;i++){
            koopa[i] = new Koopa();
        }
        for(int i = 0;i<plant.length;i++){
            plant[i] = new Plant();
        }
        for(int i = 0;i<pipe.length;i++){
            pipe[i] = new Pipe();
        }

        level.setlevel();
        loadmap(level.getlevel1(),map);//call this function to load a new map
    }
    public void loadmap(String array[][],String map[][]) {
        int a=0,b=0,c=0,d=0,e=0,f=0,g=0;
        for (int i = 0; i < 200; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = array[i][j];
                if(array[i][j] == "brick"){
                    brick[a].SetCoordinate(i,j);
                    brick[a].alive = true;
                    a++;
                }
                if(array[i][j] == "itemreward"){
                    itemreward[b].SetCoordinate(i,j);
                    itemreward[b].alive=true;
                    b++;
                }
                if(array[i][j] == "coin"){
                    coins[c].SetCoordinate(i,j);
                    coins[c].alive = true;
                    c++;
                }
                if(array[i][j] == "buzzy_normal"){
                    buzzy[d].SetCoordinate(i,j);
                    buzzy[d].alive = true;
                    buzzy[d].state = "buzzy_normal";
                    buzzy[e].direction = "right";
                    d++;
                }
                if(array[i][j] == "koopa_fly"){
                    koopa[e].SetCoordinate(i,j);
                    koopa[e].alive = true;
                    koopa[e].state = "koopa_fly";
                    koopa[e].direction = "up";
                    e++;
                }
                if(array[i][j] == "plant"){
                    plant[f].SetCoordinate(i,j);
                    plant[f].alive = true;
                    plant[f].plant_flag = 0;
                    f++;
                }
                if(array[i][j] == "pipe"){
                    pipe[g].SetCoordinate(i,j);
                    pipe[g].alive = true;
                    g++;
                }
                if(array[i][j] == "level_switch"){
                    level_switch.SetCoordinate(i,j);
                }
            }
        }
    }
}
