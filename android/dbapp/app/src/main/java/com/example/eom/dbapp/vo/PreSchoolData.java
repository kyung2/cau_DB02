package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-05.
 */

import java.util.Date;

/**
 * <String> 유치원이름,시도,시군구,주소,전화번호(팩스), 어린이집유형
 *     string name : 유치원 이름
 *     string si_do, string si_gun_gu 시도,시군구
 *     String tel,fax;
 *     String address :주소
 *     String assess_certification_type;타입
 * <Int> 우편번호,놀이터수,보육실수,보육교직원수,현원,정원,총 시설규모
 *     postcode,playground_num,nursing_room_num,nursing_room_area,capacity,NumofCCTV
 *     int postcode : 우편번호
 *     int playground_num,nursing_room_num,nursing_room,area,capacity,NumofCCTV;
 *
 *<Double> 위도,경도
 * Double latitude,longitude 위도 경도
 * <Boolean>운영현황 통학차량여부
 * bool operation_state,school_bus;
 *
 *
 *<Date> 인가일자, 휴지시작일지,휴지종료일자 폐지일자
 * Permit,pause_S,pause_E,close
 *
 * Date pause_start_date,pause_end_date,close_date,permit_date;
 *
 *
 **/



public class PreSchoolData {
    public String name, address, si_do, si_gun_gu, tel,fax;
    public int id,postcode,assess_certification_type,playground_num,nursing_room_num,nursing_room_area,capacity,NumofCCTV;
    public Date pause_start_date,pause_end_date,close_date,permit_date;
    public String operation_state,school_bus,type;
    public double latitude,longitude;

    public PreSchoolData(String name, String address, String tel){
        this.name=name;
        this.address = address;
        this.tel=tel;
    }//values('id','name','si_do','si_gun_gu','tel','latitude','longitude')
    public PreSchoolData(int id, String name,String si_do, String si_gun_gu, String tel,double latitude,double longitude){
        this.id= id;
        this.name=name;
        this.si_do= si_do;
        this.si_gun_gu=si_gun_gu;
        this.address=si_do+" "+si_gun_gu;
        this.tel=tel;
        this.latitude=latitude;
        this.longitude = longitude;
    }
    public PreSchoolData(int id, String name, String type, String address,String si_do,String si_gun_gu,String tel ,
                         String fax, int assess_certification_type
                         , int postcode ,int capacity ,int playground_num , int nursing_room_num,
                         int nursing_room_area, int NumofCCTV,
                         Date pause_start_date  , Date pause_end_date,
                         Date permit_date , Date close_date,
                         double latitude, double longitude,
                         String operation_state , String school_bus) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
        this.tel=tel;
        this.fax = fax;

        //int
        this.assess_certification_type = assess_certification_type;
        this.postcode = postcode;
        this.capacity = capacity;
        this.playground_num = playground_num;
        this.nursing_room_area = nursing_room_area;
        this.nursing_room_num = nursing_room_num;
        this.NumofCCTV = NumofCCTV;

//date
        this.pause_start_date = pause_start_date;
        this.pause_end_date = pause_end_date;
        this.permit_date = permit_date;
        this.close_date = close_date;
//dpuble
        this.latitude =  latitude;
        this.longitude = longitude;

        this.operation_state = operation_state;
        this.school_bus = school_bus;

    }
    public static PreSchoolData getSamplePreschoolData(){
        return new PreSchoolData(11,"어린이가 잘 자라는 유치원","허가","서울시 어디구 무슨동",
                "서울시","어디구","010-1234-5678","02-1234-5678",1,1,312,3,1,1,1,

                new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()),
                36.5,120.5,"영업","운영");
    }
}
