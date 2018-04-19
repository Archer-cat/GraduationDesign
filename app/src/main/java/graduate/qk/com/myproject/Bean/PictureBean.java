package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 *
 * @classname PictureBean
 * @author qinkang
 * @time 2018/3/14 17:59
 * @version 1.0
 */

public class PictureBean {
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

    public class Results {

        private String owner;
        private String grade;
        private String name;
        private String style;
        private String id;
        private String time;
        private String url;
        private String others;
        private String info;
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

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setOthers(String others) {
            this.others = others;
        }
        public String getOthers() {
            return others;
        }

        public void setInfo(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }

    }
}
