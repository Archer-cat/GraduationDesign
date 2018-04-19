package graduate.qk.com.myproject.Tool;

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

/**
 * Created by Administrator on 2017/10/22.
 * 内存缓存
 * @param :SoftReference软引用
 */

public class MemoryCacheUtil {
    private LruCache<String,Bitmap>  mLruCache;
    //内存缓存数据，放入集合
   // private HashMap<String ,SoftReference<Bitmap>> bitmapHashMap=new HashMap<String,SoftReference< Bitmap>>();

    public MemoryCacheUtil(){
       Long maxMemory =  Runtime.getRuntime().maxMemory();//获取虚拟机给的最大内存
        mLruCache=new LruCache<String,Bitmap>((int) (maxMemory/8)){
            //返回单个内存所占大小

            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                //计算图片所占内存大小
              int byteCount =bitmap.getRowBytes()*bitmap.getHeight();
                return byteCount;
            }
        };
    }

    //写缓存
    public void setBitmapCache(String url,Bitmap bitmap){
//        SoftReference<Bitmap> soft=new SoftReference<Bitmap>(bitmap);//引用软引用包装bitmap
//        bitmapHashMap.put(url,soft);
        mLruCache.put(url,bitmap);
    }

    //读缓存
    public Bitmap getBitmapCache(String url){
//        SoftReference<Bitmap> soft=bitmapHashMap.get(url);
//        if (soft!=null){
//            Bitmap bitmap=soft.get();//从软引用中取出当前bitmap对象
//            return bitmap;
//        }
//      return null;
        return mLruCache.get(url);
    }
}
