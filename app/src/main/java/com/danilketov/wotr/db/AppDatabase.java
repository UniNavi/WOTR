package com.danilketov.wotr.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.danilketov.wotr.entity.Account;
import com.danilketov.wotr.entity.UserInfo;

@Database(entities = {Account.class, UserInfo.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static String DATABASE_NAME = "wotr_database";

    public abstract UserDao getUserDao();
}
