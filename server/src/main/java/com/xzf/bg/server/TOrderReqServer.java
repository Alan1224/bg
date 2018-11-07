package com.xzf.bg.server;

import com.xzf.bg.dto.TOderReq;
import com.xzf.bg.entity.RateTime;
import com.xzf.bg.entity.Result;
import com.xzf.bg.entity.TOrderReq;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("tOrderReqServer")
public interface TOrderReqServer {
    //成功率，成功数，金额，
    //拿到所有在时间内的订单，再分为每个下游，再计算成功率
    List<Result> success(String timeType);
    TOrderReq difference(String type, String id);
    //总计
    public Result successAll(List<Result> list);
    /**
     * 获取最近一段时间的cpId
     */

    public String feeBack(String ffid,String cpparam,String fee,String cpid);
//    查询实时成功率计算
    public List<TOderReq> rateTime(String typeId,String cpId);
    public List<TOderReq> rateTimeMinute(String typeId,String time,String cpId);
    TOrderReq select(String cpParam,String ffId);
}