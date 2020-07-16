package com.example.FlappyDiver;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;

public class GameActivity extends AppCompatActivity {
    private  GameView gameView;
    MediaPlayer mysong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point=new Point();
       // mysong = MediaPlayer.create(GameActivity.this, R.raw.ukulele);
      //  mysong.start();
        getWindowManager().getDefaultDisplay().getSize(point);
        try {
            gameView=new GameView(this,point.x,point.y);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setContentView(gameView);
    }

    @Override
    protected void onPause() {
        super.onPause();
   //     mysong.release();
          gameView.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        gameView.resume();
    }
}
