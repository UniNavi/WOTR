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

    public ArrayList<Account> getAccount(String jsonString) {

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        Type accountCollectionType = new TypeToken<ArrayList<Account>>() {}.getType();
        ArrayList<Account> result = GSON.fromJson(dataArray, accountCollectionType);
        return result;
    }

    public ArrayList<Clans> getClans(String jsonString) {

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonArray dataArray = jsonObject.getAsJsonArray("data");
        Type accountCollectionType = new TypeToken<ArrayList<Account>>() {}.getType();
        ArrayList<Clans> result = GSON.fromJson(dataArray, accountCollectionType);
        return result;
    }

    public UserInfo getInfo(String jsonString, int stringId) {

        JsonObject jsonObject = GSON.fromJson(jsonString, JsonObject.class);
        JsonElement data = jsonObject.get("data");

        JsonObject idObject = GSON.fromJson(data, JsonObject.class);
        JsonElement idAccount = idObject.get(String.valueOf(stringId));

        return GSON.fromJson(idAccount, UserInfo.class);
    }
}
