package com.getadhell.androidapp.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.getadhell.androidapp.db.entity.AppInfo;

import java.util.List;

@Dao
public interface AppInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AppInfo> apps);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AppInfo info);

    @Query("DELETE FROM AppInfo")
    void deleteAll();

    @Query("DELETE FROM AppInfo WHERE id = :id")
    void deleteAppInfoById(int id);

    @Query("DELETE FROM AppInfo WHERE packageName = :packageName")
    void deleteAppInfoByPackageName(String packageName);

    @Query("SELECT MAX(id) FROM AppInfo")
    long getMaxId();

    @Query("SELECT * FROM AppInfo ORDER BY appName ASC")
    List<AppInfo> getAll();

    @Query("SELECT * FROM AppInfo WHERE system = 0 ORDER BY appName ASC")
    LiveData<List<AppInfo>> getAllNonSystemLiveData();

    @Query("SELECT * FROM AppInfo WHERE appName LIKE :str ORDER BY appName ASC")
    List<AppInfo> getAllAppsWithStrInName(String str);

    @Query("SELECT * FROM AppInfo WHERE packageName LIKE :str ORDER BY appName ASC")
    List<AppInfo> getAllPackagesWithStrInName(String str);

    @Query("SELECT * FROM AppInfo WHERE appName LIKE :str ORDER BY installTime DESC")
    List<AppInfo> getAllAppsWithStrInNameTimeOrder(String str);

    @Query("SELECT * FROM AppInfo WHERE packageName LIKE :str ORDER BY installTime DESC")
    List<AppInfo> getAllPackagesWithStrInNameAlphOrder(String str);

    @Query("SELECT * FROM AppInfo ORDER BY installTime DESC")
    List<AppInfo> getAllRecentSort();

    @Query("SELECT * FROM AppInfo ORDER BY adhellWhitelisted DESC, appName ASC")
    LiveData<List<AppInfo>> getAllSortedByWhitelist();

    @Query("SELECT * FROM AppInfo WHERE adhellWhitelisted = 1")
    List<AppInfo> getWhitelistedApps();

    @Query("SELECT * FROM AppInfo WHERE packageName = :packageName")
    AppInfo getByPackageName(String packageName);

    @Update
    void update(AppInfo appInfo);
}