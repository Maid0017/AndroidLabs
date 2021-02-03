package com.example.androidlabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;
    private EditText emailEdit;
    private EditText passwordEdit;
    private Button loginButton;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);
    passwordEdit = findViewById(R.id.passwordEdit);
    emailEdit = findViewById(R.id.emailEdit);
    loginButton = findViewById(R.id.loginButton);

    mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    mEditor = mPreferences.edit();
    checkSharedPreferences();
    loginButton.setOnClickListener(view -> {
        String email = emailEdit.getText().toString();
        mEditor.putString(getString(R.string.email), email);
        mEditor.commit();

        String password = passwordEdit.getText().toString();
        mEditor.putString(getString(R.string.password), password);
        mEditor.commit();

        Intent i = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(i);
    });
    
    }
    private void checkSharedPreferences() {
        String email = mPreferences.getString(getString(R.string.email), "");
        String password = mPreferences.getString(getString(R.string.password), "");

        emailEdit.setText(email);
        passwordEdit.setText(password);
    };
}