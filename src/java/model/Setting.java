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
public class Setting {
    private int id, order, status;
    private String type, value;
    private String description;
    public Setting() {
    }

    public Setting(int id, int type, int value, int order, int status) {
        this.id = id;
        this.type = type+"";
        this.value = value+"";
        this.order = order;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type+"";
    }

    public String getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value+"";
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        return "Setting{" + "id=" + id + ", order=" + order + ", status=" + status + ", type=" + type + ", value=" + value + ", description=" + description + '}';
    }
    
}
