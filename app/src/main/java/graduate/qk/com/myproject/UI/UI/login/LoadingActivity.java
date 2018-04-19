package graduate.qk.com.myproject.UI.UI.login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;

import graduate.qk.com.myproject.R;

/**
 * Created by Administrator on 2017/10/20.
 */

public class LoadingActivity extends Activity {
  private  LinearLayout layout;
  private ImageView img;
  public MediaPlayer mediaPlayer;
  private AudioManager audioManager;
  private String musicUrl=null;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_loading);
        init();
    }

    private void init() {
        img=(ImageView) findViewById(R.id.loading_img);
        layout=(LinearLayout)findViewById(R.id.loading_layout);


        //旋转
        RotateAnimation rAnimation=new RotateAnimation(0,360,Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
        rAnimation.setDuration(1000);
        rAnimation.setFillAfter(true);
        // 将图片放大1.2倍，从中心开始缩放
        Animation sAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        sAnimation.setDuration(1000);
        sAnimation.setFillAfter(true);
        //渐变
        AlphaAnimation aAnimation=new AlphaAnimation(0,1);
        aAnimation.setDuration(2000);
        aAnimation.setFillAfter(true);

        //动画集合
        AnimationSet animationSet=new AnimationSet(false);
        animationSet.addAnimation(aAnimation);
        animationSet.addAnimation(sAnimation);
        animationSet.addAnimation(rAnimation);

        //启动动画
        layout.startAnimation(animationSet);
        //动画监听
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent intent=new Intent(LoadingActivity.this,StartActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        img.setAnimation(animationSet);
        img.startAnimation(animationSet);
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int current = audioManager.getStreamVolume(AudioManager.STREAM_RING);//获取当前系统铃声音量，判断是否为静音
//            mediaPlayer.reset();
        bgmUrl();
//            mediaPlayer.prepare();
        if (current == 0) {
            mediaPlayer.stop();
            return;
        } else {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
    }
    /*获取背景音乐播放地址;若无,则默认音乐*/
    private void bgmUrl() {
        if (musicUrl==null){
            mediaPlayer = MediaPlayer.create(LoadingActivity.this, R.raw.walve);
            //  mediaPlayer = MediaPlayer.create(Loading.this, R.raw.cat);
        }else {
            try {
                mediaPlayer.setDataSource(musicUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
