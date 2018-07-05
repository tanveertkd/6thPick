package com.example.baseplate.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    public  int counter = 0;
    private ArrayList<pojo> dataSet;
    Context context;
    static boolean flush_flag= false;

    public static class MyViewHolder extends RecyclerView.ViewHolder{


        TextView questionsView;
        RadioButton radioBtn, radioBtn2;
        CheckBox checkBox, checkBox2, checkBox3, checkBox4;
        EditText userAnswer;
        Button submit;


        public MyViewHolder(View itemView) {
            super(itemView);
            this.questionsView = (TextView) itemView.findViewById(R.id.textView2);
            this.radioBtn = (RadioButton) itemView.findViewById(R.id.radioButton);
            this.radioBtn2 = (RadioButton) itemView.findViewById(R.id.radioButton2);
            this.checkBox = (CheckBox) itemView.findViewById(R.id.checkBox);
            this.checkBox2 = (CheckBox) itemView.findViewById(R.id.checkBox2);
            this.checkBox3 = (CheckBox) itemView.findViewById(R.id.checkBox3);
            this.checkBox4 = (CheckBox) itemView.findViewById(R.id.checkBox4);
            this.userAnswer = (EditText) itemView.findViewById(R.id.editText);
            this.submit = (Button)itemView.findViewById(R.id.submit);
        }
    }

    public CustomAdapter(ArrayList<pojo> data, Context context) {
        this.dataSet = data;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        view.setOnClickListener(MainActivity.myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        final TextView textViewName = holder.questionsView;
        final RadioButton radioOne = holder.radioBtn;
        final RadioButton radioTwo = holder.radioBtn2;
        final CheckBox checkBoxOne = holder.checkBox;
        final CheckBox checkBoxTwo = holder.checkBox2;
        final CheckBox checkBoxThree = holder.checkBox3;
        final CheckBox checkBoxFour = holder.checkBox4;
        final EditText textBox = holder.userAnswer;
        final Button submit = holder.submit;

        if (!flush_flag){
            flush_pref();
            flush_flag = true;
        }
        counter = 0;

        textBox.setVisibility(View.INVISIBLE);
        checkBoxOne.setVisibility(View.INVISIBLE);
        checkBoxTwo.setVisibility(View.INVISIBLE);
        checkBoxThree.setVisibility(View.INVISIBLE);
        checkBoxFour.setVisibility(View.INVISIBLE);
        radioOne.setVisibility(View.INVISIBLE);
        radioTwo.setVisibility(View.INVISIBLE);
        submit.setVisibility(View.INVISIBLE);


        if(listPosition>=0 && listPosition<2){
            checkBoxOne.setVisibility(View.VISIBLE);
            checkBoxTwo.setVisibility(View.VISIBLE);
            checkBoxThree.setVisibility(View.VISIBLE);
            checkBoxFour.setVisibility(View.VISIBLE);

        }
        else if(listPosition>=2 && listPosition<4){
            checkBoxOne.setVisibility(View.INVISIBLE);
            checkBoxTwo.setVisibility(View.INVISIBLE);
            checkBoxThree.setVisibility(View.INVISIBLE);
            checkBoxFour.setVisibility(View.INVISIBLE);
            radioOne.setVisibility(View.VISIBLE);
            radioTwo.setVisibility(View.VISIBLE);
        }
        else{
            textBox.setVisibility(View.VISIBLE);
            submit.setVisibility(View.VISIBLE);
            radioOne.setVisibility(View.INVISIBLE);
            radioTwo.setVisibility(View.INVISIBLE);
        }

        textViewName.setText(dataSet.get(listPosition).getQuestion());
        checkBoxOne.setText(dataSet.get(listPosition).getOptions(0));
        checkBoxTwo.setText(dataSet.get(listPosition).getOptions(1));
        checkBoxThree.setText(dataSet.get(listPosition).getOptions(2));
        checkBoxFour.setText(dataSet.get(listPosition).getOptions(3));
        radioOne.setText(dataSet.get(listPosition).getOptions(0));
        radioTwo.setText(dataSet.get(listPosition).getOptions(1));


        checkBoxOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxOne.getText().toString().equals(dataSet.get(listPosition).getAnswer())) {

                    push_pref();

                    Log.d("CORRECT", "Correct");
                    counter++;
                    Log.d("ADebugTag", "Value: " + Integer.toString(counter));
                }
                else
                    Log.d("inCORRECT", "inCorrect");

            }
        });
        checkBoxTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxTwo.getText().toString().equals(dataSet.get(listPosition).getAnswer())){

                    push_pref();

                    Log.d("CORRECT", "Correct");
                    counter++;
                    Log.d("ADebugTag", "Value: " + Integer.toString(counter));
                }
                else
                    Log.d("inCORRECT", "inCorrect");

            }
        });
        checkBoxThree.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxThree.getText().toString().equals(dataSet.get(listPosition).getAnswer())){

                    push_pref();

                    Log.d("CORRECT", "Correct");
                    counter++;
                    Log.d("ADebugTag", "Value: " + Integer.toString(counter));
                }
                else
                    Log.d("inCORRECT", "inCorrect");

            }
        });
        checkBoxFour.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBoxFour.getText().toString().equals(dataSet.get(listPosition).getAnswer())) {

                    push_pref();

                    Log.d("CORRECT", "Correct");
                    counter++;
                    Log.d("ADebugTag", "Value: " + Integer.toString(counter));
                }
                else
                    Log.d("inCORRECT", "inCorrect");
            }
        });

        radioOne.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(radioOne.getText().toString().equals(dataSet.get(listPosition).getAnswer())) {

                    push_pref();

                    Log.d("CORRECT", "Correct");
                    counter++;
                    Log.d("ADebugTag", "Value: " + Integer.toString(counter));
                }
                else
                    Log.d("inCORRECT", "inCorrect");
            }
        });
        radioTwo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(radioTwo.getText().toString().equals(dataSet.get(listPosition).getAnswer())) {

                    push_pref();

                    Log.d("CORRECT", "Correct");
                    counter++;
                    Log.d("ADebugTag", "Value: " + Integer.toString(counter));
                }
                else
                    Log.d("inCORRECT", "inCorrect");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Log.d("list : ", listPosition + "");
                if (listPosition == 5)
                    view.getContext().startActivity(new Intent(view.getContext(), StartActivity.class));

                if(textBox.getText().toString().equals(dataSet.get(listPosition).getAnswer())) {

                    push_pref();

                    Log.d("CORRECT", "Correct");
                    counter++;
                    Log.d("ADebugTag", "Value: " + Integer.toString(counter));
                }
                else
                    Log.d("inCORRECT", "inCorrect");

            }
        });

    }

    public void push_pref(){

        Log.d("CALLED ::::::: ", "called");

        SharedPreferences preferences = context.getSharedPreferences("score_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        int i = preferences.getInt("count", 0);
        Log.d("::::::: PREF INIT :", i + "");

        editor.putInt("count", i+1);
        editor.apply();

        Log.d("pref :::::: ", i + 1 + "");
    }

    public void flush_pref(){

        Log.d("flushing .... ", "");
        SharedPreferences preferences = context.getSharedPreferences("score_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.clear();
        editor.apply();
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }


}