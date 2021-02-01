package com.danilketov.wotr;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.danilketov.wotr.entity.UserInfo;
import com.danilketov.wotr.repository.DataRepository;
import com.danilketov.wotr.viewmodel.InfoUserViewModel;

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

import static com.danilketov.wotr.AccountViewModelTest.TEST_EXECUTOR;

public class InfoUserViewModelTest {
    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    public static final UserInfo USER_INFO = new UserInfo(
            "WestSoulder",
            11170684,
            7689,
            1611816812,
            null
    );

    @Mock
    private DataRepository dataRepository;
    private InfoUserViewModel viewModel;

    @Before
    public void before() {
        MockitoAnnotations.initMocks(this);
        viewModel = new InfoUserViewModel(dataRepository, TEST_EXECUTOR);

        viewModel.getRepository().observeForever((dataRepository -> {
        }));
    }

    @Test
    public void searchUsers_ValidRepositoryResponse_SameValueInLiveData() throws IOException, JSONException {
        // arrange
        int accountId = 11170684;

        Mockito.when(dataRepository.getUserInfo(accountId)).thenReturn(USER_INFO);

        // act
        viewModel.updateData(String.valueOf(accountId));

        // assert
        UserInfo userInfo = viewModel.getRepository().getValue();
        Assert.assertEquals(USER_INFO, userInfo);
    }

    @Test
    public void updateData_ErrorResponse_ErrorLiveDataIsTrue() throws IOException, JSONException {
        int accountId = 11170684;

        Mockito.when(dataRepository.getUserInfo(accountId)).thenThrow(new IOException());

        // act
        viewModel.updateData(String.valueOf(accountId));

        // assert
        Boolean result = viewModel.isException().getValue();
        Assert.assertEquals(true, result);
    }
}
