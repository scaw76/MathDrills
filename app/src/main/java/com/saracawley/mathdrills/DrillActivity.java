package com.saracawley.mathdrills;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class DrillActivity extends AppCompatActivity {
    // Tags
    private static final String INDEX_FOR_TIME = ".com.saracawley.android.mathdrills.index_for_time";
    private static final String TAG = "DrillActivity";

    private int mTimeIndex;
    private Timer mClockTimer;

    private static final long DELAY = 500;
    private static final long SECONDS = 1000;

    boolean mTestFinished = false;
    // Questions
    QuestionBank mQuestionBank = QuestionBank.getInstance();

    //Widgets
    private ImageButton mNextButton;
    private ImageButton mPreviousButton;

    private TextView mTimerView;
    private TextView mFirstNumberView;
    private TextView mSecondNumberView;
    private TextView mMathTypeView;
    private EditText mAnswerFieldView;

    private TextView mTotalTimeView;
    private LinearLayout mTestOverView;
    private LinearLayout mTestView;
/*
    public static Intent newIntent(Context packageContext, int index) {
        Intent i =  new Intent(packageContext, DrillActivity.class);
        i.putExtra(INDEX_FOR_TIME, i);
        return i;
    }
  */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drill);
/*
        // get index for time
        mTimeIndex = getIntent().getIntExtra(INDEX_FOR_TIME,0);
        Log.d(TAG, "time index "+ mTimeIndex);
*/
        // connect widgets
        mNextButton = (ImageButton) findViewById(R.id.next_button);
        mPreviousButton = (ImageButton) findViewById(R.id.previous_button);
        mFirstNumberView = (TextView) findViewById(R.id.number1);
        mSecondNumberView = (TextView) findViewById(R.id.number2);
        mMathTypeView = (TextView) findViewById(R.id.math_type);
        mAnswerFieldView = (EditText) findViewById(R.id.answer_question);
        mTimerView = (TextView) findViewById(R.id.timer);
        mTotalTimeView = (TextView) findViewById(R.id.total_time);
        mTestOverView = (LinearLayout) findViewById(R.id.test_ended);
        mTestView = (LinearLayout) findViewById(R.id.test_layout);

        //Timer
        mClockTimer = new Timer();
        mClockTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                if(!mTestFinished){
                    mQuestionBank.setEndTime(System.currentTimeMillis());
                    long difference = mQuestionBank.getEndTime()-mQuestionBank.getStartTime();
                    long diffSeconds = difference / DateUtils.SECOND_IN_MILLIS;
                    mQuestionBank.setTotalTime(DateUtils.formatElapsedTime(diffSeconds));
                    mTimerView.setText(mQuestionBank.getTotalTime());
                }
                }
            });
            }
        }, 0,SECONDS);

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
            if(mQuestionBank.getSize() != 0){
                // go to previous question
                if(mQuestionBank.getIndex() == 0){
                    mQuestionBank.setIndex(mQuestionBank.getSize() - 1);
                }else {
                    mQuestionBank.setIndex(mQuestionBank.getIndex() - 1);
                }
                updateView();
            }
            }
        });
        // answer changed
        mAnswerFieldView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Log.d(TAG, "before");
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Log.d(TAG,"on " + charSequence);
            }
            // delay to see answer
            private Timer timer = new Timer();
            @Override
            public void afterTextChanged(Editable editable) {
                // still have questions, input, and test is not over
                if(mQuestionBank.getSize() != 0 && !editable.toString().matches("") && !mTestFinished){
                    //Log.d(TAG, "after" + editable.toString());
                    // delay for user to see answer before loading next question
                    timer.cancel();
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            DrillActivity.this.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Log.d(TAG, "here");
                                    checkAnswer();
                                }
                            });
                        }
                    }, DELAY);
                }
            }
        });
        updateView();
    }

    private void goToNextQuestion(){
        // go to next question
        if(mQuestionBank.getSize() != 0){
            int index = mQuestionBank.getIndex();
            int newIndex = (index + 1) % mQuestionBank.getSize();
            mQuestionBank.setIndex(newIndex);
        }
        updateView();
    }
   private void updateView(){
       Log.d(TAG, "size in update " +mQuestionBank.getSize());
       //Log.d(TAG, "index in update " +mQuestionBank.getIndex());
       if(mQuestionBank.getSize() == 0){

           mTestView.setVisibility(View.GONE);
           mAnswerFieldView.setEnabled(false);
           mTestOverView.setVisibility(View.VISIBLE);
           mTotalTimeView.setText(mQuestionBank.getTotalTime());

       }else{
           Question question = mQuestionBank.getQuestion(mQuestionBank.getIndex());
           //question.Display();
           mFirstNumberView.setText(String.valueOf(question.getFirstNumber()));
           mSecondNumberView.setText(String.valueOf(question.getSecondNumber()));
           mMathTypeView.setText(question.getMathType());
       }
       mAnswerFieldView.setText("");
   }

    private final void checkAnswer(){
        Question question = mQuestionBank.getQuestion(mQuestionBank.getIndex());
        //question.Display();
        String textAnswer = mAnswerFieldView.getText().toString();
        //Log.d(TAG, textAnswer);
        int answer;
        try {
            answer = Integer.parseInt(textAnswer);
        } catch (NumberFormatException e){
            //Log.d(TAG, "purposely ignoring "+e.getMessage());
            return;
        }
        if(question.checkAnswer(answer)){
            //Toast toast = Toast.makeText(DrillActivity.this, R.string.correct, Toast.LENGTH_SHORT);
            //toast.show();
            int index = mQuestionBank.getIndex();
            if( index == mQuestionBank.getSize()-1){
               index =  0;
            }
            mQuestionBank.removeQuestion(mQuestionBank.getIndex());
            mQuestionBank.setIndex(index);
            if(mQuestionBank.getSize() == 0){
                mTestFinished = true;
                mClockTimer.cancel();
            }
            updateView();
            //mQuestionBank.printQuestions();
        }
    }

}
