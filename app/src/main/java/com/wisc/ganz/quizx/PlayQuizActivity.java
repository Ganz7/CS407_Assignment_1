package com.wisc.ganz.quizx;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PlayQuizActivity extends AppCompatActivity
        implements ImageQuestionFragment.OnFragmentInteractionListener{

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

    public void onFragmentInteraction(Uri uri){
        //Empty for now
        //Will expand if need to communicate between fragments
    }

}
