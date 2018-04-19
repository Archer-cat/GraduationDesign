package graduate.qk.com.myproject.Tool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/8/28.
 */

public class ImageLoader {

    private ImageView imageview;
    private String mUrl;
    private LruCache<String, Bitmap> mCache;


    /*异步加载(加载图片)的两种方式：多线程（Handler）和AsyncTask
    * */

    //   一、   这里调用的是Handler方法
    private Handler handler = new Handler() {
        @Override
        //这里的if判断语句是对线程池的缓存图片错位的事件进行判定，用获取的TAG是否和mUrl相同时就设置图片的操作
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (imageview.getTag().equals(mUrl)) {
                imageview.setImageBitmap((Bitmap) msg.obj);
            }
        }
    };
    /* 这里需要注意的是这里使用url，是因为缓存机制可能造成listitem的错位，所以就item进行绑定
      String url=mList.get(position).imageUrl;
       viewHolder.image.setTag(url);*/
    public void iamgeshow(ImageView imageView, final String url) {
        //使用成员变量对相应的数据进行缓存，从而避免了由于网络不稳定下载导致的时序的混乱，其功能作用类似Adapter中的ViewHolder方法
        imageview = imageView;
        mUrl = url;
        new Thread() {
            @Override
            public void run() {
                super.run();
                Bitmap bitmap = getBitmap(url);
                Message message = Message.obtain();
                message.obj = bitmap;
                handler.sendMessage(message);
            }
        }.start();
    }

    public Bitmap getBitmap(String urlstring) {
        Bitmap bitmap;
        InputStream is = null;
        try {
            URL url = new URL(urlstring);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            is = new BufferedInputStream(connection.getInputStream());
            bitmap = BitmapFactory.decodeStream(is);
            connection.disconnect();
           Thread.sleep(1000);
            return bitmap;
       } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /*   二、 下面的内容是对图面的加载后不重复加载的方法，提升listview的流畅度，提高了用户体验
    AsyncTask方法对图片的缓存加载
    * */
    public ImageLoader() {
        //获取最大内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cache = maxMemory / 4;
        mCache = new LruCache<String, Bitmap>(cache) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存的时候调用
                return value.getByteCount();//这里是吧Bitmap的大小保存就去
            }
        };
    }

    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCache.put(url, bitmap);
        }
    }

    public Bitmap getBitmapFromCache(String url) {
        return mCache.get(url);
    }
    public void showImageAsyncktask(ImageView imageView, String url) {
        //从缓存中取出图片
        Bitmap bitmap = getBitmapFromCache(url);
        //缓存中没有图片，那么必须下载
        if (bitmap == null) {
            new NewsAsyncktask(imageView, url).execute(url);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }
    private class NewsAsyncktask extends AsyncTask<String, Void, Bitmap> {
        private ImageView mImageView;
        private String mUrl;

        public NewsAsyncktask(ImageView imageView, String url) {
            mImageView = imageView;
            mUrl = url;
        }

        @Override
        //从网络上获取图片
        protected Bitmap doInBackground(String... params) {
            String url = params[0];
            //调用图片的Thread方法
            Bitmap bitmap = getBitmap(url);
            //将不在缓存的图片加入缓存
            if (bitmap != null) {
                addBitmapToCache(url, bitmap);
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            if (mImageView.getTag().equals(mUrl)) {
                mImageView.setImageBitmap(bitmap);
            }
        }
    }

}
