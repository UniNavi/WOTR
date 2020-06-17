package com.danilketov.wotr.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.entity.UserInfo;

import java.util.List;

@Dao
public interface UserDao {

    // При добавлении аккаунтов, сервер присылает новые данные старые значения заменят новыми (
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAccounts(List<Account> accounts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUserInfo(UserInfo userInfo);

    // Получение всех Account из БД
    @Query("SELECT * FROM Account WHERE nickname LIKE :searchNickname")
    List<Account> getAccounts(String searchNickname);

    @Query("SELECT * FROM UserInfo WHERE accountId LIKE :accountId")
    UserInfo getUserInfo(int accountId);
}
