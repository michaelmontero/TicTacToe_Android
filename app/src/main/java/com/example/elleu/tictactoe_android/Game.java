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
        int winner = 0;
        int couter = 0;
        int winnerPlayer = TILE[0];
        int newTile[][] = new int[3][3];

            for(int p =0 ; p < 3; p++){
                for (int i = 0; i < 3;i++){
                    newTile[p][i] = TILE[couter];
                    couter++;
                }
            }

        for(int x = 0 ; x < 3; x ++){
            winnerPlayer = newTile[x][0];
            for(int y =0 ; y < 3; y++){
                if(newTile[x][y] != 0 && newTile[x][y] == winnerPlayer){
                    winner++;
                    winnerPlayer = newTile[x][y];
                }
                else{
                    winner = 0;
                    break;
                }
                if(winner == 3){
                    return  winnerPlayer;
                }
            }
        }

        for(int x = 0 ; x < 3; x ++){
            winnerPlayer = newTile[0][x];
            for(int y =0 ; y < 3; y++){
                if(newTile[y][x] != 0 && newTile[y][x] == winnerPlayer){
                    winner++;
                    winnerPlayer = newTile[y][x];
                }
                else{
                    winner = 0;
                    break;
                }
                if(winner == 3){
                    return  winnerPlayer;
                }
            }
        }

        //Diagonal
        winnerPlayer = TILE[0];
        for(int x = 0; x < 3; x++){
            if(newTile[x][x] != 0 && winnerPlayer == newTile[x][x]){
                winner++;
                winnerPlayer = newTile[x][x];
            }
            else{
                winner = 0;
                break;
            }
            if(winner == 3){
                return  winnerPlayer;
            }
        }

        winnerPlayer = TILE[2];
        int counter = 0;
        for(int x = 2; x > -1; x--){

            if(newTile[counter][x] != 0 && winnerPlayer == newTile[counter][x]){
                winner++;
                winnerPlayer = newTile[counter][x];
            }
            else{
                winner = 0;
                break;
            }
            counter++;
            if(winner == 3){
                return  winnerPlayer;
            }
        }


        return 0;
    }
    public int getPlayer() {  return playerX; }
}

