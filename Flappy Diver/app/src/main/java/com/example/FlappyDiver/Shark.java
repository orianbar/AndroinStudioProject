package com.example.FlappyDiver;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.FlappyDiver.GameView.screenRatioX;
import static com.example.FlappyDiver.GameView.screenRatioY;

public class Shark {
    public int speed=30;
    int x=0,y,width,height,sharkCounter=1;
    Bitmap shark1,shark2,shark3,shark4;
    Shark(Resources resources){
        shark1= BitmapFactory.decodeResource(resources,R.drawable.shark1);
        shark2= BitmapFactory.decodeResource(resources,R.drawable.shark2);
        shark3= BitmapFactory.decodeResource(resources,R.drawable.shark1);
        shark4= BitmapFactory.decodeResource(resources,R.drawable.shark2);
        width=shark1.getWidth();
        height=shark1.getHeight();
        width/=5;
        height/=5;
        width = (int) (width * screenRatioX);
        height = (int) (height * screenRatioY);
        shark1=Bitmap.createScaledBitmap(shark1,width,height,false);
        shark2=Bitmap.createScaledBitmap(shark2,width,height,false);
       shark3=Bitmap.createScaledBitmap(shark3,width,height,false);
        shark4=Bitmap.createScaledBitmap(shark4,width,height,false);
        y=-height;
    }
    Bitmap getShark(){
        if(sharkCounter>=1&&sharkCounter<=20){
            sharkCounter++;
            return shark1;
        }
        if(sharkCounter>20&&sharkCounter<=40){
            sharkCounter++;
            return shark2;
        }

        sharkCounter=1;
        return shark1;
    }
    Rect getCollisionShape(){
        return new Rect(x,y,x+width,y+height);
    }
}
