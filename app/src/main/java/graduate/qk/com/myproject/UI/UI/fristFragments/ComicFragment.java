package graduate.qk.com.myproject.UI.UI.fristFragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
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
import graduate.qk.com.myproject.Bean.MoveBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerAdapter;
import graduate.qk.com.myproject.Tool.RecyclerViewFramWork.BaseRecyclerHolder;
import graduate.qk.com.myproject.Videotools.VitamioDemo;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/19.
 */

public class ComicFragment extends BaseFragment implements View.OnClickListener{
    private View view;
    private ViewFlipper flippers;
    private ImageButton pre, next;
    private int[] imgList = {R.drawable.hd, R.drawable.ljl, R.drawable.hll, R.drawable.xqj, R.drawable.zl};
    private String[] strList = {"第一张", "第二张", "第三张", "第四张", "第五张"};
    private TextView Imgtext;
    private RecyclerView recyclerView;
    private ImageView monthTop,hotTop,newsTop;
    private MoveBean moveBean;
    private List<MoveBean.Results> resultsList=new ArrayList<>();
    private BaseRecyclerAdapter<MoveBean.Results> baseRecyclerAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frist_comic, null);
        return view;
    }

    @Override
    public void fetchData() {
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    getAllMoves();//这儿是耗时操作，完成之后更新UI；
                }catch (Exception e){
                    e.printStackTrace();
                }
//                getActivity().runOnUiThread(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        baseRecyclerAdapter.addAll(resultsList);//更新UI
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
        flippers = (ViewFlipper) view.findViewById(R.id.flipper_comic);
        recyclerView= (RecyclerView) view.findViewById(R.id.comicrecyclerList);
        pre = (ImageButton) view.findViewById(R.id.pre_comic);
        next = (ImageButton) view.findViewById(R.id.next_comic);
        Imgtext = (TextView) view.findViewById(R.id.textStr_comic);
        monthTop= (ImageView) view.findViewById(R.id.month_top1);
        hotTop= (ImageView) view.findViewById(R.id.hot_top1);
        newsTop= (ImageView) view.findViewById(R.id.news_top1);
        flippers.setOnClickListener(this);
        pre.setOnClickListener(this);
        next.setOnClickListener(this);
        monthTop.setOnClickListener(this);
        hotTop.setOnClickListener(this);
        newsTop.setOnClickListener(this);
        //comicgrid.setOnItemClickListener(this);
       // OnclickItem();
    }

    private void getAllMoves() {
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.Ip_Leman);
        service.getMoves().enqueue(new Callback<MoveBean>() {
            @Override
            public void onResponse(Call<MoveBean> call, Response<MoveBean> response) {
                Log.e("testmove1",response.body().toString());
                moveBean=response.body();
                resultsList=moveBean.getResults();
               SetAdapter(resultsList);
            }

            @Override
            public void onFailure(Call<MoveBean> call, Throwable t) {
                Log.e("testmove2",t.toString());
                Log.e("testmove3","o((⊙﹏⊙))o出错哒！");
            }
        });
    }
//
//    private void OnclickItem() {
//        comicgrid.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Bundle bundle = new Bundle();
//                try{
//                    bundle.putInt("photo", Integer.parseInt(listgrid.get(i).get("img").toString()));
//                    bundle.putString("name", listgrid.get(i).get("name").toString());
//                    bundle.putString("type", listgrid.get(i).get("type").toString());
//                    bundle.putString("massage", listgrid.get(i).get("massage").toString());
//                }catch (NullPointerException e){
//                    e.printStackTrace();
//                }
//                Intent intent = new Intent();
//                intent.putExtras(bundle);
//                intent.setClass(getContext(), VitamioDemo.class);
//                startActivity(intent);
//            }
//        });
//    }

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
        //image.setBackgroundResource(imgList);
        Picasso.with(this.getContext()).load(imgList).into(image);
        return image;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pre_comic:
                Toast.makeText(this.getContext(), "查看上一张图片", Toast.LENGTH_LONG).show();
                flippers.setInAnimation(this.getContext(), R.anim.left_in);
                flippers.setOutAnimation(this.getContext(), R.anim.right_out);
                flippers.showPrevious();
                flippers.stopFlipping();
                break;
            case R.id.next_comic:
                Toast.makeText(this.getContext(), "查看下一张图片", Toast.LENGTH_LONG).show();
                flippers.setInAnimation(this.getContext(), R.anim.right_in);
                flippers.setOutAnimation(this.getContext(), R.anim.left_out);
                flippers.showNext();
                flippers.stopFlipping();
                break;
            case R.id.month_top1:
                Toast.makeText(this.getContext(), "刷新资源聚合月榜", Toast.LENGTH_LONG).show();
                    break;
            case R.id.hot_top1:
                Toast.makeText(this.getContext(), "刷新资源聚合热榜", Toast.LENGTH_LONG).show();
                break;
            case R.id.news_top1:
                Toast.makeText(this.getContext(), "刷新资源聚合新榜", Toast.LENGTH_LONG).show();
                break;
        }
    }
    private void SetAdapter(final List<MoveBean.Results> resultsList) {
        baseRecyclerAdapter=new BaseRecyclerAdapter<MoveBean.Results>(getContext(),resultsList,R.layout.gridview_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, MoveBean.Results item, int position) {
                holder.setImageByUrl(R.id.tv_bg,item.getBgm_img());
                holder.setText(R.id.video_name,item.getName());
                holder.setText(R.id.video_style,item.getStyle());
            }
        };
        baseRecyclerAdapter.setOnItemClickListener(new BaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(RecyclerView parent, View view, int position) {
                        Bundle bundle = new Bundle();
                        try{
                            bundle.putString("bgm_play", resultsList.get(position).getBgm_msg());
                            bundle.putString("name_play", resultsList.get(position).getName());
                            bundle.putString("type_play", resultsList.get(position).getStyle());
                            bundle.putString("info_play", resultsList.get(position).getBgm_msg());
                            bundle.putString("path_play", resultsList.get(position).getUrl());
                            Toast.makeText(getContext(),"bundle的数据="+ resultsList.get(position).getUrl(),Toast.LENGTH_SHORT).show();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(getContext(), VitamioDemo.class);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager( new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
       // View headView= LayoutInflater.from(getContext()).inflate(R.layout.recyclerview_header,null,false);
       // baseRecyclerAdapter.addHeadView(headView);
        recyclerView.setAdapter(baseRecyclerAdapter);
    }


}
