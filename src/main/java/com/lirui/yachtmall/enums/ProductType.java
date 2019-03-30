package com.lirui.yachtmall.enums;

import lombok.Getter;

@Getter
public enum ProductType {
  YACHT(1,"游艇"),ITEM(2,"零件");

  private Integer type;
  private String desc;

  ProductType(Integer type, String desc) {
    this.type = type;
    this.desc = desc;
  }
}
