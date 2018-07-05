package com.example.baseplate.quizapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);


        SharedPreferences preferences = getSharedPreferences("score_pref", MODE_PRIVATE);

        int count = preferences.getInt("count", 0);

        TextView tv = (TextView)findViewById(R.id.textView);
        tv.setText("You scored : " + count + "/6");


    }
}
