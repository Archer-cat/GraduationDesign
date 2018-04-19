package graduate.qk.com.myproject.UI.UI;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import graduate.qk.com.myproject.R;

/**
 * Created by Admin on 2018/3/25.
 */

public class ListItemInfo extends Activity {
    private String name = null, url = null, style = null,
            time = null, info = null, owner = null, other = null, grade = null;
    private ImageView image, back;
    private TextView nameChat, ownerChat, gradeChat, timeChat, typeChat, msgChat;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_chat_msg);
        init();
        datas();
        setdata();
    }

    //初始化
    private void init() {
        back = (ImageView) findViewById(R.id.picinfo_back);
        image = (ImageView) findViewById(R.id.item_chat_image);
        nameChat = (TextView) findViewById(R.id.item_chat_name);
        ownerChat = (TextView) findViewById(R.id.item_chat_owner_text);
        gradeChat = (TextView) findViewById(R.id.item_chat_grade_text);
        timeChat = (TextView) findViewById(R.id.item_chat_time_text);
        typeChat = (TextView) findViewById(R.id.item_chat_type_text);
        msgChat = (TextView) findViewById(R.id.chat_msg);
    }

    //赋值bundle的传递数据
    private void datas() {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("Name_pic");
        url = bundle.getString("Url_pic");
        style = bundle.getString("Style_pic");
        time = bundle.getString("Time_pic");
        info = bundle.getString("Info_pic");
        owner = bundle.getString("Owner_pic");
        other = bundle.getString("Other_pic");
        grade = bundle.getString("Grade_pic");
    }

    //设置数据
    private void setdata() {
        Picasso.with(this).load(url).into(image);
        nameChat.setText(name);
        ownerChat.setText(owner);
        gradeChat.setText(grade);
        timeChat.setText(time);
        typeChat.setText(style);
        msgChat.setText(info);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplication(), "狠心离去...", Toast.LENGTH_SHORT);
                finish();
            }
        });
    }

}
