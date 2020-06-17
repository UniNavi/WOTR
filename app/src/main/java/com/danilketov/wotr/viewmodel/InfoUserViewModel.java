package com.danilketov.wotr.viewmodel;

import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilketov.wotr.App;
import com.danilketov.wotr.R;
import com.danilketov.wotr.entity.UserInfo;
import com.danilketov.wotr.fragment.InfoUserFragment;
import com.danilketov.wotr.repository.DataRepository;

import org.json.JSONException;

import java.io.IOException;

public class InfoUserViewModel extends ViewModel {

    private DataRepository dataRepository = App.getDataRepository();

    private MutableLiveData<UserInfo> repository = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNetworkException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();


    public LiveData<UserInfo> getRepository() {
        return repository;
    }

    public LiveData<Boolean> isException() {
        return isNetworkException;
    }

    public LiveData<Boolean> isLoading() {
        return isLoading;
    }

    public void updateData(String accountId) {
        new GetUserInfoAsyncTask().execute(Integer.valueOf(accountId));
    }

    private class GetUserInfoAsyncTask extends AsyncTask<Integer, Void, UserInfo> {

        @Override
        protected void onPreExecute() {
            isLoading.setValue(true);
        }

        @Override
        protected UserInfo doInBackground(Integer... params) {
            int accountId = params[0];
            try {
                return dataRepository.getUserInfo(accountId);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(UserInfo userInfo) {
            isLoading.setValue(false);

            if (userInfo !=null) {
                repository.setValue(userInfo);
            } else {
                isNetworkException.setValue(true);
            }
        }
    }

}
