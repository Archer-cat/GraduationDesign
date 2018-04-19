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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.BaseFragment;
import graduate.qk.com.myproject.Bean.PictureBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.Giftools.GifPlay;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerAdapter;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerHolder;
import graduate.qk.com.myproject.Tool.SPUtil;
import graduate.qk.com.myproject.UI.UI.ListItemInfo;
import graduate.qk.com.myproject.UI.UI.ShowStar;
import io.vov.vitamio.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 *
 * @classname PictureFragment
 * @author qinkang
 * @time 2018/3/15 13:05
 * @version 1.0
 */

public class PictureFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private Button add,like;
    private RecyclerView recyclerView;
    private ImageView no_internet_bgm;
    private BaseRecyclerAdapter<PictureBean.Results> adapter;
    private PictureBean pictureBean=new PictureBean();
    private List<PictureBean.Results> pictureInfoList=new ArrayList<>();


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
       view= inflater.inflate(R.layout.frist_picture,null);
        return view;
    }
    @SuppressWarnings("unchecked")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void init() {
        recyclerView= (RecyclerView) view.findViewById(R.id.main_pc_recycler);
        no_internet_bgm= (ImageView) view.findViewById(R.id.no_internet_picture);
        add= (Button) view.findViewById(R.id.add);
        like= (Button) view.findViewById(R.id.like);
        add.setOnClickListener(this);
        like.setOnClickListener(this);

       // recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL));
      //  recyclerView.notify();
       // new Thread(new Mythread()).start();//开启子线程网络数据交互
    }

    @Override
    public void fetchData() {
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    PictureInfo();//这儿是耗时操作，完成之后更新UI；
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.add:
                Intent gif_intent=new Intent();
                gif_intent.setClass(getActivity(),GifPlay.class);
                startActivity(gif_intent);
                break;
            case R.id.like:
                Toast.makeText(getContext(),"王的宝库",Toast.LENGTH_SHORT);
                startActivity(new Intent(getActivity(), ShowStar.class));
                break;
        }
    }
    /*网络图片资源*/
    public void PictureInfo(){
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
        service.getPicture().enqueue(new Callback<PictureBean>() {
            @Override
            public void onResponse(Call<PictureBean> call, Response<PictureBean> response) {
                Log.i("PictureInfo()返回成功的response信息="+response.toString());
//                Toast.makeText(getContext(),"网络数据连接中...",Toast.LENGTH_SHORT);
                pictureBean=response.body();
                Log.i("PictureInfo()返回成功的pictureBean信息="+pictureBean);
                pictureInfoList=pictureBean.getResults();
                String nx=pictureInfoList.get(0).getName();
                android.util.Log.e("nx=", nx.toString());
                Log.i("PictureInfo()返回成功的pictureInfoList信息="+pictureInfoList);
//                if (pictureInfoList==null){
//                    no_internet_bgm.setVisibility(View.VISIBLE);
//                }
                setAdapter(pictureInfoList);//更新UI
            }

            @Override
            public void onFailure(Call<PictureBean> call, Throwable t) {
                Log.i("PictureInfo()返回失败的t信息="+t.toString());
              //  Toast.makeText(getContext(),"网络连接失败！！！",Toast.LENGTH_SHORT);
                no_internet_bgm.setVisibility(View.VISIBLE);
            }
        });
    }
   public void setAdapter(final List<PictureBean.Results> datas){
       //组件设置
       adapter=new BaseRecyclerAdapter<PictureBean.Results>(this.getContext(),datas,R.layout.list_item) {
           @Override
           public void convert(BaseRecyclerHolder holder, PictureBean.Results item, int position) {
               //holder.setText(R.id.list_item_name,item.getName());
               if (position >datas.size()){
                   holder.getView(R.layout.progress_item);
               }else {
                   holder.setImageByUrl(R.id.list_item_image,item.getUrl());
                   holder.setText(R.id.list_item_text,item.getName());
                   holder.setText(R.id.list_item_owner,item.getOwner());
                   holder.setText(R.id.list_item_grade,item.getGrade());
                   holder.setText(R.id.list_item_time,item.getTime());
                   holder.setText(R.id.list_item_type,item.getStyle());
                   holder.setText(R.id.list_item_other,item.getOthers());
                   //SPUtil.putString(getContext(),"position",position);
               }
           }
       };
       //点击触发
       adapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(RecyclerView parent, View view, int position) {
               SPUtil.putString(getContext(),"URL",datas.get(position).getName());
               Log.e("跳转数据====",datas.get(position).getName());
               Log.e("跳转数据,SP过后====",SPUtil.getString(getContext(),"pc_name",datas.get(position).getName()));
               Bundle bundle = new Bundle();
               try{
                   bundle.putString("Name_pic", datas.get(position).getName());
                   bundle.putString("Style_pic", datas.get(position).getStyle());
                   bundle.putString("Time_pic", datas.get(position).getTime());
                   bundle.putString("Url_pic", datas.get(position).getUrl());
                   bundle.putString("Info_pic", datas.get(position).getInfo());
                   bundle.putString("Grade_pic", datas.get(position).getGrade());
                   bundle.putString("Owner_pic", datas.get(position).getOwner());
                   bundle.putString("Other_pic", datas.get(position).getOthers());
               }catch (NullPointerException e){
                   e.printStackTrace();
               }
               Intent intent = new Intent();
               intent.putExtras(bundle);
               intent.setClass(getContext(), ListItemInfo.class);
               startActivity(intent);
           }
       });

       //长按删除
       adapter.setOnItemLongClickListener(new BaseRecyclerAdapter.OnItemLongClickListener() {
           @Override
           public boolean onItemLongClick(RecyclerView parent, View view, int position) {
               adapter.delete(position);
               return true;
           }
       });
      // LinearLayoutManager linearM=new LinearLayoutManager(this.getContext(),LinearLayoutManager.VERTICAL,false);
       recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
       recyclerView.setAdapter(adapter);
       adapter.notifyDataSetChanged();
   }

}
