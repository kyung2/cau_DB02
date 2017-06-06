package com.example.eom.dbapp.vo;

import java.util.Date;

/**
 * Created by hyunkyung  on 2017-06-06.
 */

public class AccidentData {

    /**
     * String : accident_injury_death , cause,type,indoor_outdoor
     * Date : accident_date
     * Preeshcool : accident_where
     */

    public PreSchoolData accident_where;
    public String accident_injuryordeath,accident_cause,accident_type,indoor_outdoor;
    public Date accident_date;
    public int id;

    public AccidentData(int id){
        this.id = id;
    }
    public AccidentData (int id, Date accident_date, PreSchoolData accident_where , String accident_injuryordeath, String accident_cause, String accident_type, String indoor_outdoor ) {

        this.id=id;
        this.accident_date = accident_date;
        this.accident_where = accident_where;
        this.accident_injuryordeath = accident_injuryordeath;
        this. accident_cause = accident_cause;
        this.accident_type = accident_type;
        this.indoor_outdoor = indoor_outdoor;
    }


}
