package com.ironsquishy.biteclub;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Edward on 7/28/2015.
 */

/**
 * @Author Edward Yao
 * Description: creates custom process dialog
 */
public class CustomProgressDialog extends ProgressDialog {

    private AnimationDrawable animation;
    MediaPlayer oneButtonPingFeedback;
    ImageView orangePacMan;
    TextView nowSearchingText;
    Animation animationToLeft;



    public static ProgressDialog initiateProgressDialog(Context context) {
        CustomProgressDialog dialog = new CustomProgressDialog(context);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.setIndeterminate(true);
        dialog.setCancelable(false);
        return dialog;
    }


    public CustomProgressDialog(Context context) {

        super(context);
    }

    public CustomProgressDialog(Context context, int theme) {

        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_progress_dialog);

        orangePacMan = (ImageView) findViewById(R.id.imageanimation);
        nowSearchingText = (TextView) findViewById(R.id.textanimation);

        //sets animation to layout as a background image
        orangePacMan.setBackgroundResource(R.drawable.pac_man_progress_dialog_animation);
        orangePacMan.bringToFront();
        orangePacMan.setTranslationX(-10);

        animation = (AnimationDrawable) orangePacMan.getBackground();

        //creates the slide to left animation
        animationToLeft = new TranslateAnimation(1500, -275, 0, 0);
        animationToLeft.setDuration(3000);
        animationToLeft.setRepeatMode(Animation.RESTART);
        animationToLeft.setRepeatCount(Animation.INFINITE);

        //sets string text to layout
        String textLeft = "Now Searching";
        nowSearchingText.setText(textLeft);
        nowSearchingText.setTypeface(null, Typeface.BOLD);
        nowSearchingText.setTextColor(Color.WHITE);
        nowSearchingText.setTextSize(15);

        oneButtonPingFeedback = MediaPlayer.create(getContext(), R.raw.sonar_one_ping_feedback);

    }

    /**
     * starts custom progress dialog
     */
    @Override
    public void show() {
        super.show();

        //starts pac man animation
        animation.start();
        //starts text animation
        nowSearchingText.startAnimation(animationToLeft);

        /**
         * creates runtime timer
         */
        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(3500);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                finally
                {

                    //playes sound
                    oneButtonPingFeedback.start();
                    //stops custom progress dialog
                    dismiss();


                }
            }
        };
        timer.start();

    }

    /**
     * stops custom progress dialog
     */
    @Override
    public void dismiss() {
        super.dismiss();
        animation.stop();

        Intent i = new Intent(getContext(), ResultActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        getContext().startActivity(i);
    }


}
