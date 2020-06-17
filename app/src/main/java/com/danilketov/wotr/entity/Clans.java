package com.danilketov.wotr.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity
public class Clans {

    @PrimaryKey
    @NonNull
    @SerializedName("clan_id")
    private int clanId;
    private String search;

    public Clans(int clanId, String search) {
        this.clanId = clanId;
        this.search = search;
    }

    public int getClanId() {
        return clanId;
    }

    public String getSearch() {
        return search;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Clans clans = (Clans) o;

        if (clanId != clans.clanId) return false;
        return search != null ? search.equals(clans.search) : clans.search == null;
    }

    @Override
    public int hashCode() {
        int result = clanId;
        result = 31 * result + (search != null ? search.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Clans{" +
                "clanId=" + clanId +
                ", search='" + search + '\'' +
                '}';
    }
}
