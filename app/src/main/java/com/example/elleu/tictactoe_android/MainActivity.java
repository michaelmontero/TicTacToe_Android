package com.example.elleu.tictactoe_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{
    private int player;
    private SharedPreferences preferences;
    private int difficult;
    Button onePlayerButton,twoPlayerButton;
    private RadioGroup configDifficult;
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

        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void onClick(View view){ //Thus method is called went click 1 player or two player nutton
        int buttonId = view.getId();
        player = 1;
        difficult = 0;

        if(buttonId == R.id.twoPlayer){
            player = 2;
        }

        configDifficult= (RadioGroup)findViewById(R.id.radioGroup);
        int idRadio = configDifficult.getCheckedRadioButtonId(); //Get radio button checked id
        if(idRadio == R.id.normalDifificult){
            difficult = 1;
        }
        else if(idRadio == R.id.hardDifificult){
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

    private void marc(int tile) {
        Handler handler = new Handler();
        final ImageView image = (ImageView) findViewById(TILES[tile]);
        if (game.getPlayer() == 1) {
            image.setImageResource(R.drawable.circulo);
        } else {
            handler.post(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(650);
                        image.setImageResource(R.drawable.aspa);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        int result = game.turn();
        if(result != 0) {
            if (result == 1) {
                game = null;
                restartGame(String.valueOf(getResources().getText(R.string.circle_win)));
                preferences.edit().putInt("circulos",preferences.getInt("circulos",0) + 1).apply();
            }
            if (result == 2) {
                game = null;
                preferences.edit().putInt("aspas",preferences.getInt("aspas",0) + 1).apply();
                restartGame(String.valueOf(getResources().getText(R.string.crosses_win)));
            }
            if (result == 3) {
                game = null;
                preferences.edit().putInt("tie",preferences.getInt("tie",0) + 1).apply();
                restartGame(String.valueOf(getResources().getText(R.string.tie)));
            }
        }
    }
    private void restartGame(String result){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(result);
        builder.setCancelable(false);
        builder.setPositiveButton(getResources().getString(R.string.configure), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                twoPlayerButton.setEnabled(true);
                onePlayerButton.setEnabled(true);
                configDifficult.setAlpha(1);
            }
        });
        builder.setNegativeButton(getResources().getString(R.string.restart), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                start();
                game = new Game(difficult);
            }
        });
        builder.create().show();
    }
}