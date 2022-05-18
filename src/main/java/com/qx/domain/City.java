package com.qx.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: ZedQ
 * @Date: 2022/5/18 22:39
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class City {

    private String name;
    private Integer nowConfirm;
    private Integer dead;
}
