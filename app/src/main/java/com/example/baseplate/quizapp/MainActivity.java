package com.example.baseplate.quizapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import fr.castorflex.android.smoothprogressbar.SmoothProgressBar;

public class MainActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;

    String data;
    ArrayList<pojo> dataset =  new ArrayList<>();
    LinearLayoutManager layoutmanager;
    RecyclerView recyclerView;
    static View.OnClickListener myOnClickListener;

    SmoothProgressBar mprogbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mprogbar = (SmoothProgressBar)findViewById(R.id.smoothProgressBar);
        CountDownTimer countDownTimer = new CountDownTimer(60000, 1000) {

            public void onTick(long millisUntilFinished) {

            }

            public void onFinish() {
                mprogbar.setVisibility(View.GONE);
                starto();
            }

        };

        countDownTimer.start();

        data = load_json_data();
//        Toast.makeText(getApplicationContext(), "data : " + data + "", Toast.LENGTH_SHORT).show();

        dataset = parse_json(data);
        myOnClickListener = new MyOnClickListener(this);

        recyclerView = findViewById(R.id.rv);
        recyclerView.setHasFixedSize(true);

        layoutmanager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutmanager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        Log.d("test : ", dataset.get(0).toString());

        adapter = new CustomAdapter(dataset, getApplicationContext());
        recyclerView.setAdapter(adapter);


    }

    private static class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {}
    }

    public String load_json_data() {
        String json = null;
        try {
            InputStream is = getAssets().open("data.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public ArrayList<pojo> parse_json(String data){

        ArrayList<pojo> rawdata = new ArrayList<>();

        try {
            JSONObject in = new JSONObject(data);

            JSONArray root = in.getJSONArray("root");
            for (int i = 0; i < root.length(); ++i){
                JSONObject buffer = root.getJSONObject(i);

                String txt = buffer.getString("txt");
                String answer = buffer.getString("ans");

                String[] options = new String[4];
                options[0] = buffer.getString("op1");
                options[1] = buffer.getString("op2");
                options[2] = buffer.getString("op3");
                options[3] = buffer.getString("op4");


                rawdata.add(new pojo(txt, answer, options));
//                Log.d("test text : ", txt + answer + "");
//                Log.d("test : ", rawdata.get(i).getQuestion() + "");
            }

            return  rawdata;

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  rawdata;
    }

    public void starto(){
        startActivity(new Intent(MainActivity.this, StartActivity.class));
    }

}