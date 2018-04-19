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
 * Created by Admin on 2018/4/16.
 */

public class GameWeb_Index extends Activity {
    private String path=null;
    private WebView webView;
    private ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_webindex);
        init();
    }

    private void init() {
        webView= (WebView) findViewById(R.id.game_web);
        imageView= (ImageView) findViewById(R.id.game_back);
        WebIndex();
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(),"正在离开网页...",Toast.LENGTH_SHORT);
                finish();
            }
        });
    }

    private void WebIndex() {
        Bundle bundle=getIntent().getExtras();
        path=bundle.getString("game_path");
        webView.loadUrl(path);//加载地址
        webView.getSettings().setJavaScriptEnabled(true);//响应式
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;   //返回true， 立即跳转，返回false,打开网页有延时
            }
        });
    }
}
