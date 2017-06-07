package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class ElmSchoolData {

    public int id;
    public String name, tel;
    public int postcode;
    public String si_do, si_gun_gu, address, homepage, public_private;
    public double longitude, latitude;

    public ElmSchoolData(String name) {
        this.name = name;
    }

    public ElmSchoolData(int id, String name, String tel,
                         int postcode, String si_do, String si_gun_gu, String address,
                         String homepage, String public_private, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.tel = tel;

        this.postcode = postcode;

        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.address = address;
        this.homepage = homepage;
        this.public_private = public_private;
        this.latitude = latitude;
        this.longitude = longitude;

    }


    public static ElmSchoolData getSampleElmSchoolData() {
        return new ElmSchoolData(1, "이름이름", "4156", 13, "수원시영통구졸려도졸령동 ", "수원시", "영통구", "ㅈㅈㅈ.ㅊ며"
                , " 서ㅏ립", 48.51, 565);
    }


}

