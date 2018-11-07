package com.xzf.bg.dao;

import com.xzf.bg.entity.TUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tUserDao")
public interface TUserDao {
    void insert(TUser tUser);
    void delete(String userName);
    void update(@Param("cpId") String cpId, @Param("username") String username, @Param("password") String password, @Param("memo") String memo);
    TUser selectByName(String userName);
    TUser selectUser(@Param("username") String username, @Param("password") String password);
    List<TUser> selectAll();
}