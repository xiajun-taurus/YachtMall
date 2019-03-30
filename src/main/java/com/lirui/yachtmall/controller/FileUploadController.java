package com.lirui.yachtmall.controller;

import com.lirui.yachtmall.utils.ReturnUtil;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件上传 前端控制器
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Slf4j
@Controller
@RequestMapping("upload")
public class FileUploadController {

  /**
   * 图片上传
   *
   * @param file 前端获得的文件对象
   */
  @PostMapping("img")
  @ResponseBody
  public ModelMap uploadImg(@RequestParam("img") MultipartFile file) {
    if (!file.isEmpty()) {
      //获取原文件名
      String saveFileName = file.getOriginalFilename();
      //新建文件对象，用来存储文件
      //TODO 上传路径应该可以配置，保存的文件名应该有统一规则
      File saveFile = new File("upload/" + saveFileName);
      log.info("文件路径:=>{}", saveFile.getPath());
      //如果路径不存在创建路径
      if (!saveFile.getParentFile().exists()) {
        saveFile.getParentFile().mkdirs();
      }
      try {
        //将文件保存
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
        out.write(file.getBytes());
        out.flush();
        out.close();
        //获得文件路径，返回给前端
        HashMap<String, String> map = new HashMap<>();
        map.put("path", saveFile.getPath());
        return ReturnUtil.success("上传成功", map);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        return ReturnUtil.error("上传失败", e.getMessage());
      } catch (IOException e) {
        e.printStackTrace();
        return ReturnUtil.error("上传失败", e.getMessage());
      }
    } else {
      return ReturnUtil.error("上传失败，文件为空");
    }

  }

  @GetMapping("upload")
  public String upload() {
    return "/admin/user/upload";
  }
}
