package com.example.elleu.tictactoe_android;

/**
 * Created by Michael Montero on 9/28/2016.
 */

public class Game{
    public static int players;
    public final int difficult;
    private int currentPlayer;

    public Game(int players, int difficult){
        this.players = 1;
        this.difficult = difficult;
        currentPlayer = 0;
    }

    public int getCurrentPlayer() {
        return currentPlayer;
    }
    public void setCurrentPlayer(int currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}

