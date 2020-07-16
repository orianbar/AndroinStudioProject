package com.example.FlappyDiver;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import static com.example.FlappyDiver.GameView.screenRatioX;
import static com.example.FlappyDiver.GameView.screenRatioY;

public class Diving {
    public boolean isGoingUp=false;
    int x,y,width,height,wingCounter=0;
    Bitmap diving1,diving2,diving3;
    Diving(int screenY, Resources res){

        diving1= BitmapFactory.decodeResource(res,R.drawable.diving1);
        diving2= BitmapFactory.decodeResource(res,R.drawable.dving2);
        diving3= BitmapFactory.decodeResource(res,R.drawable.diving4);


        width=diving1.getWidth();
        height=diving1.getHeight();
        width /=4;
        height /=4;
        width=(int) (width*screenRatioX);
        height=(int)(height*screenRatioY);
        diving1=Bitmap.createScaledBitmap(diving1,width,height,false);
        diving2=Bitmap.createScaledBitmap(diving2,width,height,false);
        diving3=Bitmap.createScaledBitmap(diving3,width,height,false);
        y=screenY/2;
        x= (int) (64*screenRatioX);
    }
    Bitmap getDiving(){
        if(wingCounter>=1&&wingCounter<8){
           wingCounter++;
            return diving1;
        }
        if(wingCounter>=8&&wingCounter<=16){
            wingCounter++;
            return diving3;
        }
       if(wingCounter>16&&wingCounter<=24){
            wingCounter++;
           return diving2;
        }


        wingCounter=1;
        return diving2;
    }
    Rect getCollisionShape(){
        return new Rect(x,y,x+width,y+height);
    }



}
