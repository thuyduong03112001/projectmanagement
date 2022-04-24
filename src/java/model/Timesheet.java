/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import model.Project;
import dal.SettingDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Admin
 */
public class Timesheet {
    SettingDAO dao = new SettingDAO();
    
    private int id;
    private Account acc;
    private Date date;
    private String title;
    private Project project;
    private double duration;
    private String workResult;
    private String rejectReason;
    
    private String process;
    private String status;

    public Timesheet() {
    }

    public Timesheet(int id, Date date, String title, Project project, String process, double duration, String workResult, String status, String rejectReason) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.project = project;
        this.process = process;
        this.duration = duration;
        this.workResult = workResult;
        this.status = status;
        this.rejectReason = rejectReason;
    }
    
    public Timesheet(Date date, String title, Project project, String process, double duration, String workResult, String status, String rejectReason) {
        this.date = date;
        this.title = title;
        this.project = project;
        this.process = process;
        this.duration = duration;
        this.workResult = workResult;
        this.status = status;
        this.rejectReason = rejectReason;
    }


    public Account getAcc() {
        return acc;
    }

    public void setAcc(Account acc) {
        this.acc = acc;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Project getProject() {
        return project;
    }
    
    public String getProjectName() {
        return project.getName();
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public String getProcess() {
        return dao.getSetting(Integer.parseInt(process));
    }
    
    public String getProcessId() {
        return process;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getWorkResult() {
        return workResult;
    }

    public void setWorkResult(String workResult) {
        this.workResult = workResult;
    }

    public String getStatus() {
        return dao.getSetting(Integer.parseInt(status));
    }
    public String getStatusId() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    
    public String getRealDate() throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date a = formatter.parse(date.toString());
        formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(a);
    }

    @Override
    public String toString() {
        return "Timesheet{"+ "id=" + id + ", date=" + date + ", title=" + title + ", project=" + project.getName() + ", duration=" + duration + ", workResult=" + workResult + ", rejectReason=" + rejectReason + ", process=" + getProcess() + ", status=" + getStatus() + '}';
    }
    
}
