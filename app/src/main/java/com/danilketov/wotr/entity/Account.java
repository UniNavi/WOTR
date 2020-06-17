package com.danilketov.wotr.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Account {

    @PrimaryKey
    @NonNull
    @SerializedName("account_id")
    private int accountId;
    private String nickname;

    public Account(int accountId, String nickname) {
        this.accountId = accountId;
        this.nickname = nickname;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getNickname() {
        return nickname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (accountId != account.accountId) return false;
        return nickname.equals(account.nickname);
    }

    @Override
    public int hashCode() {
        int result = accountId;
        result = 31 * result + nickname.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId=" + accountId +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
