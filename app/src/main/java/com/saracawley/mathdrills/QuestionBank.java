package com.saracawley.mathdrills;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sara on 3/2/2017.
 */
public class QuestionBank {

    private static final String TAG= "QuestionBank";
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
        mGoTo = 3;
        mMathTypeForQuestions = "×";
        setUpQuestions();
        Collections.shuffle(mQuestions);
        printQuestions();
        mStartTime = System.currentTimeMillis();
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
        Log.d(TAG, mQuestions.size() + " questions here");
    }
    private void setUpQuestions(){
        for(int i = 0; i<mGoTo+1; i++){
            for(int j = 0; j<mGoTo+1; j++){
                mQuestions.add(new Question(i,j,mMathTypeForQuestions));
            }
        }
        //printQuestions();
    }
}
