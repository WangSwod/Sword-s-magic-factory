package com.example.apple.gift;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    static final String TAG = "MAINACTIVITY";
    ParticleView rose;

    int animationDuration = Util.DURATION;
    Particle[][] particles;

    float rose_H , rose_W, bitmap_Rose_H,bitmap_Rose_W;

    Bitmap bitmap;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rose = (ParticleView) findViewById(R.id.rose_view);

        BitmapDrawable bitmapDrawable = (BitmapDrawable)rose.getDrawable();
        bitmap = bitmapDrawable.getBitmap();



        rose.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    rose_H = v.getHeight();
                    rose_W = v.getWidth();
                    bitmap_Rose_W  = bitmap.getWidth();
                    bitmap_Rose_H = bitmap.getHeight();

                    particles =  Util.createParticlesFromBitmap(bitmap,rose_H,rose_W);
                    rose.setParticles(particles);

                    beginAnimation();

                }
                return false;
            }
        });

    }

    private void beginAnimation(){
        initView();
//        ValueAnimator animator = ValueAnimator.ofInt(0,300).setDuration(animationDuration);
        ObjectAnimator animator =  ObjectAnimator.ofFloat(rose, "translationY",rose.getTranslationY(), -(rose_H - bitmap_Rose_H)/2).setDuration(animationDuration);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                rose.invalidate();
            }
        });
//        animator.addListener(new AnimatorListenerAdapter() {
//
//            public void onAnimationEnd(Animator animation) {
//                Log.i(TAG,"onAnimationStart:" + rose.getTranslationY())
//                super.onAnimationStart(animation);
//            }
//        });
        animator.start();
    }

    private  void initView(){
        rose.setTranslationY(0f);
    }
}
