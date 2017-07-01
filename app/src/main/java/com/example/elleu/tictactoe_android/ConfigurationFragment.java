package com.example.elleu.tictactoe_android;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

/**
 * Created by elleu on 01/07/2017.
 */

public class ConfigurationFragment extends Fragment implements View.OnClickListener{
    Button onePlayerButton,twoPlayerButton;
    private int player;
    private int difficult;
    RadioGroup configDifficult;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.configuration_layout, container,false);
        onePlayerButton = (Button)view.findViewById(R.id.onePlayer);
        onePlayerButton.setOnClickListener(this);
        twoPlayerButton = (Button)view.findViewById(R.id.twoPlayer);
        twoPlayerButton.setOnClickListener(this);

        configDifficult = (RadioGroup)view.findViewById(R.id.radioGroup);
        return view;
    }

    @Override
    public void onClick(View view) {
        int buttonId = view.getId();
        ((MainActivity)getActivity()).setPlayer(1);
        ((MainActivity)getActivity()).setDifficult(0);

        if(buttonId == R.id.twoPlayer){
            ((MainActivity)getActivity()).setPlayer(2);
        }

        int idRadio = configDifficult.getCheckedRadioButtonId(); //Get radio button checked id
        if(idRadio == R.id.normalDifificult){
            ((MainActivity)getActivity()).setDifficult(2);
        }
        else if(idRadio == R.id.hardDifificult){
            ((MainActivity)getActivity()).setDifficult(2);
        }

        twoPlayerButton.setEnabled(false);
        onePlayerButton.setEnabled(false);
        configDifficult.setAlpha(0);
        ((MainActivity)getActivity()).start();
    }
}
