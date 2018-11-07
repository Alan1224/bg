package com.xzf.bg.controller;

import com.xzf.bg.dto.InsteadNameDto;
import com.xzf.bg.entity.InsteadInfo;
import com.xzf.bg.entity.TUser;
import com.xzf.bg.server.InsteadPayServer;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

@RequestMapping("instead")
@Controller("insteadPayController")
public class InsteadPayController {
    @Autowired
    InsteadPayServer insteadPayServer;
    @RequestMapping("to_instead")
    public String toInstead(HttpSession session,Map<String, Object> map){
        TUser tUser = (TUser) session.getAttribute("tUser");
        List<InsteadInfo> insteadInfos = insteadPayServer.getInsteadInfoByCp(tUser.getCpId());
        map.put("insteadInfoDto",insteadInfos);
        return "instead_info";
    }
    @RequestMapping("instead_update")
    public String insteadUpdate(HttpServletRequest request, Map<String,Object> map) {
        String cpId = request.getParameter("cpId");
        Double withdraw = Double.valueOf(request.getParameter("withdraw"));
        InsteadNameDto nameDto = insteadPayServer.parsExcel(request);
        if ("success".equals(insteadPayServer.makeInstead(nameDto.getList(),cpId,nameDto.getName()))){
            //成功跳转提现记录页面
            return "redirect:history?cpId="+cpId;
        }else {
            //失败提示失败信息
            map.put("status","提现失败，余额不足");
            return "index";
        }
    }
    @RequestMapping("instead")
    public String instead(String cpId, Double withdraw, Map<String,Object> map) {
        map.put("cpId",cpId);
        map.put("withdraw",withdraw);
        return "instead_pay";
    }

    @RequestMapping("history")
    public String history(HttpSession session,String cpId,Map<String,Object> map){
        if (cpId == null || "".equals(cpId)){
            TUser tUser = (TUser) session.getAttribute("tUser");
            cpId = tUser.getCpId();
        }
        map.put("history",insteadPayServer.get(cpId));
        return "instead_history_list";
    }

    @RequestMapping("download")
    public String download(String insteadId ,HttpServletRequest request, HttpServletResponse response) throws IOException {
        insteadPayServer.install(request,response,insteadId);
        return "redirect:history";
    }
    /**
     * 审核，拒绝
     * 修改状态，把资金加入资金池
     */
    @RequestMapping("refuse")
    public String refuse(String insteadId , Double money ,String cpId) throws IOException {
        //修改状态,并把资金返回
        insteadPayServer.edit(insteadId,2 ,cpId ,money * 100);
        return "redirect:history?cpId="+cpId;
    }
    @RequestMapping("handle")
    public String handle(String insteadId,String cpId) throws IOException {
        //修改状态
        insteadPayServer.edit(insteadId,1 ,null ,null);
        return "redirect:history?cpId="+cpId;
    }

    /**
     * 拿出会用到的cpId
     * 3，有资金的cpId
     * @param insteadId
     * @return
     * @throws IOException
     */
    @RequestMapping("findCpId")
    public String findCpId(String insteadId) throws IOException {
        //修改状态
        insteadPayServer.edit(insteadId,1 ,null ,null);
        return "redirect:history";
    }
}