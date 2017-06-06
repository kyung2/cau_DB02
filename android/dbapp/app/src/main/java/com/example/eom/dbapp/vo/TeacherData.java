package com.example.eom.dbapp.vo;

/**
 * Created by hyunkyung on 2017-06-06.
 *
 */

public class TeacherData {
    public String name;
    public PreSchoolData kindergarten;
    public String sex,type;
    public int id;

    public TeacherData(String name){
        this.name = name;
    }
    public TeacherData(int id, PreSchoolData kindergarten, String sex, String type) {
        this.id = id;
        this.kindergarten = kindergarten ;
        this.sex = sex;
        this.type= type;
    }
}
