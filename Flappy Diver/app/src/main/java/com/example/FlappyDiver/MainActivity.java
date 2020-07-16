package com.example.FlappyDiver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.RatingBar;
import android.widget.ViewFlipper;

import org.firezenk.bubbleemitter.BubbleEmitterView;

import java.util.Random;


public class MainActivity extends AppCompatActivity  {
    private static int SPLASH_SCREEN = 5000;
    private SeekBar volumeSeekBar;
    private AudioManager audioManager;
    Animation TopAnim, BottomAnim;
    ImageView diver;
    TextView logo;
    ImageView diver1;
    private ViewFlipper viewFlipper;
    //   private Button button;
    ImageView gameOver;
    Button next;
    LinearLayout yes;
    LinearLayout no;
    ImageView FlappyDiver;
    Dialog myDialog;
    Dialog myDialog1;
    Dialog myDialog2;
    Switch mySwitch;
    Dialog myDialog3;
    Switch mySwitch2;
    String name1,name2,name3,score1,score2,score3;
    ImageView myImage;
    ImageView myImage2;
    ImageView myImage3;
    ImageView settingsX;
    TextView oneName,twoName,threeName,oneScore,twoScore,threeScore;
    SharedPreferences prefs;
    Button startBubble, stopBubble;
    Button retry;
    BubbleEmitterView bubbleEmitter;
    Handler handler = new Handler();
    MediaPlayer mysong;
    private int position;
    RatingBar r1;
    private String[] arr = {"Dog", "Cat", "Hamster"};
    Spinner aSpinner, aSpinner2, aSpinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doBindService();
        prefs = getSharedPreferences("game", MODE_PRIVATE);
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

        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point point=new Point();
        getWindowManager().getDefaultDisplay().getSize(point);

        name1=prefs.getString("name1",null);
        name2=prefs.getString("name2",null);
        name3=prefs.getString("name3",null);
        score1=prefs.getString("score1",null);
        score2=prefs.getString("score2",null);
        score3=prefs.getString("score3",null);

        TextView textView = new TextView(this);
        textView.setText("Dynamically added TextView");
        textView.setGravity(Gravity.CENTER);
        gameOver=(ImageView)findViewById(R.id.gameover);
        retry=(Button) findViewById(R.id.retry);

        diver1=findViewById(R.id.diver);
        myImage = findViewById(R.id.settings);
        myImage2=findViewById(R.id.imageView6);
        myImage3=findViewById(R.id.info);
        FlappyDiver=findViewById(R.id.imageView5);
        myImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog1();
            }
        });

        myImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });

        myImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog2();
            }
        });
//HERE
        final Button next = (Button) findViewById(R.id.next);
//
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        diver1.startAnimation(animation);

//        oneScore.setText("ben");
//
//
//      twoScore.setText("ben");
//        threeScore.setText("ben");

        //mysong = MediaPlayer.create(MainActivity.this, R.raw.ukulele);
        //mysong.start();
        Animation animation3 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        FlappyDiver.startAnimation(animation);


        bubbleEmitter= findViewById(R.id.bubbleEmitter);

        emitBubbles();

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(MainActivity.this, R.array.animals, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        final Button button = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button) findViewById(R.id.button4);
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                button.startAnimation(animation);
                openDialog7();

            }
        });


        final Button button2 = (Button) findViewById(R.id.play);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide);
                button2.startAnimation(animation);
                Intent toSecondary = new Intent(MainActivity.this,DialogActivity.class);
                startActivity(toSecondary);

            }
        });




        myDialog = new Dialog(this);
        myDialog1=new Dialog(this);
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
                Scon,Context.BIND_AUTO_CREATE);
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

    public void previousView(View v) {
        viewFlipper.setInAnimation(this, R.anim.slide_in_right);
        viewFlipper.setOutAnimation(this, R.anim.slide_out_left);
        viewFlipper.showPrevious();
    }
    public void nextView(View v) {
        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);
        viewFlipper.showNext();
    }


    private void startAnimation(){
        Animation animation= AnimationUtils.loadAnimation(this,R.anim.rotate);
        gameOver.startAnimation(animation);
    }
    private void openDialog7() {

        final Dialog dialog=new Dialog(MainActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View custompopup8= layoutInflater.inflate(R.layout.custompopup8,null);

        dialog.setContentView(custompopup8);
        dialog.setTitle("quit");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);

        TextView txtclose = (TextView) dialog.findViewById(R.id.txtclose);
        txtclose.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
               dialog.dismiss();
            }
        });


        LinearLayout no = (LinearLayout) dialog.findViewById(R.id.no);
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        LinearLayout yes = (LinearLayout) dialog.findViewById(R.id.yes);
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
            }

    private void openDialog2() {

        final Dialog dialog=new Dialog(MainActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View infodialog= layoutInflater.inflate(R.layout.infodialog,null);

        dialog.setContentView(infodialog);
        dialog.setTitle("info");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);

        ImageView closeInfo = (ImageView) dialog.findViewById(R.id.closeInfo);
        //settingsX=findViewById(R.id.imageView4);
        closeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
private void initControls(){

        try{
            volumeSeekBar=(SeekBar)findViewById(R.id.seekBar);
            audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
            volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
}


    private void openDialog1() {

        final  Dialog dialog=new Dialog(MainActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View recordstable= layoutInflater.inflate(R.layout.recordstable,null);

        dialog.setContentView(recordstable);

        dialog.setTitle("Table");
        dialog.show();


        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);
        oneName=(TextView) dialog.findViewById(R.id.firstName_tv);
        twoName=(TextView) dialog.findViewById(R.id.secondName_tv);
        threeName=(TextView) dialog.findViewById(R.id.thirdName_tv);
        oneScore=(TextView) dialog.findViewById(R.id.firstScore_tv);
        twoScore=(TextView) dialog.findViewById(R.id.secondScore_tv);
        threeScore=(TextView) dialog.findViewById(R.id.thirdScore_tv);
        oneName.setText(name1);
        twoName.setText(name2);
        threeName.setText(name3);
        oneScore.setText(score1);
        twoScore.setText(score2);
        threeScore.setText(score3);
        ImageView closeRec = (ImageView) dialog.findViewById(R.id.closeRecords);
        //settingsX=findViewById(R.id.imageView4);
        closeRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }
    private void openDialog() {

        final  Dialog dialog=new Dialog(MainActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View settings= layoutInflater.inflate(R.layout.settings,null);

        dialog.setContentView(settings);
        dialog.setTitle("Settings");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();
        volumeSeekBar=(SeekBar)dialog.findViewById(R.id.seekBar);
        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        volumeSeekBar.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar.setProgress(audioManager.getStreamVolume(AudioManager.STREAM_MUSIC));
        volumeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,progress,0);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);
          setVolumeControlStream(AudioManager.STREAM_MUSIC);
             initControls();
        mySwitch=(Switch)dialog.findViewById(R.id.sound);

        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {

                    SharedPreferences.Editor collection = prefs.edit();
                    collection.putBoolean("isMute",true);
                    collection.apply();
//                    mServ.stopMusic();

                    }
                else {
                    SharedPreferences.Editor collection = prefs.edit();
                    collection.putBoolean("isMute",false);
                    collection.apply();
//                    mServ.startMusic();
                }
            }
        });


        ImageView settingsX = (ImageView) dialog.findViewById(R.id.imageView4);
        //settingsX=findViewById(R.id.imageView4);
        settingsX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


    }

    public void ShowPopup(View v) {
        TextView txtclose;
        Button btnFollow;
        myDialog.setContentView(R.layout.custompopup);
        txtclose = (TextView) myDialog.findViewById(R.id.txtclose);
        txtclose.setText("M");
        btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
            }
        });
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.show();

    }




    private void showStartDialogue(){
        new AlertDialog.Builder(this)
                .setTitle("New Player Detected")
                .setMessage("Welcome to Flappy Diver. It is recommended for new players to take the tutorial. Have a fun session!")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create().show();

        SharedPreferences prefs = getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("firststart",false);
        editor.apply();
    }

    private void emitBubbles() {

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

//
    //       @Override
    //        public void onNothingSelected(AdapterView<?> parent) {

    //        }
    ////     });

    //   }

    public void toFoodActivity(View view) {
        Intent toFood = new Intent(MainActivity.this, FoodAvcivity.class);
        toFood.putExtra("animal", this.arr[this.position]);
        startActivity(toFood);
    }



 //   @Override
 //   protected void onPause() {
 //       super.onPause();
  //      mysong.release();
  //      finish();
    //}




    public void toSlide(View view) {
        Button button = (Button)findViewById(R.id.play);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.slide);
        button.startAnimation(animation);
    }

    public void toMove(View view) {
        Button button = (Button)findViewById(R.id.button3);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.move);
        button.startAnimation(animation);
        Intent toFoodActivity = new Intent(MainActivity.this, FoodAvcivity.class);
        startActivity(toFoodActivity);

    }

    public void toBlink(View view) {
        Button button = (Button)findViewById(R.id.button4);
        Animation animation = AnimationUtils.loadAnimation(this,R.anim.blink);
        button.startAnimation(animation);
    }



}
