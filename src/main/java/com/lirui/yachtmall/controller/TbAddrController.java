package com.lirui.yachtmall.controller;


import com.lirui.yachtmall.entity.TbAddr;
import com.lirui.yachtmall.service.TbAddrService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;

/**
 * <p>
 * 收货地址表 前端控制器
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-16
 */
@Controller
@RequestMapping("/tb-addr")
public class TbAddrController{
  @RequestMapping("main")
  public String showMain(){
    return "main";
  }


}
