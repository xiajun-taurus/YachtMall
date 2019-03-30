package com.lirui.yachtmall.dao;

import static org.junit.Assert.*;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lirui.yachtmall.entity.TbUser;
import com.lirui.yachtmall.utils.RandomUtils;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
public class TbUserDaoTest {
  @Autowired
  private TbUserDao tbUserDao;
  @Test
  @Ignore
  public void testPage(){
    Page<TbUser> tbUserPage = new Page<>();
    tbUserPage.setCurrent(0);
    tbUserPage.setSize(5);
    IPage<TbUser> tbUserIPage = tbUserDao.selectPage(tbUserPage, null);
    System.out.println(tbUserIPage);
  }

  /**
   * 测试生成100个用户
   */
  @Test
  @Ignore
  public void createUsers(){
    for (int i = 0; i < 100; i++) {
      TbUser tbUser = new TbUser();
      //设置8位随机用户名
      tbUser.setUsr(RandomUtils.generateUserName(8));
      tbUser.setUserType(1);
      //设置随机姓名
      tbUser.setName(RandomUtils.generateTrueName());
      tbUser.setCreateTime(LocalDateTime.now());
      tbUser.setLastEditTime(LocalDateTime.now());
      tbUserDao.insert(tbUser);
    }
  }

  @Test
  @Ignore
  public void deleteAll(){
    tbUserDao.delete(null);
  }
}