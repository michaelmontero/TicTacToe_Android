package com.example.elleu.tictactoe_android;

import java.util.Random;

/**
 * Created by Michael Montero on 9/28/2016.
 */

public class Game{


    public static int player;
    public final int difficult;
    private int TILE [];

    public Game(int difficult){
        player  = 1;
        this.difficult = difficult;
        TILE = new int[9];
        for(int x = 0; x < 9; x++){
            TILE[x] = 0;
        }
    }

    public int getRondomTile(){
        Random randomTile = new Random();
        return  randomTile.nextInt(9);

    }

    public boolean isTileMack(int tile){ //A tile is 1 when was played before
        if(TILE[tile] == 1){
            return  false;
        }else {
            TILE[tile] = player;
        }
        return true;
    }

    public  void turno(){
        player++;
        if(player > 2){
            player = 1;
        }
    }
    public int getPlayer() {  return player; }
}

