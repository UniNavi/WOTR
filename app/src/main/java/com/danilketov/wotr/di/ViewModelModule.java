package com.danilketov.wotr.di;

import androidx.lifecycle.ViewModel;

import com.danilketov.wotr.viewmodel.AccountViewModel;
import com.danilketov.wotr.viewmodel.InfoUserViewModel;

import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public class ViewModelModule {

    @IntoMap
    @Provides
    @ViewModelKey(AccountViewModel.class)
    public ViewModel accountViewModel(AccountViewModel accountViewModel) {
        return accountViewModel;
    }

    @IntoMap
    @Provides
    @ViewModelKey(InfoUserViewModel.class)
    public ViewModel infoUserViewModel(InfoUserViewModel infoUserViewModel) {
        return infoUserViewModel;
    }
}
