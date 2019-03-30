package com.lirui.yachtmall.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lirui.yachtmall.entity.TbNews;
import com.lirui.yachtmall.service.impl.TbNewsServiceImpl;
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
 * 信息发布表 前端控制器
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Slf4j
@Controller
public class TbNewsController {
  @Autowired
  private TbNewsServiceImpl tbNewsService;


  /**
   * 跳转到文章列表
   */
  @GetMapping("news/list")
  public String newsList() {
    return "/admin/news/news-list";
  }


  /**
   * 分页条件查询符合条件的所有文章，JSON格式返回
   */
  @PostMapping("news/list")
  @ResponseBody
  public ModelMap list(@RequestBody Page<TbNews> tbNewsPage) {
    IPage<TbNews> page = tbNewsService.page(tbNewsPage);
    return ReturnUtil.success("ok", page, null);
  }


  /**
   * 跳转到表单页面，如果传入的对象不是null，获取对象的所有信息，反填到表单中
   *
   * @param tbNews 表单传入的User对象
   */
  @GetMapping({"news/edit"})
  public String showForm(TbNews tbNews, Model model) {
    TbNews news = new TbNews();
    if (!StringUtils.isEmpty(tbNews.getId())) {
      news = tbNewsService.getById(tbNews.getId());
    }
    model.addAttribute("news", news);
    return "/admin/news/edit";
  }

  /**
   * 文章新增/保存
   *
   * @param tbNews 表单传入的User对象
   */
  @PostMapping("news/save")
  @ResponseBody
  public ModelMap saveAdmin(TbNews tbNews) {

    return savenews(tbNews);
  }


  /**
   * 根据id删除文章
   */
  @GetMapping("news/del")
  @ResponseBody
  public ModelMap del(@RequestParam("id") String id) {
    boolean b = tbNewsService.removeById(id);
    if (b) {
      return ReturnUtil.success("删除成功", null, null);
    }
    return ReturnUtil.error("删除失败", null, null);
  }

  /**
   * 具体的保存方法，根据id判断文章是否存在，存在执行update，否则执行insert
   *
   * @param tbNews 表单传入的News对象
   */
  //TODO:经常使用，后期改成泛型的，用反射改造
  public ModelMap savenews(TbNews tbNews) {
    if (!StringUtils.isEmpty(tbNews.getId())) {
      log.info("此文章存在，本次操作为：更新文章");
      //设置最后更新时间
      tbNews.setLastEditTime(LocalDateTime.now());
      boolean save = tbNewsService.updateById(tbNews);
      if (save) {
        return ReturnUtil.success("更新成功", null, null);
      } else {
        return ReturnUtil.error("更新失败", null, null);
      }
    } else {
      log.info("文章不存在，本次操作为：新增文章");
      tbNews.setCreateTime(LocalDateTime.now());
      tbNews.setLastEditTime(LocalDateTime.now());
      boolean save = tbNewsService.save(tbNews);
      if (save) {
        return ReturnUtil.success("保存成功", null, "news-list");
      } else {
        return ReturnUtil.error("操作失败", null, null);
      }
    }
  }
}
