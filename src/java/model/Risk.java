/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author HP
 */
public class Risk {

    private int ID;
    private String ProjectName;
    private Date CreatedDate;
    private String Title;
    private int cateID;
    private String Owner;
    private String Description;
    private String RootCause;
    private String Possibility;
    private String CreatedBy;
    private String Impact;
    private int Rank;
    private int StatusID;
    private Date UpdatedDate;
    private String UpdatedDate1;
   
    public String getCreatedBy() {
        return CreatedBy;
    }
    public String getUpdatedDate1() {
        return UpdatedDate1;
    }
   
    public void setUpdatedDate1(String date){
        this.UpdatedDate1 = date;
    }
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }



    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public String getCreatedDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(CreatedDate);
    }

    public void setCreatedDate(Date CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public String getProjectName() {
        return ProjectName;
    }

    public void setProjectName(String ProjectName) {
        this.ProjectName = ProjectName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String getOwner() {
        return Owner;
    }

    public void setOwner(String Owner) {
        this.Owner = Owner;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getRootCause() {
        return RootCause;
    }

    public void setRootCause(String RootCause) {
        this.RootCause = RootCause;
    }

    public String getPossibility() {
        return Possibility;
    }

    public void setPossibility(String Possibility) {
        this.Possibility = Possibility;
    }

    public String getImpact() {
        return Impact;
    }

    public void setImpact(String Impact) {
        this.Impact = Impact;
    }

    public int getRank() {
        return Rank;
    }

    public void setRank(int Rank) {
        this.Rank = Rank;
    }

    public int getStatusID() {
        return StatusID;
    }

    public void setStatusID(int StatusID) {
        this.StatusID = StatusID;
    }

    public String getUpdatedDate() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(UpdatedDate);
    }

    public void setUpdatedDate(Date UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public Risk(Date CreatedDate, String ProjectName, String Title, int cateID, String CreatedBy, String Owner, String Description, String RootCause, String Possibility, String Impact, int Rank, int StatusID, Date UpdatedDate) {
        this.CreatedDate = CreatedDate;
        this.ProjectName = ProjectName;
        this.Title = Title;
        this.cateID = cateID;
        this.CreatedBy = CreatedBy;
        this.Owner = Owner;
        this.Description = Description;
        this.RootCause = RootCause;
        this.Possibility = Possibility;
        this.Impact = Impact;
        this.Rank = Rank;
        this.StatusID = StatusID;
        this.UpdatedDate = UpdatedDate;
    }
    public Risk(String ProjectName, String Title, int cateID, String CreatedBy, String Owner, String Description, String RootCause, String Possibility, String Impact, int Rank, int StatusID, String UpdatedDate) {
        this.ProjectName = ProjectName;
        this.Title = Title;
        this.cateID = cateID;
        this.CreatedBy = CreatedBy;
        this.Owner = Owner;
        this.Description = Description;
        this.RootCause = RootCause;
        this.Possibility = Possibility;
        this.Impact = Impact;
        this.Rank = Rank;
        this.StatusID = StatusID;
        this.UpdatedDate1 = UpdatedDate;
    }
    public Risk(int id,Date CreatedDate, String ProjectName, String Title, int cateID, String CreatedBy, String Owner, String Description, String RootCause, String Possibility, String Impact, int Rank, int StatusID, Date UpdatedDate) {
        this.ID = id;
        this.CreatedDate = CreatedDate;
        this.ProjectName = ProjectName;
        this.Title = Title;
        this.cateID = cateID;
        this.CreatedBy = CreatedBy;
        this.Owner = Owner;
        this.Description = Description;
        this.RootCause = RootCause;
        this.Possibility = Possibility;
        this.Impact = Impact;
        this.Rank = Rank;
        this.StatusID = StatusID;
        this.UpdatedDate = UpdatedDate;
    }
    public Risk(int id, String ProjectName, String Title, int cateID, String CreatedBy, String Owner, String Description, String RootCause, String Possibility, String Impact, int Rank, int StatusID, String UpdatedDate) {
        this.ID = id;
        this.ProjectName = ProjectName;
        this.Title = Title;
        this.cateID = cateID;
        this.CreatedBy = CreatedBy;
        this.Owner = Owner;
        this.Description = Description;
        this.RootCause = RootCause;
        this.Possibility = Possibility;
        this.Impact = Impact;
        this.Rank = Rank;
        this.StatusID = StatusID;
        this.UpdatedDate1 = UpdatedDate;
    }

    public Risk() {
    }

    public String toString() {
        return "CreatedDate : " + CreatedDate + " ProjectName : " + ProjectName + " Title : " + Title + " cateID : " + cateID
                + " Owner : " + Owner + " Description : " + Description + " RootCause : " + RootCause + " Possiblility : " + Possibility + " Impact : " + Impact
                + " Rank : " + Rank + " StatusID : " + StatusID + " UpdatedDate : " + UpdatedDate +" Id : "+ID;
    }
}
