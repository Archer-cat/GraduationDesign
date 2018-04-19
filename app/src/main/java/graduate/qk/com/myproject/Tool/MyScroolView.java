package graduate.qk.com.myproject.Tool;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Administrator on 2017/9/22.
 */

public class MyScroolView extends ScrollView {
    private ScroolViewListener scrollViewListener=null;
    public MyScroolView(Context context) {
        super(context);
    }

    public MyScroolView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScroolView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyScroolView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if (scrollViewListener!=null){
            scrollViewListener.ScroolViewListener(this,w, h, oldw,oldh);
        }
    }
    public interface ScroolViewListener{
        void ScroolViewListener(MyScroolView scrollView,int w, int h, int oldw, int oldh);
    }
}
