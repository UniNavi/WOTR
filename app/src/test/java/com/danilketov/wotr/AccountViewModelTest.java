package com.danilketov.wotr;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.repository.DataRepository;
import com.danilketov.wotr.viewmodel.AccountViewModel;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class AccountViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    public static final Account ACCOUNT = new Account(
            11170684,
            "WestSoulder"
    );

    public static final List<Account> ACCOUNTS = Collections.singletonList(ACCOUNT);
    public static Executor TEST_EXECUTOR = command -> command.run();

    @Mock
    private DataRepository dataRepository;
    private AccountViewModel viewModel;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        viewModel = new AccountViewModel(dataRepository, TEST_EXECUTOR);
        viewModel.getAccount().observeForever((accounts -> {
        }));
    }

    @Test
    public void searchAccounts_ValidAccountResponse_SameValueInLiveData() throws IOException, JSONException {
        // arrange
        String query = "WestSoulder";
        Mockito.when(dataRepository.getAccounts(query)).thenReturn(ACCOUNTS);

        // act
        viewModel.searchUsers(query);

        // assert
        List<Account> result = viewModel.getAccount().getValue();
        Assert.assertEquals(ACCOUNTS, result);
    }

    @Test
    public void searchAccounts_ErrorResponse_ErrorLiveDataIsTrue() throws IOException, JSONException {
        // arrange
        String query = "WestSoulder";
        Mockito.when(dataRepository.getAccounts(query)).thenThrow(new IOException());

        // act
        viewModel.searchUsers(query);

        // assert
        Boolean result = viewModel.isNetworkException().getValue();
        Assert.assertEquals(true, result);
    }

    @Test
    public void searchAccounts_QueryIsEmpty_ValidationError() {
        // arrange
        String query = "";

        // act
        viewModel.searchUsers(query);

        // assert
        Boolean result = viewModel.isQueryValidationException().getValue();
        Assert.assertEquals(true, result);
    }
}
