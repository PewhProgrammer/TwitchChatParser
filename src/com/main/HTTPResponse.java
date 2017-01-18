package com.main;

import com.Log.Logger;
import com.api.calls.*;
import com.datastructure.Champion;
import com.datastructure.CurrentMatch;
import com.datastructure.Game;
import com.datastructure.Summoner;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Set;

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
            case FreeToPlay:
                freeToPlay(StaticData.__REGION__);
                break;
            case CurrentMatch:
                currentMatch(StaticData.__NAME__,StaticData.__REGION__);
                break;
            case AnalyseRecentMatches:
                analyseData(StaticData.__NAME__,StaticData.__REGION__);
                break;
            default:
                Logger.debug("Unresolved StaticData found: " + StaticData.task);
                break;
        }

    }

    private static void freeToPlay(String $region){

        Logger.info("retrieve freeToPlay rotation this week...");

        ICalls freeToPlayCall = new FreeToPlayCall($region);
        Set<Champion> champs = CallExecutioner.parseFreeToPlay(freeToPlayCall);

        //display champs in console
        Logger.info(champs.toString());
    }

    private static void currentMatch(String $name, String $region){

        Logger.info("initializing Player " + $name +"(" + $region + ") ...");

        ICalls summonerCall = new SummonerCall($name,$region) ;
        Summoner player = CallExecutioner.parseSummoner(summonerCall) ;
        ICalls currentMatchCall = new CurrentMatchCall("" + player.getID(), $region, "EUW1");

        CurrentMatch cMatch = CallExecutioner.parseCurrentMatch(currentMatchCall) ;

        StringBuilder buildRed = new StringBuilder();
        StringBuilder buildBlue = new StringBuilder();

        for(Summoner summ : cMatch.getSummoners()){
            if(summ.getTeam().equals(CurrentMatch.GameSide.Red))
                    buildRed.append(summ).append("("+summ.getChamp().toString()+")").append(", ");
            else    buildBlue.append(summ).append("("+summ.getChamp().toString()+")").append(", ") ;
        }

        Logger.info("Team Blue: " + buildBlue.toString());
        Logger.info("Team Red : " + buildRed.toString());

    }

    private static void analyseData(String $name,String $region){
        Logger.info("analysing Player " + $name +"(" + $region + ") recent matches ...");

        ICalls summonerCall = new SummonerCall($name,$region) ;
        Summoner player = CallExecutioner.parseSummoner(summonerCall) ;

        ICalls recent = new RecentGamesCall($region,player.getID()) ;
        List<Game> games = CallExecutioner.parseRecentMatches(recent);



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
