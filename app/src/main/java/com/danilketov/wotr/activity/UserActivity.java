package com.danilketov.wotr.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.danilketov.wotr.db.AppDatabase;
import com.danilketov.wotr.R;
import com.danilketov.wotr.db.UserDao;
import com.danilketov.wotr.fragment.UserFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class UserActivity extends AppCompatActivity {

    private static final String LOG_TAG = "wotr.UserActivity";

    private EditText searchEditText;
    private FloatingActionButton searchFB;

    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        AppDatabase db = Room
                .databaseBuilder(getApplicationContext(), AppDatabase.class, "account-database")
                .build();
        userDao = db.getUserDao();


        initView();

        if(savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container, new UserFragment(), UserFragment.LOG_TAG)
                    .commit();
        }

        searchFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);
                UserFragment userFragment = (UserFragment) fragment;
                if (userFragment == null) throw new NullPointerException("Fragment null");

                String query = searchEditText.getText().toString();
                userFragment.searchUsers(query);
            }
        });

    }

    private void initView() {
        searchEditText = findViewById(R.id.search_edit_text);
        searchFB = findViewById(R.id.search_floating_action_button);
    }
}
