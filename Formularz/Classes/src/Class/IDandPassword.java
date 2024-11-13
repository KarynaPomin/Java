package Class;

import java.util.HashMap;

public class IDandPassword {

    HashMap<String, String> loginInfo = new HashMap<String, String>();

    IDandPassword(){

        loginInfo.put("Bro", "pizza");
        loginInfo.put("Brometheus", "PASSWORD");
        loginInfo.put("BroCode", "abc123");
    }

    protected HashMap getLoginInfo(){
        return loginInfo;
    }
}
