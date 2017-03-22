package com.example.elleu.tictactoe_android;

import android.app.Activity;
import android.widget.Toast;

import java.util.Random;

/**
 * Created by Michael Montero on 9/28/2016.
 */

public class Game{


    public static int playerX;
    public final int difficult;

    private int TILE [];
    public Game(int difficult){
        playerX  = 1;
        this.difficult = difficult;
        TILE = new int[9];
        for(int x = 0; x < 9; x++){
            TILE[x] = 0;
        }
    }

    public int getRondomTile(){
        int tile;
        Random randomTile = new Random();
        return randomTile.nextInt(9);

    }

    public boolean isTileMack(int tile){ //A tile is 1 when was played before
        if(TILE[tile] != 0){
            return  false;
        }else {
            TILE[tile] = playerX;
        }
        return true;
    }

    public  int turno(){
        int winner = isWinner();
        if(winner != 0){
            return  winner;
        }

        this.playerX++;
        if(playerX > 2){
            playerX = 1;
        }
        return 0;
    }
    public int isWinner(){
        //Horizontal

        int couter = 0;
        int winnerPlayer = TILE[0];
        int newTile[][] = new int[3][3];

            for(int p =0 ; p < 3; p++){
                for (int i = 0; i < 3;i++){
                    newTile[p][i] = TILE[couter];
                    couter++;
                }
            }
        //Horizontal validation
        horizontalValidation:
            for(int x = 0; x < 3; x++){
                for(int j =0; j < 3; j++){
                    if(winnerPlayer != newTile[x][j] || newTile[x][j] == 0){
                        break horizontalValidation;
                    }
                    winnerPlayer = newTile[x][j];
                    if(j == 2){ //Horizontal winner
                        return  winnerPlayer;
                    }
                }
            }
        winnerPlayer = TILE[0];
        //Vertical
        verticalValidation:
        for(int x = 0; x < 3; x++){
            for(int j =0; j < 3; j++){
                if(winnerPlayer != newTile[j][x] || newTile[j][x] == 0){
                    break verticalValidation;
                }
                winnerPlayer = newTile[j][x];
                if(j == 2){ //Vertical winner
                    return  winnerPlayer;
                }
            }
        }

        //Diagonal
        winnerPlayer = TILE[0];
        for(int x = 0; x < 3; x++){
            if(winnerPlayer != newTile[x][x] || newTile[x][x] == 0){
                break;
            }
            winnerPlayer = newTile[x][x];
            if(x == 2){ //Vertical winner
                return  winnerPlayer;
            }
        }
        winnerPlayer = TILE[0];
        for(int x = 3; x < 0; x--){
            if(winnerPlayer != newTile[x][x] || newTile[x][x] != 0){
                break;
            }
            winnerPlayer = newTile[x][x];
            if(x == 0){ //Vertical winner
                return  winnerPlayer;
            }
        }

        return 0;
    }
    public int getPlayer() {  return playerX; }
}

