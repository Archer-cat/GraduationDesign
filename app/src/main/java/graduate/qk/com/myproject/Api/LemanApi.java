package graduate.qk.com.myproject.Api;

import graduate.qk.com.myproject.Bean.ChatBean;
import graduate.qk.com.myproject.Bean.GameBean;
import graduate.qk.com.myproject.Bean.MoveBean;
import graduate.qk.com.myproject.Bean.NewsDateBean;
import graduate.qk.com.myproject.Bean.PictureBean;
import graduate.qk.com.myproject.Bean.PlayerBean;
import graduate.qk.com.myproject.Bean.SearchMoveBean;
import graduate.qk.com.myproject.Bean.UserBean;
import graduate.qk.com.myproject.Bean.VideoBean;
import graduate.qk.com.myproject.Bean.VideoComicEntity;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Admin on 2018/3/14.
 */

public interface LemanApi {
    @FormUrlEncoded
    @POST("user")
    Call<UserBean> login(@Field("userName") String userName, @Field("userPassword") String userPassword);//用户登录接口

    @FormUrlEncoded
    @POST("register")
    Call<UserBean> register(@Field("name") String userName, @Field("password") String password,@Field("age") String age,@Field("sex") String sex,@Field("gamer_id") String gamer_id);//用户注册接口

    @FormUrlEncoded
    @POST("user")
    Call<UserBean> modified(@Field("userName") String userName, @Field("userPassword") String password);//用户修改接口

    @GET("VideoComic")
    Call<VideoComicEntity> getComic();//漫画接口

    @GET("video")
    Call<VideoBean> getVideos();//小视频接口

    @GET("move")
    Call<MoveBean> getMoves();//电影视频接口

    @GET("picture")
    Call<PictureBean> getPicture();//美图接口

    @GET("newsinfo")
    Call<NewsDateBean> getNews();//新闻接口

    @GET("gameinfo")
    Call<GameBean> getGames();//游戏接口

    @GET("room")
    Call<ChatBean> getRooms();//圈子接口
    @FormUrlEncoded
    @POST("gamer")
    Call<PlayerBean> online_game(@Field("gamerId")String id);//游戏用户接口

    @FormUrlEncoded
    @POST("search")
    Call<SearchMoveBean> lookingfor(@Field("SearchKey")String SearchKey);//搜索资源接口

    @FormUrlEncoded
    @POST("addpictures")
    Call<PictureBean> addpicture(@Field("name")String name,@Field("url")String url,@Field("owner")String owner,@Field("info")String info,@Field("time")String time,@Field("grade")String grade,@Field("style")String style,@Field("others")String others);//搜索资源接口
}
