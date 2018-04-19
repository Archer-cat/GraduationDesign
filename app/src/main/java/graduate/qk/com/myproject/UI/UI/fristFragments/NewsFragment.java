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
import android.widget.ImageView;

import java.util.List;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.BaseFragment;
import graduate.qk.com.myproject.Bean.NewsDateBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerAdapter;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerHolder;
import graduate.qk.com.myproject.UI.UI.LemanMark;
import io.vov.vitamio.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsFragment extends BaseFragment implements View.OnClickListener{
    private RecyclerView list;
    private ImageView bgm_img;
    private View view;
    private NewsDateBean newsBean;
    private BaseRecyclerAdapter<NewsDateBean.Results> adapter;
    private List<NewsDateBean.Results> newsInfoList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frist_news, null);
        return view;
    }

    @Override
    public void fetchData() {
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    NewsInfo();//这儿是耗时操作，完成之后更新UI；
                }catch (Exception e){
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
    }

    private void init() {
        list= (RecyclerView) view.findViewById(R.id.list_views);
        bgm_img= (ImageView) view.findViewById(R.id.no_internet_news);
       // VideoInfo();
    }

    public void NewsInfo(){
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
        service.getNews().enqueue(new Callback<NewsDateBean>() {
            @Override
            public void onResponse(Call<NewsDateBean> call, Response<NewsDateBean> response) {
                Log.i("VideoInfo()的成功返回信息="+response.toString());
                newsBean=response.body();
                Log.i("newsBean="+newsBean.toString());

                newsInfoList=newsBean.getResults();
                SetAdapter(newsInfoList);
            }

            @Override
            public void onFailure(Call<NewsDateBean> call, Throwable t) {
                Log.i("VideoInfo()的失败返回信息="+t);
                bgm_img.setVisibility(View.VISIBLE);
            }
        });
    }
    public void SetAdapter(List<NewsDateBean.Results> datas){
        adapter=new BaseRecyclerAdapter<NewsDateBean.Results>(getContext(),datas,R.layout.news_listitem) {
            @Override
            public void convert(BaseRecyclerHolder holder, NewsDateBean.Results item, int position) {
                if (item.getImg()!=null){
                    holder.setImageByUrl(R.id.news_image,item.getImg());
                    holder.setText(R.id.news_title,item.getName());
                    holder.setText(R.id.news_content,item.getInfo());
                    holder.setText(R.id.owner_url,item.getUrl());
                    holder.setText(R.id.news_time,item.getDate());
                }else {
                    holder.setImageResource(R.id.news_image,R.drawable.ic_movie_pay_order_error);
                    holder.setText(R.id.news_title,"无数据");
                    holder.setText(R.id.news_content,"无数据");
                    holder.setText(R.id.news_time,"无数据");
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
               startActivity(new Intent(getContext(), LemanMark.class));
            }
        });

        list.setLayoutManager( new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        list.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onDestroy() {
        view=null;
        super.onDestroy();
    }
}
