package graduate.qk.com.myproject.UI.UI.chatroom;

import android.content.Context;

import graduate.qk.com.myproject.Tool.SPUtil;

/**
 * Created by Administrator on 2017/10/21.
 * 网络缓存.把json数据缓存起来
 * 第一种方式
 *@param: setCache 写缓存
 *@param :getCache 读取缓存
 *
 * 第二种方式
 * 有时保存到文件中，以url为文件名，以json为文件内容，保存位置在sdcard中
 */

public class CacheUtil {


    //写缓存，以url为标识，json为值，保存在本地，用SharedPreferences
    public static void setCache(Context context,String url , String json){
     SPUtil.putString(context,url,json);
    }

    //读取缓存
    public static String getCache(Context context,String url ){
       return SPUtil.getString(context,url,null);
    }
}
