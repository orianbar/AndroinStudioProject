package com.example.FlappyDiver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameView extends SurfaceView implements Runnable {
    private Thread thread;
    private Coin[] coins;
    private Diving diving;
    private ArrayList<Shark> sharks;
    private String userName;
    private Canvas canvas;
    private Heart[] hearts;

    LinearLayout image;
    LinearLayout image1;
    TextView logo;
    Boolean pressed;

    private Shark[] sharks2;
    private Shark[] sharks3;
    private SoundPool soundPool, soundPool2, soundPool3;
    private SharedPreferences prefs;
    private int sound, sound2, sound3;
    private boolean isPlaying;
    private Random random;
    private GameActivity activity;
    private Paint paint;
    private int level;
    private int scoreThree;
    private int score = 0;
    private Bitmap life[] = new Bitmap[2];
    public static float screenRatioX, screenRatioY;
    private static int isGameOver;
    private float screenX = 0, screenY = 0;
    private Background background1, background2;

    public GameView(GameActivity activity, int screenX, int screenY) throws InterruptedException {
        super(activity);
        this.activity = activity;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            AudioAttributes audioAttributes = new AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).setUsage(AudioAttributes.USAGE_GAME).build();

            soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
            soundPool2 = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
            soundPool3 = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        } else
            soundPool2 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundPool3 = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);

        sound = soundPool.load(activity, R.raw.coin1, 1);
        sound2 = soundPool2.load(activity, R.raw.functionfail, 1);
        sound3 = soundPool3.load(activity, R.raw.gameover, 1);
        prefs = activity.getSharedPreferences("game", Context.MODE_PRIVATE);

        this.screenX = screenX;
        this.screenY = screenY;
        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);
        screenRatioX = 2340f / screenX;
        screenRatioY = 1080f / screenY;
        background1 = new Background(screenX, screenY, getResources());
        background2 = new Background(screenX, screenY, getResources());
        background2.x = screenX;
        diving = new Diving(screenY, getResources());
        paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(80);
        isGameOver = 3;
        level=0;


        sharks=new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Shark shark = new Shark(getResources());
            sharks.add(shark);
            random = new Random();
        }



        coins = new Coin[3];
        for (int j = 0; j < 3; j++) {

            Coin coin = new Coin(getResources());
            coins[j] = coin;
            random = new Random();
        }
        hearts = new Heart[1];
        for (int j = 0; j < 1; j++) {

            Heart heart = new Heart(getResources());
            hearts[j] = heart;
            random = new Random();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void run() {
        while (isPlaying) {

            try {
                update();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                draw();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sleep();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void update() throws InterruptedException {
        background1.x -= 10 * screenRatioX;
        background2.x -= 10 * screenRatioX;

        if (background1.x + background1.background.getWidth() < 10) {
            background1.x = screenX;
            level=level+1;

        }
        if (background2.x + background2.background.getWidth() < 10) {
            background2.x = screenX;

        }

        if (diving.isGoingUp) {
            diving.y -= 40 * screenRatioY;
        } else
            diving.y += 25 * screenRatioY;
        if (diving.y < 0)
            diving.y = 0;
        if (diving.y >= screenY - diving.height)
            diving.y = ((int) screenY - diving.height);
        if (level >= 0 && level < 3) {
            for (int i=0;i<2;i++) {
                sharks.get(i).x -=   sharks.get(i).speed;
                if ( sharks.get(i).x +   sharks.get(i).width < 0) {
                    int bound = (int) (25 * screenRatioX);
                    sharks.get(i).speed = random.nextInt(bound);
                    sharks.get(i).speed = (int) (25 * screenRatioX);
                    sharks.get(i).x = (int) screenX;
                    sharks.get(i).y = random.nextInt((int) screenY -   sharks.get(i).height);
                }

                if (diving.x < sharks.get(i).x +   sharks.get(i).width / 2 && diving.x >   sharks.get(i).x -   sharks.get(i).width / 2)
                    if (diving.y <  sharks.get(i).y +   sharks.get(i).height / 2 && diving.y >  sharks.get(i).y -   sharks.get(i).height / 2) {
                        if (!prefs.getBoolean("isMute", false)) {
                            soundPool2.play(sound2, 1, 1, 0, 0, 1);
                        }

                        isGameOver = isGameOver - 1;
                        sharks.get(i).y =  sharks.get(i).y - 10000;

                        if (isGameOver == 0) {
                            waitBeforeExiting();
                        }
                    }


            }

            }
        if(level>3) {

                for (Heart heart : hearts) {
                    heart.x -= heart.speed;
                    if (heart.x + heart.width < 0) {
                        int bound = (int) (40 * screenRatioX);
                        heart.speed = random.nextInt(bound);


                            heart.speed = (int) (40 * screenRatioX);
                        heart.x = (int) screenX;
                        heart.y = random.nextInt((int) screenY - heart.height);
                    }

                    if (Rect.intersects(heart.getCollisionShape(), diving.getCollisionShape())) {
                        if(!prefs.getBoolean("isMute",false)){
                            soundPool.play(sound,1,1,0,0,1);
                        }
                        score += 30;
                        heart.y = heart.y - 10000;
                        return;
                    }
            }
            }




        if (level >2&&level<6) {

            for (int i=0;i<3;i++) {
                sharks.get(i).x -=   sharks.get(i).speed;
                if (  sharks.get(i).x +   sharks.get(i).width < 0) {
                    int bound = (int) (30 * screenRatioX);

                    sharks.get(i).speed = (int) (30 * screenRatioX);
                    sharks.get(i).x = (int) screenX;
                    sharks.get(i).y = random.nextInt((int) screenY -   sharks.get(i).height);
                }

                if (diving.x <   sharks.get(i).x +   sharks.get(i).width / 2 && diving.x >   sharks.get(i).x -   sharks.get(i).width / 2)
                    if (diving.y <   sharks.get(i).y +   sharks.get(i).height / 2 && diving.y >  sharks.get(i).y -   sharks.get(i).height / 2) {
                        if (!prefs.getBoolean("isMute", false)) {
                            soundPool2.play(sound2, 1, 1, 0, 0, 1);
                        }

                        isGameOver = isGameOver - 1;
                        sharks.get(i).y =  sharks.get(i).y - 10000;

                        if (isGameOver == 0) {
                            waitBeforeExiting();
                        }
                    }


            }

        }
        if (level >=6) {

            for (int i=0;i<4;i++) {
                sharks.get(i).x -=  sharks.get(i).speed;
                if (  sharks.get(i).x +   sharks.get(i).width < 0) {
                    int bound = (int) (30 * screenRatioX);
                    sharks.get(i).speed = (int) (30 * screenRatioX);
                    sharks.get(i).x = (int) screenX;
                    sharks.get(i).y = random.nextInt((int) screenY -   sharks.get(i).height);
                }

                if (diving.x <   sharks.get(i).x +   sharks.get(i).width / 2 && diving.x >   sharks.get(i).x -   sharks.get(i).width / 2)
                    if (diving.y <   sharks.get(i).y +   sharks.get(i).height / 2 && diving.y > sharks.get(i).y -   sharks.get(i).height / 2) {
                        if (!prefs.getBoolean("isMute", false)) {
                            soundPool2.play(sound2, 1, 1, 0, 0, 1);
                        }

                        isGameOver = isGameOver - 1;
                        sharks.get(i).y =   sharks.get(i).y - 10000;

                        if (isGameOver == 0) {
                            waitBeforeExiting();
                        }
                    }


            }

        }


        for (Coin coin : coins) {
            coin.x -= coin.speed;
            if (coin.x + coin.width < 0) {
                int bound = (int) (20 * screenRatioX);
                coin.speed = random.nextInt(bound);

                if (coin.speed < 20 * screenRatioX)
                    coin.speed = (int) (20 * screenRatioX);
                coin.x = (int) screenX;
                coin.y = random.nextInt((int) screenY - coin.height);

            }

            if (Rect.intersects(coin.getCollisionShape(), diving.getCollisionShape())) {
                if(!prefs.getBoolean("isMute",false)){
                    soundPool.play(sound,1,1,0,0,1);
                }
                score += 10;
                coin.y = coin.y - 10000;
                return;
            }
        }


    }

    private void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void draw() throws InterruptedException {
        if (getHolder().getSurface().isValid()) {
            Canvas canvas = getHolder().lockCanvas();

            canvas.drawBitmap(background1.background, background1.x, background1.y, paint);
            canvas.drawBitmap(background2.background, background2.x, background2.y, paint);

            for (int i = 0; i < 3; i++) {
                int x = (int) (1800 + life[0].getWidth() * 1.5 * i);
                int y = 30;
                if (i >= isGameOver) {
                    canvas.drawBitmap(life[1], x, y, paint);
                } else canvas.drawBitmap(life[0], x, y, paint);

            }
            canvas.drawText("score:" + score, 250, 180, paint);
            if (isGameOver == 0) {
                getHolder().unlockCanvasAndPost(canvas);
               saveIfHighScore();
              waitBeforeExiting();

                return;
           }
            for (Coin coin : coins)
                canvas.drawBitmap(coin.getCoins(), coin.x, coin.y, paint);


                for (Shark shark : sharks) {
                    canvas.drawBitmap(shark.getShark(), shark.x, shark.y, paint);


                }
            for (Heart heart : hearts) {
                canvas.drawBitmap(heart.getHearts(), heart.x, heart.y, paint);

            }



            canvas.drawBitmap(diving.getDiving(), diving.x, diving.y, paint);
            getHolder().unlockCanvasAndPost(canvas);


        }
    }




    @RequiresApi(api = Build.VERSION_CODES.N)
    private void waitBeforeExiting() {
        try {
            if(!prefs.getBoolean("isMute",false)){
                soundPool3.play(sound3,1,1,0,0,1);
            }
            saveIfHighScore();


            Thread.sleep(1000);


        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.N)
    private void saveIfHighScore() {

        String name1, name2, name3, score1, score2, score3;
        name1 = prefs.getString("name1", "ben");
        name2 = prefs.getString("name2", "ben");
        name3 = prefs.getString("name3", "ben");
        userName=prefs.getString("username",null);
        score1 = prefs.getString("score1", null);
        score2 = prefs.getString("score2", null);
        score3 = prefs.getString("score3", null);

        if (score1 == null && score2 == null && score3 == null) {
            SharedPreferences.Editor collection = prefs.edit();
            String name4 = "ben";
            String name5 = "ben";
            String name6 = "ben";
            String score4 = "0";
            String score5 = "0";
            String score6 = "0";
            collection.putString("name1", name4);
            collection.putString("name2", name5);
            collection.putString("name3", name6);
            collection.putString("score1", score4);
            collection.putString("score2", score5);
            collection.putString("score3", score6);

            collection.apply();


        }

        ArrayList<Integer> arrayList = new ArrayList<>();
        int scoreOne = (Integer.parseInt(String.valueOf(score1)));
        int scoreTwo = (Integer.parseInt(String.valueOf(score2)));
         scoreThree = (Integer.parseInt(String.valueOf(score3)));
        arrayList.add(scoreOne);
        arrayList.add(scoreTwo);
        arrayList.add(scoreThree);
        arrayList.add(score);
        Collections.sort(arrayList);
        SharedPreferences.Editor collection = prefs.edit();
        collection.putInt("scoreOver",score);

        if (score != arrayList.indexOf(0)) {
            activity.startActivity(new Intent(activity,DialogScore.class));

            for (int i = 3; i > 0; i--) {
                if (i == 3) {
                    if (arrayList.get(i) == score) {
                        collection.putString("name1", userName);
                        collection.putString("score1", String.valueOf(score));
                    }
                    if (arrayList.get(i) == scoreOne) {
                        collection.putString("name1", name1);
                        collection.putString("score1", String.valueOf(scoreOne));
                    }
                    if (arrayList.get(i) == scoreTwo) {
                        collection.putString("name1", name2);
                        collection.putString("score1", String.valueOf(scoreTwo));
                    }
                    if (arrayList.get(i) == scoreThree) {
                        collection.putString("score1", String.valueOf(scoreThree));
                        collection.putString("name1", name3);
                    }
                }
                if (i == 2) {
                    if (arrayList.get(i) == score) {
                        collection.putString("name2", userName);
                        collection.putString("score2", String.valueOf(score));
                    }
                    if (arrayList.get(i) == scoreOne) {
                        collection.putString("name2", name1);
                        collection.putString("score2", String.valueOf(scoreOne));
                    }
                    if (arrayList.get(i) == scoreTwo) {
                        collection.putString("name2", name2);
                        collection.putString("score2", String.valueOf(scoreTwo));
                    }
                    if (arrayList.get(i) == scoreThree) {
                        collection.putString("score2", String.valueOf(scoreThree));
                        collection.putString("name2", name3);
                    }
                }
                if (i == 1) {
                    if (arrayList.get(i) == score) {
                        collection.putString("name3", userName);
                        collection.putString("score3", String.valueOf(score));

                    }
                    if (arrayList.get(i) == scoreOne) {
                        collection.putString("name3", name1);
                        collection.putString("score3", String.valueOf(scoreOne));
                    }
                    if (arrayList.get(i) == scoreTwo) {
                        collection.putString("name3", name2);
                        collection.putString("score3", String.valueOf(scoreTwo));
                    }
                    if (arrayList.get(i) == scoreThree) {
                        collection.putString("score3", String.valueOf(scoreThree));
                        collection.putString("name3", name3);
                    }
                }

            }


        }
        collection.apply();
    }


    public void resume(){
        isPlaying=true;
    thread=new Thread(this);
    thread.start();
    }
    public void pause(){
        try {
            isPlaying=false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if (event.getX()<screenX){
                    diving.isGoingUp=true;
                }
                break;
            case MotionEvent.ACTION_UP:
                diving.isGoingUp=false;
                break;
        }
        return true;
    }

    }





