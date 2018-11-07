package com.xzf.bg.server.impl;

import com.xzf.bg.dao.TCpInfoDao;
import com.xzf.bg.dao.TOrderReqDao;
import com.xzf.bg.dto.TOderReq;
import com.xzf.bg.entity.TCpInfo;
import com.xzf.bg.server.TCpInfoServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service("tCpInfoServer")
public class TCpInfoServerImpl implements TCpInfoServer {
    Calendar cal = Calendar.getInstance();
    int month = cal.get(Calendar.MONTH) + 1;
    @Autowired
    private TCpInfoDao tCpInfoDao;
    @Autowired
    TOrderReqDao tOrderReqDao;
    @Override
    public TCpInfo getTCpInfo(String cpId) {
        return tCpInfoDao.selectByCpId(cpId);
    }

    @Override
    public List<TCpInfo> getTCpInfoLately() {
        List<String> cpIds = tOrderReqDao.selectCpId(String.valueOf(month),"cp_id");
        List<TCpInfo> tCpInfoList = new ArrayList<TCpInfo>();
        for (String cpid:cpIds) {
            tCpInfoList.add(tCpInfoDao.selectByCpId(cpid));
        }
        return tCpInfoList;
    }

    @Override
    public List<TCpInfo> getTCpInfoAll() {
        return tCpInfoDao.selectAll();
    }

    @Override
    public void edit(TCpInfo tCpInfo) {
        tCpInfo.setLocalSpId(tCpInfo.getLocalSpId().replaceAll(" ",""));
        tCpInfoDao.update(tCpInfo);
    }

    @Override
    public void editSpForNameAllType(String cpFlag, String localSpId) {
        tCpInfoDao.updateSpForNameAllType(cpFlag.replaceAll(" ",""),localSpId.replaceAll(" ",""));
    }

    @Override
    public void editSpForType(String cpId, String localSpId, String cpFlag) {
        cpId = cpId.substring(0,2);
        tCpInfoDao.updateSpForType(cpId.replaceAll(" ",""),localSpId.replaceAll(" ",""),cpFlag.replaceAll(" ",""));
    }
}
