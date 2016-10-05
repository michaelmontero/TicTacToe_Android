package com.example.elleu.tictactoe_android;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button btnOnePlayer, btnTwoPlayer;
    private int [] TILE =new int[9];
    private Game game;
    private int player = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOnePlayer = (Button) findViewById(R.id.onePlayer);
        btnTwoPlayer = (Button) findViewById(R.id.twoPlayer);

        //Initialize each tile
        TILE[0] = R.id.a1;
        TILE[1] = R.id.a2;
        TILE[2] = R.id.a3;
        TILE[3] = R.id.b1;
        TILE[4] = R.id.b2;
        TILE[5] = R.id.b3;
        TILE[6] = R.id.c1;
        TILE[7] = R.id.c2;
        TILE[8] = R.id.c3;

        btnOnePlayer.setOnClickListener(this);
        btnTwoPlayer.setOnClickListener(this);
    }
    public void onClick(View view){
        int id = view.getId();
        if(id == R.id.onePlayer){
            player = 1;
            start();
        }
        else if(id == R.id.twoPlayer){
            player = 2;
            start();
        }
    }

    public void marcTile(View view){//This method is called went click in any tile in the board

        if(game != null) { //Is the game does not start
            for (int x = 0; x < TILE.length; x++) {
                if (view.getId() == TILE[x]) {
                    ImageView imageView = (ImageView)findViewById(TILE[x]);
                    if(Game.players == 1){
                        imageView.setImageResource(R.drawable.circulo);
                     }else{
                        imageView.setImageResource(R.drawable.aspa);
                    }
                    break;
                }
            }
        }
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
}