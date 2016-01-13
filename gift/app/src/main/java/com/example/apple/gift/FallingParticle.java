package com.example.apple.gift;

import android.graphics.Rect;

import java.util.Random;

/**
 * Created by apple on 12/18/15.
 */
public class FallingParticle extends Particle {
    static final int G = 10;
    int order ;
    Rect bound ;

    Random random ;
    public FallingParticle(int color,float x, float y ,int order,Rect bound){
        super(color,x,y);
        this.order = order;
        this.bound = bound;
        random = new Random();
    }

    @Override
    public void advance() {
        incrementTime();
//        if(getTime() > order) {
            float old_Y = getY();
            float old_X = getX();
//            float new_Y = Util.particle_Length /2 + old_Y;
            float new_X = old_X +  random.nextInt(bound.width()) * (random.nextFloat() - 0.5f);
            float new_Y = old_Y + random.nextInt(bound.height() / 6);

            setY(new_Y);
            setX(new_X);
        float old_Alpha = getAlpha();

        if(old_Alpha>=0f){

            float new_Alpha = old_Alpha - 0.04f;
            setAlpha(new_Alpha);
//            }
        }
    }


}
