package com.example.FlappyDiver;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Background {
   float x=0,y=0;
    Bitmap background;
    Background(int screenX, int screenY, Resources res){
            background= BitmapFactory.decodeResource(res,R.drawable.background33);
        background=Bitmap.createScaledBitmap(background,screenX,screenY,false);
    }
}
