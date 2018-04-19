package graduate.qk.com.myproject.Api;

import graduate.qk.com.myproject.Bean.VideoComicEntity;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Administrator on 2017/9/26.
 */

public interface VideoComicApi {
    @GET("VideoResServlet")
    Call<VideoComicEntity> getVideos();
}
