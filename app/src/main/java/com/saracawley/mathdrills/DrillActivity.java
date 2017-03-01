package com.saracawley.mathdrills;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DrillActivity extends AppCompatActivity {
    // Tag
    private static final String TAG = "DrillActivity";

    boolean mTestFinished = false;
    private int mAnswerInput = 999999;

    // question setup info
    private int mGoTo = 3;
    private String mMathTypeForQuestions = "×";
    private int mIndex = 0;

    //Widgets
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;
    private TextView mFirstNumber;
    private TextView mSecondNumber;
    private TextView mMathType;
    private EditText mAnswerField;

    // Questions
    private List<Question> mQuestions = new ArrayList<Question>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill);

        // connect widgets
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mFirstNumber = (TextView) findViewById(R.id.number1);
        mSecondNumber = (TextView) findViewById(R.id.number2);
        mMathType = (TextView) findViewById(R.id.math_type);
        mAnswerField = (EditText) findViewById(R.id.answer_question);

        // Button Clicked
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to next question
                goToNextQuestion();
            }
        });
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // go to previous question
                if(mIndex == 0){
                    mIndex = mQuestions.size()-1;
                }else {
                    mIndex = (mIndex - 1);
                }
                updateView();
            }
        });
        // answer changed
        mAnswerField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String textAnswer = mAnswerField.getText().toString();
                Log.d(TAG, textAnswer);
                if(textAnswer == ""){
                    return;
                }
                int answer;
                try {
                    answer = Integer.parseInt(textAnswer);
                    mAnswerInput = answer;
                } catch (NumberFormatException e){
                    Log.d(TAG, "purposely ignoring "+e.getMessage());
                    return;
                }
/*
                if(mAnswerField.getText().toString().trim().length() == 0 || mTestFinished){
                    return;
                }
*/


                if(mQuestions.get(mIndex).checkAnswer(answer)){
                    if(mIndex == mQuestions.size()){
                        mIndex = 0;
                    }

                    Log.d(TAG, mQuestions.size() + " questions ");
                    mQuestions.remove(mIndex);
                    if(mQuestions.size() == 0){
                        mTestFinished = true;
                    }
                    updateView();

                }
            }
        });

        // setup questions
        setUpQuestions();
        Collections.shuffle(mQuestions);
        printQuestions();

        updateView();
    }
    private void setUpQuestions(){
        for(int i = 0; i<mGoTo+1; i++){
            for(int j = 0; j<mGoTo+1; j++){
                mQuestions.add(new Question(i,j,mMathTypeForQuestions));
            }
        }
        printQuestions();
    }
    private void goToNextQuestion(){
        // go to next question
        if(mQuestions.size() != 0){
            mIndex = (mIndex + 1) % mQuestions.size();
        }
        updateView();
    }
    private void printQuestions(){
        for(int k=0; k<mQuestions.size(); k++){
            Question q = mQuestions.get(k);
            q.Display();
        }
        Log.d(TAG, mQuestions.size() + " questions ");
    }
   private void updateView(){
       if(mQuestions.size() == 0){
           mFirstNumber.setText("Finished!");
           mSecondNumber.setText("");
           mMathType.setText("");
       }else{
           mFirstNumber.setText(String.valueOf(mQuestions.get(mIndex).getFirstNumber()));
           mSecondNumber.setText(String.valueOf(mQuestions.get(mIndex).getSecondNumber()));
           mMathType.setText(mQuestions.get(mIndex).getMathType());
       }
       mAnswerField.setText("");
   }
}
