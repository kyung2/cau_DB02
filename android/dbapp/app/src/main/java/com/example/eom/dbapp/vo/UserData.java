package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-06.
 */


/**
 * <String>user_id,user_pw,nick_name,si_do,si_gun_gu
 *int : preSchool_id
 * */

public class UserData {

    public String user_id,user_pw,nick_name, si_do, si_gun_gu;
    public int preSchool_id;

    public UserData(String user_id){
        this.user_id= user_id;
    }
    public UserData(String user_id,int preSchool_id,String user_pw, String nick_name, String si_do , String si_gun_gu ){
        this.user_id= user_id;
        this.preSchool_id = preSchool_id;
        this.user_pw = user_pw;
        this.nick_name = nick_name;
        this.si_do = si_do;
        this.si_gun_gu = si_gun_gu;
    }
}
