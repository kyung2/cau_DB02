package com.example.eom.dbapp.vo;

import java.util.Date;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class HospitalData {
    public int id;
    public String name, tel;
    public int postcode;
    public String si_do, si_gun_gu, address;
    public Date designated_date;
    public double longitude, latitude;

    public HospitalData(int id, String name, String tel,int postcode,
                              String si_do, String si_gun_gu, String address,
                              Date designated_date, double longitude, double latitude) {
        this.id =id;
        this.name = name;
        this.tel = tel;
        this.postcode = postcode;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.address = address;
        this.designated_date =designated_date;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static HospitalData getSampleHospitalData() {
        return new HospitalData(1, "애새기키즈카페", "23",122,"수원시", "영통구 영통동", "풀주소", new Date(System.currentTimeMillis()),
                125.5, 14);
    }

}
