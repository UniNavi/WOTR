package com.danilketov.wotr.di;

import com.danilketov.wotr.fragment.InfoUserFragment;
import com.danilketov.wotr.fragment.UserFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ViewModelModule.class)
public interface AppComponent {

    void inject(UserFragment userFragment);

    void inject(InfoUserFragment infoUserFragment);
}
