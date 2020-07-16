//package com.example.openscreen;
package com.example.FlappyDiver;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;



public class secondary_activity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation TopAnim, BottomAnim;
    ImageView image;
    TextView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_animation);

        TopAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        BottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.iv);
        logo = findViewById(R.id.tv);



     //   final Handler handler = new Handler();
      //  handler.postDelayed(new Runnable() {
         //   @Override
        //    public void run() {
         image.setAnimation(TopAnim);
         logo.setAnimation(BottomAnim);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                //   }
                Intent in = new Intent(secondary_activity.this, GameActivity.class);
                startActivity(in);

            }
        }, 2000);


       // }, 17000);

    //    handler.postDelayed(new Runnable() {
    //        @Override
    //        public void run() {

    //            Intent in = new Intent(secondary_activity.this, MainActivity.class);
    //            startActivity(in);
    //        }
    //    }, 24000);


    }



    }




