package com.xzf.bg.dao;

import com.xzf.bg.entity.InsteadInfo;
import com.xzf.bg.entity.TCpInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tCpInfoDao")
public interface TCpInfoDao {
    void insert(@Param("cpId") String cpId, @Param("name") String name, @Param("url") String url, @Param("localSpId") String localSpId);
//    void update(@Param("cpId") String cpId, @Param("localSpId") String localSpId, @Param("name") String name, @Param("url") String url);
    void update(TCpInfo tCpInfo);
    void updateSpForNameAllType(@Param("cpFlag") String cpFlag, @Param("localSpId") String localSpId);
    void updateSp(@Param("cpId") String cpId, @Param("localSpId") String localSpId);
    void updateSpForType(@Param("cpId") String cpId, @Param("localSpId") String localSpId, @Param("cpFlag") String cpFlag);
    List<TCpInfo> selectAll();
    TCpInfo selectByCpId(@Param("cpId") String cpId);
    TCpInfo selectInstead(@Param("cpId") String cpId);
    void updateInstead(@Param("cpId") String cpId,@Param("money") Double money);
    void updateInsteadAdd(@Param("cpId") String cpId,@Param("money") Double money);
}