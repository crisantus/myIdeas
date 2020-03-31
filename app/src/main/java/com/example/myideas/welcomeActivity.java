package com.example.myideas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class welcomeActivity extends AppCompatActivity {

    public int SLEEP_TIMER=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Title bar option
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Title bar hide
        getSupportActionBar().hide();

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                                 WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_welcome);

        LogerLauncher logerLauncher=new LogerLauncher();
        logerLauncher.start();
    }



 private class LogerLauncher extends Thread{
        public void run(){
            try {
                sleep(1000 * SLEEP_TIMER);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent =new Intent(welcomeActivity.this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

}

