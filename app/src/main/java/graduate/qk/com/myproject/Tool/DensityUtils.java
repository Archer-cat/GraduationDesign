package graduate.qk.com.myproject.Tool;

import android.content.Context;

/**
 * Created by Administrator on 2017/9/25.
 * //px和dp相互转换
 //float scale = getResources().getDisplayMetrics().density;
 //这个得到的不应该叫做密度，应该是密度的一个比例。不是真实的屏幕密度，而是相对于某个值的屏幕密度。
 //也可以说是相对密度
 */

public class DensityUtils {

    private static Context mContext;

    public static void init(Context context) {
        mContext = context;
    }

    public static int dp2px(float dpVal) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpVal * density + 0.5f);
    }

    public static int px3dp(float pxVal) {
        float density = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxVal / density + 0.5f);
    }
}
