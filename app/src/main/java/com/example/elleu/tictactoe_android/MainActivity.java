package com.example.elleu.tictactoe_android;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;

public class MainActivity extends Activity implements View.OnClickListener{

    private Button btnOnePlayer, btnTwoPlayer;
    private int [] tile =new int[9];
    private Game game;
    private int player = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnOnePlayer = (Button) findViewById(R.id.onePlayer);
        btnTwoPlayer = (Button) findViewById(R.id.twoPlayer);

        //Initialize each tile
        tile[0] = R.id.a1;
        tile[1] = R.id.a2;
        tile[2] = R.id.a3;
        tile[3] = R.id.b1;
        tile[4] = R.id.b2;
        tile[5] = R.id.b3;
        tile[6] = R.id.c1;
        tile[7] = R.id.c2;
        tile[8] = R.id.c3;

        btnOnePlayer.setOnClickListener(this);
        btnTwoPlayer.setOnClickListener(this);
    }
    public void onClick(View view){
        int id = view.getId();
        if(id == R.id.onePlayer){
            start();
        }
        else if(id == R.id.twoPlayer){
            start();
            player = 2;
        }
    }

    private void start(){ //Thus methos should be called went click
        ImageView image;
        for(int tiles : tile){ //Reset the board
            image = (ImageView)findViewById(tiles);
            image.setImageResource(R.drawable.casilla);
        }

        RadioGroup difficult = (RadioGroup) findViewById(R.id.radioGroup);
        int selectedDifficult = difficult.getCheckedRadioButtonId();
        selectedDifficult = 1;

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

        game = new Game(player, selectedDifficult);
    }
}