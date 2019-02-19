package com.example.sketchapk;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Locale;

import androidx.navigation.NavAction;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link difficulty.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link difficulty#newInstance} factory method to
 * create an instance of this fragment.
 */
public class difficulty extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    Bundle b = new Bundle();

    public difficulty() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment difficulty.
     */
    // TODO: Rename and change types and number of parameters
    public static difficulty newInstance(String param1, String param2) {
        difficulty fragment = new difficulty();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_difficulty, container, false);
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
    private void goToNext()
    {
        NavHostFragment.findNavController(this).navigate(R.id.diffToAmount,b);
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        Button button1 = getView().findViewById(R.id.buttonEasy);
        Button button2 = getView().findViewById(R.id.buttonNormal);
        Button button3 = getView().findViewById(R.id.buttonHard);
        Button custom = getView().findViewById(R.id.buttonCustom);
        TextView popup = getView().findViewById(R.id.popup);


        boolean lol = false;


        button1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_BUTTON_PRESS)
                    b.putInt("Timer", 60000);
                //b.putString("StringDiff", "big gay android");
                b.putString("time", "60000");
                if (arg1.getAction()==MotionEvent.ACTION_UP)
                    Navigation.findNavController(arg0).navigate(R.id.diffToAmount,b);
                return true;
            }
        });

        button2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN)
                    b.putInt("Timer", 30000);
                b.putString("StringDiff", "30");
                b.putString("time", "30000");
                Navigation.createNavigateOnClickListener(R.id.diffToAmount,b);
                if (arg1.getAction()==MotionEvent.ACTION_UP)
                    Navigation.findNavController(arg0).navigate(R.id.diffToAmount,b);
                return true;
            }
        });
        button3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                if (arg1.getAction()==MotionEvent.ACTION_DOWN)
                    b.putInt("Timer", 15000);
                b.putString("StringDiff", "15");
                b.putString("time", "15000");
                if (arg1.getAction()==MotionEvent.ACTION_UP)
                    Navigation.findNavController(arg0).navigate(R.id.diffToAmount,b);
                return true;
            }
        });

        custom.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1)
            {
                arg0 =LayoutInflater.from(arg0.getContext()).inflate(R.layout.user_input,null);
                final AlertDialog.Builder alertpop= new AlertDialog.Builder(arg0.getContext());
                alertpop.setView(arg0);
                final EditText userInput = arg0.findViewById(R.id.userinput);
                Button set = arg0.findViewById(R.id.Set);
                final Dialog dialog = alertpop.create();
                dialog.show();

                set.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View arg0, MotionEvent arg1)
                    {
                        int i =0;
                        if (arg1.getAction()==MotionEvent.ACTION_DOWN)
                            b.putInt("Timer", 15000);
                        b.putString("StringDiff", "15");
                        b.putString("time", "userInput");
                        if ((arg1.getAction()==MotionEvent.ACTION_UP)&&(i==0))
                            dialog.dismiss();
                        i++;
                        goToNext();


                        return true;

                    }
                });
                return true;
            }
        });





       /* button1.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.diffToAmount,a));
        button2.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.diffToAmount,a));
        button3.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.diffToAmount,a));*/




    }



}

