package com.main;

import com.apicalls.APICalls;
import com.datastructure.TwitchUser;
import org.json.JSONObject;

/**
 * Created by Thinh on 19.06.2016.
 */
public class CallExecutioner {

    public static TwitchUser parseUser(APICalls call) {

        JSONObject profil = call.execute();
        String name = profil.keys().next();
        JSONObject data = profil.getJSONObject(name);

        TwitchUser user = new TwitchUser();
        return user;

    }

}
