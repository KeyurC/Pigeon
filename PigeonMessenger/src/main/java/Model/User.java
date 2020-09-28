package Model;

import com.google.gson.JsonObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "User")
public class User {


    @Id
    @Column(name = "userID")
    private int user_id;

    @Column(name = "userName")
    private String user_name;

    @Column(name = "status")
    private Boolean status = false;



    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String usernameToJson() {
        Map<String,String> userAtt = new HashMap<String, String>();
        userAtt.put("user_name",user_name);
        return buildJsonObject(userAtt);
    }

    @Override
    public String toString() {
        Map<String,String> userAtt = new HashMap<String, String>();
        userAtt.put("user_id",Integer.toString(user_id));
        userAtt.put("user_name",user_name);
        userAtt.put("status",Boolean.toString(status));

        return buildJsonObject(userAtt);
    }

    private String buildJsonObject(Map<String,String> userAtt) {
        JsonObject json = new JsonObject();
        userAtt.forEach(json::addProperty);

        return json.toString();
    }
}
