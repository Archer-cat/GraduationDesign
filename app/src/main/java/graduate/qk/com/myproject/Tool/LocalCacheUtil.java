package graduate.qk.com.myproject.Tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2017/10/22.
 * 本地缓存工具类
 */

public class LocalCacheUtil {
    private static final String Path= Environment.getExternalStorageDirectory().getAbsolutePath()+"LocalBitmap";//缓存文件夹
    private File file;

    //写入本地缓存
    public void setLocalCache(String url, Bitmap bitmap){
         //写入本地文件
        file=new File(Path);
        if (!file.exists()||!file.isDirectory()){//文件夹不存在或者file不是文件夹.则创建文件夹
            file.mkdir();
        }
        try {
            File cachefile=new File(file,"url");//创建本地文件，命名为url
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,new FileOutputStream(cachefile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    //得到本地缓存
    public Bitmap getLocalCache(String url){
        File cachefile=new File(Path,"url");
        if (cachefile.exists()){
            //缓存存在
            try {
                Bitmap bitmap= BitmapFactory.decodeStream(new FileInputStream(cachefile));
                return bitmap;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
