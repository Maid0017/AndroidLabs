package com.example.androidlabs;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

    passwordEdit = (EditText) findViewById(R.id.passwordEdit);
    emailEdit = (EditText) findViewById(R.id.emailEdit);
    loginButton = (Button) findViewById(R.id.loginButton);

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
    });
    
    }
    private void checkSharedPreferences() {
        String email = mPreferences.getString(getString(R.string.email), "");
        String password = mPreferences.getString(getString(R.string.password), "");

        emailEdit.setText(email);
        passwordEdit.setText(password);
    };
}