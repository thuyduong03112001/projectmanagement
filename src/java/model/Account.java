package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Account {

    private int id;
    private String fullName;
    private String dob;
    private int gender;
    private String email;
    private String phoneNumber;
    private String account;
    private String password;
    private int statusId;
    private String joinDate;
    private String image;
    private int roleId;
    private String bio;

    private String status;
    private String role;
    private int GroupID;
    private String genderOfvalue;
    private String groupCode;
    private String StatusOfAccount;
    private String groupName;
    private String projectname;
    private String StartDate;
    private String EndDate;
    private String progress;
    private String Description;
    private String projectRole;
    private int effort;
    private int ProjectRoleID;
    private int ProjectId;
    private int RID;
    private String notes;

    public Account() {
    }

    public Account(int id, String fullName, String dob, int gender, String email, String phoneNumber, String account, String password, int statusId, String joinDate, String image, int roleId, String bio) {
        this.id = id;
        this.fullName = fullName;
        this.dob = dob;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.password = password;
        this.statusId = statusId;
        this.joinDate = joinDate;
        this.image = image;
        this.roleId = roleId;
        this.bio = bio;
    }
    
    public Account(String fullName, int gender, String email, String phoneNumber, String account, String password,int roleID) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.password = password;
        this.roleId = roleID;
    }
    public Account(String fullName, String email, String phoneNumber, int gender, String account, String password) {
        this.fullName = fullName;
        this.gender = gender;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.account = account;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getGenderOfvalue() {
        return genderOfvalue;
    }

    public void setGenderOfvalue(String genderOfvalue) {
        this.genderOfvalue = genderOfvalue;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getStatusOfAccount() {
        return StatusOfAccount;
    }

    public void setStatusOfAccount(String StatusOfAccount) {
        this.StatusOfAccount = StatusOfAccount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getProjectname() {
        return projectname;
    }

    public void setProjectname(String projectname) {
        this.projectname = projectname;
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

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getProjectRole() {
        return projectRole;
    }

    public void setProjectRole(String projectRole) {
        this.projectRole = projectRole;
    }

    public int getEffort() {
        return effort;
    }

    public void setEffort(int effort) {
        this.effort = effort;
    }
    
    public String getDateOfBirth() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(dob);
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
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

    public int getProjectRoleID() {
        return ProjectRoleID;
    }

    public void setProjectRoleID(int ProjectRoleID) {
        this.ProjectRoleID = ProjectRoleID;
    }

    public int getProjectId() {
        return ProjectId;
    }

    public void setProjectId(int ProjectId) {
        this.ProjectId = ProjectId;
    }

    public int getRID() {
        return RID;
    }

    public void setRID(int RID) {
        this.RID = RID;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getGroupID() {
        return GroupID;
    }

    public void setGroupID(int GroupID) {
        this.GroupID = GroupID;
    }
      
      
}
