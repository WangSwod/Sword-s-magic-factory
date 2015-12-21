package com.example.apple.gift;

/**
 * Created by apple on 12/18/15.
 */
public class FallingParticle extends Particle {
    static final int G = 10;

    public FallingParticle(int color,float x, float y ){
        super(color,x,y);
    }

    @Override
    public void advance() {
        if(isStarted()){
            incrementTime();
            int time = getTime();
            float old_Y = getY();
            float new_Y = Util.particle_Length + old_Y;
//        Log.i("FallingParticle", "time: "+ time );
            setY(new_Y);
        }
    }


}
