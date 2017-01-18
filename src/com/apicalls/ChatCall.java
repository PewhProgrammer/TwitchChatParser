package com.apicalls;

import com.Log.Logger;
import org.json.JSONObject;

import java.io.IOException;
import com.main.HTTPResponse;

/**
 * Created by Thinh-Laptop on 18.01.2017.
 */
public class ChatCall implements APICalls {
    @Override
    public JSONObject execute() {
        StringBuilder param = new StringBuilder() ;

        //TODO: create param string

        JSONObject result = null;

        try{
            result = HTTPResponse.GETRequest(param.toString());
        }catch(IOException e){
            Logger.info("Couldn't attach to twitch chat! ChatCall_Line:24");
            System.exit(1);
        }

        return result ;
    }
}
