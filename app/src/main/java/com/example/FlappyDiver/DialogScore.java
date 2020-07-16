package com.example.FlappyDiver;

import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

public class DialogScore extends DialogFragment {


    private SharedPreferences prefs;
    ImageView image;
    ImageView image1;
    TextView logo;
    EditText editText;
    Button buttonOk,buttonExit;
    Boolean pressed;
    TextView textView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.custompopup7, container, false);

        prefs = Objects.requireNonNull(getActivity()).getSharedPreferences("game", MODE_PRIVATE);
        //Initialize the textview for choosing an image from memory

        editText=v.findViewById(R.id.editText);
        textView=v.findViewById(R.id.txtclose);
        buttonOk= v.findViewById(R.id.approvi);
        buttonExit = v.findViewById(R.id.cancel);

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor collection = prefs.edit();
                collection.putString("username", String.valueOf(editText.getText()));
                collection.apply();

                Objects.requireNonNull(getDialog()).dismiss();

            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Objects.requireNonNull(getDialog()).dismiss();

            }
        });

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Objects.requireNonNull(getDialog()).dismiss();

            }
        });

        return v;

    }

    void openDialog2() {











            //           ImageView image1 = (ImageView) dialog.findViewById(R.id.cancel);

            //           image.setOnClickListener(new View.OnClickListener() {
            //               @Override
            //                public void onClick(View v) {
            //                    dialog.dismiss();
            //                }

        //            ImageView image = (ImageView) dialog.findViewById(R.id.cancel);

        //             image.setOnClickListener(new View.OnClickListener() {
        //                 @Override
        //               public void onClick(View v) {
        //                  Intent in1 = new Intent(DialogScore.this, RatingbarActivity.class);
        //                  startActivity(in1);
        //              }
        //            });
        //        }
        //    });


    }


}



