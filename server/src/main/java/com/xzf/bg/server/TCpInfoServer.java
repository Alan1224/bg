package com.xzf.bg.server;

import com.xzf.bg.entity.TCpInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("tCpInfoServer")
public interface TCpInfoServer {
    TCpInfo getTCpInfo(String cpId);
    List<TCpInfo> getTCpInfoLately();
    List<TCpInfo> getTCpInfoAll();
    void edit(TCpInfo tCpInfo);
    void editSpForNameAllType(String cpFlag, String localSpId);
    void editSpForType( String cpId, String localSpId, String cpFlag);
}