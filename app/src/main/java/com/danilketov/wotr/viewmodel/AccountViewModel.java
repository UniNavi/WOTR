package com.danilketov.wotr.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.repository.DataRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class AccountViewModel extends ViewModel {

    private DataRepository dataRepository;
    private Executor executor;

    private MutableLiveData<List<Account>> account = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNetworkException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isQueryValidationException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @Inject
    public AccountViewModel(DataRepository dataRepository, Executor executor) {
        this.dataRepository = dataRepository;
        this.executor = executor;
    }

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
            requestUsers(query);
        }
    }

    private void requestUsers(String query) {
        isLoading.setValue(true);
        executor.execute(() -> {
            try {
                List<Account> result = dataRepository.getAccounts(query);
                account.postValue(result);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                isNetworkException.postValue(true);
            } finally {
                isLoading.postValue(false);
            }
        });
    }
}
