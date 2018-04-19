package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 *
 * @classname LoginEntity
 * @author qinkang
 * @time 2018/3/14 17:47
 * @version 1.0
 */

public class LoginEntity {


    /**
     * resCode : 100
     * resMsg : true
     * list : [{"userHeadImg":"/axis2/photo/star111.png","userPassword":"99","userClass":"平民","userGoods":"贵客赏赐的2两银子","userState":"无等级","userMoney":"白银3两","userName":"王小二","userFlg":"勤劳疯"}]
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
         * userHeadImg : /axis2/photo/star111.png
         * userPassword : 99
         * userClass : 平民
         * userGoods : 贵客赏赐的2两银子
         * userState : 无等级
         * userMoney : 白银3两
         * userName : 王小二
         * userFlg : 勤劳疯
         */

        private String userHeadImg;
        private String userPassword;
        private String userClass;
        private String userGoods;
        private String userState;
        private String userMoney;
        private String userName;
        private String userFlg;

        public String getUserHeadImg() {
            return userHeadImg;
        }

        public void setUserHeadImg(String userHeadImg) {
            this.userHeadImg = userHeadImg;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String getUserClass() {
            return userClass;
        }

        public void setUserClass(String userClass) {
            this.userClass = userClass;
        }

        public String getUserGoods() {
            return userGoods;
        }

        public void setUserGoods(String userGoods) {
            this.userGoods = userGoods;
        }

        public String getUserState() {
            return userState;
        }

        public void setUserState(String userState) {
            this.userState = userState;
        }

        public String getUserMoney() {
            return userMoney;
        }

        public void setUserMoney(String userMoney) {
            this.userMoney = userMoney;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserFlg() {
            return userFlg;
        }

        public void setUserFlg(String userFlg) {
            this.userFlg = userFlg;
        }
    }
}
