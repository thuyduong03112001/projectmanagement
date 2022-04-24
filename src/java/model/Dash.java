package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Dash {
    private String date;
    private double type1;
    private double type2;
    private double type3;
    private double type4;
    private double type5;
    
    public Dash() {
    }

    public Dash(String date, double type1, double type2, double type3) {
        this.date = date;
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getType1() {
        return type1;
    }

    public void setType1(double type1) {
        this.type1 = type1;
    }

    public double getType2() {
        return type2;
    }

    public void setType2(double type2) {
        this.type2 = type2;
    }

    public double getType3() {
        return type3;
    }

    public void setType3(double type3) {
        this.type3 = type3;
    }

    public double getType4() {
        return type4;
    }

    public void setType4(double type4) {
        this.type4 = type4;
    }

    public double getType5() {
        return type5;
    }

    public void setType5(double type5) {
        this.type5 = type5;
    } 
    
    public String getRealDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(date);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }

    @Override
    public String toString() {
        try {
            return getRealDate() + "-" + getType1() + "-" + getType2() + "-" + getType3();
        } catch (ParseException ex) {
            Logger.getLogger(Dash.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.toString();
    } 
}
