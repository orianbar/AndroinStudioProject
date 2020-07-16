package com.example.FlappyDiver;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.FlappyDiver.GameView.screenRatioX;
import static com.example.FlappyDiver.GameView.screenRatioY;

public class CoinEx {
    public int speed=50;
    int x=0,y,width,height;
    Bitmap heart;
    CoinEx(Resources resources){
        BitmapFactory.Options op = new BitmapFactory.Options();
        op.inPreferredConfig = Bitmap.Config.ARGB_8888;;

        heart= BitmapFactory.decodeResource(resources,R.drawable.premiumcoin,op);

        width=heart.getWidth();
        height=heart.getHeight();
        width/=10;
        height/=10;
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);
        heart=Bitmap.createScaledBitmap(heart,width,height,false);

        y=-height;
    }
    Bitmap getHearts(){
        return heart;
    }
    Rect getCollisionShape(){
        return new Rect(x,y,x+width,y+height);
    }
}


