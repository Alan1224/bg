package com.xzf.bg.dao;

import com.xzf.bg.entity.RateTime;
import com.xzf.bg.entity.TOrderReq;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("tOrderReqDao")
public interface TOrderReqDao {

    TOrderReq selectByCpParam(@Param("cpParam") String cpParam, @Param("month") String month);
    TOrderReq selectByFfId(@Param("ffId") String ffId, @Param("month") String month);
    List<Double> selectSuccess(@Param("timeType") String timeType, @Param("month") String month);
    List<Integer> selectCount(@Param("timeType") String timeType, @Param("month") String month);
    List<Double> selectFee(@Param("timeType") String timeType, @Param("month") String month);
    Integer selectIsSuccess(@Param("cpId") String cpId,@Param("timeType") String timeType, @Param("month") String month);
    int updateFeeBack(@Param("cpParam") String cpParam, @Param("month") String month);
    //获取实时成功率的数据
    List<String> selectCpId(@Param("month") String month,@Param("typeId") String typeId);
    List<RateTime> selectRateTime(@Param("time") String time, @Param("month") String month,@Param("typeId") String typeId,@Param("timeType") String timeType);
    List<RateTime> selectRateTimeFee(@Param("time") String time, @Param("month") String month,@Param("typeId") String typeId,@Param("timeType") String timeType);
}