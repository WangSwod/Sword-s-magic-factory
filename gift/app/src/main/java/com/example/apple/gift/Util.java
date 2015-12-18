package com.example.apple.gift;

import android.graphics.Bitmap;

/**
 * Created by apple on 12/16/15.
 */
public class Util {

    static int particle_Length = 10;
    static Particle[][] particles;

    public static Particle[][] createParticlesFromBitmap(Bitmap bitmap){
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        int x , y ;

        int num_h = height/particle_Length;
        int num_w = width / particle_Length;


        particles = new Particle[num_h][num_w];

        for(int i = 0 ; i < num_h ; i ++){
            for(int j = 0 ; j < num_w; j++){

                x = j * particle_Length;
                y = i *particle_Length;

                int color = bitmap.getPixel(x, y);
                particles[i][j] = new FallingParticle(color, x,y);

            }
        }

        return particles;
    }
}
