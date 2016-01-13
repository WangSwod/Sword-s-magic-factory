package com.example.apple.gift;

import android.graphics.Bitmap;
import android.graphics.Rect;

/**
 * Created by apple on 12/16/15.
 */
public class Util {

    static int particle_Length = 10;
    static Particle[][] particles;
    static int DURATION =1000;

    public static Particle[][] createParticlesFromBitmap(Bitmap bitmap ,float height_View,float width_View){
        int height_Bitmap = bitmap.getHeight();
        int width_Bitmap = bitmap.getWidth();
        int x , y ;

        int num_h = height_Bitmap/particle_Length;
        int num_w = width_Bitmap/ particle_Length;

        float offset_X = (width_View - width_Bitmap)/2;
        float offset_Y = (height_View - height_Bitmap)/2;

        Rect bound = new Rect(0,0,width_Bitmap,height_Bitmap);



        particles = new Particle[num_h][num_w];

        for(int i = 0 ; i < num_h ; i ++){
            for(int j = 0 ; j < num_w; j++){

                x = j * particle_Length;
                y = i *particle_Length;

                int color = bitmap.getPixel(x, y);
                particles[i][j] = new FallingParticle(color, x+offset_X,y+offset_Y,(num_h -1-i),bound);


            }
        }

        return particles;
    }
}
