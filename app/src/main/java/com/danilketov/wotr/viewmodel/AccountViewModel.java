package com.danilketov.wotr.viewmodel;

import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilketov.wotr.App;
import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.repository.DataRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class AccountViewModel extends ViewModel {

    private DataRepository dataRepository = App.getDataRepository();

    private MutableLiveData<List<Account>> account = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNetworkException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isQueryValidationException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public LiveData<List<Account>> getAccount() {
        return account;
    }

    public LiveData<Boolean> isNetworkException() {
        return isNetworkException;
    }

    public LiveData<Boolean> isQueryValidationException() {
        return isQueryValidationException;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public void searchUsers(String query) {
        if (query.isEmpty() || query.contains(" ")) {
            isQueryValidationException.setValue(true);
        } else {
            new GetUserAsyncTask().execute(query);
        }
    }

    private class GetUserAsyncTask extends AsyncTask<String, Integer, List<Account>> {

        @Override
        protected void onPreExecute() {
            isLoading.setValue(true);
        }

        @Override
        protected List<Account> doInBackground(String... queries) {
            try {
                return dataRepository.getAccounts(queries[0]);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Account> result) {
            isLoading.setValue(false);

            if (result != null) {
                account.setValue(result);
            } else {
                isNetworkException.setValue(true);
            }
        }
    }
}
