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
import java.util.List;

/**
 *
 * @author HP
 */
public class Groups {

    private int id;
    private String name;
    private int cateID;
    private String joinDate;
    private int managerID;
    private int typeId, statusId;
    private String description;
    private int parentId;
    private List<Account> managers;
    private Setting type, status;
    private Groups parent;

    public Groups() {
    }

    public int getParentId() {
        if (parent != null) {
            return parent.getId();
        } else {
            return -1;
        }
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(joinDate);
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String strDate = dateFormat.format(date);
            this.joinDate = strDate;
        } catch (ParseException e) {
        }
    }

    public int getManagerID() {
        return managerID;
    }

    public void setManagerID(int managerID) {
        this.managerID = managerID;
    }

    public List<Account> getManagers() {
        return managers;
    }

    public void setManagers(List<Account> managers) {
        this.managers = managers;
    }

    public Groups getParent() {
        return parent;
    }

    public void setParent(Groups parent) {
        this.parent = parent;
    }

    public Setting getType() {
        return type;
    }

    public void setType(Setting type) {
        this.type = type;
    }

    public Setting getStatus() {
        return status;
    }

    public void setStatus(Setting status) {
        this.status = status;
    }

    public int getTypeId() {
        return type.getId();
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStatusId() {
        return status.getId();
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCateID() {
        return cateID;
    }

    public void setCateID(int cateID) {
        this.cateID = cateID;
    }

    public String toString() {
        return "Name : " + name + "||ID : " + id;
    }
}
