package com.qx.dao;

import com.qx.domain.CityDetail;

import java.util.ArrayList;


public interface UserDao {


    public void save(CityDetail user);

    public void saves(ArrayList<CityDetail> list);

}
