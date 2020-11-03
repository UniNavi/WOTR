package com.danilketov.wotr.activity;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.danilketov.wotr.R;
import com.danilketov.wotr.fragment.UserFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class UserActivity extends AppCompatActivity {

    private static final String LOG_TAG = "wotr.UserActivity";

    private EditText searchEditText;
    private FloatingActionButton searchFB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initView();

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new UserFragment(), UserFragment.LOG_TAG)
                    .commit();
        }

        searchFB.setOnClickListener(v -> {
            Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
            UserFragment userFragment = (UserFragment) fragment;
            if (userFragment == null) throw new NullPointerException("Fragment null");

            String query = searchEditText.getText().toString().trim();
            userFragment.searchUsers(query);
        });

    }

    private void initView() {
        searchEditText = findViewById(R.id.search_edit_text);
        searchFB = findViewById(R.id.search_floating_action_button);
    }
}
