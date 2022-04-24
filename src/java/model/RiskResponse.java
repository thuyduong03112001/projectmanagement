/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author HP
 */
public class RiskResponse {
    private int id;
    private int resID;
    private String action;
    private Date from;
    private Date to;
    private String newPossibility;
    private String newImpact;
    private String Dfrom;
    private String Dto;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public int getResID() {
        return resID;
    }

    public void setResID(int resID) {
        this.resID = resID;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getNewPossibility() {
        return newPossibility;
    }

    public void setNewPossibility(String newPossibility) {
        this.newPossibility = newPossibility;
    }

    public String getNewImpact() {
        return newImpact;
    }

    public String getDfrom() {
        return Dfrom;
    }

    public void setDfrom(String Dfrom) {
        this.Dfrom = Dfrom;
    }

    public String getDto() {
        return Dto;
    }

    public void setDto(String Dto) {
        this.Dto = Dto;
    }



    public void setNewImpact(String newImpact) {
        this.newImpact = newImpact;
    }
     
    public RiskResponse() {
    }

    public RiskResponse(int id, int resID, String action, Date from, Date to, String newPossibility, String newImpact) {
        this.id = id;
        this.resID = resID;
        this.action = action;
        this.from = from;
        this.to = to;
        this.newPossibility = newPossibility;
        this.newImpact = newImpact;
    }
    public RiskResponse(int id, int resID, String action, String from, String to, String newPossibility, String newImpact) {
        this.id = id;
        this.resID = resID;
        this.action = action;
        this.Dfrom = from;
        this.Dto = to;
        this.newPossibility = newPossibility;
        this.newImpact = newImpact;
    }
    
   public String toString(){
       return "Id : "+id +" resID : "+resID+" action : "+action+" from : "+from+" to : "+to+" newPossibility : "+newPossibility+" newImpact : "+newImpact;
   }
}
