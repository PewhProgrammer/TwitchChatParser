package com.apicalls;

import com.Log.Logger;
import com.main.HTTPResponse;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Thinh-Laptop on 19.01.2017.
 */
public class UserCall implements APICalls {
    private final String username ;

    public UserCall(String name){
        username = name;
    }

    @Override
    public JSONObject execute() {
        StringBuilder param = new StringBuilder() ;
        String accepts = "application/vnd.twitchtv.v5+json";
        param.append("https://api.twitch.tv/kraken/users?login=");
        param.append(username);

        JSONObject result = null;

        try{
            result = HTTPResponse.cURLRequest(param.toString(),accepts);
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

        return result ;
    }
}
