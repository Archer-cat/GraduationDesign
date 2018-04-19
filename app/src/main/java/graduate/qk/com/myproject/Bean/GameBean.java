package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 * 
 * @classname GameBean
 * @author qinkang
 * @time 2018/4/1 21:30
 * @version 1.0
 */

public class GameBean {
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
    public class Results {

        private String owner;
        private String name;
        private String style;
        private String time;
        private String startimg;
        private String url;
        private String info;
        public void setOwner(String owner) {
            this.owner = owner;
        }
        public String getOwner() {
            return owner;
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

        public void setTime(String time) {
            this.time = time;
        }
        public String getTime() {
            return time;
        }

        public void setStartimg(String startimg) {
            this.startimg = startimg;
        }
        public String getStartimg() {
            return startimg;
        }

        public void setUrl(String url) {
            this.url = url;
        }
        public String getUrl() {
            return url;
        }

        public void setInfo(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }

    }
}