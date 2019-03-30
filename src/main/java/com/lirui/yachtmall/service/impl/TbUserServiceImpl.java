package com.lirui.yachtmall.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lirui.yachtmall.entity.TbUser;
import com.lirui.yachtmall.dao.TbUserDao;
import com.lirui.yachtmall.service.TbUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.sql.Wrapper;
import java.util.List;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Service
public class TbUserServiceImpl extends ServiceImpl<TbUserDao, TbUser> implements TbUserService {
  @Autowired
  private TbUserDao userDao;

  @Override
  public IPage<TbUser> page(IPage<TbUser> page) {
    return userDao.selectPage(page,null);
  }

  @Override
  public TbUser getByUsr(String usr) {
    QueryWrapper<TbUser> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("usr",usr);
    return userDao.selectOne(queryWrapper);
  }
}
