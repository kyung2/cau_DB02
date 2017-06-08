package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class ChildCareCenterData {

    public int id;


    public String name, tel;
    public int postcode;
    public String si_do, si_gun_gu, address;
    public double longitude, latitude;

    public ChildCareCenterData(int id, String name, String tel, int postcode,
                               String si_do, String si_gun_gu, String address,
                               double longitude, double latitude) {
        this.id = id;
        this.name = name;
        this.tel = tel;
        this.postcode = postcode;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.address = address;

        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static ChildCareCenterData getSampleChildCareCenterData() {
        return new ChildCareCenterData(1, "이름이름", "114", 123, "수원시영통구졸려도졸령동 ", "수원시", "영통구",
                12346, 10);


    }
}

