package com.fumiao.assistant.gen;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.fumiao.assistant.bean.home.InComing;

import com.fumiao.assistant.gen.InComingDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig inComingDaoConfig;

    private final InComingDao inComingDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        inComingDaoConfig = daoConfigMap.get(InComingDao.class).clone();
        inComingDaoConfig.initIdentityScope(type);

        inComingDao = new InComingDao(inComingDaoConfig, this);

        registerDao(InComing.class, inComingDao);
    }
    
    public void clear() {
        inComingDaoConfig.clearIdentityScope();
    }

    public InComingDao getInComingDao() {
        return inComingDao;
    }

}