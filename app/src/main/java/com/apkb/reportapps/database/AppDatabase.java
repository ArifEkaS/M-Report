package com.apkb.reportapps.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.apkb.reportapps.dao.DatabaseDao;
import com.apkb.reportapps.model.ModelDatabase;


@Database(entities = {ModelDatabase.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DatabaseDao databaseDao();
}
