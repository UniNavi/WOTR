package com.danilketov.wotr.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Statistics {

    @PrimaryKey
    @Embedded
    private All all;

    public Statistics(All all) {
        this.all = all;
    }

    public All getAll() {
        return all;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Statistics that = (Statistics) o;

        return all != null ? all.equals(that.all) : that.all == null;
    }

    @Override
    public int hashCode() {
        return all != null ? all.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Statistics{" +
                "all=" + all +
                '}';
    }
}
