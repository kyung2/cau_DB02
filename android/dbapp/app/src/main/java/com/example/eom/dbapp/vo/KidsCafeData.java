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

    String name, address, phone_num, sido,sigun, business_operator_name;
    int id, zipcode, facility_size;
    Date authDate;
    boolean state, multiuser;
    double latitude, longitude; //위도경도


    public KidsCafeData(int id, String name, String address, String phone_num,int zipcode,int facility_size,String business_operator_name,
                        String sido,String sigun,boolean state, boolean multiuser, double latitude , double longitude, Date authDate) {
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
        this.id = id;
        this.zipcode = zipcode;
        this.sido = sido;
        this.sigun =sigun;
        this.business_operator_name = business_operator_name;
        this.state = state;
        this.multiuser = multiuser;
        this.latitude = latitude;
        this.longitude = longitude;
        this.facility_size = facility_size;
        this.authDate = authDate;

    }
}
