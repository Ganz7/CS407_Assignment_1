package com.wisc.ganz.quizx;

import android.content.Context;
import android.net.Uri;
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
 * {@link ImageQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageQuestionFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String ARG_PARAM3 = "param3";

    private Button submitButton;
    private EditText answerBox;

    private int totalQuestions;
    private int answeredQuestions;
    private int correctAnswers;

    private OnFragmentInteractionListener mListener;

    public ImageQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ImageQuestionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ImageQuestionFragment newInstance(int param1, int param2, int param3) {
        ImageQuestionFragment fragment = new ImageQuestionFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
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
        View view = inflater.inflate(R.layout.fragment_image_question, container, false);

        TextView question = (TextView)view.findViewById(R.id.tv_ImgQuestion);
        question.setText(R.string.question_img_1);

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
                if(answer.toLowerCase().equals("google"))
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
     * @param etText
     * @return True if it is Empty. False otherwise
     */
    private boolean isEmpty(EditText etText) {
        return etText.getText().toString().trim().length() == 0;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
