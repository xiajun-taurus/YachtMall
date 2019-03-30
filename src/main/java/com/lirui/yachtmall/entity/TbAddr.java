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
 * 收货地址表
 * </p>
 *
 * @author Li Rui
 * @since 2019-03-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_addr")
public class TbAddr implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 收货地址id
     */
    @TableId(value = "id",type = IdType.UUID)
    private String id;

    /**
     * 收件人
     */
    @TableField("addressee")
    private String addressee;

    /**
     * 收货地址
     */
    @TableField("addr")
    private String addr;

    /**
     * 收货人手机号
     */
    @TableField("phone")
    private String phone;

    /**
     * 该地址对应的用户，对应tb_user.id
     */
    @TableField("user_id")
    private String userId;

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
