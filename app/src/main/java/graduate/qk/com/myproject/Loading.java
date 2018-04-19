//package graduate.qk.com.myproject;
//
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.media.AudioManager;
//import android.media.MediaPlayer;
//import android.os.Bundle;
//import android.view.animation.Animation;
//import android.view.animation.ScaleAnimation;
//import android.widget.ImageView;
//
//import java.io.IOException;
//
///**
// * Created by Administrator on 2017/tab9/19.
// */
//
//public class Loading extends Activity {
//    private ImageView imageView;
//    private MediaPlayer mediaPlayer;
//    private AudioManager audioManager;
//    private String musicUrl=null;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.loadingActivity);
//       // new Handler();
//        startAction();
//    }
//
//    private void startAction() {
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//            Animation animation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f); // 将图片放大1.2倍，从中心开始缩放
//            animation.setFillAfter(true); // 动画结束后停留在结束的位置
//            imageView = (ImageView) findViewById(R.id.logo);
//            // AlphaAnimation animation = new AlphaAnimation(0.0f, 1.0f);
//            animation.setDuration(10000); // 动画持续时间
//            animation.setAnimationListener(new Animation.AnimationListener() {
//
//                @Override
//                public void onAnimationStart(Animation animation) {
//                }
//
//                @Override
//                public void onAnimationRepeat(Animation animation) {
//                }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    Intent intent = new Intent(Loading.this, MainActivity.class);
//                    startActivity(intent);
//                    mediaPlayer.stop();
//                    finish();
//                }
//            });
//            imageView.setAnimation(animation);
//            imageView.startAnimation(animation);
//            audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
//            int current = audioManager.getStreamVolume(AudioManager.STREAM_RING);//获取当前系统铃声音量，判断是否为静音
////            mediaPlayer.reset();
//            bgmUrl();
////            mediaPlayer.prepare();
//            if (current == 0) {
//                mediaPlayer.stop();
//                return;
//            } else {
//                mediaPlayer.start();
//                mediaPlayer.setLooping(true);
//            }
//
//
//    }
///*获取背景音乐播放地址;若无,则默认音乐*/
//    private void bgmUrl() {
//        if (musicUrl==null){
//            mediaPlayer = MediaPlayer.create(Loading.this, R.raw.walve);
//          //  mediaPlayer = MediaPlayer.create(Loading.this, R.raw.cat);
//        }else {
//            try {
//                mediaPlayer.setDataSource(musicUrl);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
////    Runnable r = new Runnable() {
////        @Override
////        public void run() {
////            // TODO Auto-generated method stub
////            mediaPlayer.stop();
////            finish();
////        }
////    };
//}