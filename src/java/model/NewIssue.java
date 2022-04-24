package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NewIssue {
    private int id;
    private String title;
    private int projectId;
    private int createdBy;
    private int typeId;
    private String createdDate;
    private String deliverable;
    private int statusId;
    private int incharge;
    private int updatedDate;
    private String UpdatedDate1;
    
    private String projectName;
    private String creator;
    private String status;
    private String itype;
    private String istatus;
    private String CreatedBy1;
    private String Incharge1;
    public NewIssue() {
    }

    public NewIssue(int id, String title, int projectId, int createdBy, int typeId, String createdDate, String deliverable, int statusId, int incharge, int updatedDate) {
        this.id = id;
        this.title = title;
        this.projectId = projectId;
        this.createdBy = createdBy;
        this.typeId = typeId;
        this.createdDate = createdDate;
        this.deliverable = deliverable;
        this.statusId = statusId;
        this.incharge = incharge;
        this.updatedDate = updatedDate;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
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

    public int getIncharge() {
        return incharge;
    }

    public void setIncharge(int incharge) {
        this.incharge = incharge;
    }

    public int getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(int updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getItype() {
        return itype;
    }

    public void setItype(String itype) {
        this.itype = itype;
    }

    public String getIstatus() {
        return istatus;
    }

    public void setIstatus(String istatus) {
        this.istatus = istatus;
    }

    public String getCreatedBy1() {
        return CreatedBy1;
    }

    public void setCreatedBy1(String CreatedBy1) {
        this.CreatedBy1 = CreatedBy1;
    }

    public String getIncharge1() {
        return Incharge1;
    }

    public void setIncharge1(String Incharge1) {
        this.Incharge1 = Incharge1;
    }

    public String getUpdatedDate1() {
        return UpdatedDate1;
    }

    public void setUpdatedDate1(String UpdatedDate1) {
        this.UpdatedDate1 = UpdatedDate1;
    }
    
    public String getDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(createdDate);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }
    
    public String getUDate1() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(UpdatedDate1);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }
}
