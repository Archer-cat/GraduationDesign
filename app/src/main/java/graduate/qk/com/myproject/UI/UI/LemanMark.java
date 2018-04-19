package graduate.qk.com.myproject.UI.UI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import graduate.qk.com.myproject.R;

/**
 * Created by Admin on 2018/4/17.
 */

public class LemanMark extends Activity {
    private WebView webViewMark;
    private ImageView backImg;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leman_mark);
        init();
    }

    private void init() {
        webViewMark= (WebView) findViewById(R.id.mark_web);
        backImg= (ImageView) findViewById(R.id.mark_back);
        backImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(),"正在离开网页...",Toast.LENGTH_SHORT);
                finish();
            }
        });
        WebIndex();
    }
    private void WebIndex(){
        String path_mark="http://www.cycang.com/";
        webViewMark.loadUrl(path_mark);//加载地址
        webViewMark.getSettings().setJavaScriptEnabled(true);//响应式
        webViewMark.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;   //返回true， 立即跳转，返回false,打开网页有延时
            }
        });
    }
}
