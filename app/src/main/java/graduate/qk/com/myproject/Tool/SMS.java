package graduate.qk.com.myproject.Tool;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import graduate.qk.com.myproject.R;

/**
 * Created by Admin on 2018/2/22.
 * 发送短信，拨号
 */

public class SMS extends Activity implements View.OnClickListener{
    private String number=null;
    private String msg=null;
    private EditText editTextNunmber,sendMsg;
    private Button buttonSend;
    private SmsManager smsManager;
    private ArrayList<String> Callmsg=new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sms_layout);
        init();
    }

    private void init() {
        editTextNunmber= (EditText) findViewById(R.id.sms_number);
        buttonSend= (Button) findViewById(R.id.send_sms);
        sendMsg= (EditText) findViewById(R.id.send_msg);
        buttonSend.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_sms:
                number=editTextNunmber.getText().toString().trim();
                msg=sendMsg.getText().toString().trim();
                if (TextUtils.isEmpty(number)){
                     Toast.makeText(this,"电话号码不能为空！",Toast.LENGTH_SHORT).show();

                }else {
                    smsManager=SmsManager.getDefault();
                    Callmsg =smsManager.divideMessage(msg);
                    for (String str:Callmsg){
                        smsManager.sendTextMessage(number,null,str,null,null);
                    }
                }
                break;
            default:
                  Toast.makeText(this,"即时定位！",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
