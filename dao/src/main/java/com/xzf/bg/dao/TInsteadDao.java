package com.xzf.bg.dao;

import com.xzf.bg.entity.InsteadInfo;
import com.xzf.bg.entity.TInstead;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tInsteadDao")
public interface TInsteadDao {
    void insert(TInstead tInstead);
    List<TInstead> select(@Param("cpId") String cpId);
    void update(@Param("insteadId") String insteadId,@Param("status") Integer status);
}