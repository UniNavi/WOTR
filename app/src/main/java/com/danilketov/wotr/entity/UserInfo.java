package com.danilketov.wotr.entity;

import androidx.annotation.NonNull;
import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class UserInfo {

    @PrimaryKey
    @NonNull
    private String nickname;

    @SerializedName("account_id")
    private int accountId;

    @SerializedName("global_rating")
    private int globalRating;

    @SerializedName("last_battle_time")
    private int lastBattleTime;

    @Embedded
    private Statistics statistics;

    public UserInfo(String nickname, int accountId, int globalRating, int lastBattleTime, Statistics statistics) {
        this.nickname = nickname;
        this.accountId = accountId;
        this.globalRating = globalRating;
        this.lastBattleTime = lastBattleTime;
        this.statistics = statistics;
    }

    public String getNickname() {
        return nickname;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getGlobalRating() {
        return globalRating;
    }

    public int getLastBattleTime() {
        return lastBattleTime;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserInfo userInfo = (UserInfo) o;

        if (accountId != userInfo.accountId) return false;
        if (globalRating != userInfo.globalRating) return false;
        if (lastBattleTime != userInfo.lastBattleTime) return false;
        if (nickname != null ? !nickname.equals(userInfo.nickname) : userInfo.nickname != null)
            return false;
        return statistics != null ? statistics.equals(userInfo.statistics) : userInfo.statistics == null;
    }

    @Override
    public int hashCode() {
        int result = nickname != null ? nickname.hashCode() : 0;
        result = 31 * result + accountId;
        result = 31 * result + globalRating;
        result = 31 * result + lastBattleTime;
        result = 31 * result + (statistics != null ? statistics.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "nickname='" + nickname + '\'' +
                ", accountId=" + accountId +
                ", globalRating=" + globalRating +
                ", lastBattleTime=" + lastBattleTime +
                ", statistics=" + statistics +
                '}';
    }
}
