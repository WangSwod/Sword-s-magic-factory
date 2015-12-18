package com.example.apple.gift;

import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MAINACTIVITY";
    ImageView rose;

    int animationDuration = 3000;

    Particle[][] particles;
    ParticleView pv;
    Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rose = (ImageView) findViewById(R.id.rose_view);

        BitmapDrawable bitmapDrawable = (BitmapDrawable)rose.getDrawable();
        bitmap = bitmapDrawable.getBitmap();

        rose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    particles =  Util.createParticlesFromBitmap(bitmap);
                    pv = new ParticleView(MainActivity.this,particles);
                    setContentView(pv);
                    beginAnimation();
//                    Log.i(TAG, bitmap_Rose.getWidth() + "/" + bitmap_Rose.getHeight());
                }
                return false;
            }
        });



    }

    private void beginAnimation(){
       ValueAnimator animator =  ValueAnimator.ofInt(0, 5).setDuration(animationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int time = (int)animation.getCurrentPlayTime();

                if (time %10 == 0){

                    pv.invalidate();
                }
            }
        });
        animator.start();
    }
}
