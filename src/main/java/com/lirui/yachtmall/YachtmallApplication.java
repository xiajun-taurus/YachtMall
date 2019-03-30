package com.lirui.yachtmall;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * <p>
 * 启动类
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-09
 */
@SpringBootApplication
@MapperScan("com.lirui.yachtmall")
public class YachtmallApplication {

  public static void main(String[] args) {
    SpringApplication.run(YachtmallApplication.class, args);
  }
}
