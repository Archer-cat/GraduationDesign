package graduate.qk.com.myproject.UI.UI.fristFragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.BaseFragment;
import graduate.qk.com.myproject.Bean.VideoBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerAdapter;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerHolder;
import graduate.qk.com.myproject.Videotools.VitamioDemo;
import io.vov.vitamio.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/19.
 */

public class VideosFragment extends BaseFragment implements View.OnClickListener {
    private ViewFlipper flippers;
    private ImageButton pre, next;
    private int[] imgList = {R.drawable.hd, R.drawable.ljl, R.drawable.hll, R.drawable.xqj, R.drawable.zl};
    private String[] strList = {"第一张", "第二张", "第三张", "第四张", "第五张"};
    private TextView Imgtext;
    private RecyclerView videoRecyclerview;
    private BaseRecyclerAdapter<VideoBean.Results> videoInfoBaseRecyclerAdapter;
    private VideoBean videoBean;
    private List<VideoBean.Results> videoInfoList = new ArrayList<>();
    private View view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frist_videos, null);
        return view;
    }

    @Override
    public void fetchData() {
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    VideoInfo();//这儿是耗时操作，完成之后更新UI；
                } catch (Exception e) {
                    e.printStackTrace();
                }
//                getActivity().runOnUiThread(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        setAdapter(pictureInfoList);//更新UI
//                    }
//
//                });
            }
        }.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setUserVisibleHint(true);
        init();
        setViewflipper();

    }

    private void init() {
        flippers = (ViewFlipper) view.findViewById(R.id.flipper);
        videoRecyclerview = (RecyclerView) view.findViewById(R.id.recyclerList);
        pre = (ImageButton) view.findViewById(R.id.pre);
        next = (ImageButton) view.findViewById(R.id.next);
        Imgtext = (TextView) view.findViewById(R.id.textStr);
        flippers.setOnClickListener(this);
        pre.setOnClickListener(this);
        next.setOnClickListener(this);
    }

    private void setViewflipper() {
        for (int i = 0; i < imgList.length; i++) {
            flippers.addView(getImg(imgList[i]));

            // Imgtext.setText(strList[i].toString());
            // Imgtext.setText(getStrText(strList[i]).getText());
        }
        flippers.setFlipInterval(3000);
        flippers.startFlipping();
    }

    private ImageView getImg(int imgList) {
        ImageView image = new ImageView(this.getContext());
        Picasso.with(this.getContext()).load(imgList).into(image);
        return image;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pre:
                Toast.makeText(this.getContext(), "查看上一张图片", Toast.LENGTH_LONG).show();
                flippers.setInAnimation(this.getContext(), R.anim.left_in);
                flippers.setOutAnimation(this.getContext(), R.anim.right_out);
                flippers.showPrevious();
                flippers.stopFlipping();
                break;
            case R.id.next:
                Toast.makeText(this.getContext(), "查看下一张图片", Toast.LENGTH_LONG).show();
                flippers.setInAnimation(this.getContext(), R.anim.right_in);
                flippers.setOutAnimation(this.getContext(), R.anim.left_out);
                flippers.showNext();
                flippers.stopFlipping();
                break;
        }
    }


    public void VideoInfo() {
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.Ip_Leman);
        service.getVideos().enqueue(new Callback<VideoBean>() {
            @Override
            public void onResponse(Call<VideoBean> call, Response<VideoBean> response) {
                Log.i("VideoInfo()的成功返回信息=" + response.toString());
                videoBean = response.body();
                videoInfoList = videoBean.getResults();
                SetAdapter(videoInfoList);
            }

            @Override
            public void onFailure(Call<VideoBean> call, Throwable t) {
                Log.i("VideoInfo()的失败返回信息=" + t);
            }
        });
    }

    public void SetAdapter(final List<VideoBean.Results> datas) {
        videoInfoBaseRecyclerAdapter = new BaseRecyclerAdapter<VideoBean.Results>(getContext(), datas, R.layout.gridview_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, VideoBean.Results item, int position) {
                holder.setImageByUrl(R.id.tv_bg, item.getBgm());
                holder.setText(R.id.video_name, item.getName());
                holder.setText(R.id.video_style, item.getOwner());
            }
        };
        videoInfoBaseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(getContext(), "点击了的item=", position).show();
                Log.e("数据Video的值是多少！！！==" + datas.get(position).getUrl());
                Log.e("数据Video的值是多少！！！==" + datas.get(position).getName());
                Bundle bundle = new Bundle();
                bundle.putString("name_play", datas.get(position).getName());
                bundle.putString("path_play", datas.get(position).getUrl());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getContext(), VitamioDemo.class);
                startActivity(intent);
            }
        });
        videoRecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        // View headView= LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_header,null,false);
        // baseRecyclerAdapter.addHeadView(headView);
        videoRecyclerview.setAdapter(videoInfoBaseRecyclerAdapter);
    }
}
