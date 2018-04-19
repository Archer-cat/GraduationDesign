package graduate.qk.com.myproject.UI.UI.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.Bean.UserBean;
import graduate.qk.com.myproject.MainActivity;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.SPUtil;
import io.vov.vitamio.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Administrator on 2017/9/4.
 */

public class RegisteActivity extends Activity implements View.OnClickListener{
    private boolean flg_register=false;
    private TextView register,register_login;
    private ImageView back_image;
    private RadioGroup radioGroup;
    private RadioButton male,female;
    private EditText userNameET,passWordET,againPasswordET,gameId;
    private UserBean registerBeans=new UserBean();
    private List<UserBean.Results> registerInfos=new ArrayList<>();
    private String userName=null,userPassword=null,againPassword=null,age=null,sex=null,gamer_Id=null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
    }

    private void GetDatas() {
        new Thread() {
            public void run() {
                Looper.prepare();
                try {
                    RegisterInfo(userName,userPassword,age,sex,gamer_Id);//这儿是耗时操作，完成之后更新UI；
                }catch (Exception e){
                    e.printStackTrace();
                }
//                getActivity().runOnUiThread(new Runnable(){
//
//                    @Override
//                    public void run() {
//                        setAdapter(pictureInfoList);//更新UI
//                    }
//
//                });
            }

            private void RegisterInfo(String userName, String userPassword, String age, String sex, String gamer_Id) {
                LemanApi service= HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
                service.register(userName,userPassword,age,sex,gamer_Id).enqueue(new Callback<UserBean>() {
                    @Override
                    public void onResponse(Call<UserBean> call, Response<UserBean> response) {
                        registerBeans=response.body();
                        registerInfos=registerBeans.getResults();
                        Log.e("register的个人注册信息成功="+registerInfos);
                        SetAdapter(registerInfos);
                    }

                    @Override
                    public void onFailure(Call<UserBean> call, Throwable t) {
                        Log.e("register的个人注册信息失败="+t);
                        flg_register=false;
                    }
                });
            }
        }.start();
    }

    private void init() {
        back_image= (ImageView) findViewById(R.id.msg_break);
        userNameET= (EditText) findViewById(R.id.seting_user_text);
        passWordET= (EditText) findViewById(R.id.seting_userPassword_text);
        againPasswordET= (EditText) findViewById(R.id.seting_userPassword_again_text);
        gameId= (EditText) findViewById(R.id.seting_game_text);
        register= (TextView) findViewById(R.id.register_bt);
        register_login= (TextView) findViewById(R.id.register_login_bt);
        radioGroup= (RadioGroup) findViewById(R.id.rg);
        register.setOnClickListener(this);
        register_login.setOnClickListener(this);
        back_image.setOnClickListener(this);
    }
    private void onclick() {
       userName=userNameET.getText().toString().trim();
       userPassword=passWordET.getText().toString().trim();
       againPassword=againPasswordET.getText().toString().trim();
       gamer_Id=gameId.getText().toString().trim();
       radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
               if (i==R.id.male){
                   sex="男";
               }else if(i==R.id.femle){
                   sex="女";
               }
                  sex="第三人者";
           }
       });
        age="18";
        if (userName==null||gamer_Id==null){
            Toast.makeText(getApplication(),"用户名或游戏名不能为空！！！",Toast.LENGTH_LONG);
        }else if (userPassword==null&&userPassword!=againPassword){
            Toast.makeText(getApplication(),"密码错误！！！",Toast.LENGTH_LONG);
        }else {
            GetDatas();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.msg_break:
                RegisteActivity.this.finish();
                break;
            case R.id.register_bt:
                onclick();
                break;
            case R.id.register_login_bt:
             if (flg_register){
                 startActivity(new Intent(getApplication(), MainActivity.class));
             }else {
                 Toast.makeText(getApplication(),"注册失败，无法登陆",Toast.LENGTH_LONG);
             }
                break;
            default:
                break;
        }
    }


    private void SetAdapter(List<UserBean.Results> registerInfos) {//注册成功数据保存
        flg_register=true;
        SPUtil.putString(getApplicationContext(),"customer_Name",registerInfos.get(0).getName());
        SPUtil.putString(getApplicationContext(),"customer_Password",registerInfos.get(0).getPassword());
        SPUtil.putString(getApplicationContext(),"customer_Sex",registerInfos.get(0).getSex());
        SPUtil.putString(getApplicationContext(),"customer_Classes",registerInfos.get(0).getClasses());
        SPUtil.putString(getApplicationContext(),"customer_Age",registerInfos.get(0).getAge());
        SPUtil.putString(getApplicationContext(),"customer_Chat_style",registerInfos.get(0).getChat_style());
        SPUtil.putString(getApplicationContext(),"customer_Frends",registerInfos.get(0).getFrends());
        SPUtil.putString(getApplicationContext(),"customer_Gamer_id",registerInfos.get(0).getGamer_id());
        SPUtil.putString(getApplicationContext(),"customer_Goods",registerInfos.get(0).getGoods());
        SPUtil.putString(getApplicationContext(),"customer_Money",registerInfos.get(0).getMoney());
        SPUtil.putString(getApplicationContext(),"customer_User_msg",registerInfos.get(0).getUser_msg());
        SPUtil.putString(getApplicationContext(),"customer_Head_img",registerInfos.get(0).getHead_img());
        SPUtil.putString(getApplicationContext(),"customer_Others",registerInfos.get(0).getOthers());
    }

}
