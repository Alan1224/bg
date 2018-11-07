package com.xzf.bg.controller;

import com.xzf.bg.entity.TUser;
import com.xzf.bg.server.TUserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller("globalController")
public class GlobalController {
    @Autowired
    private TUserServer tUserServer;

    @RequestMapping("/to_login")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(HttpSession httpSession,@RequestParam String username,@RequestParam String password){
        TUser tUser = tUserServer.getTUser(username,password);
        if (tUser != null){
            httpSession.setAttribute("tUser",tUser);
            return "redirect:/order/show?time=10";
        }
        return "redirect:to_login";
    }
    @RequestMapping("/quit")
    public String quit(HttpSession session){
        session.setAttribute("tUser",null);
        return "redirect:to_login";
    }
    @RequestMapping("/to_change_password")
    public String toChangePassword(){
        return "change_password";
    }
    @RequestMapping("/change_password")
    public String changePassword(HttpSession session,String old,String new1,String new2){
        TUser tUser = (TUser) session.getAttribute("tUser");
        if (tUser.getPassword().equals(old) && new1.equals(new2)){
            tUserServer.edit(tUser.getCpId(),tUser.getUsername(),new1,tUser.getMemo());
            session.setAttribute("tUser",null);
            return "redirect:to_login";
        }else {
            return "redirect:to_change_password";
        }
    }
}