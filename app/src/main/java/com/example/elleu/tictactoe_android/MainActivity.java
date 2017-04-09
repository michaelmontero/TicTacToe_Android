package com.example.elleu.tictactoe_android;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
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
        int buttonId = view.getId();
        player = 1;
        difficult = 0;

        if(buttonId == R.id.twoPlayer){
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

        game = new Game(difficult);

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
            if(!game.isTileMack(tile)){
                return;
            }
            marc(tile);
            if(player == 1) { //if they is a player agains the machine get a random tile
                if (game != null) {
                    tile = game.getRondomTile(); //After
                    while (!game.isTileMack(tile) && !game.isGameTie()) {
                        tile = game.getRondomTile();
                    }
                        marc(tile);
                }
            }
        }
    }

    private void marc(int tile){
        ImageView image = (ImageView)findViewById(TILES[tile]);
        if(game.getPlayer() == 1){
            image.setImageResource(R.drawable.circulo);
        }else{
            image.setImageResource(R.drawable.aspa);
        }
        int result = game.turn();
        if(result == 1) {
            Toast.makeText(MainActivity.this, String.valueOf(getResources().getText(R.string.circle_win)), Toast.LENGTH_LONG).show();
            game = null;
        }
        if(result == 2) {
            Toast.makeText(MainActivity.this, String.valueOf(getResources().getText(R.string.crosses_win)), Toast.LENGTH_LONG).show();
            game = null;
        }
         if(result == 3){
               Toast.makeText(MainActivity.this, String.valueOf(getResources().getText(R.string.tie)), Toast.LENGTH_LONG).show();
             game = null;
             //start();
         }
    }
}