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
import android.widget.Toast;

public class TextQuestionFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param2";

    private String mParam1;
    private String mParam2;

    private Button submitButton;
    private RadioGroup optionsRadioGroup;


    private int totalQuestions;
    private int answeredQuestions;
    private int correctAnswers;

    public TextQuestionFragment() {
        // Required empty public constructor
    }

    public static TextQuestionFragment newInstance(int param1, int param2, int param3) {
        TextQuestionFragment fragment = new TextQuestionFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, param2);
        args.putInt(ARG_PARAM3, param3);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            totalQuestions = getArguments().getInt(ARG_PARAM1);
            answeredQuestions = getArguments().getInt(ARG_PARAM2);
            correctAnswers = getArguments().getInt(ARG_PARAM3);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_question, container, false);
        submitButton = (Button) view.findViewById(R.id.text_submit_button);
        optionsRadioGroup = (RadioGroup) view.findViewById(R.id.text_radio_group);

        return view;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton chosenButton = (RadioButton) getActivity().findViewById(optionsRadioGroup.getCheckedRadioButtonId());
                int chosenIndex = optionsRadioGroup.indexOfChild(chosenButton);

                /*No option has been chosen yet*/
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

        resultDialogBuilder.show();

    }

}
