package graduate.qk.com.myproject.Tool;

import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/10/22.
 * 自定义三级缓存
 * @param :NetCacheUtil 网络缓存
 * @param :LocalCacheUtil 本地缓存
 * @param :MemoryCacheUtil 内存缓存
 */

public class BitmapCacheUtils {
    private NetCacheUtil netCache;
    private LocalCacheUtil localCache;
    private MemoryCacheUtil memoryCache;

    public BitmapCacheUtils(){
         memoryCache=new MemoryCacheUtil();
        localCache=new LocalCacheUtil();
        netCache=new NetCacheUtil(localCache,memoryCache);
    }

    public void  BitmapShow(ImageView imageView ,String url){
        //内存缓存，优先(内存缓存数据进行两次数据写入，1、在网络缓存的时候写入 2、在本地内存中写入，)
        Bitmap bitmap=memoryCache.getBitmapCache(url);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);
            return;
        }

        //本地缓存，其次
        bitmap=localCache.getLocalCache(url);
        if (bitmap!=null){
            imageView.setImageBitmap(bitmap);

            //在本地缓存的时候就把数据缓存到内存中去
            memoryCache.setBitmapCache(url,bitmap);

            return;
        }

        //网络缓存，最后
        netCache.getNetChache(imageView,url);
    }
}
