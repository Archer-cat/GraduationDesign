package graduate.qk.com.myproject.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import graduate.qk.com.myproject.R;

/**
 * Created by Administrator on 2017/9/22.
 */

public class GridViewAdapter extends BaseAdapter implements View.OnClickListener{
    private List<Map<String,Object>> gridviews;
    private Context mContext;
    private LayoutInflater inflater;
    private InnerItemOnclickListener mListener;
    public GridViewAdapter(Context context, List<Map<String,Object>> gridviews){
        this.mContext=context;
        this.gridviews=gridviews;
        this.inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return gridviews.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
      ViewHolder holder=null;
        if (view==null){
            holder=new ViewHolder();
            view=inflater.inflate(R.layout.gridview_item,null);
            holder.img=(ImageView) view.findViewById(R.id.tv_bg);
            holder.name=(TextView) view.findViewById(R.id.video_name);
            holder.type=(TextView) view.findViewById(R.id.video_style);
            view.setTag(holder);
        }else {
            holder=(ViewHolder)view.getTag();
        }
        holder.img.setImageResource((Integer) gridviews.get(position).get("img"));
        holder.name.setText((String) gridviews.get(position).get("name"));
        holder.type.setText((String) gridviews.get(position).get("type"));
        return view;
    }

    class  ViewHolder{
        public ImageView img;
        public TextView name;
        public  TextView type;
    }
    public interface InnerItemOnclickListener {
        void itemClick(View v);
    }

    public void setOnInnerItemOnClickListener(InnerItemOnclickListener listener){
        this.mListener=listener;
    }

    @Override
    public void onClick(View v) {
        mListener.itemClick(v);
    }
}
