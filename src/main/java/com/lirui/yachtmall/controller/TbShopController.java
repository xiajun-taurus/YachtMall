package com.lirui.yachtmall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lirui.yachtmall.entity.TbShop;
import com.lirui.yachtmall.enums.EnableStatus;
import com.lirui.yachtmall.service.impl.TbShopServiceImpl;
import com.lirui.yachtmall.utils.ReturnUtil;
import java.time.LocalDateTime;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 商铺表 前端控制器
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Slf4j
@Controller
@RequestMapping("shop")
public class TbShopController {

  @Autowired
  private TbShopServiceImpl tbShopService;


  /**
   * 跳转到商铺列表
   */
  @GetMapping("list")
  public String shopList() {
    return "/admin/shop/shop-list";
  }


  /**
   * 分页条件查询符合条件的所有商铺，JSON格式返回
   */
  @PostMapping("list")
  @ResponseBody
  public ModelMap list(@RequestBody Page<TbShop> tbShopPage) {
    IPage<TbShop> page = tbShopService.page(tbShopPage);
    return ReturnUtil.success("ok", page, null);
  }


  /**
   * 跳转到表单页面，如果传入的对象不是null，获取对象的所有信息，反填到表单中
   *
   * @param tbShop 表单传入的Shop对象
   */
  @GetMapping({"form"})
  public String showForm(TbShop tbShop, Model model) {
    TbShop shop = new TbShop();
    if (!StringUtils.isEmpty(tbShop.getId())) {
      shop = tbShopService.getById(tbShop.getId());
    }
    model.addAttribute("shop", shop);
    return "/admin/shop/form";
  }

  /**
   * 商铺新增/保存
   *
   * @param tbShop 表单传入的Shop对象
   */
  @PostMapping("save")
  @ResponseBody
  public ModelMap saveAdmin(TbShop tbShop) {

    return saveshop(tbShop);
  }


  /**
   * 根据id禁用商铺
   */
  @GetMapping("disable")
  @ResponseBody
  public ModelMap del(@RequestParam("id") String id) {
    TbShop shop = tbShopService.getById(id);
    shop.setEnableStatus(EnableStatus.DISABLE.getValue());
    boolean b = tbShopService.updateById(shop);
    if (b) {
      return ReturnUtil.success("修改成功", null, null);
    }
    return ReturnUtil.error("修改失败", null, null);
  }

  /**
   * 具体的保存方法，根据id判断商铺是否存在，存在执行update，否则执行insert
   *
   * @param tbShop 表单传入的Shop对象
   */
  //TODO:经常使用，后期改成泛型的，用反射改造
  public ModelMap saveshop(TbShop tbShop) {
    if (!StringUtils.isEmpty(tbShop.getId())) {
      log.info("此商铺存在，本次操作为：更新商铺");
      //设置最后更新时间
      tbShop.setLastEditTime(LocalDateTime.now());
      boolean save = tbShopService.updateById(tbShop);
      if (save) {
        return ReturnUtil.success("更新成功", null, null);
      } else {
        return ReturnUtil.error("更新失败", null, null);
      }
    } else {
      log.info("商铺不存在，本次操作为：新增商铺");
      tbShop.setCreateTime(LocalDateTime.now());
      tbShop.setLastEditTime(LocalDateTime.now());
      boolean save = tbShopService.save(tbShop);
      if (save) {
        return ReturnUtil.success("保存成功", null, "shop-list");
      } else {
        return ReturnUtil.error("操作失败", null, null);
      }
    }
  }
}

