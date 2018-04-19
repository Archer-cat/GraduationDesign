package graduate.qk.com.myproject.Tool;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * Created by Administrator on 2017/8/21.
 * 这是自定义当ScrollView里面嵌套GridView计算高度并获得焦点，这中方法适用于多嵌套情况，
 * 是解决listview和gridview间嵌套的因ScrollView滑动冲突造成单行，或者无滑动效果的方法。
 * 方法的核心是重定义onMeasure()的方法
 */

public class MyListViewSrcollView extends ListView {
    public MyListViewSrcollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    public MyListViewSrcollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public MyListViewSrcollView(Context context) {
        super(context);
    }

/*
*  核心在此，重写了计算宽高方法
*  */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
       int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
      //  int expandSpecw = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec+10, expandSpec);
    }

    @Override
    protected void measureChildren(int widthMeasureSpec, int heightMeasureSpec) {

        super.measureChildren(widthMeasureSpec, heightMeasureSpec);
    }
}
