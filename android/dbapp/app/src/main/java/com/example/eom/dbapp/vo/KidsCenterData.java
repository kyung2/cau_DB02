package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-06.
 */

import java.util.Date;

/**
 * <String> name,tel,si_do,si_gun_gu,address
 * <Int>postcode,capacity</Int>
 * <Double>longitude, latitude</Double>
 * */

public class KidsCenterData {

    public String name, tel, si_do, si_gun_gu, address;
    public int postcode, capacity,id;
    public double longitude, latitude;

    public KidsCenterData(String name) {
        this.name = name;
    }
    public KidsCenterData(int id, String name,String si_do, String si_gun_gu, String tel,double latitude,double longitude){
        this.id= id;
        this.name=name;
        this.si_do= si_do;
        this.si_gun_gu=si_gun_gu;
        this.address=si_do+" "+si_gun_gu;
        this.tel=tel;
        this.latitude=latitude;
        this.longitude = longitude;
    }
    public KidsCenterData(String name, String tel, String address, String si_do, String si_gun_gu,
                          int postcode, int capacity, double longitude, double latitude) {
        this.name = name;
        this.tel = tel;
        this.address = address;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.postcode = postcode;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public static KidsCenterData getSampleKidsCenterData() {
        return new KidsCenterData("이름이름", "01089785892", "수원시영통구졸려도졸령동 ", "수원시", "영통구", 12346, 10, 12.5, 68.5);
    }

}
        
