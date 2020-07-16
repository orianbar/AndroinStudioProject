package com.example.FlappyDiver;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DialogScore extends AppCompatActivity {

    private SharedPreferences prefs;
    ImageView image;
    ImageView image1;
    TextView logo;
    EditText editText;
    Button buttonOk,buttonExit;
    Boolean pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_score);
        prefs = this.getSharedPreferences("game", Context.MODE_PRIVATE);

                super.onCreate(savedInstanceState);
                openDialog2();


}


    void openDialog2() {

        final Dialog dialog=new Dialog(DialogScore.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View custompopup7= layoutInflater.inflate(R.layout.custompopup7,null);

        dialog.setContentView(custompopup7);
        dialog.setTitle("Example");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();
        editText=findViewById(R.id.editText);
        buttonOk= findViewById(R.id.approvi);
        buttonExit = findViewById(R.id.cancel);
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor collection = prefs.edit();
                collection.putString("username", String.valueOf(editText.getText()));
                collection.apply();
                pressed = true;
                Intent in1 = new Intent(DialogScore.this, RatingbarActivity.class);
                startActivity(in1);
            }
        });
        buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed = true;
                Intent in1 = new Intent(DialogScore.this, RatingbarActivity.class);
                startActivity(in1);
            }
        });

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);


        TextView closeInfo = (TextView) dialog.findViewById(R.id.txtclose);
        closeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed = true;
                Intent in1 = new Intent(DialogScore.this, RatingbarActivity.class);
                startActivity(in1);
            }


                //           ImageView image1 = (ImageView) dialog.findViewById(R.id.cancel);

                //           image.setOnClickListener(new View.OnClickListener() {
                //               @Override
                //                public void onClick(View v) {
                //                    dialog.dismiss();
                //                }
                            });
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


