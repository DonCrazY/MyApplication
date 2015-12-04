package com.example.doncrazy.myapplication;

/**
 * Created by 228 on 02.10.2015.
 */
public class Question {
    private int QuestionId;
    private boolean Answer;

    public Question(int _QuestionId, boolean _Answer) {
        QuestionId = _QuestionId;
        Answer = _Answer;
    }

    public int getQuestionId() {
        return QuestionId;
    }

    public void setQuestionId(int questionId) {
        QuestionId = questionId;
    }

    public boolean isAnswer() {
        return Answer;
    }

    public void setAnswer(boolean answer) {
        Answer = answer;
    }
}
