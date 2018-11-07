package com.xzf.bg.server.impl;

import com.xzf.bg.dao.TUserDao;
import com.xzf.bg.entity.TUser;
import com.xzf.bg.server.TUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("tUserServer")
public class TUserServerImpl implements TUserServer {
    @Autowired
    private TUserDao tUserDao;
    @Override
    public TUser getTUser(String username, String password) {
        return tUserDao.selectUser(username,password);
    }

    @Override
    public void edit(String cpId, String username, String password, String memo) {
        tUserDao.update(cpId,username,password,memo);
    }
}
