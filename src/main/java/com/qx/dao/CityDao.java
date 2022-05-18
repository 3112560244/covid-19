package com.qx.dao;

import com.qx.domain.City;
import com.qx.domain.CityDay;
import com.qx.domain.CityDetail;

import java.util.ArrayList;


public interface CityDao {


    public void save(CityDetail user);

    public void saves(ArrayList<City> list);

    public ArrayList<CityDay> getAll();
}
