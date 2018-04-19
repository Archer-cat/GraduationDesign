package graduate.qk.com.myproject.Bean;

import java.util.List;

/**
 *
 * @classname PlayerBean
 * @author qinkang
 * @time 2018/3/14 17:11
 * @version 1.0
 */

public class PlayerBean {
    private String code;
    private String msg;
    private List<GameInfo> game_info;

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public List<GameInfo> getGame_info() {
        return game_info;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setGame_info(List<GameInfo> game_info) {
        this.game_info = game_info;
    }

    public static class GameInfo{
        private String gamer_id;
        private String game_grade;
        private String game_money;
        private String game_class;
        private String game_state;
        private String game_goods;
        private String game_name;

        public String getGamer_id() {
            return gamer_id;
        }

        public String getGame_grade() {
            return game_grade;
        }

        public String getGame_money() {
            return game_money;
        }

        public String getGame_class() {
            return game_class;
        }

        public String getGame_state() {
            return game_state;
        }

        public String getGame_goods() {
            return game_goods;
        }

        public String getGame_name() {
            return game_name;
        }

        public void setGamer_id(String gamer_id) {
            this.gamer_id = gamer_id;
        }

        public void setGame_grade(String game_grade) {
            this.game_grade = game_grade;
        }

        public void setGame_money(String game_money) {
            this.game_money = game_money;
        }

        public void setGame_class(String game_class) {
            this.game_class = game_class;
        }

        public void setGame_state(String game_state) {
            this.game_state = game_state;
        }

        public void setGame_goods(String game_goods) {
            this.game_goods = game_goods;
        }

        public void setGame_name(String game_name) {
            this.game_name = game_name;
        }
    }

}
