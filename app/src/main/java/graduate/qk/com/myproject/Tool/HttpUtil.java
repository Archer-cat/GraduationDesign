package graduate.qk.com.myproject.Tool;

import android.support.annotation.NonNull;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
        * Created by qk on 2017/12/26.
        * 对网络请求连接进行泛型封装
        */

public class HttpUtil {
    //这里的API_BASE_URL是调试网络连接的测试网络地址
    private static final String API_BASE_URL = "http://www.baidu.com/";
    private static final int DEFAULT_TIMEOUT = 10;
    private static HttpUtil httpUtil = null;
    private Retrofit mRetorfit;

    private HttpUtil() {
    }

    //判断地址是否为空
    public static HttpUtil getHttpUtil() {
        if (httpUtil == null)
            synchronized (HttpUtil.class) {
                if (httpUtil == null)
                    httpUtil = new HttpUtil();
            }
        return httpUtil;
    }

    public <T> T createService(Class<T> serviceClass) {
        return this.createService(serviceClass, null);
        /*定义泛型类回调方法,不管传入参数是否有地址，我都让她运行带有地址参数的方法*/
    }

    public <T> T createService(Class<T> serviceClass, String url) {
        if (url == null) {
            initRetrofit(API_BASE_URL);
        } else {
            initRetrofit(url);
        }

        T service = null;
        try {
            Class<T> aClass = (Class<T>) Class.forName(serviceClass.getName());
            service = mRetorfit.create(aClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return service;
    }

    private void initRetrofit(String BaseUrl) {
        mRetorfit = new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
        /*（1）BaseUrl: 原来的 setEndPoint 方法变成了 BaseUrl
        （2）client 即下面的 OkHttpClient getClient()的返回对象
        （3）addCallAdapterFactory 增加 RxJava 适配器
        （4）addConverterFactory 增加 Gson 转换器*/

    }

    @NonNull
    private OkHttpClient getClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        //connectTimeout 设置超时时间,writeTimeout设置等待超时时间，readTimeout 设置读取超时时间
    }
}
