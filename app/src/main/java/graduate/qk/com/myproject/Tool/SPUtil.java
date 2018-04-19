package graduate.qk.com.myproject.Tool;

import android.content.Context;
import android.content.SharedPreferences;



/**
 * Created by Administrator on 2017/9/29.
 * Sharepreference的封装工具类
 */

public class SPUtil {
    /*
    *
    * 第一种封装方式，new一个Context对象
    * */
   /*  static SharedPreferences sharedPreferences;

    public  static SPUtil getSharPreference(Context context){
        sharedPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return new SPUtil();
    }

    如果用第一种方式，相应的get、put方法都变得更加简洁
    *  public  static  void putString(Context context,String key,String valuse){
        sharedPreferences.edit().putString(key,valuse).commit();
    }
    */
    /*
    * 第二种封装方式，没有对sharpreference进行再次的封装
    * */
    public static final String NAME="sharedprefrence";


    public  static  void putString(Context context,String key,String valuse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key,valuse).commit();
    }
    public static  String getString(Context context,String key,String defultvaluse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key,defultvaluse);
    }
    public  static  void putLong(Context context,String key,Long valuse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putLong(key, valuse).commit();
    }
    public static  Long getLong(Context context,String key,Long defultvaluse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(key, defultvaluse);
    }
    public static void putInt(Context context,String key,int valuse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(key,valuse).commit();
    }
    public static int getInt(Context context,String key,int defultvaluse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,defultvaluse);
    }
    public static void putBoolean(Context context,String key,Boolean valuse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(key,valuse);
    }
    public  static Boolean getBoolean(Context context,String key,Boolean defultvaluse){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key,defultvaluse);
    }
    public static void delete(Context context,String key){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().remove(key).commit();
    }
    public static void clear(Context context){
        SharedPreferences sharedPreferences=context.getSharedPreferences(NAME,Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }
}
