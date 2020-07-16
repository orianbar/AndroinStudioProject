
package com.example.FlappyDiver;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;




public class DialogActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    Animation TopAnim, BottomAnim;
    ImageView image;
    TextView logo;
    Boolean pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_dialog);

        TopAnim = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        BottomAnim = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);

        image = findViewById(R.id.iv);
        logo = findViewById(R.id.tv);
        pressed=false;
        openDialog2();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if(pressed==false) {
                            Intent in = new Intent(DialogActivity.this, secondary_activity.class);
                            startActivity(in);
                        }
                   }
               }, 10000);



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
    private void openDialog2() {

        final Dialog dialog=new Dialog(DialogActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View custompopup4= layoutInflater.inflate(R.layout.custompopup4,null);

        dialog.setContentView(custompopup4);
        dialog.setTitle("Diver");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openDialog3();

            }
        }, 2000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

            }
        }, 3000);
        TextView closeInfo = (TextView) dialog.findViewById(R.id.txtclose);
        closeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed=true;
                Intent in1 = new Intent(DialogActivity.this, secondary_activity.class);
                startActivity(in1);

            }
        });
    }
    private void openDialog3() {

        final  Dialog dialog=new Dialog(DialogActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View custompopup3= layoutInflater.inflate(R.layout.custompopup3,null);

        dialog.setContentView(custompopup3);
        dialog.setTitle("Heart");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openDialog4();

            }
        }, 2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

            }
        }, 3000);
        TextView closeInfo = (TextView) dialog.findViewById(R.id.txtclose);
        closeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed=true;
                Intent in2 = new Intent(DialogActivity.this, secondary_activity.class);
                startActivity(in2);
            }
        });
    }

    private void openDialog4() {

        final  Dialog dialog=new Dialog(DialogActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View custompopup2= layoutInflater.inflate(R.layout.custompopup2,null);

        dialog.setContentView(custompopup2);
        dialog.setTitle("Coin");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openDialog5();

            }
        }, 2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

            }
        }, 3000);
        TextView closeInfo = (TextView) dialog.findViewById(R.id.txtclose);
        closeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed=true;
                Intent in3 = new Intent(DialogActivity.this, secondary_activity.class);
                startActivity(in3);
            }
        });
    }

    private void openDialog5() {

        final  Dialog dialog=new Dialog(DialogActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View custompopup6= layoutInflater.inflate(R.layout.custompopup6,null);

        dialog.setContentView(custompopup6);
        dialog.setTitle("Coin");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);

        //  Context context = getApplicationContext();
        //  CharSequence text = "Press back to continue";
        // int duration = Toast.LENGTH_SHORT;
        //  Toast toast = Toast.makeText(context, text, duration);
        //  toast.show();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                openDialog6();

            }
        }, 2000);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();

            }
        }, 3000);
        TextView closeInfo = (TextView) dialog.findViewById(R.id.txtclose);
        closeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed=true;
                Intent in4 = new Intent(DialogActivity.this, secondary_activity.class);
                startActivity(in4);
            }
        });
    }
    private void openDialog6() {

        final  Dialog dialog=new Dialog(DialogActivity.this,R.style.CustomDialogTheme);
        LayoutInflater layoutInflater=this.getLayoutInflater();
        View custompopup= layoutInflater.inflate(R.layout.custompopup,null);

        dialog.setContentView(custompopup);
        dialog.setTitle("Coin");
        dialog.show();
        Display display =((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        int height=display.getHeight();

        Log.v("width", width+"");
        dialog.getWindow().setLayout((6*width)/7,(4*height)/5);

        //  Context context = getApplicationContext();
        //  CharSequence text = "Press back to continue";
        // int duration = Toast.LENGTH_SHORT;
        //  Toast toast = Toast.makeText(context, text, duration);
        //  toast.show();
      //  final Handler handler = new Handler();
      //  handler.postDelayed(new Runnable() {
      //      @Override
      //      public void run() {
       //         dialog.dismiss();

       //     }
     //   }, 3000);
        TextView closeInfo = (TextView) dialog.findViewById(R.id.txtclose);
        closeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pressed=true;
                Intent in5 = new Intent(DialogActivity.this, secondary_activity.class);
                startActivity(in5);
            }
        });
    }
}



