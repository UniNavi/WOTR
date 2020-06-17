package com.danilketov.wotr.repository;

import com.danilketov.wotr.App;
import com.danilketov.wotr.db.AppDatabase;
import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.entity.UserInfo;
import com.danilketov.wotr.network.HttpClient;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private HttpClient httpClient = App.getHttpClient();
    private AppDatabase db = App.getAppDatabase();

    public List<Account> getAccounts(String searchNickname) throws IOException, JSONException {
        List<Account> accounts = null;
        try {
            accounts = httpClient.getUserAccount(searchNickname);
            db.getUserDao().insertAccounts(accounts);
        } catch (IOException e) {
            String dbWildCardNickname = "%" + searchNickname + "%";
            accounts = db.getUserDao().getAccounts(dbWildCardNickname);
        }

        if(accounts == null) throw new IOException("Can't find repositories entities in db or api");

        return accounts;
    }

    public UserInfo getUserInfo(int accountId) throws IOException, JSONException {
        UserInfo userInfo = null;
        try {
            userInfo = httpClient.getUserInfo(accountId);
            db.getUserDao().insertUserInfo(userInfo);
        } catch (IOException e) {
            String dbWildCardAccountId = "%" + accountId + "%";
            userInfo = db.getUserDao().getUserInfo(Integer.parseInt(dbWildCardAccountId));
        }

        if (userInfo == null) throw new IOException("Can't find repositories entities in db or api");

        return userInfo;
    }
}
