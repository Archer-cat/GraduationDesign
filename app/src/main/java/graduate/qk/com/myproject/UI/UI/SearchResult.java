package graduate.qk.com.myproject.UI.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.Bean.SearchMoveBean;
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
 * Created by Admin on 2018/4/16.
 */

public class SearchResult extends Activity implements View.OnClickListener{
    private TextView share;
    private ImageView bgmimag;
    private RecyclerView recyclerView;
    private SearchMoveBean searchMoveBean = new SearchMoveBean();
    private List<SearchMoveBean.Results> resultsList = new ArrayList<>();
    private BaseRecyclerAdapter<SearchMoveBean.Results> searchBaseRecyclerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_recyclerview);
        init();
    }

    private void init() {
        share= (TextView) findViewById(R.id.share);
        bgmimag= (ImageView) findViewById(R.id.bgm);
        recyclerView = (RecyclerView) findViewById(R.id.search_recycler);
        Bundle bundle = getIntent().getExtras();
        String SearchKey = bundle.getString("search_key");
        SearchMove(SearchKey);

        bgmimag.setOnClickListener(this);
        share.setOnClickListener(this);
    }

    private void SearchMove(final String Keyword) {
        if (TextUtils.isEmpty(Keyword)) {
            Toast.makeText(SearchResult.this, R.string.search_no_name, Toast.LENGTH_SHORT).show();
        } else {
            new Thread() {
                public void run() {
                    Looper.prepare();
                    try {
                        SearchResults(Keyword);//这儿是耗时操作，完成之后更新UI；
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                runOnUiThread(new Runnable(){
//
//                    @Override
//                    public void run() {
//                    }
//
//                });
                }
            }.start();
        }
    }

    public void SearchResults(final String SearchKey) {
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
        service.lookingfor(SearchKey).enqueue(new Callback<SearchMoveBean>() {
            @Override
            public void onResponse(Call<SearchMoveBean> call, Response<SearchMoveBean> response) {
                Log.e("SearchResults搜索数据获取IP消息++" + IP.IP + SearchKey);
                Log.e("SearchResults搜索数据获取response++" + response);
                searchMoveBean = response.body();
                Log.e("SearchResults搜索数据获取searchMoveBean++" + searchMoveBean);
                if (searchMoveBean.getCode() == "200") {
                    Toast.makeText(getApplication(), "无数据返回！", Toast.LENGTH_SHORT);
                } else {
                    resultsList = searchMoveBean.getResults();
                    String img=resultsList.get(0).getBgm_img();
                    Log.e("img=" + img);
                    SetAdapter(resultsList);
                }
            }
            @Override
            public void onFailure(Call<SearchMoveBean> call, Throwable t) {
                bgmimag.setImageResource(R.drawable.ic_backgroup_image);
                bgmimag.setVisibility(View.VISIBLE);
            }
        });
    }

    private void SetAdapter(final List<SearchMoveBean.Results> resultsList) {
        searchBaseRecyclerAdapter = new BaseRecyclerAdapter<SearchMoveBean.Results>(getApplicationContext(), resultsList, R.layout.sarch_recyclerview_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, SearchMoveBean.Results item, int position) {
                Log.e("search数据Video的值是多少holder！==" + item.getBgm_img());
                holder.setImageByUrl(R.id.image_search, item.getBgm_img());
                holder.setText(R.id.title_search, item.getName());
                holder.setText(R.id.text_search, item.getBgm_msg());
            }
        };
        searchBaseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(getApplicationContext(), "点击了的item=", position).show();
                Log.e("search数据Video的值是多少！！！==" + resultsList.get(position).getUrl());
                Log.e("search数据Video的值是多少！！！==" + resultsList.get(position).getName());
                Bundle bundle = new Bundle();
                bundle.putString("name_play", resultsList.get(position).getName());
                bundle.putString("path_play", resultsList.get(position).getUrl());
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getApplicationContext(), VitamioDemo.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL));
        recyclerView.setAdapter(searchBaseRecyclerAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
            case R.id.share:
                Toast.makeText(getApplication(),"分享开启中。。。",Toast.LENGTH_SHORT);
                break;
        }
    }

    ;
}
