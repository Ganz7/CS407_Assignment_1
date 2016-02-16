package com.wisc.ganz.quizx;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

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
                    return; //do nothing
                }

            }
        });
    }

}
