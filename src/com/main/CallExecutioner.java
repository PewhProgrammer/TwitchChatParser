package com.main;

import com.Log.Logger;
import com.api.calls.ICalls;
import com.api.calls.MatchCall;
import com.datastructure.Champion;
import com.datastructure.CurrentMatch;
import com.datastructure.Game;
import com.datastructure.Summoner;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

/**
 * Created by Thinh on 19.06.2016.
 */
public class CallExecutioner {

    public static Summoner parseSummoner(ICalls call) {

        JSONObject profil = call.execute();
        String name = profil.keys().next();
        JSONObject data = profil.getJSONObject(name);

        Summoner player = new Summoner(name, data.getInt("id"));

        return player;

    }

    public static CurrentMatch parseCurrentMatch(ICalls call) {


        JSONObject profil = call.execute();
        Set<String> keys = profil.keySet();

        /**
         * gameId , gameType , gameStartTime , mapId , platformId , gameLength , gameMode , gameQueueConfigId
         * bannedChampions , participants ,
         */

        ArrayList<Integer> list = new ArrayList<>();
        list.add(profil.getInt("gameId"));
        list.add(profil.getInt("gameStartTime"));
        list.add(profil.getInt("mapId"));
        list.add(profil.getInt("gameLength"));
        list.add(profil.getInt("gameQueueConfigId"));

        CurrentMatch returnValue = new CurrentMatch(profil.getJSONArray("participants"), list, profil.getString("gameType"),
                profil.getString("gameMode")
        );


        return returnValue;
    }

    public static Set<Champion> parseFreeToPlay(ICalls call) {

        Set<Champion> champs = new HashSet<>();

        JSONObject champions = null;
        champions = call.execute();

        JSONArray allChampions = champions.getJSONArray("champions");
        Iterator<Object> it = allChampions.iterator();

        while (it.hasNext()) {
            JSONObject obj = (JSONObject) it.next();
            champs.add(new Champion(obj.getInt("id"), true));
        }

        return champs;

    }

    public static List<Game> parseRecentMatches(ICalls call) {
        Set<Game> recentGames = new HashSet<>();

        JSONObject request = call.execute();

        JSONArray gamesArray = request.getJSONArray("games");

        Iterator<Object> it = gamesArray.iterator();
        while (it.hasNext()) {
            JSONObject game = (JSONObject) it.next();
            if (!game.getString("subType").equals("RANKED_SOLO_5x5"))
                continue;
            Logger.info(game.toString());
            JSONObject matchDetails = new MatchCall(StaticData.__REGION__, game.getLong("gameId")).execute();
            Logger.info(matchDetails.keySet().toString());
            break;

        }


        return null;
    }

    private Game newGame(JSONObject game, JSONObject stats) {
        return null;
    }


}
