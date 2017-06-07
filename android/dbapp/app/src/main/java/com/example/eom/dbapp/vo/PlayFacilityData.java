package com.example.eom.dbapp.vo;

import java.util.Date;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class PlayFacilityData {
    public int id;
    public String name, tel, si_do, si_gun_gu, address;
    public int postcode;
    public Date install_date;
    public String install_place, public_private, indoor_outdoor, is_excellent;

    public double longitude, latitude;

    public PlayFacilityData(String name) {
        this.name = name;
    }

    public PlayFacilityData(int id, String name, String tel,
                            String si_do, String si_gun_gu, String address,
                            int postcode, Date install_date, String install_place,
                            String public_private, String indoor_outdoor, String is_excellent,
                            double longitude, double latitude) {
        this.id = id;
        this.name = name;
        //ㅇㅁㄴㅇㅁ

        this.tel = tel;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.address = address;
        this.postcode = postcode;

        this.install_date = install_date;
        this.install_place = install_place;
        this.public_private = public_private;
        this.indoor_outdoor = indoor_outdoor;
        this.is_excellent = is_excellent;

        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static PlayFacilityData getSamplePlayFacilityData() {
        return new PlayFacilityData(1, "잘래", "1818", "졸려시", "졸려구", "졸려시졸려구",
                15498521, new Date(System.currentTimeMillis()), "5vl", "겅용 ", "gldgld", "fd", 45.4, 456);
    }

}




