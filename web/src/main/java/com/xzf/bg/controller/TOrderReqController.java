package com.xzf.bg.controller;

import com.xzf.bg.entity.TCpInfo;
import com.xzf.bg.entity.TUser;
import com.xzf.bg.server.TOrderReqServer;
import com.xzf.bg.server.impl.TOrderReqServerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller("tOrderReqController")
@RequestMapping("/order")
public class TOrderReqController {
    @Resource(name = "tOrderReqServer")
    @Autowired
    private TOrderReqServer tOrderReqServer;

    @RequestMapping("/self")
    public String self(Map<String,Object> map){
        map.put("success",new TCpInfo());
        return "t_cp_info";
    }

    @RequestMapping("/show_cp")
    public String showCp(HttpSession session,Map<String, Object> map){
        TUser tUser = (TUser) session.getAttribute("tUser");
        map.put("success",tOrderReqServer.rateTime("cp_id",tUser.getCpId()));
        map.put("type","cp");
        map.put("rough","rough");
        return "t_order_req_list";
    }
    @RequestMapping("/show_sp")
    public String showSp(HttpSession session,Map<String,Object> map){
        TUser tUser = (TUser) session.getAttribute("tUser");
        map.put("success",tOrderReqServer.rateTime("sp_id",tUser.getCpId()));
        map.put("type","sp");
        map.put("rough","rough");
        return "t_order_req_list";
    }
    @RequestMapping("/show")
    public String show(String time ,HttpSession session, Map<String,Object> map){
        TUser tUser = (TUser) session.getAttribute("tUser");
        map.put("success",tOrderReqServer.rateTimeMinute("cp_id",time,tUser.getCpId()));
        map.put("rough","detail");
        return "t_order_req_list";
    }
    @RequestMapping("/show_order")
    public String showOrder(String cpParam ,String ffId , Map<String,Object> map) {
        map.put("order", tOrderReqServer.select(cpParam, ffId));
        return "t_order_req";
    }
    @RequestMapping("/to_show_order")
    public String toShowOrder() {
        return "t_order_req_find";
    }

    @RequestMapping("/feeBack")
    public String feeBack(String ffId,String cpParam,String fee,String cpId, Map<String,Object> map) {
        map.put("result",tOrderReqServer.feeBack(ffId,cpParam,fee,cpId));
        return "result";
    }
}
