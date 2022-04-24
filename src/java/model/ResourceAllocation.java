package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ResourceAllocation {
        private int RID;
        private int GroupID;
        private int ProjectId;
        private String account;
        private String fullName;
        private int ProjectRoleID;
        private String StartDate;
        private String EndDate;
        private int effort;
        private String notes;
        private int StaffID;
        
        private String GroupCode;
        private String projectname;
        private String GroupName;
        private String ProjectRole;

    public ResourceAllocation() {
    }

    public ResourceAllocation(int RID, int GroupID, int ProjectId, String account, String fullName, int ProjectRoleID, String StartDate, String EndDate, int effort, String notes, int StaffID) {
        this.RID = RID;
        this.GroupID = GroupID;
        this.ProjectId = ProjectId;
        this.account = account;
        this.fullName = fullName;
        this.ProjectRoleID = ProjectRoleID;
        this.StartDate = StartDate;
        this.EndDate = EndDate;
        this.effort = effort;
        this.notes = notes;
        this.StaffID = StaffID;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int GroupID) {
        this.GroupID = GroupID;
    }
    
    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int ProjectId) {
        this.ProjectId = ProjectId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getProjectRoleID() {
        return ProjectRoleID;
    }

    public void setProjectRoleID(int ProjectRoleID) {
        this.ProjectRoleID = ProjectRoleID;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String StartDate) {
        this.StartDate = StartDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public float getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getStaffID() {
        return StaffID;
    }

    public void setStaffID(int StaffID) {
        this.StaffID = StaffID;
    }

    public String getGroupCode() {
        return GroupCode;
    }

    public void setGroupCode(String GroupCode) {
        this.GroupCode = GroupCode;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
    }

    public String getGroupName() {
        return GroupName;
    }

    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }

    public String getProjectRole() {
        return ProjectRole;
    }

    public void setProjectRole(String ProjectRole) {
        this.ProjectRole = ProjectRole;
    }
        
    public String getStartDate1() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(StartDate);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }
     
      public String getEndDate1() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(EndDate);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }
}
