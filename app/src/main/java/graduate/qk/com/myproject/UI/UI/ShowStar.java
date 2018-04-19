package graduate.qk.com.myproject.UI.UI;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.io.File;

import graduate.qk.com.myproject.Api.LemanApi;
import graduate.qk.com.myproject.Bean.PictureBean;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.HttpUtil;
import graduate.qk.com.myproject.Tool.IP;
import graduate.qk.com.myproject.Tool.SPUtil;
import graduate.qk.com.myproject.Tool.TimeUtil;
import graduate.qk.com.myproject.Tool.UploadFile;
import graduate.qk.com.myproject.Tool.UrlTools;
import io.vov.vitamio.utils.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Admin on 2018/4/16.
 */

public class ShowStar extends Activity implements View.OnClickListener {
    private ImageView bgm;
    private TextView picTextView, voTextView, moTextView;
    private boolean changebgm = false;
    public static final int GET_PHOTO = 1;//相册获取的结果
    public static final int GET_VOICE_CAMERA = 2;//获取录音的结果
    private Uri fileUri = null;
    public File files;
    public String imageFile = null;
    public String videoFile = null;
    public String test = null;
    private String name = null, owner = null, info = null, grade = null, others = null, times = null, style = null;
    public boolean confirm = false;
    private PictureBean AddpictureBeans = new PictureBean();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showstar);
        init();
    }

    private void init() {
        bgm = (ImageView) findViewById(R.id.show_bgm);
        picTextView = (TextView) findViewById(R.id.picture_show);
        voTextView = (TextView) findViewById(R.id.video_show);
        moTextView = (TextView) findViewById(R.id.more_show);
        picTextView.setOnClickListener(this);
        voTextView.setOnClickListener(this);
        moTextView.setOnClickListener(this);
        setBGM();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.picture_show:
                getPhoto();
                break;
            case R.id.video_show:
                getVideo();
                break;
            case R.id.more_show:
                break;
        }
    }

    //设置背景图片
    private void setBGM() {
        Log.i("原始imageFile=", imageFile);
        if (changebgm) {
            Picasso.with(this).load(R.drawable.hd).into((bgm));
        } else {
            Picasso.with(this).load(R.drawable.login1).into((bgm));
        }
    }

    /**
     * 获取录像
     */
    private void getVideo() {
        String style = "mp4";
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("video/*"); //String VIDEO_UNSPECIFIED = "video/*";
        //Intent wrapperIntent = Intent.createChooser(intent, null);
        startActivityForResult(intent, 200);
    }

    /**
     * 获取从相册图片
     */
    private void getPhoto() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case 100://从系统相册返回的
                    if (data != null) {
                        Uri uri = data.getData();
                        Log.i("图片绝对路径uri=", uri);
                        if (uri != null) {
                            String realFilePath = UrlTools.getFilePathByFileUri(this,
                                    uri);
                            String filePath = UrlTools.compressImageUpload(realFilePath);
                            files = new File(filePath);
                            Log.i("fileName:" + files.getName());
                            Log.i("filePath:" + filePath);
                            Bitmap drawable = BitmapFactory.decodeFile(realFilePath);
                            bgm.setImageBitmap(drawable);
                            String imagefile = UploadFile.bitmaptoString(drawable);
                            imageFile = imagefile;
                            Log.i("imageFile:" + imageFile);
                            Log.i("fileIsExists:" + files.exists());
                            confirm = true;
                            SPUtil.putString(getApplication(), "imageFile", imageFile);
                        } else {
                            confirm = false;
                        }
                    }
                    showAlertDialog(imageFile,files.getName());
                    break;
                case 200:
                    if (data != null) {
                        Uri uri = data.getData();
                        Log.i("视频绝对路径uri=", uri);
                        Log.i("视频绝对路径data=", data.toString());
                        if (uri != null) {
                            String realFilePath = UrlTools.getFilePathByFileUri(this,
                                    uri);
                            Log.i("视频绝对路径=", realFilePath);
                            String filePath = UrlTools.compressImageUpload(realFilePath);
                            files = new File(filePath);
                            Log.i("fileName:" + files.getName());
                            Log.i("filePath:" + filePath);
                            String videofile = null;
                            try {
                                videofile = UploadFile.encodeBase64File(realFilePath);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            videoFile = videofile;
                            Log.i("videoFile:" + videoFile);
                            Log.i("fileIsExists:" + files.exists());
                            confirm = true;
                        }
                        return;
                    }
                    break;
            }
        }
    }

    //确认是否上传
    private void comfirmUpload(final String imageFiles ,final  String picName) {

        if (confirm) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("温馨提示").setIcon(R.drawable.ic_action_favorites).setMessage("是否确定上传").setPositiveButton("确定",
                    new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // TODO Auto-generated method stub
                            Toast.makeText(getApplicationContext(), "即将上传到服务器", Toast.LENGTH_LONG);
                            Log.i("即将上传到服务器");
                            String url = imageFiles;
                           // UploadImage(url,picName);
                        }
                    }).setNegativeButton("取消", null);
            //setNeutralButton("", null)
            alert.create();
            alert.show();
        } else {
            Toast.makeText(getApplicationContext(), "选择本地照片", Toast.LENGTH_LONG);
        }
        String size = SPUtil.getString(getApplication(), "imageFile", null);
        Log.i("处理=", size);
    }

    private void UploadImage(String name,String url,String pic_name) {//上传图片
        UserInfo();
        LemanApi service = HttpUtil.getHttpUtil().createService(LemanApi.class, IP.IP);
        service.addpicture(name, url, owner, info, times, grade, pic_name, others).enqueue(new Callback<PictureBean>() {
            @Override
            public void onResponse(Call<PictureBean> call, Response<PictureBean> response) {
                Log.i("上传成功码=", response);
                AddpictureBeans = response.body();
                test = AddpictureBeans.getCode();
                Toast.makeText(getApplicationContext(), "上传成功", Toast.LENGTH_SHORT);
            }

            @Override
            public void onFailure(Call<PictureBean> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "上传失败", Toast.LENGTH_SHORT);
                Log.i("失败错误码=", t);
            }
        });
    }

    private void UserInfo() {
        owner = SPUtil.getString(getApplication(), "customer_Name", "游客");
        times = TimeUtil.GetDate();
        grade = "未评分";
        info = "未评价";
        others = IP.Ip_Leman_pic;
    }

    private void showAlertDialog(final String imageFiles ,final  String picName) {
        final AlertDialog dialog = new AlertDialog.Builder(this).create();
        dialog.setView(LayoutInflater.from(this).inflate(R.layout.alert_dialog, null));
        dialog.show();
        dialog.getWindow().setContentView(R.layout.alert_dialog);
        Button btnPositive = (Button) dialog.findViewById(R.id.ok);
        Button btnNegative = (Button) dialog.findViewById(R.id.no);
        final EditText etContent = (EditText) dialog.findViewById(R.id.upload_name);
        btnPositive.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                String str = etContent.getText().toString();
                if (isNullEmptyBlank(str)) {
                    etContent.setError("输入内如不能为空");
                } else {
                    Toast.makeText(getApplicationContext(), "即将上传到服务器", Toast.LENGTH_LONG);
                    Log.i("即将上传到服务器");
                    String url = imageFiles;
                    name=etContent.getText().toString().trim();
                    UploadImage(name,url,picName);
                    dialog.dismiss();
                }
            }
        });
        btnNegative.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                dialog.dismiss();
            }
        });
    }

    private static boolean isNullEmptyBlank(String str) {
        if (str == null || "".equals(str) || "".equals(str.trim()))
            return true;
        return false;
    }
}
