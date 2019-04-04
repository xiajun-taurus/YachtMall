package com.lirui.yachtmall.config;

import com.lirui.yachtmall.shiro.ShiroRealm;
import java.util.LinkedHashMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

@Slf4j
@Configuration
public class ShiroConfig {

  /**
   * 创建ShiroFilterFactoryBean
   */
  @Bean(name = "shiroFilterFactoryBean")
  public ShiroFilterFactoryBean getShiroFilterFactoryBean(
      @Qualifier("defaultWebSecurityManager") DefaultWebSecurityManager defaultWebSecurityManager) {
    ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
    //设置安全管理器
    shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);
    //添加shiro内置过滤器
        /*
         * anon:表示可以匿名使用。
           authc:表示需要认证(登录)才能使用，没有参数
           roles：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，当有多个参数时，例如admins/user/**=roles["admin,guest"],每个参数通过才算通过，相当于hasAllRoles()方法。
           perms：参数可以写多个，多个时必须加上引号，并且参数之间用逗号分割，例如/admins/user/**=perms["user:add:*,user:modify:*"]，当有多个参数时必须每个参数都通过才通过，想当于isPermitedAll()方法。
           rest：根据请求的方法，相当于/admins/user/**=perms[user:method] ,其中method为post，get，delete等。
           port：当请求的url的端口不是8081是跳转到schemal://serverName:8081?queryString,其中schmal是协议http或https等，serverName是你访问的host,8081是url配置里port的端口，queryString是你访问的url里的？后面的参数。
           authcBasic：没有参数表示httpBasic认证
           ssl:表示安全的url请求，协议为https
           user:当登入操作时不做检查
         */
    //使用linkedHashMap保证有序
    LinkedHashMap<String, String> filter = new LinkedHashMap<>();
    filter.put("/assets/**","anon");
    filter.put("/css/**","anon");
    filter.put("/js/**","anon");
    filter.put("/scss/**","anon");
    filter.put("/logout","logout");
    filter.put("/**","authc");
    //被拦截返回登录页面
    shiroFilterFactoryBean.setLoginUrl("/login");
    //授权拦截返回页面
    shiroFilterFactoryBean.setUnauthorizedUrl("/noPermission");
    shiroFilterFactoryBean.setFilterChainDefinitionMap(filter);
    return shiroFilterFactoryBean;

  }

  /**
   * 创建DefaultSecurityManager
   */
  @Bean(name = "defaultWebSecurityManager")
  public DefaultWebSecurityManager getDefaultSecurityManager(
      @Qualifier("shiroRealm") ShiroRealm realml) {
    DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
    defaultWebSecurityManager.setRealm(realml);
    return defaultWebSecurityManager;
  }

  /**
   * 创建自定义Realm
   */
  @Bean(name = "shiroRealm")
  public ShiroRealm getShiroRealm() {
    return new ShiroRealm();
  }

  /**
   * 下面的代码是添加注解支持
   */
  @Bean
  @DependsOn("lifecycleBeanPostProcessor")
  public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
    DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
    // 强制使用cglib，防止重复代理和可能引起代理出错的问题
    // https://zhuanlan.zhihu.com/p/29161098
    defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
    return defaultAdvisorAutoProxyCreator;
  }

  @Bean
  public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
    return new LifecycleBeanPostProcessor();
  }

  @Bean
  public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
      DefaultWebSecurityManager securityManager) {
    AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
    advisor.setSecurityManager(securityManager);
    return advisor;
  }
}
