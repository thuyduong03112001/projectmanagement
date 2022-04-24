package model;
import dal.IssueDAO;
/**
 *
 * @author Admin
 */
public class Issue {
    private String id,title,projectId,createdBy,typeId,createdDate,Deliverable,statusId, incharge, updatedDate, itype, istatus, projectName;
    private IssueDAO dao = new IssueDAO();
    public Issue() {
    }

    public Issue(String id, String title, String projectId, String createdBy, String typeId, String createdDate, String Deliverable, String statusId) {
        this.id = id;
        this.title = title;
        this.projectId = projectId;
        this.createdBy = createdBy;
        this.typeId = typeId;
        this.createdDate = createdDate;
        this.Deliverable = Deliverable;
        this.statusId = statusId;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getTypeId() {
        return typeId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getDeliverable() {
        return Deliverable;
    }

    public void setDeliverable(String Deliverable) {
        this.Deliverable = Deliverable;
    }
    
    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getIncharge() {
        return incharge;
    }

    public void setIncharge(String incharge) {
        this.incharge = incharge;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
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

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
    
    public String getStatus() {
        return dao.getSetting(Integer.parseInt(statusId));
    }
    public String getType() {
        return dao.getSetting(Integer.parseInt(typeId));
    }

    @Override
    public String toString() {
        return "Issue{" + "title=" + title + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", incharge=" + incharge + ", updatedDate=" + updatedDate + ", itype=" + itype + ", istatus=" + istatus + ", projectName=" + projectName + '}';
    }
    
    
    
    
}
