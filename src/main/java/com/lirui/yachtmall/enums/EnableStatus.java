package com.lirui.yachtmall.enums;

import lombok.Getter;

@Getter
public enum EnableStatus {

  ABLE(1, "启用"), DISABLE(2, "禁用");

  private Integer value;
  private String desc;

  EnableStatus(Integer value, String desc) {
    this.value = value;
    this.desc = desc;
  }
}
