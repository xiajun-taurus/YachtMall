package com.lirui.yachtmall.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lirui.yachtmall.entity.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import javafx.scene.control.Pagination;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */

public interface TbUserDao extends BaseMapper<TbUser> {

}
