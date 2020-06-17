package com.danilketov.wotr.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class ClansData {

    @PrimaryKey
    @NonNull
    @SerializedName("members_count")
    private int membersCount;
    private int cladId;

    public ClansData(int membersCount, int cladId) {
        this.membersCount = membersCount;
        this.cladId = cladId;
    }

    public int getMembersCount() {
        return membersCount;
    }

    public int getCladId() {
        return cladId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClansData clansData = (ClansData) o;

        if (membersCount != clansData.membersCount) return false;
        return cladId == clansData.cladId;
    }

    @Override
    public int hashCode() {
        int result = membersCount;
        result = 31 * result + cladId;
        return result;
    }

    @Override
    public String toString() {
        return "ClansData{" +
                "membersCount=" + membersCount +
                ", cladId=" + cladId +
                '}';
    }
}
