package com.danilketov.wotr.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.danilketov.wotr.entity.UserInfo;
import com.danilketov.wotr.repository.DataRepository;

import org.json.JSONException;

import java.io.IOException;
import java.util.concurrent.Executor;

import javax.inject.Inject;

public class InfoUserViewModel extends ViewModel {

    private DataRepository dataRepository;
    private Executor executor;

    private MutableLiveData<UserInfo> repository = new MutableLiveData<>();
    private MutableLiveData<Boolean> isNetworkException = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    @Inject
    public InfoUserViewModel(DataRepository dataRepository, Executor executor) {
        this.dataRepository = dataRepository;
        this.executor = executor;
    }

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
        isLoading.setValue(true);
        executor.execute(() -> {
            try {
                UserInfo result = dataRepository.getUserInfo(Integer.parseInt(accountId)); // Почему accountId нужно явно приводить
                repository.postValue(result);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
                isNetworkException.postValue(true);
            } finally {
                isLoading.postValue(false);
            }
        });
    }
}


