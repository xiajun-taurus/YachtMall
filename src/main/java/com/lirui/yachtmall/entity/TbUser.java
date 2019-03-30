package com.lirui.yachtmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_user")
public class TbUser implements Serializable {

  private static final long serialVersionUID = 1L;

  /**
   * 用户实体id
   */
  @TableId(value = "id", type = IdType.UUID)
  private String id;

  /**
   * 账户
   */
  @TableField("usr")
  private String usr;

  /**
   * 密码
   */
  @TableField("pwd")
  private String pwd;

  /**
   * 用户名，用来显示
   */
  @TableField("name")
  private String name;

  /**
   * 是否可用 1：可用，0：不可用
   */
  @TableField("enable_status")
  private Integer enableStatus;

  /**
   * 用户角色，1.顾客；2.企业用户；3.管理员；4.超级管理员
   */
  @TableField("user_type")
  private Integer userType;

  /**
   * 创建时间
   */
  @TableField("create_time")
  private LocalDateTime createTime;

  /**
   * 最后修改时间
   */
  @TableField("last_edit_time")
  private LocalDateTime lastEditTime;


}
