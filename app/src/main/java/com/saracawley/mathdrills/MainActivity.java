package com.saracawley.mathdrills;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String BEST_TIMES = ".com.saracawley.android.mathdrills.my_best_times";
    private static final String TAG = "MainActivity";

    QuestionBank mQuestionBank = QuestionBank.getInstance();

    // Views
    private LinearLayout mAdditionBeginner;
    private LinearLayout mAdditionIntermediate;
    private LinearLayout mAdditionAdvanced;
    private LinearLayout mSubtractionBeginner;
    private LinearLayout mSubtractionIntermediate;
    private LinearLayout mSubtractionAdvanced;
    private LinearLayout mMultiplicationBeginner;
    private LinearLayout mMultiplicationIntermediate;
    private LinearLayout mMultiplicationAdvanced;
    private LinearLayout mDivisionBeginner;
    private LinearLayout mDivisionIntermediate;
    private LinearLayout mDivisionAdvanced;

    private TextView mTimeAdditionBeginner;
    private TextView mTimeAdditionIntermediate;
    private TextView mTimeAdditionAdvanced;
    private TextView mTimeSubtractionBeginner;
    private TextView mTimeSubtractionIntermediate;
    private TextView mTimeSubtractionAdvanced;
    private TextView mTimeMultiplicationBeginner;
    private TextView mTimeMultiplicationIntermediate;
    private TextView mTimeMultiplicationAdvanced;
    private TextView mTimeDivisionBeginner;
    private TextView mTimeDivisionIntermediate;
    private TextView mTimeDivisionAdvanced;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // load best practices
        SharedPreferences settings = getSharedPreferences(BEST_TIMES, 0);
        for(int i = 0; i<12;i++){
            long bestTime = settings.getLong("bestTime"+i, 0);
            mQuestionBank.setBestTime(i,bestTime);
        }

        // connect views
        mAdditionBeginner = (LinearLayout) findViewById(R.id.addition_beginner);
        mAdditionIntermediate = (LinearLayout) findViewById(R.id.addition_intermediate);
        mAdditionAdvanced = (LinearLayout) findViewById(R.id.addition_advanced);
        mSubtractionBeginner = (LinearLayout) findViewById(R.id.subtraction_beginner);
        mSubtractionIntermediate = (LinearLayout) findViewById(R.id.subtraction_intermediate);
        mSubtractionAdvanced = (LinearLayout) findViewById(R.id.subtraction_advanced);
        mMultiplicationBeginner = (LinearLayout) findViewById(R.id.multiplication_beginner);
        mMultiplicationIntermediate = (LinearLayout) findViewById(R.id.multiplication_intermediate);
        mMultiplicationAdvanced = (LinearLayout) findViewById(R.id.multiplication_advanced);
        mDivisionBeginner = (LinearLayout) findViewById(R.id.division_beginner);
        mDivisionIntermediate = (LinearLayout) findViewById(R.id.division_intermediate);
        mDivisionAdvanced = (LinearLayout) findViewById(R.id.division_advanced);

        mTimeAdditionBeginner = (TextView) findViewById(R.id.time_addition_beginner);
        mTimeAdditionIntermediate = (TextView) findViewById(R.id.time_addition_intermediate);
        mTimeAdditionAdvanced = (TextView) findViewById(R.id.time_addition_advanced);
        mTimeSubtractionBeginner = (TextView) findViewById(R.id.time_subtraction_beginner);
        mTimeSubtractionIntermediate = (TextView) findViewById(R.id.time_subtraction_intermediate);
        mTimeSubtractionAdvanced = (TextView) findViewById(R.id.time_subtraction_advanced);
        mTimeMultiplicationBeginner = (TextView) findViewById(R.id.time_multiplication_beginner);
        mTimeMultiplicationIntermediate = (TextView) findViewById(R.id.time_multiplication_intermediate);
        mTimeMultiplicationAdvanced = (TextView) findViewById(R.id.time_multiply_advanced);
        mTimeDivisionBeginner = (TextView) findViewById(R.id.time_division_beginner);
        mTimeDivisionIntermediate = (TextView) findViewById(R.id.time_division_intermediate);
        mTimeDivisionAdvanced = (TextView) findViewById(R.id.time_division_advanced);

        mAdditionBeginner.setOnClickListener(this);
        mAdditionIntermediate.setOnClickListener(this);
        mAdditionAdvanced.setOnClickListener(this);
        mMultiplicationAdvanced.setOnClickListener(this);
        mSubtractionBeginner.setOnClickListener(this);
        mSubtractionIntermediate.setOnClickListener(this);
        mSubtractionAdvanced.setOnClickListener(this);
        mMultiplicationBeginner.setOnClickListener(this);
        mMultiplicationIntermediate.setOnClickListener(this);
        mMultiplicationAdvanced.setOnClickListener(this);
        mDivisionBeginner.setOnClickListener(this);
        mDivisionIntermediate.setOnClickListener(this);
        mDivisionAdvanced.setOnClickListener(this);

        updateView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        updateView();
    }

    @Override
    protected void onStop() {
        super.onStop();
        SharedPreferences settings = getSharedPreferences(BEST_TIMES, 0);
        SharedPreferences.Editor editor = settings.edit();
        for(int i = 0; i<12;i++){
            editor.putLong("bestTime"+i, mQuestionBank.getBestTime(i));
        }
        editor.commit();
    }

    private void updateView(){
        mTimeAdditionBeginner.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(0)));
        mTimeAdditionIntermediate.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(1)));
        mTimeAdditionAdvanced.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(2)));
        mTimeSubtractionBeginner.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(3)));
        mTimeSubtractionIntermediate.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(4)));
        mTimeSubtractionAdvanced.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(5)));
        mTimeMultiplicationBeginner.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(6)));
        mTimeMultiplicationIntermediate.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(7)));
        mTimeMultiplicationAdvanced.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(8)));
        mTimeDivisionBeginner.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(9)));
        mTimeDivisionIntermediate.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(10)));
        mTimeDivisionAdvanced.setText(DateUtils.formatElapsedTime(mQuestionBank.getBestTime(11)));

        if(mQuestionBank.getBestTime(0)!= 0){
            mTimeAdditionBeginner.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(1)!= 0){
            mTimeAdditionIntermediate.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(2)!= 0){
            mTimeAdditionAdvanced.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(3)!= 0){
            mTimeSubtractionBeginner.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(4)!= 0){
            mTimeSubtractionIntermediate.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(5)!= 0){
            mTimeSubtractionAdvanced.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(6)!= 0){
            mTimeMultiplicationBeginner.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(7)!= 0){
            mTimeMultiplicationIntermediate.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(8)!= 0){
            mTimeMultiplicationAdvanced.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(9)!= 0){
            mTimeDivisionBeginner.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(10)!= 0){
            mTimeDivisionIntermediate.setBackgroundColor(Color.GREEN);
        }
        if(mQuestionBank.getBestTime(11)!= 0){
            mTimeDivisionAdvanced.setBackgroundColor(Color.GREEN);
        }
    }

    private void startDrill(){
        Intent i = new Intent(MainActivity.this, DrillActivity.class);
        startActivity(i);
        /*
            Intent i = DrillActivity.newIntent(MainActivity.this, 9);
            startActivity(i);7
        */
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            // Addition
            case R.id.addition_beginner:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(5,"+");
                mQuestionBank.setTimeIndex(0);
                startDrill();
                break;
            case R.id.addition_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(9,"+");
                mQuestionBank.setTimeIndex(1);
                startDrill();
                break;
            case R.id.addition_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(12,"+");
                mQuestionBank.setTimeIndex(2);
                startDrill();
                break;
            // Subtraction

            case R.id.subtraction_beginner:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(5,"-");
                mQuestionBank.setTimeIndex(3);
                startDrill();
                break;
            case R.id.subtraction_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(9,"-");
                mQuestionBank.setTimeIndex(4);
                startDrill();
                break;
            case R.id.subtraction_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(12,"-");
                mQuestionBank.setTimeIndex(5);
                startDrill();
                break;
            // Multiplication
            case R.id.multiplication_beginner:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(5,"×");
                mQuestionBank.setTimeIndex(6);
                startDrill();
                break;
            case R.id.multiplication_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(9,"×");
                mQuestionBank.setTimeIndex(7);
                startDrill();
                break;
            case R.id.multiplication_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(12,"×");
                mQuestionBank.setTimeIndex(8);
                startDrill();
                break;
            // Division
            case R.id.division_beginner:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(5,"÷");
                mQuestionBank.setTimeIndex(9);
                startDrill();
                break;
            case R.id.division_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(9,"÷");
                mQuestionBank.setTimeIndex(10);
                startDrill();
                break;
            case R.id.division_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(12,"÷");
                mQuestionBank.setTimeIndex(11);
                startDrill();
                break;
            default:
                break;
        }
    }
}
