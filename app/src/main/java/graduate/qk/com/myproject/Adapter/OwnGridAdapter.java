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
 * Created by Administrator on 2017/9/23.
 */

public class OwnGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<Map<String,Object>> Ownlist;
    private LayoutInflater inflater;
    private int[] ic_img = {R.drawable.ic_index, R.drawable.ic_index, R.drawable.ic_index, R.drawable.ic_index, R.drawable.ic_index, R.drawable.ic_index};
    private String[] ic_text = {"动态", "消息", "等级", "财富", "粉丝", "话题"};
    public  OwnGridAdapter(Context context){
        this.mContext=context;
        inflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return ic_img.length;
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
        ViewHolder viewHolder = null;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.owncenter_item, null);
            viewHolder.mImg = (ImageView) view.findViewById(R.id.ic_launcher);
            viewHolder.mText = (TextView) view.findViewById(R.id.ic_text);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.mImg.setImageResource((int)ic_img[position]);
        viewHolder.mText.setText((String)ic_text[position]);
        return view;
    }

    class ViewHolder{
        ImageView mImg;
        TextView mText;
    }
}
