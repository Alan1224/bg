package com.xzf.bg.controller;

import com.xzf.bg.entity.TCpInfo;
import com.xzf.bg.server.TCpInfoServer;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.BinaryConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Map;

@Controller("tCpInfoController")
@RequestMapping("/cp")
public class TCpInfoController {
    net.spy.memcached.MemcachedClient client = new net.spy.memcached.MemcachedClient(new BinaryConnectionFactory(), AddrUtil.getAddresses("127.0.0.1:11211"));

    @Autowired
    private TCpInfoServer tCpInfoServer;

    public TCpInfoController() throws IOException {
    }

    @RequestMapping("/list")
    public String list(Map<String,Object> map){
        map.put("tCpInFos",tCpInfoServer.getTCpInfoAll());
        return "t_cp_info_list";
    }
    @RequestMapping("/list_lately")
    public String listLately(Map<String,Object> map){
        map.put("tCpInFos",tCpInfoServer.getTCpInfoLately());
        return "t_cp_info_list";
    }
    @RequestMapping("/to_edit")
    public String toEdit(String cpId ,Map<String,Object> map){
        map.put("tCpInFo",tCpInfoServer.getTCpInfo(cpId));
        return "t_cp_info";
    }
    @RequestMapping("/edit")
    public String edit(TCpInfo tCpInfo,Map<String,Object> map){
        tCpInfoServer.edit(tCpInfo);
        client.flush();
        map.put("tCpInFos",tCpInfoServer.getTCpInfoLately());
        return "t_cp_info_list";
    }
    @RequestMapping("/edit_type")
    public String editType(String cpFlag, String localSpId){
        tCpInfoServer.editSpForNameAllType(cpFlag,localSpId);
        client.flush();
        return "redirect:list_lately";
    }
    @RequestMapping("/to_edit_type")
    public String toEditType(){
        return "t_cp_info_edit_type";
    }
    @RequestMapping("/edit_cp")
    public String editCpType(String cpId, String localSpId, String cpFlag){
        tCpInfoServer.editSpForType(cpId,localSpId,cpFlag);
        client.flush();
        return "redirect:list_lately";
    }
    @RequestMapping("/to_edit_cp")
    public String toEditCpType(){
        return "t_cp_info_edit_cp";
    }
}