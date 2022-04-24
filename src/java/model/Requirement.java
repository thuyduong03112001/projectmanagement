/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Admin
 */
public class Requirement {

    private int id;
    private String title;
    private int groupCode;
    private String joinDate;
    private int groupId;
    private int projectId;
    private int statusId;
    private String description;
    private int typeId;
    private int ownerId;
    private Account owner;
    private Groups group;
    private Project project;
    private Setting status, type;

    public Requirement() {
    }

    public Requirement(int id, String name, int groupCode, String joinDate, int projectId, int statusId, String description, int typeId) {
        this.id = id;
        this.title = name;
        this.groupCode = groupCode;
        this.joinDate = joinDate;
        this.projectId = projectId;
        this.statusId = statusId;
        this.description = description;
        this.typeId = typeId;
    }

    public int getGroupId() {
        return group.getId();
    }

    public int getOwnerId() {
        return owner.getId();
    }

    public void setOwner(Account owner) {
        this.owner = owner;
    }

    public Account getOwner() {
        return owner;
    }

    public Groups getGroup() {
        return group;
    }

    public void setGroup(Groups group) {
        this.group = group;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = name;
    }

    public int getGroupCode() {
        return group.getId();
    }

    public void setGroupCode(int groupCode) {
        this.groupCode = groupCode;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate)  {
        try{
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(joinDate);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String strDate = dateFormat.format(date);
        this.joinDate = strDate;
        }catch(ParseException e){
        }
    }

    public int getProjectId() {
        return Integer.parseInt(project.getId());
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Setting getStatus() {
        return status;
    }

    public void setStatus(Setting status) {
        this.status = status;
    }

    public Setting getType() {
        return type;
    }

    public void setType(Setting type) {
        this.type = type;
    }

    public int getStatusId() {
        return status.getId();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTypeId() {
        return type.getId();
    }
}
