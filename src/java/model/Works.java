/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Admin
 */
public class Works {
    int id;
    Account staff;
    Groups groups;

    public Works() {
    }

    public Works(int id, Account staff, Groups group) {
        this.id = id;
        this.staff = staff;
        this.groups = group;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Account getStaff() {
        return staff;
    }

    public void setStaff(Account staff) {
        this.staff = staff;
    }

    public Groups getGroup() {
        return groups;
    }

    public void setGroup(Groups group) {
        this.groups = group;
    }
    
}
