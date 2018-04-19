package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 *
 * @classname ChatBean
 * @author qinkang
 * @time 2018/3/14 17:59
 * @version 1.0
 */

public class ChatBean {
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

        private String num;
        private String name;
        private String room_class;
        private String id;
        private String time;
        private String url;
        private String hot_flg;
        private String info;
        public void setNum(String num) {
            this.num = num;
        }
        public String getNum() {
            return num;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }

        public void setRoom_class(String room_class) {
            this.room_class = room_class;
        }
        public String getRoom_class() {
            return room_class;
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

        public void setHot_flg(String hot_flg) {
            this.hot_flg = hot_flg;
        }
        public String getHot_flg() {
            return hot_flg;
        }

        public void setInfo(String info) {
            this.info = info;
        }
        public String getInfo() {
            return info;
        }

    }
}
