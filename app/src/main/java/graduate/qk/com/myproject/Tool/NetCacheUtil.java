package graduate.qk.com.myproject.Tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/10/22.
 * 网络缓存工具类
 */

public class NetCacheUtil {
    private LocalCacheUtil localCacheUtil;
    private MemoryCacheUtil memoryCacheUtil;

    private ImageView imageView;
    private String url=null;

    public NetCacheUtil(LocalCacheUtil localCacheUtil,MemoryCacheUtil memoryCacheUtil){
      this.localCacheUtil=localCacheUtil;
        this.memoryCacheUtil=memoryCacheUtil;
    }

    public void getNetChache(ImageView imageView, String url) {
        //异步下载资源
        new BitmapAsyncTask().execute(imageView, url);//这里传入的参数类型不一致，使用用Object
    }

    class BitmapAsyncTask extends AsyncTask<Object, Void, Bitmap> {//这里的参数
        @Override
        protected void onPostExecute(Bitmap aVoid) {//得到图片后，设置imageview
            if (aVoid!=null){
             String urls = (String) imageView.getTag();
                if (url.equals(urls)){//判断当前的url和正在下载的url是否一致
                    imageView.setImageBitmap(aVoid);
                    //写入缓存
                    localCacheUtil.setLocalCache(url,aVoid);
                    //写入内存缓存
                    memoryCacheUtil.setBitmapCache(urls,aVoid);
                }
            }
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(Object... voids) {
             imageView = (ImageView) voids[0];
             url = (String) voids[1];

            imageView.setTag(url);//给当前的imageview打标签

            Bitmap bitmap=download(url);

            return bitmap;
        }
    }

    public Bitmap download(String url){
        HttpURLConnection connection=null;
        try{//这里用的是传统的网络连接获取数据
            connection=(HttpURLConnection)new URL(url).openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(6000);
            connection.setReadTimeout(6000);
            connection.connect();

          int ResponseCode = connection.getResponseCode();
            if (ResponseCode==200){
                Bitmap bitmap= BitmapFactory.decodeStream(connection.getInputStream());//BitmapFactory中的方法，使用一个输入流生成一个Bitmap对象
                return bitmap;
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection!=null){
                connection.disconnect();
            }

        }



        return null;
    }
}
