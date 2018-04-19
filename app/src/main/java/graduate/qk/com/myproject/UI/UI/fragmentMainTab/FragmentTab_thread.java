package graduate.qk.com.myproject.UI.UI.fragmentMainTab;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Adapter.RecyclerViewAdapter;
import graduate.qk.com.myproject.Adapter.SecondRecyclerAdapter;
import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.BaseFragment;
import graduate.qk.com.myproject.Bean.ChatBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.MyWaveView;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerAdapter;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerHolder;
import graduate.qk.com.myproject.UI.UI.chatroom.ClientChat;
import io.vov.vitamio.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/8/9.
 */

public class FragmentTab_thread extends BaseFragment implements View.OnClickListener{
    private ChatBean chatBean;
    private List<ChatBean.Results> resultsList=new ArrayList<>();
    private BaseRecyclerAdapter baseRecyclerAdapter,listInfoRecyclerAdapter;
    private View view;
    private ImageView bgm_img;
    private MyWaveView myWaveView;
    private RecyclerView mH_rc,look_rc;
    private RecyclerViewAdapter adapter1;
    private SecondRecyclerAdapter adapter2;
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_thread,null);
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        DataSet();
    }

    private void DataSet() {
        myWaveView.startMove();
    }

    private void init() {
    /*    mRollViewPager= (RollPagerView) view.findViewById(R.id.look_rc);
        //设置播放时间间隔
        mRollViewPager.setPlayDelay(1000);
        //设置透明度
        mRollViewPager.setAnimationDurtion(500);
        //设置适配器
        mRollViewPager.setAdapter(new TestNormalAdapter());*/
        mH_rc= (RecyclerView) view.findViewById(R.id.hrc);
        look_rc=(RecyclerView)view.findViewById(R.id.look_rc);
        myWaveView= (MyWaveView) view.findViewById(R.id.wave_view);
        bgm_img= (ImageView) view.findViewById(R.id.bgm_default);
    }

    @Override
    public void fetchData() {
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    ChatInfo();//这儿是耗时操作，完成之后更新UI；
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

            private void ChatInfo() {
                LemanApi service= HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
                service.getRooms().enqueue(new Callback<ChatBean>() {
                    @Override
                    public void onResponse(Call<ChatBean> call, Response<ChatBean> response) {
                        chatBean=response.body();
                        resultsList=chatBean.getResults();
                        Log.e("交易成功，获取数据="+resultsList.toString());
                        SetAdapter(resultsList);
                        ListSetAdapter(resultsList);
                    }

                    @Override
                    public void onFailure(Call<ChatBean> call, Throwable t) {
                        //bgm_img.setVisibility(View.VISIBLE);
                    }
                });
            }
        }.start();
    }

    private void SetAdapter(List<ChatBean.Results> datas) {
        baseRecyclerAdapter=new BaseRecyclerAdapter<ChatBean.Results>(getContext(),datas,R.layout.thread_hrc_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ChatBean.Results item, int position) {
                if (item.getHot_flg().equals("1")) {
                    holder.setText(R.id.rcicleName,item.getName());
                    holder.setImageByUrl(R.id.rcicleImg,item.getUrl());
                } else {
                    holder.setText(R.id.rcicleName,"程序员天堂");
                    holder.setImageResource(R.id.rcicleImg,R.drawable.biggirl);
                }
            }
        };
        LinearLayoutManager ms= new LinearLayoutManager(this.getContext());
        ms.setOrientation(LinearLayoutManager.HORIZONTAL);// 设置 recyclerview 布局方式为横向布局;
        mH_rc.setLayoutManager(ms);
        mH_rc.setAdapter(baseRecyclerAdapter);
    }

    private void ListSetAdapter(final List<ChatBean.Results> resultsList) {
        listInfoRecyclerAdapter=new BaseRecyclerAdapter<ChatBean.Results>(getContext(),resultsList,R.layout.second_listinfo_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ChatBean.Results item, int position) {
                if (item.getHot_flg().equals("1")){
                    holder.setImageByUrl(R.id.listinfo_item_image,item.getUrl());
                    holder.setText(R.id.listinfo_item_info,item.getInfo());
                    holder.setText(R.id.listinfo_item_owner,item.getName());
                    holder.setText(R.id.listinfo_item_class,item.getRoom_class());
                    holder.setText(R.id.listinfo_item_num,item.getNum());
                    holder.setText(R.id.listinfo_item_time,item.getTime());
                }else {
                  //  listInfoRecyclerAdapter.delete(position);

                    //notifyDataSetChanged();
                }
            }
        };
        listInfoRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                Bundle bundle = new Bundle();
                try{
                    bundle.putString("name_chatroom", resultsList.get(position).getName());
                    Toast.makeText(getContext(),"bundle的数据="+ resultsList.get(position).getUrl(),Toast.LENGTH_SHORT).show();
                }catch (NullPointerException e){
                    e.printStackTrace();
                }
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getContext(), ClientChat.class);
                startActivity(intent);
            }
        });
        look_rc.setLayoutManager( new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        look_rc.setAdapter(listInfoRecyclerAdapter);
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
