package com.main;

import com.Log.Logger;
import com.apicalls.APICalls;
import com.datastructure.TwitchUser;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Created by Thinh on 19.06.2016.
 */
public class CallExecutioner {

    public static TwitchUser parseUser(APICalls call) {

        JSONObject profil = call.execute();
        JSONArray userList = profil.getJSONArray("users");

        JSONObject userObject = userList.getJSONObject(0);
        String userName = userObject.getString("name");
        String userBio = userObject.getString("bio");
        String userID = userObject.getString("_id");
        String userType = userObject.getString("type");

        TwitchUser user = new TwitchUser(userName,userBio,userID,userType);
        return user;

    }

    public static void hookStream(APICalls call) {

        call.execute();
    }


}
