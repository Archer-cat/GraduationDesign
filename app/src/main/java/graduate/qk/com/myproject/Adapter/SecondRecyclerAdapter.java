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

import java.util.List;
import java.util.Map;

import graduate.qk.com.myproject.R;

/**
 * Created by Administrator on 2017/9/22.
 */

public class SecondRecyclerAdapter extends RecyclerView.Adapter<SecondRecyclerViewHolder> implements View.OnClickListener {
    public static final int TYPE_HEADER = 0;
    public static final int TYPE_FOOTER = 1;
    public static final int TYPE_NOMAL = 2;
    private List<Map<String, Object>> datas;
    private Context context;
    private LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener = null;
    private OnItemLongClickListener mOnItemLongClickListener = null;
    private View mfooterView;
    private View mheadView;
    private LayoutInflater layoutInflater;
    private SecondRecyclerViewHolder holder=null;

    public SecondRecyclerAdapter(Context context, List<Map<String, Object>> datas) {
        this.context = context;
        this.datas = datas;
        this.inflater = LayoutInflater.from(context);
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
    public SecondRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.news_listitem, parent, false);
         holder = new SecondRecyclerViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SecondRecyclerViewHolder holder, final int position) {
        holder.picture.setImageResource((int) datas.get(position).get("picture"));
        holder.Imguers.setText((String) datas.get(position).get("imguser"));
        holder.nice.setText((String) datas.get(position).get("type"));
        holder.itemView.setTag(position);
        holder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "点击图片" + position, Toast.LENGTH_SHORT).show();
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
        return datas.size();
    }

    public void deleteData(int position) {
        datas.remove(position);
        notifyItemInserted(position);
    }

    public void addData(int position) {
        datas.add(datas.size() + 1, datas.get(position));
        notifyItemInserted(position);
    }


}

//由于itemView是item的布局文件，我们需要的是里面的textView，因此利用itemView.findViewById获
//取里面的textView实例，后面通过onBindViewHolder方法能直接填充数据到每一个textView了
class SecondRecyclerViewHolder extends RecyclerView.ViewHolder {
    ImageView picture;
    TextView Imguers, nice;

    public SecondRecyclerViewHolder(View itemView) {
        super(itemView);
        picture = (ImageView) itemView.findViewById(R.id.image);
        nice = (TextView) itemView.findViewById(R.id.title);
        Imguers = (TextView) itemView.findViewById(R.id.content);
    }
}