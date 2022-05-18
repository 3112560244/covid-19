package com.qx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: ZedQ
 * @Date: 2022/5/17 22:45
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CityDetail {
    //更新时间
    private Date mtime;
    //年份
    private int syear;
    //月天
//    @JsonFormat(pattern = "MM/dd",timezone = "Asia/Shanghai")
//    private Date date;
    //月天
    private String date;
    //省份
    private String province;
    //市区
    private String city;

    //新增确诊
    private int confirmAdd;
    //新增无症状
    private int wzz_add;
    //现存确诊
    private int nowConfirm;


    //累计确诊
    private int confirm;
    //累计治愈
    private int heal;
    //累计死亡
    private int dead;

}
