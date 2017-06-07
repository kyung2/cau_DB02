package com.example.eom.dbapp.vo;

import java.util.Date;

/**
 * Created by hyunkyung on 2017-06-08.
 */

public class WalfareServiceData {
    public int id;
    public String name, purpose;
    public int center_num;
    public String center_name, operator,operator_org;
    public Date op_start_data,op_end_date;

    public WalfareServiceData(int id, String name, String purpose,int center_num,
                              String center_name, String operator, String operator_org,
                              Date op_start_data, Date op_end_date) {
        this.id =id;
        this.name = name;
        this.purpose = purpose;
        this.center_num = center_num;
        this.center_name = center_name;
        this.operator = operator;
        this.operator_org = operator_org;
        this.op_start_data =op_start_data;
        this.op_end_date = op_end_date;
    }

    public static WalfareServiceData getSampleWalfareServiceData() {
        return new WalfareServiceData(1, "애새기키즈카페", "살려줘",122,"벌리 ", "dhvpfldjxmsmsahiu","시;발 ",
                new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));
    }

}
