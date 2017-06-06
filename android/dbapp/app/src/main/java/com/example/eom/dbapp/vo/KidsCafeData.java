package com.example.eom.dbapp.vo;

import java.util.Date;

/**
 * Created by hyunkyung on 2017-06-05.
 */

public class KidsCafeData {
    /**
    name : 키즈카페이름
    id : 번호(고유)
    sido: 시도
    sigun : 시군구
     business_operator_name :사업자명
     zipcode :소새지 우편번호
     address  :소재지지번주소
     autoDate  :인허가일자 (ㅇdate)
      state ㅣ:영업상태명(운영중 TF)
     multiuser  다중 이용 업소 여부 :TF
    facility_size   총 시설규모 (m^2)
     (Remove: ) 위생업조명(일반음식점이냐 아니냐
     (Remove:  위생업태명 (키즈카페)
     double : latitude,longitude  위도경도 double
    **/

    public String name, address, phone_num, si_do,si_gun_gu,operation_state, multi_use,hygiene_name;
    public int id, postcode,facility_size;
    public Date permit_date;
    public double latitude, longitude; //위도경도

    public KidsCafeData(int id, String name, String address, String phone_num){
        this.id = id;
        this.name=name;
        this.address=address;
        this.phone_num=phone_num;
    }

    public KidsCafeData(int id, String name, String address, String phone_num,int postcode,int facility_size,String hygiene_name,
                        String si_do,String si_gun_gu,String operation_state, String multi_use, double latitude , double longitude, Date permit_date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
        this.postcode = postcode;
        this.si_do = si_do;
        this.si_gun_gu =si_gun_gu;
        this.hygiene_name = hygiene_name;
        this.operation_state = operation_state;
        this.multi_use = multi_use;
        this.latitude = latitude;
        this.longitude = longitude;
        this.facility_size = facility_size;
        this.permit_date = permit_date;

    }
}
