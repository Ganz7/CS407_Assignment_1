package com.wisc.ganz.quizx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class PlayQuizActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        int totalQuestions = 2;
        int answeredQuestions = 0;
        int correctAnswers = 0;

        getFragmentManager()
                .beginTransaction()
                .replace(R.id.quiz_fragment_container,
                        ImageQuestionFragment.newInstance(totalQuestions, answeredQuestions, correctAnswers))
                .addToBackStack(null)
                .commit();
    }

}
