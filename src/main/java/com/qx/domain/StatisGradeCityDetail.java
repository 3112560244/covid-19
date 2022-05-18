package com.qx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Author: ZedQ
 * @Date: 2022/5/17 18:49
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisGradeCityDetail {
    private String date;
    private int wzz_add;
    private String sdate;
    private String city;
    private int dead;
    private Date mtime;
    private int confirm;
    private int confirmAdd;
    private String province;
    private String grade;
    private int heal;
    private int nowConfirm;
    private int syear;


}

