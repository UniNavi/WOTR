package com.danilketov.wotr.network;

import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.entity.Clans;
import com.danilketov.wotr.entity.UserInfo;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonParser {

    private static final Gson GSON = new Gson();

//    public ArrayList<Account> getAccount(String jsonString) throws JSONException {
//
//        JSONObject jsonObject = new JSONObject(jsonString);
//
//        JSONArray dataArray = jsonObject.getJSONArray("data");
//        ArrayList<Account> resultAccount = new ArrayList<>();
//
//        for (int i = 0; i < dataArray.length(); i++) {
//            JSONObject repositoryJson = dataArray.getJSONObject(i);
//
//            String nickname = repositoryJson.getString("nickname");
//            int accountId = repositoryJson.getInt("account_id");
//
//            Account account = new Account(accountId, nickname);
//
//            resultAccount.add(account);
//        }
//
//        return resultAccount;
//    }

    public ArrayList<Account> getAccount(String jsonString) {

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        Type accountCollectionType = new TypeToken<ArrayList<Account>>(){}.getType();
        ArrayList<Account> result = GSON.fromJson(dataArray, accountCollectionType);
        return result;
    }

    public ArrayList<Clans> getClans(String jsonString) {

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        Type accountCollectionType = new TypeToken<ArrayList<Account>>(){}.getType();
        ArrayList<Clans> result = GSON.fromJson(dataArray, accountCollectionType);
        return result;
    }

//    public UserInfo getInfo(String jsonString, int stringId) throws JSONException {
//
//        JSONObject jsonObject = new JSONObject(jsonString);
//        JSONObject dataObject = jsonObject.getJSONObject("data");
//
//        String id = String.valueOf(stringId);
//        JSONObject idObject = dataObject.getJSONObject(id);
//
//        JSONObject statObject = idObject.getJSONObject("statistics");
//        JSONObject allObject = statObject.getJSONObject("all");
//
//        // user
//        String nickname = idObject.getString("nickname");
//        int accountId = idObject.getInt("account_id");
//        int globalRating = idObject.getInt("global_rating");
//        int lastBattleTime = idObject.getInt("last_battle_time");
//        // all
//        int maxXp = allObject.getInt("max_xp");
//
//        All all = new All(maxXp);
//        Statistics statistics = new Statistics(all);
//        UserInfo user = new UserInfo(nickname, accountId, globalRating, lastBattleTime, statistics);
//
//        return user;
//    }

    public UserInfo getInfo(String jsonString, int stringId) {
        
        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonElement data = jsonObject.get("data");

        JsonObject idObject = GSON.fromJson(data, JsonObject.class);
        JsonElement idAccount = idObject.get(String.valueOf(stringId));

        return GSON.fromJson(idAccount, UserInfo.class);


    }
}
