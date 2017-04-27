package com.saracawley.mathdrills;

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
    private int mQuestionIndex;
    private int mGoTo;
    private String mMathTypeForQuestions;
    private long mTotalTime;
    private long mStartTime;
    private long mEndTime;

    private long[] mBestTimes = new long[12] ;
    private int mTimeIndex = 0;

    private QuestionBank() {
        mQuestions = new ArrayList<Question>();
        mQuestionIndex = 0;
    }

    public int getmTimeIndex() {
        return mTimeIndex;
    }

    public void setTimeIndex(int mTimeIndex) {
        this.mTimeIndex = mTimeIndex;
    }
    public void setBestTime(int index, long time){
        if(mBestTimes[index]> time || mBestTimes[index]== 0){
            mBestTimes[index] = time;
        }
    }
    public long getBestTime(int index){
        return mBestTimes[index];
    }


    public long getTotalTime() {
        return mTotalTime;
    }

    public void setTotalTime(long totalTime) {
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
        return mQuestionIndex;
    }

    public void setIndex(int index) {
        mQuestionIndex = index;
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
        mQuestionIndex = 0;
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
