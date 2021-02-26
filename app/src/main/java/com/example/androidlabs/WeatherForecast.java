package com.example.androidlabs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {
    private static String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric";
    private static String imgUrl = "http://openweathermap.org/img/w/";
    private static String uvUrl = "http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389";

    private ImageView weatherIconView;
    private TextView currentView;
    private TextView minView;
    private TextView maxView;
    private ProgressBar progressBar;
    private TextView uvTextView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        uvTextView = (TextView) findViewById(R.id.uvView);
        weatherIconView = (ImageView) findViewById(R.id.weatherIconView);
        currentView = (TextView) findViewById(R.id.currentView);
        minView = (TextView) findViewById(R.id.minView);
        maxView = (TextView) findViewById(R.id.maxView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        new ForecastQuery().execute();

    }


    private class ForecastQuery extends AsyncTask<String, Integer, String> {
        private String minTemp = null;
        private String currentTemp = null;
        private String maxTemp = null;
        private String uvText = null;
        private String fname = null;
        private Bitmap weatherIcon = null;

        @Override
        protected String doInBackground(String... params) {
            XmlPullParser xpp = null;
            int eventType = 0;
            try {
                URL url = new URL(weatherUrl);
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream response = urlConnection.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                xpp = Xml.newPullParser();
                xpp.setInput(response, "UTF-8");

                eventType = xpp.getEventType();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
            while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equals("current")) {
                        } else if (xpp.getName().equals("temperature")) {
                            currentTemp = xpp.getAttributeValue(null, "value");
                            publishProgress(25);
                            minTemp = xpp.getAttributeValue(null, "min");
                            publishProgress(50);
                            maxTemp = xpp.getAttributeValue(null, "max");
                            publishProgress(75);
                        } else if (xpp.getName().equals("weather")) {
                            fname = xpp.getAttributeValue(null, "icon");
                            if (!fileExistance(fname)) {
                                HttpURLConnection connection = null;
                                try {
                                    URL iconUrl = new URL(imgUrl + fname + ".png");
                                    connection = (HttpURLConnection) iconUrl.openConnection();
                                    connection.connect();
                                    int responseCode = connection.getResponseCode();
                                    if (responseCode == 200) {
                                        weatherIcon = BitmapFactory.decodeStream(connection.getInputStream());
                                        FileOutputStream outputStream = openFileOutput(fname, Context.MODE_PRIVATE);
                                        weatherIcon.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                        outputStream.flush();
                                        outputStream.close();
                                        Log.i("WeatherForecast", fname + " was displayed from download.");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                Log.i("WeatherForecast", fname + " was displayed from local file.");
                                try {
                                    FileInputStream in = null;
                                    in = openFileInput(fname);
                                    weatherIcon = BitmapFactory.decodeStream(in);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            publishProgress(100);
                        }
                    }
                    try {
                        eventType = xpp.next();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    }
                }

            try {
                URL url = new URL(uvUrl);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                InputStream response = urlConnection.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
                StringBuilder sb = new StringBuilder();

                String line = null;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");
                }
                String result = sb.toString();

                JSONObject uvReport = new JSONObject(result);
                uvText = String.valueOf(uvReport.getDouble("value"));

            } catch (Exception e) {
                e.printStackTrace();
            }
                return null;
            }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            uvTextView.setText("UV: " + uvText);
            currentView.setText("Current: " + currentTemp);
            minView.setText("Min: " + minTemp);
            maxView.setText("Max: " + maxTemp);
            weatherIconView.setImageBitmap(weatherIcon);
        }

        public boolean fileExistance(String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        }
}