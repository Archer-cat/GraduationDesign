package graduate.qk.com.myproject.UI.UI.fragmentMainTab;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.BaseFragment;
import graduate.qk.com.myproject.Bean.GameBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerAdapter;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerHolder;
import graduate.qk.com.myproject.Tool.SPUtil;
import graduate.qk.com.myproject.Tool.StaticToastString;
import graduate.qk.com.myproject.UI.UI.GameWeb_Index;
import io.vov.vitamio.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

;


/**
 * Created by Administrator on 2017/8/9.
 */

@SuppressLint("ValidFragment")
public class FragmentTab_second extends BaseFragment {
    private ImageView bg_img;
    private View view;
    private GameBean gameBean;
    private List<GameBean.Results> gameInfo = new ArrayList<>();
    private BaseRecyclerAdapter<GameBean.Results> adapter;
    private RecyclerView mRecylerView;
    private Toolbar toolbar;
    private String customer_Gamer_id = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        view = inflater.inflate(R.layout.layout_gamedisplay, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getActionBar();
        //datas=getListgrid();
        init();
    }

    private void init() {
        mRecylerView = (RecyclerView) view.findViewById(R.id.list_games);
        bg_img = (ImageView) view.findViewById(R.id.no_internet);
       // setHasOptionsMenu(true);
        UserId();
    }

    private void UserId() {
        customer_Gamer_id = SPUtil.getString(getContext(), "customer_Gamer_id", "游客");
    }


    @Override
    public void fetchData() {
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    Toast.makeText(getContext(), StaticToastString.game_login, Toast.LENGTH_SHORT);
                    GameInfo();//这儿是耗时操作，完成之后更新UI；
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


    private void GameInfo() {
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
        service.getGames().enqueue(new Callback<GameBean>() {
            @Override
            public void onResponse(Call<GameBean> call, Response<GameBean> response) {
                gameBean = response.body();
                gameInfo = gameBean.getResults();
                Log.e("返回数据gameInfo=" + gameInfo.toString());
                SetAdapter(gameInfo);
            }

            @Override
            public void onFailure(Call<GameBean> call, Throwable t) {
               // bg_img.setVisibility(View.VISIBLE);
            }
        });
    }

    public void SetAdapter(final List<GameBean.Results> datas) {
        adapter = new BaseRecyclerAdapter<GameBean.Results>(getContext(), datas, R.layout.layout_gamedisplay_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, GameBean.Results item, int position) {
                // holder.setText(R.id., "测试数据");
                if (item.getUrl() != null) {
                    holder.setImageByUrl(R.id.gameds_img, item.getStartimg());
                    holder.setText(R.id.gameds_name, item.getName());
                    holder.setText(R.id.gameds_owner, item.getOwner());
                    holder.setText(R.id.gameds_time, item.getTime());
                    holder.setText(R.id.gameds_info, item.getInfo());
                } else {
                    holder.setImageResource(R.id.gameds_img, R.drawable.ic_movie_pay_order_error);
                    holder.setText(R.id.gameds_name, "无数据");
                    holder.setText(R.id.gameds_owner, "无数据");
                    holder.setText(R.id.gameds_time, "无数据");
                    holder.setText(R.id.gameds_info, "无数据");
                }
            }
        };
        adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Toast.makeText(getContext(), "点击了" + position + "个item", Toast.LENGTH_SHORT);
                Bundle bundle = new Bundle();
                try{
                    bundle.putString("game_path", datas.get(position).getUrl());
                    Toast.makeText(getContext(),"bundle的数据="+ datas.get(position).getUrl(),Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getContext(), GameWeb_Index.class);
                startActivity(intent);
            }
        });
        mRecylerView.setAdapter(adapter);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        view = null;
        super.onDestroy();
    }
}
