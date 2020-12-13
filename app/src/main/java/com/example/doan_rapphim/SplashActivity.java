package com.example.doan_rapphim;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        loadData();
    }

    private void loadData() {
        if(AppUtil.isNetworkAvailable(this)){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 3000);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // Set a title for alert dialog
            builder.setTitle("Thông Báo");

            // Ask the final question
            builder.setMessage("Điện thoại chưa kết nối Wifi hoặc dữ liệu di động");

            // Set the alert dialog yes button click listener
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when user clicked the Yes button
                    // Set the TextView visibility GONE
                    System.exit(0);
                }
            });

            // Set the alert dialog no button click listener


            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();
        }
    }
}