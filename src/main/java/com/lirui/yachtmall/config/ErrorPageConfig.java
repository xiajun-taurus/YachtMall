package com.lirui.yachtmall.config;

import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * 错误页面配置
 */
@Configuration
public class ErrorPageConfig {
  @Bean
  public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer(){
    return (factory -> {
      ErrorPage errorPage404 = new ErrorPage(HttpStatus.NOT_FOUND,"pages-error-404.html");
    });
  }
}
