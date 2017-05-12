package com.spiderman.calnories.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.spiderman.calnories.R;
import com.spiderman.calnories.main.MainActivity;
import com.spiderman.calnories.signInGoogle.LoginActivity;
import com.spiderman.calnories.util.SessionUtils;

public class SpalshActivity extends AppCompatActivity {

    private final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if (SessionUtils.isLoggedIn(SpalshActivity.this)) {
                    intent = new Intent(SpalshActivity.this, MainActivity.class);
                } else {
                    intent = new Intent(SpalshActivity.this, LoginActivity.class);
                }

                startActivity(intent);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
