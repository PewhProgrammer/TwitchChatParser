package com.main;

import com.Log.Logger;
import com.apicalls.APICalls;
import com.apicalls.ChatCall;
import com.datastructure.TwitchUser;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Thinh on 19.06.2016.
 */
public class HTTPResponse {


    /**
     * processes the JSON Response
     * @return
     * @throws IOException
     */
    public static void processHTTP() throws IOException {

        /**
         * display GUI Information in the methods
         */

        Logger.setLevel(Logger.Level.INFO);

        switch(StaticData.task){
            case ParseChat:
                startListening(StaticData.__REGION__);
                break;
            default:
                Logger.debug("Unresolved StaticData found: " + StaticData.task);
                break;
        }

    }

    private static void startListening(String $region){

        Logger.info("retrieve freeToPlay rotation this week...");

        APICalls chat_api = new ChatCall();
        TwitchUser champs = CallExecutioner.parseUser(chat_api);

        //display champs in console
        Logger.info(champs.toString());
    }



    public static JSONObject GETRequest(String param) throws IOException{
        URL url = new URL(param.toString());
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();

        // read the contents using an InputStreamReader
        InputStream stream = connection.getInputStream();
        JSONObject profil = new JSONObject(HTTPResponse.convertStreamToString(stream));

        return profil ;

    }

    /**
     * Little trick with Scanner to convert Stream into String via Scanner as
     * it takes the whole line as a token
     * @param is
     * @return
     */
    public static String convertStreamToString(InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
