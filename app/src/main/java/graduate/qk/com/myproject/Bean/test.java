package graduate.qk.com.myproject.Bean;

/**
 * Created by Administrator on 2017/9/26.
 */

public class test {

    public String getJsons() {
        return jsons;
    }

    public void setJsons(String jsons) {
        this.jsons = jsons;
    }

    /**
     * userId : 4
     * userName : dd
     * userPassword : 2

     */
  public  String  jsons;

    @Override
    public String toString() {
        return "test{" +
                "jsons='" + jsons + '\'' +
                '}';
    }
}
