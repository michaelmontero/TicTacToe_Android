package com.example.elleu.tictactoe_android;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onClick(View view) {
        int buttonId = view.getId();
        MainActivity mainActivity = (MainActivity)getActivity();
        mainActivity.setPlayer(1);
        mainActivity.setDifficult(0);

        if(buttonId == R.id.twoPlayer){
            ((MainActivity)getActivity()).setPlayer(2);
        }

        int idRadio = configDifficult.getCheckedRadioButtonId(); //Get radio button checked id
        if(idRadio == R.id.normalDifificult){
            mainActivity.setDifficult(2);
        }
        else if(idRadio == R.id.hardDifificult){
            mainActivity.setDifficult(2);
        }
        if(idRadio > 0){
            mainActivity.start();//Clear all the tile and hide the fragment
        }
    }
}
