package com.example.elleu.tictactoe_android;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    private int player;
    private int difficult;
    Button onePlayerButton,twoPlayerButton;
    private int[] TILES;
    private Game game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initializing each tile and saving in the array
        TILES = new int[9];

        TILES[0] = R.id.a1;
        TILES[1] =R.id.a2;
        TILES[2] = R.id.a3;

        TILES[3] = R.id.b1;
        TILES[4] = R.id.b2;
        TILES[5] = R.id.b3;

        TILES[6] = R.id.c1;
        TILES[7] = R.id.c2;
        TILES[8] = R.id.c3;



        onePlayerButton = (Button)findViewById(R.id.onePlayer);
        onePlayerButton.setOnClickListener(this);
        twoPlayerButton = (Button)findViewById(R.id.twoPlayer);
        twoPlayerButton.setOnClickListener(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void onClick(View view){ //Thus method is called went click 1 player or two player nutton
        int id = view.getId();
        player = 1;
        difficult = 0;

        if(id == R.id.twoPlayer){
            player = 2;
        }

        RadioGroup configDifficult = (RadioGroup)findViewById(R.id.radioGroup);
        int idRadio = configDifficult.getCheckedRadioButtonId(); //Get radio button checked id
        if(idRadio == R.id.normalDifificult){
            difficult = 1;
        }
        else if(idRadio == R.id.hardDifificult)
        {
            difficult = 2;
        }

        game = new Game(difficult, player);

        twoPlayerButton.setEnabled(false);
        onePlayerButton.setEnabled(false);
        configDifficult.setAlpha(0);

        start();
    }

    private void start(){
        ImageView image;
        for(int tile : TILES){
            image=(ImageView)findViewById(tile);
            image.setImageResource(R.drawable.casilla);
        }
    }

    public void marcTile(View view){//This method is called went click in any tile in the board

        if(game != null) { //Is the game exists does not start
            int tile = 0;
            for (int x = 0; x < 9; x++) {
                if (TILES[x] == view.getId()) {
                    tile = x;
                    break;
                }
            }
            marc(tile);
            if(player == 1){ //if they is a player agains the machine get a random tile
                tile = game.getRondomTile(); //After
                marc(tile);
            }
        }
    }

    private void marc(int tile){
        ImageView image = (ImageView)findViewById(TILES[tile]);

        if(game.getCurrentPlayer() == "x"){
            image.setImageResource(R.drawable.circulo);
            game.setCurrentPlayer("y");
        }else{
            image.setImageResource(R.drawable.aspa);
            game.setCurrentPlayer("x");
        }
    }


         /*   for (int x = 0; x < TILES.length; x++) {
                if (view.getId() == TILES[x]) {
                    ImageView imageView = (ImageView)findViewById(TILES[x]);
                    if(game.getCurrentPlayer() == "x"){
                        imageView.setImageResource(R.drawable.circulo);
                        game.setCurrentPlayer("y");
                     }else{
                        imageView.setImageResource(R.drawable.aspa);
                        game.setCurrentPlayer("x");
                    }
                    break;
                }
            }
            */

/*
    private void checkMarkTile(){

    }
    private void start(){ //Thus methos should be called went click 1 player or two player
        ImageView image;
        for(int tiles : TILE){ //Reset the board
            image = (ImageView)findViewById(tiles);
            image.setImageResource(R.drawable.casilla);
        }

        RadioGroup difficult = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedDifficult = difficult.getCheckedRadioButtonId();

        if(selectedDifficult == R.id.easyDifificult){
            selectedDifficult = 1;
        }else if(selectedDifficult == R.id.normalDifificult){
            selectedDifficult = 2;
        }else if(selectedDifficult == R.id.hardDifificult){
            selectedDifficult = 3;
        }

        btnOnePlayer.setEnabled(false);
        btnTwoPlayer.setEnabled(false);
        difficult.setAlpha(0);

        game = new Game(player, selectedDifficult); //Start the game!
    }
    */
}