package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/26.
 */

public class VideoComicEntity {


    /**
     * resCode : 100
     * resMsg : true
     * list : [{"videoInformation":"菲特，今晚留下来。。","videoUrl":"http://www.wooyun.site/1987.mp4","videoName":"fate/stay at night","videoImg":"/axis2/photo/test.jpg","videoClass":"新刊第一"},{"videoInformation":"动漫版三国演义，独具风格","videoUrl":"http://www.wooyun.site/1987.mp4","videoName":"三国演义","videoImg":"/axis2/photo/03.jpg","videoClass":"实力第一"},{"videoInformation":"生死哲理，椿湫者，鱼焉得之","videoUrl":"http://www.wooyun.site/1987.mp4","videoName":"大鱼海棠","videoImg":"/axis2/photo/09.jpg","videoClass":"国漫第一"}]
     */

    private String resCode;
    private String resMsg;
    private List<ListBean> list;

    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * videoInformation : 菲特，今晚留下来。。
         * videoUrl : http://www.wooyun.site/1987.mp4
         * videoName : fate/stay at night
         * videoImg : /axis2/photo/test.jpg
         * videoClass : 新刊第一
         */

        private String videoInformation;
        private String videoUrl;
        private String videoName;
        private String videoImg;
        private String videoClass;

        public String getVideoInformation() {
            return videoInformation;
        }

        public void setVideoInformation(String videoInformation) {
            this.videoInformation = videoInformation;
        }

        public String getVideoUrl() {
            return videoUrl;
        }

        public void setVideoUrl(String videoUrl) {
            this.videoUrl = videoUrl;
        }

        public String getVideoName() {
            return videoName;
        }

        public void setVideoName(String videoName) {
            this.videoName = videoName;
        }

        public String getVideoImg() {
            return videoImg;
        }

        public void setVideoImg(String videoImg) {
            this.videoImg = videoImg;
        }

        public String getVideoClass() {
            return videoClass;
        }

        public void setVideoClass(String videoClass) {
            this.videoClass = videoClass;
        }
    }
}
