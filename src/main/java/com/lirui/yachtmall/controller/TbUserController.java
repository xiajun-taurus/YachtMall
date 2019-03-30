package com.lirui.yachtmall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lirui.yachtmall.entity.TbUser;
import com.lirui.yachtmall.enums.Role;
import com.lirui.yachtmall.service.impl.TbUserServiceImpl;
import com.lirui.yachtmall.utils.ReturnUtil;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Slf4j
@Controller
public class TbUserController {

  @Autowired
  private TbUserServiceImpl tbUserService;

  //跳转到管理员列表
  @GetMapping("admin/list")
  public String adminList() {
    return "/admin/user/admin-list";
  }

  //跳转到用户列表
  @GetMapping("user/list")
  public String userList() {
    return "/admin/user/user-list";
  }


  //展示所有用户
  @PostMapping("user/list")
  @ResponseBody
  public ModelMap list(@RequestBody Page<TbUser> tbUserPage) {
    IPage<TbUser> page = tbUserService.page(tbUserPage);
    return ReturnUtil.success("ok", page, null);
  }


  //表单页面
  @GetMapping({"admin/form","user/form"})
  public String showForm(TbUser tbUser, Model model) {
    TbUser user = new TbUser();
    if (!StringUtils.isEmpty(tbUser.getId())) {
      user = tbUserService.getById(tbUser.getId());
    }
    model.addAttribute("user", user);
    return "/admin/user/form";
  }

  //管理员新增/保存
  @PostMapping("admin/save")
  @ResponseBody
  public ModelMap saveAdmin(TbUser tbUser) {
    tbUser.setUserType(Role.ADMIN.getUserType());
    return saveUser(tbUser);
  }


  //用户删除
  @GetMapping("user/del")
  @ResponseBody
  public ModelMap del(@RequestParam("id") String id) {
    boolean b = tbUserService.removeById(id);
    if (b) {
      return ReturnUtil.success("删除成功", null, null);
    }
    return ReturnUtil.error("删除失败", null, null);
  }

  public ModelMap saveUser(TbUser tbUser) {
    //TODO: 不同角色创建，可以在此方法外面包一个方法，设置用户角色，再执行此方法
    if (!StringUtils.isEmpty(tbUser.getId())) {
      log.info("此用户存在，本次操作为：更新用户");
      //更新修改时间
      tbUser.setLastEditTime(LocalDateTime.now());
      boolean save = tbUserService.updateById(tbUser);
      if (save) {
        return ReturnUtil.success("更新成功", null, null);
      } else {
        return ReturnUtil.error("更新失败", null, null);
      }
    } else {
      log.info("用户不存在，本次操作为：新增用户");
      String usr = tbUser.getUsr();
      TbUser byUsr = tbUserService.getByUsr(usr);
      if (byUsr != null) {
        log.info("有同名用户");
        return ReturnUtil.error("存在同名用户");
      }
      //定义创建时间和修改时间
      tbUser.setCreateTime(LocalDateTime.now());
      tbUser.setLastEditTime(LocalDateTime.now());
      boolean save = tbUserService.save(tbUser);
      if (save) {
        return ReturnUtil.success("保存成功", null, "user-list");
      } else {
        return ReturnUtil.error("操作失败", null, null);
      }
    }
  }
}
//TODO 模糊查询，排序，用户相同功能+ 商品审批列表（只能改变使用状态）