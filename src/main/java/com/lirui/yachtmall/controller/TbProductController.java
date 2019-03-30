package com.lirui.yachtmall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lirui.yachtmall.entity.TbProduct;
import com.lirui.yachtmall.service.impl.TbProductServiceImpl;
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
 * 商品表 前端控制器
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Slf4j
@Controller
public class TbProductController {
  @Autowired
  private TbProductServiceImpl tbProductService;
 

  //跳转到商品列表
  @GetMapping("product/list")
  public String productList() {
    return "/admin/product/product-list";
  }


  //展示所有商品
  @PostMapping("product/list")
  @ResponseBody
  public ModelMap list(@RequestBody Page<TbProduct> tbProductPage) {
    IPage<TbProduct> page = tbProductService.page(tbProductPage);
    return ReturnUtil.success("ok", page, null);
  }


  //表单页面
  @GetMapping({"product/form"})
  public String showForm(TbProduct tbProduct, Model model) {
    TbProduct product = new TbProduct();
    if (!StringUtils.isEmpty(tbProduct.getId())) {
      product = tbProductService.getById(tbProduct.getId());
    }
    model.addAttribute("product", product);
    return "/admin/product/form";
  }

  //管理员新增/保存
  @PostMapping("product/save")
  @ResponseBody
  public ModelMap saveAdmin(TbProduct tbProduct) {
    
    return saveproduct(tbProduct);
  }


  //商品删除
  @GetMapping("product/del")
  @ResponseBody
  public ModelMap del(@RequestParam("id") String id) {
    boolean b = tbProductService.removeById(id);
    if (b) {
      return ReturnUtil.success("删除成功", null, null);
    }
    return ReturnUtil.error("删除失败", null, null);
  }

  //TODO:经常使用，后期改成泛型的，用反射改造
  public ModelMap saveproduct(TbProduct tbProduct) {
    if (!StringUtils.isEmpty(tbProduct.getId())) {
      log.info("此商品存在，本次操作为：更新商品");
      //设置最后更新时间
      tbProduct.setLastEditTime(LocalDateTime.now());
      boolean save = tbProductService.updateById(tbProduct);
      if (save) {
        return ReturnUtil.success("更新成功", null, null);
      } else {
        return ReturnUtil.error("更新失败", null, null);
      }
    } else {
      log.info("商品不存在，本次操作为：新增商品");
      tbProduct.setCreateTime(LocalDateTime.now());
      tbProduct.setLastEditTime(LocalDateTime.now());
      boolean save = tbProductService.save(tbProduct);
      if (save) {
        return ReturnUtil.success("保存成功", null, "product-list");
      } else {
        return ReturnUtil.error("操作失败", null, null);
      }
    }
  }
}
