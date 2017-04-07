package com.example.elleu.tictactoe_android;

import java.util.Random;

/**
 * Created by Michael Montero on 9/28/2016.
 */

public class Game{

    private final static int TIE = 3;
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

        tile = getTileToPlay(2);
        if(tile != -1) return tile;

        if(difficult>0){
            tile =getTileToPlay(1);
            if(tile!=-1)return  tile;
        }
        if(difficult==2){
            if(TILE[4]==0){
                  return 4;
            }
            if(TILE[0] == 1 && TILE[8] == 0){
                return 8;
            }
            if(TILE[2] == 1 && TILE[6] == 0){
                return 6;
            }
            if(TILE[6] == 1 && TILE[2] == 0){
                return 0;
            }
            if(TILE[8] == 1 && TILE[0] == 0){
                return 0;
            }

            if(TILE[0] == 0) return 0;
            if(TILE[2] == 0) return 2;
            if(TILE[6] == 0) return 6;
            if(TILE[8] == 0) return 8;
        }
        Random randomTile = new Random();
        tile=randomTile.nextInt(9);
        return tile;
    }

    public boolean isTileMack(int tile){ //A tile is 1 when was played before
        if(TILE[tile] != 0){
            return  false;
        }else {
            TILE[tile] = playerX;
        }
        return true;
    }
    public  int turn(){
        int winner = isWinner();
        if(winner != 0){
            return  winner;
        }

        playerX++;
        if(playerX > 2){
            playerX = 1;
        }
        return 0;
    }
    public int isWinner(){
        //Horizontal
        int couter = 0;
        int winnerPlayer = TILE[0];
        int newTile[][] = new int[3][3];  //Represent the board

            for(int p =0 ; p < 3; p++){
                for (int i = 0; i < 3;i++){
                    newTile[p][i] = TILE[couter]; //Fill the board
                    couter++;
                }
            }
        for(int x = 0 ; x < 3; x ++){
            winnerPlayer = newTile[x][0];
            for(int y =0 ; y < 3; y++){
                if(newTile[x][y] == 0 || newTile[x][y] != winnerPlayer){
                    break;
                }
                winnerPlayer = newTile[x][y];
                if(y == 2){
                    return  winnerPlayer;
                }
            }
        }
        for(int x = 0 ; x < 3; x ++){
            winnerPlayer = newTile[0][x];
            for(int y = 0 ; y < 3; y++){
                if(newTile[y][x] == 0 || newTile[y][x] != winnerPlayer){
                    break;
                }
                winnerPlayer = newTile[y][x];
                if(y == 2){
                    return  winnerPlayer;
                }
            }
        }
        //Diagonal
        winnerPlayer = TILE[0];
        for(int x = 0; x < 3; x++){
            if(newTile[x][x] == 0 || winnerPlayer != newTile[x][x]){
                break;
            }
            winnerPlayer = newTile[x][x];
            if(x == 2){
                return  winnerPlayer;
            }
      }
        winnerPlayer = TILE[2];
        int counter = 0;
        for(int x = 2; x > -1; x--){
            if(newTile[counter][x] == 0 || winnerPlayer != newTile[counter][x]){
                break;
            }
            winnerPlayer = newTile[counter][x];
            if(x == 0){
                return  winnerPlayer;
            }
            counter++;
        }
        if(isGameTie()) return Game.TIE;
        return 0;
    }

    private int getTileToPlay(int player){
        int [][]COMBINATIONS = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
        int tileToPlay = -1;
        int count = 0;
        for(int i =0 ; i < COMBINATIONS.length; i++){
            for(int pos:COMBINATIONS[i]){
                if(TILE[pos]==player) count++;
                if(TILE[pos]==0) tileToPlay = pos;
            }
            if(count == 2 && tileToPlay!=-1)return tileToPlay;
            tileToPlay = -1;
            count = 0;
        }
        return -1;
    }

    public boolean isGameTie(){ //This function check is the game is tie
        for(int x =0; x < TILE.length; x++){
            if(TILE[x] == 0){ //Is there's an 0 in the board is't means there is a tile in blank
                break;
            }
            if(x == 8){
                return true;
            }
        }
        return false;
    }
    public int getPlayer() {  return playerX; }
}