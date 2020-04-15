package com.fumiao.assistant.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.fumiao.assistant.bean.home.InComing;
import com.fumiao.assistant.gen.DaoMaster;
import com.fumiao.assistant.gen.DaoSession;
import com.fumiao.assistant.gen.InComingDao;
import org.greenrobot.greendao.query.QueryBuilder;
import java.util.List;

public class DBManager {
    private final static String dbName = "incoming.db";
    private static DBManager mInstance;
    private DaoMaster.DevOpenHelper openHelper;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
        openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
    }

    /**
     * 获取单例引用
     *
     * @param context
     * @return
     */
    public static DBManager getInstance(Context context) {
        if (mInstance == null) {
            synchronized (DBManager.class) {
                if (mInstance == null) {
                    mInstance = new DBManager(context);
                }
            }
        }
        return mInstance;
    }

    /**
     * 获取可读数据库
     */
    private SQLiteDatabase getReadableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getReadableDatabase();
        return db;
    }

    /**
     * 获取可写数据库
     */
    private SQLiteDatabase getWritableDatabase() {
        if (openHelper == null) {
            openHelper = new DaoMaster.DevOpenHelper(context, dbName, null);
        }
        SQLiteDatabase db = openHelper.getWritableDatabase();
        return db;
    }

    public void insertInComing(InComing inComing) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        InComingDao inComingDao = daoSession.getInComingDao();
        inComingDao.insertOrReplace(inComing);
    }


    //单个删除
    public void deleteInComing(InComing inComing) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        InComingDao inComingDao = daoSession.getInComingDao();
        //根据手机号在数据库在数据库中是否存在,存在删除该数据
        String phone = inComing.getMblNo();
//        InComing inComings = inComingDao.queryBuilder().where(InComingDao.Properties.MblNo.eq(phone)).unique();
        List<InComing> inComingsList = inComingDao.queryBuilder().where(InComingDao.Properties.MblNo.eq(phone)).list();
        if(inComingsList != null){
            for(InComing coming : inComingsList){
                inComingDao.delete(coming);
            }
        }
    }
    //删除所有
    public void deleteInComing() {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        InComingDao inComingDao = daoSession.getInComingDao();
        inComingDao.deleteAll();
    }
    //更新
    public void updateInComing(InComing user) {
        DaoMaster daoMaster = new DaoMaster(getWritableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        InComingDao inComingDao = daoSession.getInComingDao();
        inComingDao.update(user);
    }
    //查询所有
    public List<InComing> queryAllInComing() {
        DaoMaster daoMaster = new DaoMaster(getReadableDatabase());
        DaoSession daoSession = daoMaster.newSession();
        InComingDao inComingDao = daoSession.getInComingDao();
        QueryBuilder<InComing> qb = inComingDao.queryBuilder();
        List<InComing> list;
        try {
            list = qb.list();
        }catch (Exception exception){
            list = null;
        }
//        List<InComing> list = qb.list();
        return list;
    }
}
