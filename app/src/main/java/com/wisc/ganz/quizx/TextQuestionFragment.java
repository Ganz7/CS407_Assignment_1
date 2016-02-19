package com.wisc.ganz.quizx;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TextQuestionFragment extends Fragment {

    private static final String ARG_TOTAL_QUESTIONS = "param_total_questions";
    private static final String ARG_ANSWERED_QUESTIONS = "param_answered_questions";
    private static final String ARG_CORRECT_ANSWERS = "param_correct_answers";

    private Button submitButton;
    private RadioGroup optionsRadioGroup;


    private int totalQuestions; //Total Number of Questions
    private int answeredQuestions; //Total Number of Questions Answered (Not used as of now)
    private int correctAnswers; //Correct Answers

    public TextQuestionFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param_total_questions Total Questions in the Quiz
     * @param param_answered_questions Total Questions answered so far
     * @param param_correct_answers Correctly Answered Questions so far
     * @return A new instance of fragment TextQuestionFragment.
     */
    public static TextQuestionFragment newInstance(
            int param_total_questions, int param_answered_questions, int param_correct_answers) {
        TextQuestionFragment fragment = new TextQuestionFragment();
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
        View view = inflater.inflate(R.layout.fragment_text_question, container, false);

        TextView questionView = (TextView)view.findViewById(R.id.tv_TextQuestion);
        String question = "Question " + (answeredQuestions+1) + " : " + getString(R.string.question_text_1);
        questionView.setText(question);

        submitButton = (Button) view.findViewById(R.id.text_submit_button);
        optionsRadioGroup = (RadioGroup) view.findViewById(R.id.text_radio_group);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton.setOnClickListener(new View.OnClickListener() {
            /**
             * When submit button is pressed,
             * evaluate if the user answer is correct and call displayResult() to show
             * results of the quiz.
             * @param v (View)
             */
            @Override
            public void onClick(View v) {
                RadioButton chosenButton = (RadioButton) getActivity().findViewById(optionsRadioGroup.getCheckedRadioButtonId());
                int chosenIndex = optionsRadioGroup.indexOfChild(chosenButton);

                /*No option has been chosen yet. So display a toast */
                if(chosenIndex == -1){
                    String message = "Please choose an option.";
                    Toast.makeText(getActivity().getApplicationContext(), message, Toast.LENGTH_LONG).show();
                    return; //do nothing
                }

                answeredQuestions++;
                String selectedText = chosenButton.getText().toString();
                if(selectedText.equals(getString(R.string.option_Germany))){
                    correctAnswers++;
                }
                displayResult(correctAnswers, totalQuestions);

            }
        });
    }

    private void displayResult(int arg_correctAnswers, int arg_totalQuestions){
        //do a prompt about the result
        AlertDialog.Builder resultDialogBuilder = new AlertDialog.Builder(getActivity());
        resultDialogBuilder.setCancelable(true)
                .setTitle("Quiz Results")
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.quiz_fragment_container,
                                        ImageQuestionFragment.newInstance(2, 0, 0))
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //back out the the start screen
                        getActivity().finish();
                    }
                });

        StringBuilder message = new StringBuilder();
        message.append("You scored ").append(arg_correctAnswers).append(" out of ")
                .append(arg_totalQuestions).append( " in this Quiz!");
        resultDialogBuilder.setMessage(message);
        resultDialogBuilder.setCancelable(false);

        resultDialogBuilder.show();

    }

}
