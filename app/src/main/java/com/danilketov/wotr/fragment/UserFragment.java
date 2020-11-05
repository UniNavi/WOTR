package com.danilketov.wotr.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.danilketov.wotr.App;
import com.danilketov.wotr.R;
import com.danilketov.wotr.activity.InfoUserActivity;
import com.danilketov.wotr.activity.UserActivity;
import com.danilketov.wotr.adapter.AccountAdapter;
import com.danilketov.wotr.di.ViewModelFactory;
import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.viewmodel.AccountViewModel;

import javax.inject.Inject;

public class UserFragment extends Fragment {

    public static final String LOG_TAG = "wotr.UserFragment";

    private AccountAdapter adapter;
    private ProgressBar progressBar;
    private TextView textViewEmptyList;
    private ImageView imageViewUser;
    private RecyclerView recyclerView;

    private UserActivity userActivity;

    private AccountViewModel viewModel;

    @Inject
    ViewModelFactory viewModelFactory;

    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof UserActivity) {
            userActivity = ((UserActivity) context);
        } else {
            throw new RuntimeException("Can't cast context to UserActivity!");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user, container, false);

        App.getAppComponent().inject(this);

        initView(view);
        initRecyclerView(view);
        initViewModel();

        return view;
    }

    private void initViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AccountViewModel.class);

        viewModel.getAccount().observe(this, accounts -> {
            if (accounts != null) {
                adapter.addItems(accounts);
            }
        });

        viewModel.isLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                imageViewUser.setVisibility(View.GONE);
                textViewEmptyList.setVisibility(View.GONE);
                progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            }
        });

        viewModel.isNetworkException().observe(this, isException -> {
            if (isException != null || isException) {
                Toast.makeText(getActivity(), R.string.error_message, Toast.LENGTH_SHORT).show();
            }
        });

        viewModel.isQueryValidationException().observe(this, isQueryValidationException -> {
            if (isQueryValidationException != null && isQueryValidationException) {
                Toast.makeText(getActivity(), R.string.incorrect_nickname, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView(View view) {
        progressBar = view.findViewById(R.id.progress_bar);
        textViewEmptyList = view.findViewById(R.id.text_view_empty_list);
        imageViewUser = view.findViewById(R.id.image_view_user);

    }

    private void initRecyclerView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view_users);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        AccountAdapter.OnInfoUserClickListener listener = account -> navigateToInfoUser(account);
        adapter = new AccountAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    private void navigateToInfoUser(Account account) {
        Intent intent = new Intent(getActivity(), InfoUserActivity.class);
        String accountId = String.valueOf(account.getAccountId());
        intent.putExtra(InfoUserActivity.EXTRA_ACCOUNT_ID, accountId);
        startActivity(intent);
    }

    public void searchUsers(String query) {
        viewModel.searchUsers(query);
        adapter.clearItems();
    }
}
