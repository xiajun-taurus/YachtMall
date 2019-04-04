package com.lirui.yachtmall.shiro;

import com.lirui.yachtmall.entity.TbUser;
import com.lirui.yachtmall.service.impl.TbUserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class ShiroRealm extends AuthorizingRealm {
  @Autowired
  private TbUserServiceImpl userService;

  /**
   * 授权
   * @param principals
   * @return
   */
  @Override
  protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    log.info("授权");
    return null;
  }

  /**
   * 默认使用此方法进行用户名正确与否验证，错误抛出异常
   * @param token
   * @return
   * @throws AuthenticationException
   */
  @Override
  protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
      throws AuthenticationException {
    log.info("执行验证----");
    String username = (String) token.getPrincipal();
    TbUser tbUser = userService.getByUsr(username);
    if (tbUser == null) {
      throw new UnknownAccountException("没有此用户->"+username);
    }

    return new SimpleAuthenticationInfo(tbUser.getUsr(),tbUser.getPwd(),getName());
  }
}
