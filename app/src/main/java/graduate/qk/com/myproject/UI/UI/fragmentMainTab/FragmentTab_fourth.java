package graduate.qk.com.myproject.UI.UI.fragmentMainTab;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graduate.qk.com.myproject.Adapter.OwnGridAdapter;
import graduate.qk.com.myproject.BaseFragment;
import graduate.qk.com.myproject.R;
import graduate.qk.com.myproject.Tool.SPUtil;
import graduate.qk.com.myproject.Tool.UploadFile;
import graduate.qk.com.myproject.Tool.UrlTools;
import graduate.qk.com.myproject.UI.UI.login.LoginActivity;


/**
 * Created by Administrator on 2017/8/9.
 */

public class FragmentTab_fourth extends BaseFragment implements View.OnClickListener {
    private View view;
    private GridView center_rc;
    private OwnGridAdapter Rcadapter;
    private List<Map<String, Object>> list = new ArrayList<>();
    private ImageView head,setup,goods;
    private TextView user_name;
    private Button loginbt;
    private final static int RESULT_OK = 0;//从相册选择
    private final static int PHOTO_LOCALIMG = 1;//从相册选择
    private final static int PHOTO_CAMERA = 2;//从相机选择
    private final static int PHOTO_REQUEST_CUT = 3;//照片更改
    private File tempFile;
    private String flg=null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fourth, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
        defaultData();
    }

    private void defaultData() {
        flg=SPUtil.getString(getContext(),"customer_Name",null);
        if(flg==null){
            user_name.setText("请登录···");
        }else {
            user_name.setText(flg);
        }
    }

    private void init() {
        head = (ImageView) view.findViewById(R.id.user_head_img);
        setup= (ImageView) view.findViewById(R.id.setup);
        loginbt= (Button) view.findViewById(R.id.user_loginbt);
        user_name= (TextView) view.findViewById(R.id.user_loginname);
        goods= (ImageView) view.findViewById(R.id.goods);
        goods.setOnClickListener(this);
        head.setOnClickListener(this);
        loginbt.setOnClickListener(this);
        setup.setOnClickListener(this);
        center_rc = (GridView) view.findViewById(R.id.own_center_rc);
        Rcadapter = new OwnGridAdapter(getContext());
        center_rc.setAdapter(Rcadapter);
    }

    @Override
    public void fetchData() {
        super.fetchData();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.user_head_img:
                Toast.makeText(getContext(), "点击了头像", Toast.LENGTH_SHORT).show();
                changeStyle();
                break;
            case R.id.user_loginbt:
                Intent intent = new Intent(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.setup:
                Toast.makeText(getContext(), "等待进入应用设置", Toast.LENGTH_SHORT).show();
                break;
            case R.id.goods:
                Toast.makeText(getContext(),"用户的物品",Toast.LENGTH_SHORT);
                break;
        }
    }


    public void changeStyle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("修改图片");
        String[] ITEMS = {"选择本地照片", "拍照"};
        builder.setNegativeButton("取消", null);
        builder.setItems(ITEMS, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch (i) {
                    case 0:
                        Intent intent1 = new Intent(Intent.ACTION_PICK);
                        intent1.setType("image/*");
                        startActivityForResult(intent1, PHOTO_LOCALIMG);
                        break;
                    case 1:
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        // 判断存储卡是否可以用，可用进行存储
                        if (UploadFile.hasSdcard()) {
                           // tempFile = new File(Environment.getExternalStorageDirectory(),
                              //      "temp_photo.jpg");
                            // 从文件中创建uri
                            Uri uri = UrlTools.getOutputMediaFileUri("jpg");
                            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                        }
                        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CAREMA
                        startActivityForResult(intent, PHOTO_CAMERA);
                        break;

                }
            }
        });
        builder.create().show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            //这里注意的是要分清requestCode，和resultCode，否则不会报错，图片方法也不会调用
            switch (requestCode) {
                case PHOTO_LOCALIMG:
                    if (data != null) {
                        CutImag(data.getData());
                        Toast.makeText(getContext(), "图片剪辑", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "图片显示失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PHOTO_CAMERA:
                    if (UploadFile.hasSdcard()) {
                        CutImag(Uri.fromFile(tempFile));
                    } else {
                        Toast.makeText(getContext(), "为找到存储卡，无法使用相机", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case PHOTO_REQUEST_CUT:
                    if (data != null) {
                        setImage(data);
                    }
                    try {
                        tempFile.delete();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    }

    protected void setImage(Intent data) {
        Bundle extras = data.getExtras();
        if (extras != null) {
            Bitmap bitmap = extras.getParcelable("data");
            head.setImageBitmap(bitmap);
        }
    }

    protected void CutImag(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image*//*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", 600);
        intent.putExtra("outputY", 600);
        intent.putExtra("noFaceDetection", true);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, PHOTO_REQUEST_CUT);
    }

}
