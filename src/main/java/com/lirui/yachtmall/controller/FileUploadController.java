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
@Slf4j
@Controller
@RequestMapping("img")
//图片上传控制器
public class FileUploadController {
  @PostMapping("upload")
  @ResponseBody
  public ModelMap uploadImg(@RequestParam("img")MultipartFile file,HttpServletRequest request){
    if (!file.isEmpty()) {
      String saveFileName = file.getOriginalFilename();
      File saveFile = new File("upload/" + saveFileName);
      log.info("文件路径:=>{}",saveFile.getPath());
      if (!saveFile.getParentFile().exists()) {
        saveFile.getParentFile().mkdirs();
      }
      try {
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(saveFile));
        out.write(file.getBytes());
        out.flush();
        out.close();
        HashMap<String, String> map = new HashMap<>();
        map.put("path",saveFile.getPath());
        return ReturnUtil.success("上传成功",map);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        return ReturnUtil.error("上传失败",e.getMessage());
      } catch (IOException e) {
        e.printStackTrace();
        return ReturnUtil.error("上传失败",e.getMessage());
      }
    } else {
      return ReturnUtil.error("上传失败，文件为空");
    }

  }
  @GetMapping("upload")
  public String upload(){
    return "/admin/user/upload";
  }
}
