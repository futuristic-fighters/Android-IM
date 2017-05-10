package com.wapchief.jpushim.greendao;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.wapchief.jpushim.greendao.model.User;
import com.wapchief.jpushim.greendao.model.ChatLog;

import com.wapchief.jpushim.greendao.UserDao;
import com.wapchief.jpushim.greendao.ChatLogDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig userDaoConfig;
    private final DaoConfig chatLogDaoConfig;

    private final UserDao userDao;
    private final ChatLogDao chatLogDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        userDaoConfig = daoConfigMap.get(UserDao.class).clone();
        userDaoConfig.initIdentityScope(type);

        chatLogDaoConfig = daoConfigMap.get(ChatLogDao.class).clone();
        chatLogDaoConfig.initIdentityScope(type);

        userDao = new UserDao(userDaoConfig, this);
        chatLogDao = new ChatLogDao(chatLogDaoConfig, this);

        registerDao(User.class, userDao);
        registerDao(ChatLog.class, chatLogDao);
    }
    
    public void clear() {
        userDaoConfig.clearIdentityScope();
        chatLogDaoConfig.clearIdentityScope();
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public ChatLogDao getChatLogDao() {
        return chatLogDao;
    }

}
