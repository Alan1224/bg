package com.xzf.bg.server;

import com.xzf.bg.entity.TUser;
import org.springframework.stereotype.Repository;

@Repository("tUserServer")
public interface TUserServer {
    TUser getTUser(String username, String password);
    void edit(String cpId, String username, String password, String memo);
}