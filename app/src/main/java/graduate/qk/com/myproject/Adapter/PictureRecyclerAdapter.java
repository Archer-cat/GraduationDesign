package graduate.qk.com.myproject.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Bean.PictureBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.ImageUtils;

/**
 * Created by Administrator on 2017/9/22.
 */

public class PictureRecyclerAdapter extends RecyclerView.Adapter<PcViewHolder> implements View.OnClickListener {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NOMAL = 2;
    private List<PictureBean.Results> data;
    private Context context;
    private LayoutInflater inflater;
    private List<Integer> mItemHeight;
    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;
    private View mfooterView;
    private View mheadView;
    private LayoutInflater layoutInflater;

    public PictureRecyclerAdapter(Context context, List<PictureBean.Results> datas) {
        this.context = context;
        this.data = datas;
        this.inflater = LayoutInflater.from(context);
        mItemHeight = new ArrayList<Integer>();
        for (int i = 0; i < datas.size(); i++) {
            mItemHeight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public void onClick(View view) {


    }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener mOnItemLongClickListener) {
        this.mOnItemLongClickListener = mOnItemLongClickListener;
    }

    @Override
    public PcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.pictureitem_recyclerview, parent, false);
        PcViewHolder holder = new PcViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(PcViewHolder holder, final int position) {
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        lp.height = mItemHeight.get(position);
        holder.itemView.setLayoutParams(lp);
        holder.pc_title.setText(data.get(position).getName());
        holder.picture_item.setImageBitmap(ImageUtils.getBitMBitmap(data.get(position).getUrl()));
        holder.users_item.setText((String)data.get(position).getName());
        holder.style.setText(data.get(position).getStyle());
        holder.time.setText(data.get(position).getTime());
        holder.grade.setText(data.get(position).getGrade());
        holder.itemView.setTag(position);
        holder.picture_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击图片" + position, Toast.LENGTH_SHORT).show();
                   // showniew();
            }
        });
        holder.nice_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点赞+1" , Toast.LENGTH_SHORT).show();
                // showniew();
            }
        });
    }

    public void showniew() {


        new AlertDialog.Builder(context)
                .setTitle("BuseAdapter")
                .setMessage("damaob")
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                }).create()
                .show();
    }
    @Override
    public int getItemCount() {
        return data.size();
    }

    public void deleteData(int position) {
        data.remove(position);
        notifyItemInserted(position);
    }

    public void addData(int position) {
        data.add(data.size() + 1, data.get(position));
        notifyItemInserted(position);
    }


}

//由于itemView是item的布局文件，我们需要的是里面的textView，因此利用itemView.findViewById获
//取里面的textView实例，后面通过onBindViewHolder方法能直接填充数据到每一个textView了
class PcViewHolder extends RecyclerView.ViewHolder {
    ImageView picture_item,nice_item;
    TextView pc_title,users_item,grade,time,style;

    public PcViewHolder(View itemView) {
        super(itemView);
        pc_title=(TextView) itemView.findViewById(R.id.title);
        picture_item = (ImageView) itemView.findViewById(R.id.picture_item);
        nice_item = (ImageView) itemView.findViewById(R.id.grade_bt);
        users_item = (TextView) itemView.findViewById(R.id.users_item);
        style=(TextView) itemView.findViewById(R.id.pc_style);
        time=(TextView) itemView.findViewById(R.id.pc_time);
        grade=(TextView) itemView.findViewById(R.id.pc_grade);
    }
}