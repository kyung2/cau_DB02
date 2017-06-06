package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-06.
 */

/**
 * <String> name,tel,si_do,si_gun_gu,address
 * <Int>postcode,capacity</Int>
 * <Double>longitude, latitude</Double>
 * */

public class KidsCenter {

    public String name,tel,si_do,si_gun_gu,address;
    public int postcode,capacity;
    public double longitude,latitude;

    public KidsCenter(String name){
        this.name=  name;
    }
    public KidsCenter(String name,String tel, String address , String si_do , String si_gun_gu,
                int postcode, int capacity , double longitude , double latitude){
        this.name=name;
        this.tel = tel;
        this.address = address;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.postcode = postcode;
        this.capacity = capacity;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
