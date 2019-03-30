package com.lirui.yachtmall.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 商铺表
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_shop")
public class TbShop implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商铺id
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    /**
     * 用户id
     */
    @TableField("owner")
    private String owner;

    /**
     * 商铺名称
     */
    @TableField("shop_name")
    private String shopName;

    /**
     * 商铺简介
     */
    @TableField("shop_desc")
    private String shopDesc;

    /**
     * 是否可用 1：可用，0：不可用
     */
    @TableField("enable_status")
    private Integer enableStatus;

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
