import java.util.HashMap;

public class User {

    private static HashMap<String, String> userInfo = new HashMap<>();

    User(String id, String password){
        userInfo.put(id, password);
    }

    protected static HashMap<String,String> getUserInfo(){
        return userInfo;
    }
}
