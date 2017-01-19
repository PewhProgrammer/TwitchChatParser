package com.apicalls;

import com.Log.Logger;
import com.main.PircBot;
import com.main.StaticData;
import org.json.JSONObject;

import java.io.IOException;
import com.main.HTTPResponse;

/**
 * Created by Thinh-Laptop on 18.01.2017.
 */
public class ChatCall implements APICalls {
    @Override
    public JSONObject execute() {
        PircBot bot = new PircBot("pewhbot");
        // Enable debugging output.
        bot.setVerbose(true);


        // Connect to the IRC server.
        try {
            bot.connect("irc.chat.twitch.tv",6667,"oauth:"+ APIKey.OAuth);
        }catch(Exception e){
            Logger.debug("HTTPResponse bot connection failure");
            System.exit(1);
        }

        bot.joinChannel(APIKey.__CHANNELNAME__);

        // Join the #pircbot channel.
        //bot.joinChannel("#pircbot");

        return null ;
    }
}
