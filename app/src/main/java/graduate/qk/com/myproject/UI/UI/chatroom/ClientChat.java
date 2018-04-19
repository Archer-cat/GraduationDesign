package graduate.qk.com.myproject.UI.UI.chatroom;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.SPUtil;

/**
 * Created by Administrator on 2017/10/17.
 */

/**
 * Created by Administrator on 2017/10/17.
 */

public class ClientChat extends Activity
{
    // 定义界面上的两个文本框
    EditText input;
    TextView chatSno,show;
    // 定义界面上的一个按钮
    Button send;
    Handler handler;
    // 定义与服务器通信的子线程
    ClientThread clientThread;
    String roomNo=null;
    String name=null;
    String contens=null;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);
        init();
        roomNO();
        Newsthread();
    }


    private void init() {
        input = (EditText) findViewById(R.id.input);
        send = (Button) findViewById(R.id.send);
        chatSno=(TextView)findViewById(R.id.chatsno);
        show = (TextView) findViewById(R.id.show);
    }

    private void roomNO() {
        Bundle bundle=getIntent().getExtras();
        roomNo=bundle.getString("name_chatroom");
        chatSno.setText(roomNo);
    }
    private void Newsthread() {
        handler = new Handler() // ②
        {
            @Override
            public void handleMessage(Message msg)
            {
                // 如果消息来自于子线程
                if (msg.what == 0x123)
                {
                    // 将读取的内容追加显示在文本框中
                    show.append("\n" + msg.obj.toString());
                    Log.e("string",msg.obj.toString());
                }
            }
        };
        clientThread = new ClientThread(handler);
        // 客户端启动ClientThread线程创建网络连接、读取来自服务器的数据
        new Thread(clientThread).start(); // ①
        send.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                try
                {
                    // 当用户按下发送按钮后，将用户输入的数据封装成Message
                    // 然后发送给子线程的Handler
                    Message msg = new Message();
                    msg.what = 0x345;
                    name= SPUtil.getString(getApplication(),"customer_Name","游客");
                    contens=input.getText().toString();
                    if (TextUtils.isEmpty(contens)){
                        Toast.makeText(ClientChat.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                    }else {
                        msg.obj = name+":"+input.getText().toString();
                        clientThread.revHandler.sendMessage(msg);
                        // 清空input文本框
                        input.setText("");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }
}