package com.example.elleu.tictactoe_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity{
    private int player,difficult;
    private int[] TILES;
    private Game game;
    private FragmentManager fragmentManager;
    ConfigurationFragment configurationFragment;
    FragmentTransaction fragmentTransaction;

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

        //Begin fragent transaction
        fragmentManager = getFragmentManager();
        fragmentTransaction = getFragmentManager().beginTransaction();

        configurationFragment = new ConfigurationFragment();
        fragmentTransaction.add(R.id.contenedor, configurationFragment, "configurationFragment").addToBackStack(null);
        fragmentManager.popBackStack();
        fragmentTransaction.commit();
        //End fragment
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public void start(){
        ImageView image;
        for(int tile : TILES){
            image=(ImageView)findViewById(tile);
            image.setImageResource(R.drawable.casilla);
        }
        game = new Game(difficult);

        fragmentManager.popBackStack();
        fragmentManager.beginTransaction().remove(fragmentManager.findFragmentByTag("configurationFragment")).commit();
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

    public void setDifficult(int difficult){
        this.difficult = difficult;
    }
    public void setPlayer(int player){
        this.player = player;
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
            }
            if (result == 2) {
                game = null;
                restartGame(String.valueOf(getResources().getText(R.string.crosses_win)));
            }
            if (result == 3) {
                game = null;
                restartGame(String.valueOf(getResources().getText(R.string.tie)));
            }
        }
    }
    private void restartGame(String result){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle(result);
        builder.setCancelable(false);
        builder.setNegativeButton("Restart", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                start();
                game = new Game(difficult);
            }
        });
        builder.create().show();
    }
}