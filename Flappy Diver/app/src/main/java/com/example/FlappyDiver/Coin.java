package com.example.FlappyDiver;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.FlappyDiver.GameView.screenRatioX;
import static com.example.FlappyDiver.GameView.screenRatioY;

public class Coin {
    public int speed=30;
    int x=0,y,width,height,coinsCounter=1;
    Bitmap coin1,coin2,coin3,coin4;
    Coin(Resources resources){
       coin1= BitmapFactory.decodeResource(resources,R.drawable.coins);
        coin2= BitmapFactory.decodeResource(resources,R.drawable.coins);
        coin3= BitmapFactory.decodeResource(resources,R.drawable.coins);
        coin4= BitmapFactory.decodeResource(resources,R.drawable.coins);
        width=coin1.getWidth();
        height=coin1.getHeight();
        width/=10;
        height/=10;
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);
        coin1=Bitmap.createScaledBitmap(coin1,width,height,false);
        coin2=Bitmap.createScaledBitmap(coin1,width,height,false);
        coin3=Bitmap.createScaledBitmap(coin4,width,height,false);
        coin4=Bitmap.createScaledBitmap(coin4,width,height,false);

        y=-height;
    }
    Bitmap getCoins(){
        if(coinsCounter==1){
            coinsCounter++;
            return coin1;
        }
        if(coinsCounter==2){
            coinsCounter++;
            return coin2;
        }
        if(coinsCounter==3){
            coinsCounter++;
            return coin3;
        }
        coinsCounter=1;
        return coin4;
    }
    Rect getCollisionShape(){
        return new Rect(x,y,x+width,y+height);
    }
}
