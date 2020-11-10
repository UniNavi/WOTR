package com.danilketov.wotr.network;

import android.net.Uri;

import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.entity.Clans;
import com.danilketov.wotr.entity.UserInfo;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Singleton
public class HttpClient {

    private static final String ACCOUNT_URL = "https://api.worldoftanks.ru/wot/account/list/";
    private static final String USER_INFO_URL = "https://api.worldoftanks.ru/wot/account/info/";
    private static final String CLANS_URL = "https://api.worldoftanks.ru/wot/clans/list/";
    private static final String APP_ID_PARAM = "application_id";
    private static final String APP_ID = "3ddc13bdd65536a7d527a679e3e03227";
    private static final String SEARCH = "search";
    private static final String ACCOUNT_ID = "account_id";

    private final JsonParser jsonParser = new JsonParser();
    private OkHttpClient client = new OkHttpClient.Builder()
            .build();

    @Inject
    public HttpClient() {

    }

    public List<Account> getUserAccount(String searchNickname) throws IOException, JSONException {

        String requestUrl = Uri.parse(ACCOUNT_URL)
                .buildUpon()
                .appendQueryParameter(APP_ID_PARAM, APP_ID)
                .appendQueryParameter(SEARCH, searchNickname)
                .build()
                .toString();

        String response = getResponse(requestUrl);

        return jsonParser.getAccount(response);

    }

    public List<Clans> getClans(String searchNickname) throws IOException, JSONException {

        String requestUrl = Uri.parse(CLANS_URL)
                .buildUpon()
                .appendQueryParameter(APP_ID_PARAM, APP_ID)
                .appendQueryParameter(SEARCH, searchNickname)
                .build()
                .toString();

        String response = getResponse(requestUrl);

        return jsonParser.getClans(response);

    }

    public UserInfo getUserInfo(int accountId) throws IOException, JSONException {

        String requestUrl = Uri.parse(USER_INFO_URL)
                .buildUpon()
                .appendQueryParameter(APP_ID_PARAM, APP_ID)
                .appendQueryParameter(ACCOUNT_ID, String.valueOf(accountId))
                .build()
                .toString();

        String response = getResponse(requestUrl);

        return jsonParser.getInfo(response, accountId);
    }

    private String getResponse(String requestUrl) throws IOException {

        Request request = new Request.Builder()
                .url(requestUrl)
                .build();

        Response responseRaw = client.newCall(request).execute();
        if (responseRaw.isSuccessful()) {
            String response = responseRaw.body().string();
            return response;
        } else {
            throw new IOException("Запрос произошел с ошибкой");
        }
    }
}
