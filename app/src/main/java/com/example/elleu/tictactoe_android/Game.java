package com.example.elleu.tictactoe_android;

import java.util.Random;

/**
 * Created by Michael Montero on 9/28/2016.
 */

public class Game{


    public static int player;
    public final int difficult;
    private String currentPlayer;

    public Game(int player, int difficult){
        this.player = player;
        this.difficult = difficult;
        currentPlayer = "x";
    }

    public int getRondomTile(){
        Random randomTile = new Random();
        return randomTile.nextInt(9);
    }
    public int getPlayer() {  return player; }

    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

