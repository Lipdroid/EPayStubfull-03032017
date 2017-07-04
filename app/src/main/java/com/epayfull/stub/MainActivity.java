package com.epayfull.stub;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void create(View v){
        startActivity(new Intent(MainActivity.this , CreateActivity.class));
    }

    public void saved(View v){

    }

    public void rateapp(View v){
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id="+ getPackageName())));
        } catch (ActivityNotFoundException e1) {
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" +getPackageName())));
            } catch (ActivityNotFoundException e2) {
                Toast.makeText(MainActivity.this, "You don't have any app that can open this link", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
