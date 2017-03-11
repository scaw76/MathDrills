package com.saracawley.mathdrills;

import android.content.Intent;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
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
                mQuestionBank.setUpQuestions(7,"+");
                startDrill();
                break;
            case R.id.addition_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(10,"+");
                startDrill();
                break;
            case R.id.addition_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(15,"+");
                startDrill();
                break;
            // Subtraction

            case R.id.subtraction_beginner:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(7,"-");
                startDrill();
                break;
            case R.id.subtraction_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(10,"-");
                startDrill();
                break;
            case R.id.subtraction_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(15,"-");
                startDrill();
                break;
            // Multiplication
            case R.id.multiplication_beginner:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(7,"×");
                startDrill();
                break;
            case R.id.multiplication_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(10,"×");
                startDrill();
                break;
            case R.id.multiplication_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(15,"×");
                startDrill();
                break;
            // Division
            case R.id.division_beginner:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(7,"÷");
                startDrill();
                break;
            case R.id.division_intermediate:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(10,"÷");
                startDrill();
                break;
            case R.id.division_advanced:
                Log.d(TAG, "start new activity");
                mQuestionBank.setUpQuestions(15,"÷");
                startDrill();
                break;
            default:
                break;
        }
    }
}
