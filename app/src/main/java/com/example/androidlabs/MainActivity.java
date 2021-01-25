package com.example.androidlabs;

import android.os.Bundle;
import android.widget.CheckBox;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_linear);

    CheckBox cb =(CheckBox)findViewById(R.id.chckbox);
    cb.setOnCheckedChangeListener( ((compoundButton, b) -> {
        Snackbar.make(cb,"Yo Snackbar", Snackbar.LENGTH_LONG)
                .setAction("Undo", click -> compoundButton.setChecked(!b))
                .show();
        }));
    }

}