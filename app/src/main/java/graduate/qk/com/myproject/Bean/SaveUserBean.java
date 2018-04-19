package graduate.qk.com.myproject.Bean;

/**
 * Created by Admin on 2018/3/14.
 */

public class SaveUserBean {
        public String userName;

    @Override
    public String toString() {
        return "SaveUserBean{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserPassword() {
            return userPassword;
        }

        public void setUserPassword(String userPassword) {
            this.userPassword = userPassword;
        }

        public String userPassword;
    }

