package graduate.qk.com.myproject.Bean;

/**
 * Created by Administrator on 2017/9/26.
 */

public class VideosEntity {

    /**
     * videoName : fate
     * videoImage : /axis2/photo/test.jpg
     * videoType : 神魔、大作、热血、恋爱
     * videoUrl : http://www.wooyun.site/1987.mp4
     */

    private String videoName;
    private String videoImage;
    private String videoType;
    private String videoUrl;

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getVideoImage() {
        return videoImage;
    }

    public void setVideoImage(String videoImage) {
        this.videoImage = videoImage;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
