package com.example.yorkl.leyuarron_assignment4;

public class Levels{//this function is designed with hard coded level arrays
    // but can be used to make new levels by modifying the strings
    //200x20 0,0 at top right corner
    String level1[][];
    String level2[][];
    String level3[][];

    public Levels() {
        this.level1 = new String[200][20];
        this.level2 = new String[200][20];
        this.level3 = new String[200][20];
    }

    public String[][] getlevel1(){
        return this.level1;
    }
    public String[][] getlevel2(){
        return this.level2;
    }
    public String[][] getlevel3(){
        return this.level3;
    }

    public void setlevel(){
        for(int i= 0;i<200;i++) {
            for (int j = 0; j < 20; j++) {
                this.level1[i][j] = "nothing";
                this.level2[i][j] = "nothing";
                this.level3[i][j] = "nothing";
            }
        }
            for(int k = 0;k<200;k++){
                this.level1[k][15] = "dirt";
                this.level1[k][16] = "dirt";
                this.level1[k][17] = "dirt";
            }

        this.level1[5][10] = "itemreward";
        this.level1[6][10] = "brick";
        this.level1[21][10] = "coin";
        this.level1[22][10] = "coin";
        this.level1[23][10] = "coin";
        this.level1[25][13] = "koopa_fly";
        this.level1[28][10] = "itemreward";
        this.level1[16][13] = "plant";
        this.level1[16][14] = "pipe";
        this.level1[14][14] = "buzzy_normal";
        this.level1[44][10] = "brick";
        this.level1[45][10] = "coin";
        this.level1[46][10] = "itemreward";
        this.level1[52][10] = "coin";
        this.level1[53][10] = "coin";
        this.level1[67][10] = "brick";
        this.level1[68][10] = "brick";
        this.level1[72][13] = "plant";
        this.level1[72][14] = "pipe";
        this.level1[90][10] = "brick";
        this.level1[91][10] = "brick";
        this.level1[92][10] = "brick";
        this.level1[93][10] = "itemreward";
        this.level1[105][10] = "coin";
        this.level1[119][13] = "koopa_fly";
        this.level1[136][13] = "plant";
        this.level1[136][14] = "pipe";
        this.level1[146][10] = "coin";
        this.level1[147][10] = "coin";
        this.level1[153][10] = "itemreward";
        this.level1[154][10] = "brick";
        this.level1[162][13] = "plant";
        this.level1[162][14] = "pipe";
        this.level1[170][14] = "level_switch";

        for(int k = 0;k<200;k++){
            this.level2[k][15] = "dirt";
            this.level2[k][16] = "dirt";
            this.level2[k][17] = "dirt";
        }

        this.level2[5][10] = "coin";
        this.level2[6][10] = "coin";
        this.level2[21][10] = "coin";
        this.level2[22][10] = "coin";
        this.level2[23][10] = "coin";
        this.level2[25][13] = "koopa_fly";
        this.level2[28][10] = "itemreward";
        this.level2[16][13] = "plant";
        this.level2[16][14] = "pipe";
        this.level2[14][14] = "buzzy_normal";
        this.level2[43][10] = "brick";
        this.level2[44][10] = "brick";
        this.level2[45][10] = "brick";
        this.level2[46][10] = "itemreward";
        this.level2[48][13] = "koopa_fly";
        this.level2[52][13] = "koopa_fly";
        //this.level2[59][14] = "buzzy_normal";
        this.level2[67][10] = "brick";
        this.level2[68][10] = "brick";
        this.level2[72][13] = "plant";
        this.level2[72][14] = "pipe";
        this.level2[90][10] = "brick";
        this.level2[91][10] = "brick";
        this.level2[92][10] = "brick";
        this.level2[93][10] = "itemreward";
        this.level2[105][10] = "coin";
        this.level2[106][10] = "coin";
        this.level2[107][10] = "coin";
        this.level2[112][13] = "koopa_fly";
        this.level2[122][13] = "koopa_fly";
        this.level2[136][13] = "plant";
        this.level2[136][14] = "pipe";
        this.level2[146][10] = "coin";
        this.level2[147][10] = "coin";
        this.level2[153][10] = "itemreward";
        this.level2[154][10] = "brick";
        this.level2[156][10] = "brick";
        this.level2[157][10] = "brick";
        this.level2[166][13] = "plant";
        this.level2[166][14] = "pipe";
        this.level2[170][14] = "level_switch";

        for(int k = 0;k<200;k++){
            this.level3[k][15] = "dirt";
            this.level3[k][16] = "dirt";
            this.level3[k][17] = "dirt";
        }

        this.level3[5][10] = "coin";
        this.level3[6][10] = "coin";
        this.level3[7][10] = "coin";
        this.level3[12][13] = "koopa_fly";
        this.level3[16][13] = "plant";
        this.level3[16][14] = "pipe";
        this.level3[21][10] = "coin";
        this.level3[22][10] = "coin";
        this.level3[23][10] = "coin";
        this.level3[25][13] = "koopa_fly";
        this.level3[28][10] = "itemreward";
        this.level3[34][13] = "koopa_fly";
        this.level3[40][10] = "brick";
        this.level3[41][10] = "brick";
        this.level3[45][10] = "coin";
        this.level3[46][10] = "itemreward";
        this.level3[48][13] = "koopa_fly";
        this.level3[52][13] = "koopa_fly";
        this.level3[59][14] = "koopa_fly";
        this.level3[67][10] = "brick";
        this.level3[68][10] = "brick";
        this.level3[72][13] = "plant";
        this.level3[72][14] = "pipe";
        this.level3[76][10] = "itemreward";
        this.level3[90][10] = "brick";
        this.level3[91][10] = "brick";
        this.level3[92][10] = "brick";
        this.level3[93][10] = "itemreward";
        this.level3[105][10] = "coin";
        this.level3[106][10] = "coin";
        this.level3[107][10] = "coin";
        this.level3[112][13] = "koopa_fly";
        this.level3[122][13] = "koopa_fly";
        this.level3[136][13] = "plant";
        this.level3[136][14] = "pipe";
        this.level3[146][10] = "coin";
        this.level3[147][10] = "coin";
        this.level3[153][10] = "itemreward";
        this.level3[154][10] = "brick";
        this.level3[156][10] = "brick";
        this.level3[157][10] = "brick";
        this.level3[166][13] = "plant";
        this.level3[166][14] = "pipe";
        this.level3[170][14] = "level_switch";
    }
}
