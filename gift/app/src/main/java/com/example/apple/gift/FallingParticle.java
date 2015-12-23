package com.example.apple.gift;

/**
 * Created by apple on 12/18/15.
 */
public class FallingParticle extends Particle {
    static final int G = 10;
    int order ;
    public FallingParticle(int color,float x, float y ,int order){
        super(color,x,y);
        this.order = order;
    }

    @Override
    public void advance() {
        incrementTime();
        if(getTime() > order) {
            float old_Y = getY();
            float new_Y = Util.particle_Length /2 + old_Y;
            setY(new_Y);

        float old_Alpha = getAlpha();

        if(old_Alpha>=0f){

            float new_Alpha = old_Alpha - 0.04f;
            setAlpha(new_Alpha);
            }
        }
    }


}
