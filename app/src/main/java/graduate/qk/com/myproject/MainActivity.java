package graduate.qk.com.myproject;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Api.VideoComicApi;
import graduate.qk.com.myproject.Bean.VideoComicEntity;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.UI.UI.DiscoverView;
import graduate.qk.com.myproject.UI.UI.fragmentMainTab.FragmentAdapter;
import graduate.qk.com.myproject.UI.UI.fragmentMainTab.FragmentTab_fourth;
import graduate.qk.com.myproject.UI.UI.fragmentMainTab.FragmentTab_frist;
import graduate.qk.com.myproject.UI.UI.fragmentMainTab.FragmentTab_second;
import graduate.qk.com.myproject.UI.UI.fragmentMainTab.FragmentTab_thread;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    private ViewPager vp;
    private FragmentTab_frist fristFragment;
    private FragmentTab_second secondFragment;
    private FragmentTab_thread threadFragment;
    private FragmentTab_fourth frouthFragment;
    private List<Fragment> fragmentList=new ArrayList<Fragment>();
    private RelativeLayout bt1,bt2,bt3,bt4;
    private LinearLayout content;
    private ImageView fristimg,secondimg,threadimg,fourthimg,search_button;
    private TextView fristtext,secondtext,threadtext,fourthtext;
    private FragmentPagerAdapter mFragmentAdapter;
    private FragmentManager fm;
    private FragmentTransaction ft;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitytab_main);
        init();
       // adaptered();
        fragmentManagerReplace();
       // getVideoDatas();
    }

    private void getVideoDatas() {
        VideoComicApi service = HttpUtil.getHttpUtil().createService(VideoComicApi.class, IP.Ip);
        service. getVideos().enqueue(new Callback<VideoComicEntity>() {
            @Override
            public void onResponse(Call<VideoComicEntity> call, Response<VideoComicEntity> response) {
                Log.e("TEst1",response.body().toString());
                Log.e("all0","succuce");
            }

            @Override
            public void onFailure(Call<VideoComicEntity> call, Throwable t) {
                Log.e("all1","fauile");
            }
        });

    }

    private void adaptered() {
        mFragmentAdapter = new FragmentAdapter(this.getSupportFragmentManager(), fragmentList);
        vp.setOffscreenPageLimit(4);//ViewPager的缓存为4帧
        vp.setAdapter(mFragmentAdapter);
        vp.setCurrentItem(0);//初始设置ViewPager选中第一帧

        //ViewPager的监听事件
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                /*此方法在页面被选中时调用*/
                changeTextColor(position);
            }

            private void changeTextColor(int position) {
                if (position == 0) {
                    fristimg.setEnabled(true);
                    secondimg.setEnabled(false);
                    threadimg.setEnabled(false);
                    fourthimg.setEnabled(false);
                    fristtext.setTextColor(Color.RED);
                    secondtext.setTextColor(Color.BLACK);;
                    threadtext.setTextColor(Color.BLACK);;
                    fourthtext.setTextColor(Color.BLACK);;
                } else if (position == 1) {
                    fristimg.setEnabled(false);
                    secondimg.setEnabled(true);
                    threadimg.setEnabled(false);
                    fourthimg.setEnabled(false);
                    fristtext.setTextColor(Color.BLACK);
                    secondtext.setTextColor(Color.RED);;
                    threadtext.setTextColor(Color.BLACK);;
                    fourthtext.setTextColor(Color.BLACK);;
                } else if (position == 2) {
                    fristimg.setEnabled(false);
                    secondimg.setEnabled(false);
                    threadimg.setEnabled(true);
                    fourthimg.setEnabled(false);
                    fristtext.setTextColor(Color.BLACK);
                    secondtext.setTextColor(Color.BLACK);
                    threadtext.setTextColor(Color.RED);
                    fourthtext.setTextColor(Color.BLACK);
                } else if (position == 3) {
                    fristimg.setEnabled(false);
                    secondimg.setEnabled(false);
                    threadimg.setEnabled(false);
                    fourthimg.setEnabled(true);
                    fristtext.setTextColor(Color.BLACK);
                    secondtext.setTextColor(Color.BLACK);
                    threadtext.setTextColor(Color.BLACK);
                    fourthtext.setTextColor(Color.RED);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                /*此方法是在状态改变的时候调用，其中arg0这个参数有三种状态（tab16，tab10，tab13）。
                arg0 ==1的时辰默示正在滑动，
                arg0==2的时辰默示滑动完毕了，
                arg0==0的时辰默示什么都没做。*/
            }
        });
    }

    private void init() {
        bt1= (RelativeLayout) findViewById(R.id.tab1);
        bt2= (RelativeLayout) findViewById(R.id.tab2);
        bt3= (RelativeLayout) findViewById(R.id.tab3);
        bt4= (RelativeLayout) findViewById(R.id.tab4);
        content= (LinearLayout) findViewById(R.id.content);
        fristimg= (ImageView) findViewById(R.id.first_image);
        secondimg= (ImageView) findViewById(R.id.second_image);
        threadimg= (ImageView) findViewById(R.id.third_image);
        fourthimg= (ImageView) findViewById(R.id.fourth_image);
        search_button= (ImageView) findViewById(R.id.search_bt_input);
        fristtext= (TextView) findViewById(R.id.first_text);
        secondtext= (TextView) findViewById(R.id.second_text);
        threadtext= (TextView) findViewById(R.id.third_text);
        fourthtext= (TextView) findViewById(R.id.fourth_text);


        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        search_button.setOnClickListener(this);
        //vp= (ViewPager) findViewById(R.id.vp);
        fristFragment=new FragmentTab_frist();
        secondFragment=new FragmentTab_second();
        threadFragment=new FragmentTab_thread();
        frouthFragment=new FragmentTab_fourth();
        fragmentList.add(fristFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(threadFragment);
        fragmentList.add(frouthFragment);
    }
    private void fragmentManagerReplace() {
        fm =getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.content,new FragmentTab_frist());
        // ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onClick(View view) {
        ft = fm.beginTransaction();
        switch (view.getId()){
            case R.id.tab1:
                ft.replace(R.id.content, new FragmentTab_frist());
                fristimg.setEnabled(true);
                secondimg.setEnabled(false);
                threadimg.setEnabled(false);
                fourthimg.setEnabled(false);
                fristtext.setTextColor(Color.RED);
                secondtext.setTextColor(Color.BLACK);
                threadtext.setTextColor(Color.BLACK);
                fourthtext.setTextColor(Color.BLACK);
                break;
            case R.id.tab2:
                ft.replace(R.id.content, new FragmentTab_second());
                fristimg.setEnabled(false);
                secondimg.setEnabled(true);
                threadimg.setEnabled(false);
                fourthimg.setEnabled(false);
                fristtext.setTextColor(Color.BLACK);
                secondtext.setTextColor(Color.RED);;
                threadtext.setTextColor(Color.BLACK);
                fourthtext.setTextColor(Color.BLACK);
                break;
            case R.id.tab3:
                ft.replace(R.id.content, new FragmentTab_thread());
                fristimg.setEnabled(false);
                secondimg.setEnabled(false);
                threadimg.setEnabled(true);
                fourthimg.setEnabled(false);
                fristtext.setTextColor(Color.BLACK);
                secondtext.setTextColor(Color.BLACK);
                threadtext.setTextColor(Color.RED);
                fourthtext.setTextColor(Color.BLACK);
                break;
            case R.id.tab4:
                ft.replace(R.id.content, new FragmentTab_fourth());
                fristimg.setEnabled(false);
                secondimg.setEnabled(false);
                threadimg.setEnabled(false);
                fourthimg.setEnabled(true);
                fristtext.setTextColor(Color.BLACK);
                secondtext.setTextColor(Color.BLACK);
                threadtext.setTextColor(Color.BLACK);
                fourthtext.setTextColor(Color.RED);
                break;
            case R.id.search_bt_input:
               startActivity( new Intent(MainActivity.this, DiscoverView.class));
                break;
        }
        ft.commit();
    }
}
