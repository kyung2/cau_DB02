package com.example.eom.dbapp.vo;

/**
 * Created by sangwon0001 on 2017-06-08.
 */

public class IdAndString {
    public int id;
    public String text;
    public int type;

    public static final int ChildCareCenterListActivity =0;
    public static final int HospitalListActivity =1;
    public static final int PlayFacilityListActivity =2;
    public static final int SafeAreaListActivity =3;
    public static final int TrafficAccidentAreaListActivity =4;
    public static final int WalfareServiceListActivity =5;




    public IdAndString(int id, String text, int type) {
        this.id = id;
        this.text = text;
        this.type= type;
    }
}
