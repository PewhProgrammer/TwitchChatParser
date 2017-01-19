package com.main;

import com.Log.Logger;
import com.apicalls.APICalls;
import com.apicalls.ChatCall;
import com.apicalls.UserCall;
import com.datastructure.TwitchUser;
import org.jibble.pircbot.*;
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
                startListening();
                break;
            case GetUser:
                startFetchUser(StaticData.__NAME__);
            default:
                Logger.debug("Unresolved StaticData found: " + StaticData.task);
                break;
        }

    }

    private static void startListening(){

        Logger.info("starting hook on twitch chat...");

        APICalls chat_api = new ChatCall();
        CallExecutioner.hookStream(chat_api);
        Logger.info("Connection successful" );

    }

    private static void startFetchUser(String $name){

        Logger.info("fetching user data...");

        APICalls chat_api = new UserCall($name);
        TwitchUser user = CallExecutioner.parseUser(chat_api);
        Logger.info("Connection successful");

    }



    public static JSONObject cURLRequest(String toConnect,String accepts) throws IOException{
        URL url = new URL(toConnect);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoOutput(true);

        connection.setRequestProperty("Accept", accepts);
        connection.setRequestProperty("Client-ID", "qsqsbll6tdal0fmy0i60m5y23w3y6x");

        connection.setRequestMethod("GET");

        connection.setDoInput(true);
        connection.setDoOutput(false);

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
