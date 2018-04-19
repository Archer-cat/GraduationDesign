package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 *
 * @classname NewsDateBean
 * @author qinkang
 * @time 2018/4/1 11:27
 * @version 1.0
 */

public class NewsDateBean {
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

            private String date;
            private String img;
            private String name;
            private String url;
            private String info;

            public void setDate(String date) {
                this.date = date;
            }

            public String getDate() {
                return date;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getImg() {
                return img;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getName() {
                return name;
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