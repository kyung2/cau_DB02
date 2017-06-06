package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-05.
 */

/**
 * <String> 유치원이름,시도,시군구,주소,전화번호
 *     string name : 유치원 이름
 *     string sido, string sigu 시도,시군구
 *     String tel_num,fax_num;
 *
 *
 *
 * <Int> 우편번호,놀이터수,보육실수,정원,총 시설규모
 *     int zipcode : 우편번호
 *     i
 *
 *
 *
 *
 * <Boolean>
 *
 *
 *<Date>
 *
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



public class PreSchoolData {
    public String name, address, phone_num,id;
    public PreSchoolData(String name, String address, String phone_num){
        this.name = name;
        this.address = address;
        this.phone_num = phone_num;
    }
}
