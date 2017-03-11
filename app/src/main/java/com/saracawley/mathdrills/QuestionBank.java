package com.saracawley.mathdrills;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sara on 3/2/2017.
 */
public class QuestionBank {

    private static final String TAG = "QuestionBank";
    private static QuestionBank ourInstance = new QuestionBank();
    public static QuestionBank getInstance() {
        return ourInstance;
    }

    private List<Question> mQuestions;
    private int mIndex;
    private int mGoTo;
    private String mMathTypeForQuestions;
    private String mTotalTime;
    private long mStartTime;
    private long mEndTime;

    private QuestionBank() {
        mQuestions = new ArrayList<Question>();
        mIndex = 0;
    }

    public String getTotalTime() {
        return mTotalTime;
    }

    public void setTotalTime(String totalTime) {
        mTotalTime = totalTime;
    }

    public long getStartTime() {
        return mStartTime;
    }

    public long getEndTime() {
        return mEndTime;
    }

    public void setEndTime(long endTime) {
        mEndTime = endTime;
    }

    public int getSize(){
        return mQuestions.size();
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        mIndex = index;
    }

    public Question getQuestion(int index){
        return mQuestions.get(index);
    }

    public void removeQuestion(int index){
        mQuestions.remove(index);
    }

    public void printQuestions(){
        for(int k=0; k<mQuestions.size(); k++){
            Question q = mQuestions.get(k);
            q.Display();
        }
        //Log.d(TAG, mQuestions.size() + " questions here");
    }

    private void additionQuestions(){
        for(int i = 0; i<mGoTo+1; i++){
            for(int j = 0; j<mGoTo+1; j++){
                mQuestions.add(new Question(i,j,mMathTypeForQuestions));
            }
        }
    }
    private void subtractionQuestions(){
        for(int i = 0; i<mGoTo+1; i++){
            for(int j = 0; j<mGoTo+1; j++){
                mQuestions.add(new Question(i+j,j,mMathTypeForQuestions));
            }
        }
    }
    private void multiplicationQuestions(){
        for(int i = 0; i<mGoTo+1; i++){
            for(int j = 0; j<mGoTo+1; j++){
                mQuestions.add(new Question(i,j,mMathTypeForQuestions));
            }
        }
    }
    private void divideQuestions(){
        for(int i = 0; i<mGoTo+1; i++){
            for(int j = 0; j<mGoTo+1; j++){
                mQuestions.add(new Question(i*j,j,mMathTypeForQuestions));
            }
        }
    }

    public void setUpQuestions(int goTo, String mathType){
        mIndex = 0;
        mQuestions.clear();
        mGoTo = goTo;
        mMathTypeForQuestions = mathType;

        if(mMathTypeForQuestions.equals("+")){
            additionQuestions();
        }
        if(mMathTypeForQuestions.equals("-")){
            subtractionQuestions();
        }
        if(mMathTypeForQuestions.equals("×")){
            multiplicationQuestions();
        }
        if(mMathTypeForQuestions.equals("÷")){
            divideQuestions();
        }

        Collections.shuffle(mQuestions);
        //printQuestions();
        mStartTime = System.currentTimeMillis();
    }
}
