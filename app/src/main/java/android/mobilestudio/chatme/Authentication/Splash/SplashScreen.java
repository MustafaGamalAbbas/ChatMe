package android.mobilestudio.chatme.authentication.splash;

import android.app.Activity;
import android.content.Intent;
import android.mobilestudio.chatme.authentication.login.LoginActivity;
import android.mobilestudio.chatme.R;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreen extends Activity {

    private int SPLASH_DISPLAY_Delay = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_srceen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Main Screen . */
                startActivity(new Intent(SplashScreen.this, LoginActivity.class));
                finish();
            }
        }, SPLASH_DISPLAY_Delay);
    }
}
