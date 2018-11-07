package com.example.abass.quoteme;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                nextActivity();
            }
        };

        new Handler().postDelayed(runnable, 4000);
    }

    void nextActivity(){
        startActivity(new Intent(SplashActivity.this, MainActivity.class));
    }
}
