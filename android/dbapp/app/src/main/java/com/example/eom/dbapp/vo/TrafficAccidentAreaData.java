package com.example.eom.dbapp.vo;

import java.util.Date;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class TrafficAccidentAreaData {
    public String si_do, si_gun_gu, address;
    public int law_code, id;
    public String police_office, near_school;
    public int occur_cnt, death_cnt, serious_cnt, light_cnt;
    public double longitude, latitude;

    public TrafficAccidentAreaData(int id, String si_do, String si_gun_gu, String address,
                                   int law_code, String police_office, String near_school,
                                   int occur_cnt, int death_cnt, int serious_cnt, int light_cnt,
                                   double longitude, double latitude) {
        this.id = id;

        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.address = address;

        this.law_code = law_code;

        this.police_office = police_office;
        this.near_school = near_school;

        this.occur_cnt = occur_cnt;
        this.death_cnt = death_cnt;
        this.serious_cnt = serious_cnt;
        this.light_cnt = light_cnt;

        this.longitude = longitude;
        this.latitude = latitude;
    }

    public static TrafficAccidentAreaData getSampleTrafficAccidentAreaData() {
        return new TrafficAccidentAreaData(1, "졸려", "졸리", "ㅇㄹㄴㅇㄹ", 11111, "ㄹㄴㅇㄹ", "ㅇㄹㄴㄹㄴ",
                1, 1, 1, 1, 45.5, 456);
    }
}
