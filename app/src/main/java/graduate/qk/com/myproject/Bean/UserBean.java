package graduate.qk.com.myproject.Bean;


import java.util.List;

/**
 *
 * @classname UserBean
 * @author qinkang
 * @time 2018/3/14 17:11
 * @version 1.0
 */
public class UserBean {
   // {"code":"100","msg":"true","list":[{"frends":"12","gamer_id":"1","sex":"女","head_img":"111111111111111111111",
    // "classes":"122","goods":"abc","chat_style":"001","user_msg":"abc","password":"123","money":"10000000","name":"233",
    // "age":"1","others":"null"}]}


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

        private String frends;
        private String gamer_id;
        private String sex;
        private String head_img;
        private String classes;
        private String goods;
        private String chat_style;
        private String user_msg;
        private String password;
        private String money;
        private String name;
        private String age;
        private String others;

        public void setFrends(String frends) {
            this.frends = frends;
        }

        public String getFrends() {
            return frends;
        }

        public void setGamer_id(String gamer_id) {
            this.gamer_id = gamer_id;
        }

        public String getGamer_id() {
            return gamer_id;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getSex() {
            return sex;
        }

        public void setHead_img(String head_img) {
            this.head_img = head_img;
        }

        public String getHead_img() {
            return head_img;
        }

        public void setClasses(String classes) {
            this.classes = classes;
        }

        public String getClasses() {
            return classes;
        }

        public void setGoods(String goods) {
            this.goods = goods;
        }

        public String getGoods() {
            return goods;
        }

        public void setChat_style(String chat_style) {
            this.chat_style = chat_style;
        }

        public String getChat_style() {
            return chat_style;
        }

        public void setUser_msg(String user_msg) {
            this.user_msg = user_msg;
        }

        public String getUser_msg() {
            return user_msg;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getPassword() {
            return password;
        }

        public void setMoney(String money) {
            this.money = money;
        }

        public String getMoney() {
            return money;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public String getAge() {
            return age;
        }

        public void setOthers(String others) {
            this.others = others;
        }

        public String getOthers() {
            return others;
        }

    }
}
