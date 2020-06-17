package com.danilketov.wotr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.danilketov.wotr.R;
import com.danilketov.wotr.fragment.InfoUserFragment;

public class InfoUserActivity extends AppCompatActivity {

    public static final String EXTRA_ACCOUNT_ID = "accountId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_user);

        String accountId = getIntent().getStringExtra(EXTRA_ACCOUNT_ID);

        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if(fragment == null) throw new NullPointerException("Fragment не найден");

        InfoUserFragment infoUserFragment = (InfoUserFragment) fragment;
        infoUserFragment.updateData(accountId);
    }
}
