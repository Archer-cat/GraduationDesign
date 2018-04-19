package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 * @author qinkang
 * @version 1.0
 * @classname MoveBean
 * @time 2018/3/14 17:17
 */

public class MoveBean {
    private String code;
    private String msg;
    private List<Results> Results;

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setResults(List<Results> Results) {
        this.Results = Results;
    }

    public List<Results> getResults() {
        return Results;
    }

    /**
     * Copyright 2018 bejson.com
     */
    /**
     * Copyright 2018 bejson.com
     */
    public class Results {

        private String owner;
        private String grade;
        private String name;
        private String bgm_msg;
        private String style;
        private String id;
        private String time;
        private String state;
        private String url;
        private String bgm_img;
        private String others;
        public void setOwner(String owner) {
            this.owner = owner;
        }
        public String getOwner() {
            return owner;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }
        public String getGrade() {
            return grade;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setBgm_msg(String bgm_msg) {
            this.bgm_msg = bgm_msg;
        }
        public String getBgm_msg() {
            return bgm_msg;
        }

        public void setStyle(String style) {
            this.style = style;
        }
        public String getStyle() {
            return style;
        }

        public void setId(String id) {
            this.id = id;
        }
        public String getId() {
            return id;
        }

        public void setTime(String time) {
            this.time = time;
        }
        public String getTime() {
            return time;
        }

        public void setState(String state) {
            this.state = state;
        }
        public String getState() {
            return state;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setBgm_img(String bgm_img) {
            this.bgm_img = bgm_img;
        }
        public String getBgm_img() {
            return bgm_img;
        }

        public void setOthers(String others) {
            this.others = others;
        }
        public String getOthers() {
            return others;
        }

    }
}
