package com.lirui.yachtmall.utils;


import java.util.Random;

public class RandomUtils {

  /**
   * 随机生成固定长度的、由大小写字母和数字构成的用户名，
   *
   * @param length 长度
   * @return 随机用户名
   */
  public static String generateUserName(int length) {
    Random random = new Random();

    int alph;
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      //0大写、1小写、2数字
      int type = random.nextInt(3);
      switch (type) {
        case 0:
          alph = random.nextInt(26) + 65;
          break;
        case 1:
          alph = random.nextInt(26) + 97;
          break;
        default:
          alph = random.nextInt(10) + 48;
          break;
      }
      stringBuilder.append((char) alph);
    }
    return new String(stringBuilder);
  }

  /**
   * 生成随机姓名 2-3个字
   *
   * @return 随机姓名
   */
  public static String generateTrueName() {
    Random random = new Random();
    char[] words = {
        '赵', '钱', '孙', '李', '周', '吴', '郑', '王', '冯', '陈', '褚', '卫', '蒋', '沈', '韩', '杨', '朱', '秦',
        '尤', '许', '何', '吕', '施', '张', '孔', '曹', '严', '华', '金', '魏', '陶', '姜', '戚', '谢', '邹', '喻',
        '柏', '水', '窦', '章', '云', '苏', '潘', '葛', '奚', '范', '彭', '郎', '鲁', '韦', '昌', '马', '苗', '凤',
        '花', '方', '俞', '任', '袁', '柳', '酆', '鲍', '史', '唐', '费', '廉', '岑', '薛', '雷', '贺', '倪', '汤',
        '滕', '殷', '罗', '毕', '郝', '邬', '安', '常', '乐', '于', '时', '傅', '皮', '卞', '齐', '康', '伍', '余',
        '元', '卜', '顾', '孟', '平', '黄', '和', '穆', '萧', '尹', '姚', '邵', '湛', '汪', '祁', '毛', '禹', '狄',
        '米', '贝', '明', '臧', '计', '伏', '成', '戴', '谈', '宋', '茅', '庞', '熊', '纪', '舒', '屈', '项', '祝',
        '董', '梁', '杜', '阮', '蓝', '闵', '席', '季', '麻', '强', '贾', '路', '娄', '危'
    };
    //确定名字长度 2-3个字
    int length = random.nextInt(2) + 2;
    StringBuilder stringBuilder = new StringBuilder();
    for (int i = 0; i < length; i++) {
      //选择姓氏表中的随机位置
      int index = random.nextInt(words.length);
      stringBuilder.append(words[index]);
    }
    return new String(stringBuilder);
  }

}
