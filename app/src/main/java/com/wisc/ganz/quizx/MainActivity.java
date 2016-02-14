package com.wisc.ganz.quizx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void startPressed(View view){
        Intent playQuiz = new Intent(this, PlayQuizActivity.class);
        startActivity(playQuiz);
    }
}
