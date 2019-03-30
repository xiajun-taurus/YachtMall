package com.lirui.yachtmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaseController {
  @RequestMapping("console")
  public String home(){
    return "admin-list";
  }
}
