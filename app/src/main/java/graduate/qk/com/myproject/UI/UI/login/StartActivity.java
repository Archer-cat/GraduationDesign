package graduate.qk.com.myproject.UI.UI.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.MainActivity;
import graduate.qk.com.myproject.R;

/**
 * Created by Administrator on 2017/10/21.
 */

public class StartActivity extends Activity implements View.OnClickListener{
    private Handler mhandler;
    private LinearLayout layout_point;
    private int mpoint=0;
    private ViewPager viewPager;
    private RadioButton indexBt;
    private List<ImageView> imageList;
    private List<String> textList;
    private ImageView redPoint;
    private TextView Titletext;
    private int[] img={R.drawable.sailya,R.drawable.darkness_sabar,R.drawable.super_fate,R.drawable.archer};
  //  private String[] text={"第一张","第二张","第三张"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guide);
        init();
        initData();
    }

    private void init() {
        viewPager=(ViewPager) findViewById(R.id.viewPager);
        layout_point=(LinearLayout)findViewById(R.id.layout_point);
        Titletext=(TextView) findViewById(R.id.enter);
        redPoint=(ImageView)findViewById(R.id.red_point);
        indexBt=(RadioButton)findViewById(R.id.index);
        indexBt.setOnClickListener(this);
        Titletext.setOnClickListener(this);
    }
    private void initData(){
        imageList=new ArrayList<>();
        textList=new ArrayList<>();
        for (int i=0;i<img.length;i++){
            ImageView image=new ImageView(this);
            image.setBackgroundResource(img[i]);

            imageList.add(image);

            //初始化小圆点
            ImageView point=new ImageView(this);
            point.setBackgroundResource(R.drawable.shape_point_d);
            LinearLayout.LayoutParams params=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
            if (i>0){
                params.leftMargin=10;
            }
            point.setLayoutParams(params);
          layout_point.addView(point);
        }
        viewPager.setAdapter(new MyViewPagerGuide());

        //viewpager的监听事件
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            //页面滑动事件
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
             int leftMargins= (int) (mpoint*positionOffset+position*mpoint+0.5f);
                RelativeLayout.LayoutParams params= (RelativeLayout.LayoutParams) redPoint.getLayoutParams();
                params.leftMargin=leftMargins;
                redPoint.setLayoutParams(params);

                //轮播标题栏
                //imgtext.setText(text[position]);
                if (position==imageList.size()-1){
                    Titletext.setVisibility(View.VISIBLE);
                }else {
                    Titletext.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageSelected(int position) {//页面点击事件

            }

            @Override
            public void onPageScrollStateChanged(int state) {//页面滑动状态

            }
        });
        //启动自动轮播
        if (mhandler==null){
            mhandler=new Handler(){
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);

                int  mCurrentItem = viewPager.getCurrentItem();
                    if (mCurrentItem<img.length-1){//小于最大数目，正常++
                        mCurrentItem++;
                    }else{
                       // mCurrentItem=0;//否则直接归0
                        mhandler.removeCallbacksAndMessages(null);//移除消息，停止自动轮播
                    }
                    viewPager.setCurrentItem(mCurrentItem);
                    mhandler.sendEmptyMessageDelayed(0,2000);
                }
            };
            //发送延时，自动轮播
            mhandler.sendEmptyMessageDelayed(0,2000);
        }
     //这里还要做一个。触摸viewpager是，暂停自动轮播
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        mhandler.removeCallbacksAndMessages(null);//移除消息，停止自动轮播
                        break;
                    case MotionEvent.ACTION_UP://移开控制的手指，恢复自动轮播
                        mhandler.sendEmptyMessageDelayed(0,2000);
                        break;
                    case MotionEvent.ACTION_CANCEL://这里事件取消也要响应，否则流氓滑动会不响应，这里也恢复轮播
                        mhandler.sendEmptyMessageDelayed(0,2000);
                        break;
                }
                return  false;
            }
        });

        //视图树，监听layout的执行结束事件，一旦结束去获取当前的left1的位置
        //观察者事件
        redPoint.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override//一旦视图树的layout方法完成，就回调该方法
            public void onGlobalLayout() {
                //布局位置
                mpoint=layout_point.getChildAt(1).getLeft()-layout_point.getChildAt(0).getLeft();
                //移除观察者
                redPoint.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.index:
                finish();
                Intent intent_index=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent_index);
               // finish();
                break;
            case R.id.enter:
                finish();
                Intent intent_enter=new Intent(StartActivity.this,LoginActivity.class);
                startActivity(intent_enter);
               // finish();
                break;
            default:
                finish();
                break;
        }
    }

    class MyViewPagerGuide extends PagerAdapter{

        @Override
        public int getCount() {
            return img.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        //初始化布局
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ImageView images=imageList.get(position);
            container.addView(images);
            return images;
        }

        //销毁布局
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }
}
