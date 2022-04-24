package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deliverable {

    private int id;
    private int projectId;
    private String deliverable;
    private int statusId;
    private String plannedDate;
    private String previousDate;
    private String actualDate;

    private String status;
    private String projectName;
    private int groupId;
    private int active;

    public Deliverable() {
    }

    public Deliverable(int id, int projectId, String deliverable, int statusId, String plannedDate, String previousDate, String actualDate) {
        this.id = id;
        this.projectId = projectId;
        this.deliverable = deliverable;
        this.statusId = statusId;
        this.plannedDate = plannedDate;
        this.previousDate = previousDate;
        this.actualDate = actualDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getDeliverable() {
        return deliverable;
    }

    public void setDeliverable(String deliverable) {
        this.deliverable = deliverable;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getPlannedDate() {
        return plannedDate;
    }

    public void setPlannedDate(String plannedDate) {
        this.plannedDate = plannedDate;
    }

    public String getPreviousDate() {
        return previousDate;
    }

    public void setPreviousDate(String previousDate) {
        this.previousDate = previousDate;
    }

    public String getActualDate() {
        return actualDate;
    }

    public void setActualDate(String actualDate) {
        this.actualDate = actualDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getRealPlanned() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(plannedDate);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }

    public String getRealPrevious() throws ParseException {
        if (previousDate == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(previousDate);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }

    public String getRealActual() throws ParseException {
        if (actualDate == null) {
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(actualDate);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }
    
    
}
