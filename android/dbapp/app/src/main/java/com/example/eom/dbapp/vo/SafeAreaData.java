package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class SafeAreaData {
    public int id;
    public String si_do, si_gun_gu, address, facility_name, management_agency, police_office;
    public int NumofCCTV;
    public String road_width;
    public int postcode;
    public double longitude, latitude;

    public SafeAreaData(int id, String si_do, String si_gun_gu, String address, String facility_name,
                        String management_agency, String police_office, int NumofCCTV, String road_width, int postcode,
                        double longitude, double latitude) {
        this.id = id;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.address = address;
        this.facility_name = facility_name;
        this.management_agency = management_agency;
        this.police_office = police_office;
        this.NumofCCTV = NumofCCTV;
        this.road_width = road_width;
        this.postcode = postcode;
        this.longitude = longitude;
        this.latitude = latitude;

    }

    public static SafeAreaData getSampleSafeAreaData() {
        return new SafeAreaData(1, "수원시", "영통구", "fsdfsd", "fsdfs", "fsdf", "Fsdf", 1, "fsdf", 1, 12.5, 68.5);


    }
}

