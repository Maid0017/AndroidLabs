package com.example.androidlabs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;


import com.google.android.material.snackbar.Snackbar;

public class TestToolbar extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_toolbar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        inflater.inflate(R.menu.nav_drawer,menu);
        return true;
    }

@Override
    public boolean onOptionsItemSelected(MenuItem item) {
    int itemId = item.getItemId();
    switch (itemId) {

        case R.id.item1:
            Snackbar.make(findViewById(R.id.toolbar), "You clicked Item1", Snackbar.LENGTH_LONG).show();
            break;

        case R.id.item2:
            Snackbar.make(findViewById(R.id.toolbar), "You clicked Item2", Snackbar.LENGTH_LONG).show();
            break;

        case R.id.item3:
            Snackbar.make(findViewById(R.id.toolbar), "You clicked Item3", Snackbar.LENGTH_LONG).show();
            break;

        case R.id.chatItem:
            Intent i = new Intent(this,ChatRoomActivity.class);
            this.startActivity(i);
            break;

        case R.id.weatherItem:
            Intent j = new Intent(this,WeatherForecast.class);
            this.startActivity(j);
            break;

        case R.id.loginItem:
            Intent y = new Intent(this,ProfileActivity.class);
            setResult(500, y);
            finish();
            break;
        default:
            break;
    }
    return true;
}

}