package com.expandablelayout;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;

import com.example.apple.swordplayer.BaseActivity;
import com.example.apple.swordplayer.R;
import com.playservice.PlayService;

/**
 * Created by apple on 1/13/16.
 */
public class SwordExpandableLayout extends RelativeLayout implements View.OnClickListener{

    static final String TAG = "SWORDEXPANDABLELAYOUT";

    private  boolean isContentShown = false;
    public  int list_Id;


    public int getListId() {
        return list_Id;
    }

    public void setListId(int id) {
        this.list_Id = id;
    }

    ImageView play_Image;
    FrameLayout headerLayout, contentLayout;
    int contentID,headerID;
    TypedArray typedArray;

    SeekBar seekBar;


    public SwordExpandableLayout(Context context) {
        super(context);

    }

    public SwordExpandableLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SwordExpandableLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs) {


        View root = View.inflate(context, R.layout.view_expandable, this);

        headerLayout = (FrameLayout)root.findViewById(R.id.view_expandable_headerlayout);
        contentLayout = (FrameLayout) root.findViewById(R.id.view_expandable_contentLayout);

        typedArray = context.obtainStyledAttributes(attrs, R.styleable.ExpandableLayout);

        contentID = typedArray.getResourceId(R.styleable.ExpandableLayout_sword_contentLayout, -1);
        headerID = typedArray.getResourceId(R.styleable.ExpandableLayout_sword_headerLayout,-1);

        if(contentID == -1 || headerID == -1 ){
            Log.i(TAG, "ID is wrong");
        }

        View contentView = View.inflate(context,contentID,null);
        View headerView = View.inflate(context,headerID,null);


        contentLayout.addView(contentView);
        headerLayout.addView(headerView);
        contentLayout.setVisibility(GONE);

        play_Image = (ImageView)findViewById(R.id.play_image);
        play_Image.setOnClickListener(this);
        seekBar = (SeekBar)findViewById(R.id.seekBar);

//        duration_time = (TextView)findViewById(R.id.duration_time);

        typedArray.recycle();


    }


    public  void setDuration(int duration){
        seekBar.setMax(duration);
    }
    public  void updateSeekBar(int duration){


        seekBar.setProgress(duration);

    }

    public void startCollapseAnimation() {

        contentLayout.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = contentLayout.getMeasuredHeight();


        ValueAnimator valueAnimator = ValueAnimator.ofInt(height,0);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                changeContentHeight((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                hideContent();

            }
        });

        valueAnimator.setDuration(500);
        valueAnimator.start();
        isContentShown = false;
        play_Image.setImageResource(R.mipmap.play_image);

    }



    public void startShowAnimation( ){
        contentLayout.measure(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int height = contentLayout.getMeasuredHeight();

        contentLayout.setVisibility(VISIBLE);
        ValueAnimator valueAnimator = ValueAnimator.ofInt(0, height);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                changeContentHeight((int) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(500);
        valueAnimator.start();
        isContentShown = true;
        play_Image.setImageResource(R.mipmap.pause_image);

    }

    private void changeContentHeight (int height){
        contentLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
    }
    private void hideContent(){
        contentLayout.setVisibility(GONE);
    }


    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.play_image:

                int position = (int)v.getTag(R.string.tag_for_position);
                Context context = (Context)v.getTag(R.string.tag_for_context);

                PlayService playService = ((BaseActivity) context).getPlayService();

                playService.connectToView(this);
                if(isContentShown){

                    startCollapseAnimation();
                    playService.stop(position);

                }else{
                    playService.start(position);
                    startShowAnimation();

                }
                break;

            default: break;
        }
    }

}
