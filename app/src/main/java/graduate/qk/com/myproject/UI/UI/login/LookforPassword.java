package graduate.qk.com.myproject.UI.UI.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import graduate.qk.com.myproject.R;


/**
 * Created by Administrator on 2017/9/8.
 */

public class LookforPassword extends Activity implements View.OnClickListener{
    private EditText phonenum,randomnum;
    private TextView applyfor,getpassword_code;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lookfor_password);
        applyfor= (TextView) findViewById(R.id.applyfor_password);
        getpassword_code= (TextView) findViewById(R.id.getpassword_code);

        applyfor.setOnClickListener(this);
        getpassword_code.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.applyfor_password:
                Intent intent=new Intent(LookforPassword.this,LookforInformation.class);
                startActivity(intent);
                break;
        }
    }
}
