package com.develpoment.gobolabali.fundamentalstatistic;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private static int splashInterval = 3000;
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(intent);
//                finish();
//            }
//
//            private void finish() {
//                // TODO Auto-generated method stub
//
//            }
//
//        }, splashInterval);

        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated met
                // hod stub
                //Intent intent = new Intent(activity,PromosiPenjualan.class);
                // startActivity(intent);
                //   finish();
                //}
                // }else {

                Intent o = new Intent(SplashActivity.this, MainActivity.class);
                o.putExtra("kuncitanyagps", "1") ;
                startActivity(o);
                finish();
            }


        };

        handler.postDelayed(runnable, 3000);

    }
}
