package com.lirui.yachtmall.controller;

import com.lirui.yachtmall.entity.TbUser;
import com.lirui.yachtmall.service.impl.TbUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class CommonController {
  @Autowired
  private TbUserServiceImpl tbUserService;
  /**
   * 跳转到登录页面
   * @return
   */
  @GetMapping("login")
  public String toLogin(Model model){
    model.addAttribute("user",new TbUser());
    return "admin/login";
  }

  /**
   * 验证登录信息
   */
  @PostMapping("login")
  public String login(TbUser user){
    String username = user.getUsr();
    String password = user.getPwd();
    //获取主体
    Subject subject = SecurityUtils.getSubject();
    //封装用户数据
    UsernamePasswordToken token = new UsernamePasswordToken(username, password);
    //执行登录方法，捕捉异常判断是否成功
    try {
      subject.login(token);
    } catch (UnknownAccountException e) {
      log.error(e.getMessage());
    }catch (IncorrectCredentialsException e){
      log.error(e.getMessage());
    }
    return "redirect:/admin/list";
  }

  @GetMapping("logout")
  public String logout(){
    return "redirect:login";
  }
}
