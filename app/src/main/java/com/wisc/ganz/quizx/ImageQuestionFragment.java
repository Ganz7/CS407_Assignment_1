package com.wisc.ganz.quizx;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * Use the {@link ImageQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageQuestionFragment extends Fragment {
    private static final String ARG_TOTAL_QUESTIONS = "param_total_questions";
    private static final String ARG_ANSWERED_QUESTIONS = "param_answered_questions";
    private static final String ARG_CORRECT_ANSWERS = "param_correct_answers";

    private Button submitButton;
    private EditText answerBox;

    private int totalQuestions;
    private int answeredQuestions;
    private int correctAnswers;


    public ImageQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param_total_questions Total Questions in the Quiz
     * @param param_answered_questions Total Questions answered so far
     * @param param_correct_answers Correctly Answered Questions so far
     * @return A new instance of fragment ImageQuestionFragment.
     */
    public static ImageQuestionFragment newInstance(
            int param_total_questions, int param_answered_questions, int param_correct_answers) {
        ImageQuestionFragment fragment = new ImageQuestionFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_TOTAL_QUESTIONS, param_total_questions);
        args.putInt(ARG_ANSWERED_QUESTIONS, param_answered_questions);
        args.putInt(ARG_CORRECT_ANSWERS, param_correct_answers);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            totalQuestions = getArguments().getInt(ARG_TOTAL_QUESTIONS);
            answeredQuestions = getArguments().getInt(ARG_ANSWERED_QUESTIONS);
            correctAnswers = getArguments().getInt(ARG_CORRECT_ANSWERS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_question, container, false);

        TextView questionView = (TextView)view.findViewById(R.id.tv_ImgQuestion);
        String question = "Question " + (answeredQuestions+1) + " : " + getString(R.string.question_img_1);
        questionView.setText(question);

        submitButton = (Button) view.findViewById(R.id.image_submit_button);
        answerBox = (EditText) view.findViewById(R.id.et_imageQuestionAnswer);

        return view;

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(isEmpty(answerBox)) {
                    String message = "Please enter some text in the text field";
                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    return;
                }

                String answer = answerBox.getText().toString().trim();
                if(answer.toLowerCase().equals(getString(R.string.answer_img_1)))
                    correctAnswers++;

                answeredQuestions++;

                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.quiz_fragment_container,
                                TextQuestionFragment.newInstance(totalQuestions, answeredQuestions, correctAnswers))
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    /**
     * Checks if a given edittext is empty (spaces are also considered empty)
     * @param eText
     * @return True if @param eText is Empty. False otherwise
     */
    private boolean isEmpty(EditText eText) {
        return eText.getText().toString().trim().length() == 0;
    }

}
