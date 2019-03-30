package com.lirui.yachtmall.enums;

import lombok.Getter;

@Getter
public enum Role {
  //  1.普通用户 2.企业用户 3.管理员 4.超级管理员
  USER(1, "普通用户"), ENTERPRISE(2, "企业用户"), ADMIN(3, "管理员"), SUPERADMIN(4, "超级管理员");

  private Integer userType;
  private String desc;

  Role(int userType, String desc) {
    this.userType = userType;
    this.desc = desc;
  }

}
