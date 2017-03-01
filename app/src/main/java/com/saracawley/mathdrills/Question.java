package com.saracawley.mathdrills;

import android.util.Log;

/**
 * Created by sara on 2/28/2017.
 */

public class Question {
    // tag
    static private final String TAG = "Question";

    // member variables
    private int mFirstNumber;
    private int mSecondNumber;
    private String mMathType;

    // create a question
    public Question(int firstNumber, int secondNumber, String mathType) {
        mFirstNumber = firstNumber;
        mSecondNumber = secondNumber;
        mMathType = mathType;
    }

    public int getFirstNumber() {
        return mFirstNumber;
    }

    public int getSecondNumber() {
        return mSecondNumber;
    }

    public String getMathType() {
        return mMathType;
    }

    // check if given correct answer
    public boolean checkAnswer(int answer){
        // + - * /
        switch(mMathType){
            case "+":
                if(answer == mFirstNumber+mSecondNumber){
                    return true;
                }
                break;
            case "-":
                if(answer == mFirstNumber-mSecondNumber){
                    return true;
                }
                break;
            case "×":
                if(answer == mFirstNumber*mSecondNumber){
                    return true;
                }
                break;
            case "÷":
                if(answer == mFirstNumber/mSecondNumber){
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    // display question
    public void Display(){
        Log.d(TAG, mFirstNumber + " " + mMathType + " " + mSecondNumber + " = ");
    }
}
