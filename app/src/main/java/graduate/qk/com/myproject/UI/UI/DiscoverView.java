package graduate.qk.com.myproject.UI.UI;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import graduate.qk.com.myproject.R;

/**
 *
 * @classname DiscoverView
 * @author qinkang
 * @time 2018/4/16 2:18
 * @version 1.0
 */

public class DiscoverView extends Activity implements View.OnClickListener{
    private EditText editTextSearch;
    private ImageButton  imageButton;
    private ImageView search_back,search_fail,search_bt;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_discover);
        init();
    }
//初始化数据
    private void init() {
        editTextSearch= (EditText) findViewById(R.id.search_edit);
        search_back= (ImageView) findViewById(R.id.search_back);
        search_fail= (ImageView) findViewById(R.id.search_scan);
        search_bt= (ImageView) findViewById(R.id.search_img);
        search_back.setOnClickListener(this);
        search_bt.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.search_img:
             String search_key=editTextSearch.getText().toString().trim();
             Bundle bundle = new Bundle();
             bundle.putString("search_key", search_key);
             Intent intent = new Intent();
             intent.putExtras(bundle);
             intent.setClass(getApplication(), SearchResult.class);
             startActivity(intent);
             break;
         case R.id.search_back:
             finish();
         break;
     }
    }

}
