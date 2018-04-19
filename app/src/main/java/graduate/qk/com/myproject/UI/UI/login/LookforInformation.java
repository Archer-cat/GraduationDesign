package graduate.qk.com.myproject.UI.UI.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import graduate.qk.com.myproject.MainActivity;
import graduate.qk.com.myproject.R;

/**
 * Created by Administrator on 2017/9/8.
 */

public class LookforInformation extends Activity implements View.OnClickListener{
    private TextView sureOk,change,loginbt;
    private EditText userName_text,userPassword_text,userphone;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookfor_information);
        sureOk= (TextView) findViewById(R.id.sure_ok);
        change= (TextView) findViewById(R.id.change);
        loginbt= (TextView) findViewById(R.id.user_loginbt);
        userName_text= (EditText) findViewById(R.id.userName_text);
        userPassword_text= (EditText) findViewById(R.id.userPassword_text);

        sureOk.setOnClickListener(this);
        change.setOnClickListener(this);
        loginbt.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change:
                Toast.makeText(LookforInformation.this,"用户信息修改成功！！！",Toast.LENGTH_SHORT).show();
                break;
            case R.id.sure_ok:
                Intent okintent=new Intent(LookforInformation.this,LoginActivity.class);
                startActivity(okintent);
                break;
            case R.id.user_loginbt:
                Intent loginIn = new Intent(LookforInformation.this, MainActivity.class);
                startActivity(loginIn);
                break;
        }
    }
}
