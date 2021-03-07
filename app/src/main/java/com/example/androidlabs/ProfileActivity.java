package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageButton;
public class ProfileActivity extends AppCompatActivity {

    private ImageButton confirmImgBtn;
    private Button chatRoomBtn;
    private Button weatherBtn;
    private Button toolbarBtn;

    public static final String PROFILE_ACTIVITY = "ACTIVITY_PROFILE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        confirmImgBtn = findViewById(R.id.confirmImgBtn);

        confirmImgBtn.setOnClickListener(view -> {
            dispatchTakePictureIntent();
        });

        chatRoomBtn = findViewById(R.id.chatRoomBtn);

        chatRoomBtn.setOnClickListener(view -> {
            Intent chatRoom = new Intent(ProfileActivity.this, ChatRoomActivity.class);
            startActivity(chatRoom);
                });

        weatherBtn = findViewById(R.id.weatherBtn);

        weatherBtn.setOnClickListener(view -> {
            Intent weatherForecast = new Intent(ProfileActivity.this, WeatherForecast.class);
            startActivity(weatherForecast);
        });

        toolbarBtn = findViewById(R.id.toolbarBtn);
        toolbarBtn.setOnClickListener(view -> {
            Intent TestToolbar = new Intent(this, TestToolbar.class);
            startActivityForResult(TestToolbar, 0);
        });

    Log.d(PROFILE_ACTIVITY, "In function: onCreate()");

    }
    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0 && resultCode == 500) {
            finish();
        }
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            confirmImgBtn.setImageBitmap(imageBitmap);
            Log.d(PROFILE_ACTIVITY, "In function: onActivityResult()");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(PROFILE_ACTIVITY, "In function: onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(PROFILE_ACTIVITY, "In function: onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(PROFILE_ACTIVITY, "In function: onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(PROFILE_ACTIVITY, "In function: onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(PROFILE_ACTIVITY, "In function: onDestroy()");
    }
}