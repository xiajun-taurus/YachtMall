package com.lirui.yachtmall.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lirui.yachtmall.dao.TbUserDao;
import com.lirui.yachtmall.entity.TbUser;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import javafx.scene.control.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
public interface TbUserService extends IService<TbUser> {

  TbUser getByUsr(String usr);
}
