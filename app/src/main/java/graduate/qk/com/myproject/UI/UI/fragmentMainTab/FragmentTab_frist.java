package graduate.qk.com.myproject.UI.UI.fragmentMainTab;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.BaseFragment;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.UI.UI.fristFragments.PictureFragment;
import graduate.qk.com.myproject.UI.UI.fristFragments.ComicFragment;
import graduate.qk.com.myproject.UI.UI.fristFragments.NewsFragment;
import graduate.qk.com.myproject.UI.UI.fristFragments.VideosFragment;

/**
 * Created by Administrator on 2017/8/9.
 */

public class FragmentTab_frist extends BaseFragment implements View.OnClickListener {
    private ViewPager vp;
    private List<Fragment> fragmentList=new ArrayList<Fragment>();
    private ComicFragment fristFragment;
    private PictureFragment secondFragment;
    private NewsFragment threadFragment;
    private VideosFragment frouthFragment;
    private FragmentPagerAdapter mFragmentAdapter;
    private RelativeLayout tb1,tb2,tb3,tb4;
    private TextView fristtext,secondtext,threadtext,fourthtext;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private  float startX;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frist_fragment, null);
        return view;
    }

    @Override
    public void fetchData() {
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUserVisibleHint(true);
        init();
        adaptered();
    }


    private void init() {
        vp= (ViewPager) view.findViewById(R.id.vp);
        tb1 = (RelativeLayout) view.findViewById(R.id.tab1);
        tb2 = (RelativeLayout) view.findViewById(R.id.tab2);
        tb3 = (RelativeLayout) view.findViewById(R.id.tab3);
        tb4 = (RelativeLayout) view.findViewById(R.id.tab4);
        fristtext= (TextView) view.findViewById(R.id.first_text);
        secondtext= (TextView) view.findViewById(R.id.second_text);
        threadtext= (TextView) view.findViewById(R.id.third_text);
        fourthtext= (TextView) view.findViewById(R.id.fourth_text);

        tb1.setOnClickListener(this);
        tb2.setOnClickListener(this);
        tb3.setOnClickListener(this);
        tb4.setOnClickListener(this);
        fristFragment=new ComicFragment();
        secondFragment=new PictureFragment();
        threadFragment=new NewsFragment();
        frouthFragment=new VideosFragment();
        fragmentList.add(fristFragment);
        fragmentList.add(secondFragment);
        fragmentList.add(threadFragment);
        fragmentList.add(frouthFragment);
    }

    private void adaptered() {
        mFragmentAdapter = new FragmentAdapter(this.getChildFragmentManager(), fragmentList);
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
                    fristtext.setTextColor(Color.BLACK);
                    secondtext.setTextColor(Color.WHITE);
                    threadtext.setTextColor(Color.WHITE);
                    fourthtext.setTextColor(Color.WHITE);
                } else if (position == 1) {
                    fristtext.setTextColor(Color.WHITE);
                    secondtext.setTextColor(Color.BLACK);
                    threadtext.setTextColor(Color.WHITE);
                    fourthtext.setTextColor(Color.WHITE);
                } else if (position == 2) {
                    fristtext.setTextColor(Color.WHITE);
                    secondtext.setTextColor(Color.WHITE);
                    threadtext.setTextColor(Color.BLACK);
                    fourthtext.setTextColor(Color.WHITE);
                } else if (position == 3) {
                    fristtext.setTextColor(Color.WHITE);
                    secondtext.setTextColor(Color.WHITE);
                    threadtext.setTextColor(Color.WHITE);
                    fourthtext.setTextColor(Color.BLACK);
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



    private ImageView getImg(int imgList) {
        ImageView image = new ImageView(this.getContext());
        image.setBackgroundResource(imgList);
        Picasso.with(this.getContext()).load(imgList).into(image);
        return image;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tab1:
                vp.setCurrentItem(0,true);
                break;
            case R.id.tab2:
                vp.setCurrentItem(1,true);
                break;
            case R.id.tab3:
                vp.setCurrentItem(2,true);
                break;
            case R.id.tab4:
                vp.setCurrentItem(3,true);
                break;
        }
    }
}
