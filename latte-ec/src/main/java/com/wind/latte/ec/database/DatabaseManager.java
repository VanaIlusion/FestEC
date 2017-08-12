package com.wind.latte.ec.database;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

/**
 * Created by theWind on 2017/8/12.
 */

public class DatabaseManager {

    private DaoSession mDaoSession = null;
    private UserProfileDao mDao = null;

    private DatabaseManager() {
    }
    public DatabaseManager init(Context context){
        initDao(context);
        return this;
    }

    private static final class Holder {
        private static final DatabaseManager INSTANCCE = new DatabaseManager();
    }

    public static DatabaseManager getInstance() {
        return Holder.INSTANCCE;
    }

    private void initDao(Context context) {
        final ReleaseOpenHelper helper = new ReleaseOpenHelper(context, "fest_ec.db");
        final Database db = helper.getWritableDb();
        mDaoSession = new DaoMaster(db).newSession();
        mDao = mDaoSession.getUserProfileDao();
    }

    public final UserProfileDao getmDao(){
        return  mDao;
    }
}
