/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Project {
    private String id;
    private String name;
    private String groupId;
    private String startDate;
    private String endDate;
    private String progress;
    private String status;
    private String description;

    public Project() {
    }

    public Project(String id, String name, String groupId, String startDate, String endDate, String progress, String status, String description) {
        this.id = id;
        this.name = name;
        this.groupId = groupId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.progress = progress;
        this.status = status;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStartDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(startDate.toString());
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(endDate.toString());
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project{" + "id=" + id + ", name=" + name + ", groupId=" + groupId + ", startDate=" + startDate + ", endDate=" + endDate + ", progress=" + progress + ", status=" + status + ", description=" + description + '}';
    }

    
}
