package com.example.FlappyDiver;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import org.firezenk.bubbleemitter.BubbleEmitterView;

import java.util.Random;

public class RatingbarActivity extends Activity  {
    ImageView gameover;
    ImageView home;
    RatingBar ratingBar;
    Button button;

    MediaPlayer mysong;
    Handler handler;
    TextView scoreOver;
    BubbleEmitterView bubbleEmitter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        final SharedPreferences prefs = getSharedPreferences("game", MODE_PRIVATE);




        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        HomeWatcher mHomeWatcher;

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });



        mHomeWatcher.startWatch();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);
        setContentView(R.layout.activity_ratingbar);
        scoreOver=findViewById(R.id.scoreOver);

        int score=prefs.getInt("scoreOver",0);
        scoreOver.setText("your score is:"+String.valueOf(score));

        Button button;
        bubbleEmitter= findViewById(R.id.bubbleEmitter);
        emitBubbles();
       button = (Button) findViewById(R.id.retry);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        button.startAnimation(animation2);

        gameover=(ImageView)findViewById(R.id.gameover);
        home=(ImageView)findViewById(R.id.home);
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toMain = new Intent(RatingbarActivity.this, MainActivity.class);
                startActivity(toMain);
            }
        });
        button = (Button) findViewById(R.id.retry);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) findViewById(R.id.retry);


                Intent toDialog = new Intent(RatingbarActivity.this,GameActivity.class);
                startActivity(toDialog);

            }
        });



        Animation animation= AnimationUtils.loadAnimation(this,R.anim.rotate);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Animation animation3= AnimationUtils.loadAnimation(RatingbarActivity.this,R.anim.blink);
                gameover.startAnimation(animation3);
            }
        }, 4000);
        gameover.startAnimation(animation);
        }


    private boolean mIsBound = false;
    private MusicService mServ;
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);

    }
    @Override
    protected void onPause() {
        super.onPause();

        PowerManager pm = (PowerManager)
                getSystemService(Context.POWER_SERVICE);
        boolean isScreenOn = false;
        if (pm != null) {
            isScreenOn = pm.isScreenOn();
        }

        if (!isScreenOn) {
            if (mServ != null) {
                mServ.pauseMusic();
            }
        }

    }


    private void emitBubbles() {
         handler=new Handler();
        handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    int size = new Random().nextInt(61) + 20;
                                    bubbleEmitter.emitBubble(size);
                                    emitBubbles();
                                }
                            },
                new Random().nextInt(401) + 100);
    }

}



