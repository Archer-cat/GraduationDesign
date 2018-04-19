package graduate.qk.com.myproject.UI.UI.login;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.Bean.SaveUserBean;
import graduate.qk.com.myproject.Bean.UserBean;
import graduate.qk.com.myproject.MainActivity;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.SPUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/4.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private TextView bt_register, bt_login, lookfor_password;
    private EditText userName_text, userPassword_text;
    private CheckBox savecheck;
    private String userName, userPassword;
    private ImageView a,c;
    private UserBean userBeans=new UserBean();
    private List<UserBean.Results> userInfos=new ArrayList<>();
    String historyName=null;
    String historyPassword=null;
    SharedPreferences spf;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        init();
        savePasswords();
    }

    private void init() {
        userName_text = (EditText) findViewById(R.id.userName_login_text);
        userPassword_text = (EditText) findViewById(R.id.userPassword_login_text);
        bt_register = (TextView) findViewById(R.id.regist_user);
        bt_login = (TextView) findViewById(R.id.login_user);
        lookfor_password = (TextView) findViewById(R.id.goback_password);
        savecheck = (CheckBox) findViewById(R.id.checkbox_password);
        a= (ImageView) findViewById(R.id.a);
        c= (ImageView) findViewById(R.id.c);
        touchedit();//EditText的点击事件
        bt_login.setOnClickListener(this);
        bt_register.setOnClickListener(this);
        lookfor_password.setOnClickListener(this);
        savecheck.setOnClickListener(this);
    }

    private void touchedit() {
        userName_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                a.setImageResource(R.drawable.a);
                c.setImageResource(R.drawable.c);
                return false;
            }
        });
        userPassword_text.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                a.setImageResource(R.drawable.b);
                c.setImageResource(R.drawable.d);
                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.regist_user:
                Intent intent = new Intent(LoginActivity.this, RegisteActivity.class);
                startActivity(intent);
                break;
            case R.id.login_user:
                userName = userName_text.getText().toString().trim();
                userPassword = userPassword_text.getText().toString().trim();
                iflogin();
                break;
            case R.id.goback_password:
                Intent lookforintent = new Intent(LoginActivity.this, LookforPassword.class);
                startActivity(lookforintent);
                break;
            case R.id.checkbox_password:
                String userName = userName_text.getText().toString().trim();
                String userPassword = userPassword_text.getText().toString().trim();

                if (savecheck.isChecked()) {
                    editor.putString("Name", userName);
                    editor.putString("Password", userPassword);
                    editor.commit();
                    Toast.makeText(this, "已保存", Toast.LENGTH_SHORT).show();
                } else {
                    editor.remove("Name");
                    editor.remove("Password");
                    editor.commit();
                    Toast.makeText(this, "已注销", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void LoginVerify(String userName, String userPassword) {
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
        Log.e("网络数据IP=",  IP.Ip_Leman.toString());
        service.login(userName, userPassword).enqueue(new Callback<UserBean>() {
            @Override
            public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                io.vov.vitamio.utils.Log.e("login搜索数据获取response++" + response);
                Log.e("test1", "成功了");
                System.out.print("网络数据交互成功！！！");
                //String tempResponse=response.body();
              //  Log.e("tempResponse=", tempResponse);
                userBeans=response.body();
            //    Log.e("userBeans=", userBeans.getList());
                userInfos=userBeans.getResults();
                String nx=userInfos.get(0).getName();
                Log.e("nx=", nx.toString());
                Log.e("userInfos=", userInfos.toString());
                if(userBeans==null){
                    Toast.makeText(getApplication(),"用户不存在！",Toast.LENGTH_LONG);
                    historyName= SPUtil.getString(getApplicationContext(),"customer_Name","游客");
                    historyPassword=SPUtil.getString(getApplicationContext(),"customer_Password","123456");
                }else {
                    Log.e("userBeans", userBeans.toString());
                    Log.e("userInfos", userInfos.toString());
                    SPUtil.putString(getApplicationContext(),"customer_Name",userInfos.get(0).getName());
                    SPUtil.putString(getApplicationContext(),"customer_Password",userInfos.get(0).getPassword());
                    SPUtil.putString(getApplicationContext(),"customer_Sex",userInfos.get(0).getSex());
                    SPUtil.putString(getApplicationContext(),"customer_Classes",userInfos.get(0).getClasses());
                    SPUtil.putString(getApplicationContext(),"customer_Age",userInfos.get(0).getAge());
                    SPUtil.putString(getApplicationContext(),"customer_Chat_style",userInfos.get(0).getChat_style());
                    SPUtil.putString(getApplicationContext(),"customer_Frends",userInfos.get(0).getFrends());
                    SPUtil.putString(getApplicationContext(),"customer_Gamer_id",userInfos.get(0).getGamer_id());
                    SPUtil.putString(getApplicationContext(),"customer_Goods",userInfos.get(0).getGoods());
                    SPUtil.putString(getApplicationContext(),"customer_Money",userInfos.get(0).getMoney());
                    SPUtil.putString(getApplicationContext(),"customer_User_msg",userInfos.get(0).getUser_msg());
                    SPUtil.putString(getApplicationContext(),"customer_Head_img",userInfos.get(0).getHead_img());
                    SPUtil.putString(getApplicationContext(),"customer_Others",userInfos.get(0).getOthers());
                }
            }
            @Override
            public void onFailure(Call<UserBean> call, Throwable t) {
                Log.e("test2", "出现错误啦");
                System.out.print("网络数据交互失败！！！");
            }
        });
    }

    public void iflogin() {
        if (TextUtils.isEmpty(userName) && TextUtils.isEmpty(userPassword)) {
            Toast.makeText(LoginActivity.this, "用户名、密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        } else if (userName.toString().length() < 6 && userPassword.toString().length() < 6 && userName.toString().length() > 12 && userPassword.toString().length() > 12) {
            Toast.makeText(LoginActivity.this, "用户名、密码格式不正确", Toast.LENGTH_SHORT).show();
            return;
        }
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    LoginVerify(userName, userPassword);//这儿是耗时操作，完成之后更新UI；
                   Intent logintent = new Intent(LoginActivity.this, MainActivity.class);
                    SaveUserBean bean = new SaveUserBean();
                    bean.setUserName(historyName);
                    bean.setUserPassword(historyPassword);

                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data", bean.toString());
                   logintent.putExtras(bundle);
                    Toast.makeText(LoginActivity.this, historyName, Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, historyPassword, Toast.LENGTH_SHORT).show();
                    startActivity(logintent);
                }catch (Exception e){
                    e.printStackTrace();
                }
//                runOnUiThread(new Runnable(){
//
//                    @Override
//                    public void run() {
//                    }
//
//                });
            }
        }.start();
    }

    /*
    * 对保存用户名、密码进行设置
    * */
    public void savePasswords() {
        spf = getSharedPreferences("Userinfo", MODE_PRIVATE);
        editor = spf.edit();
        String name = spf.getString("Name", userName);
        String password = spf.getString("Password", userPassword);
        if (name != null && password != null) {
            savecheck.setChecked(true);
            userName_text.setText(name);
            userPassword_text.setText(password);
        } else {
            savecheck.setChecked(false);
        }
    }
}
