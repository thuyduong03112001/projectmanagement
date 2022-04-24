/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Timesheet;
import model.Project;
import model.Account;

/**
 *
 * @author Admin
 */
public class TimesheetDAO extends DBContext {

    ProjectDAO prjDao = new ProjectDAO();
    AccountDAO accDao = new AccountDAO();

    public Timesheet getById(String id) {
        String sql = "SELECT * FROM `timesheets` where ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Timesheet timesheet = new Timesheet();
                timesheet.setId(rs.getInt(1));
                //timesheet.setAcc(accDao.getAccountById(rs.getInt(2)));
                timesheet.setDate(rs.getDate(3));
                timesheet.setTitle(rs.getString(4));
                timesheet.setProject(prjDao.getProjectById(rs.getInt(5)));
                timesheet.setProcess(rs.getString(6));
                timesheet.setDuration(rs.getDouble(7));
                timesheet.setWorkResult(rs.getString(8));
                timesheet.setRejectReason(rs.getString(9));
                timesheet.setStatus(rs.getString(10));
                timesheet.setProcess(rs.getString(11));

                return timesheet;
//                timesheet.setAcc(accDao.getAccountById(rs.getInt(2)));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Timesheet> getByAcc(Account acc, String projectId, String start, String end, String process) {
        List<Timesheet> list = new ArrayList<>();
        String sql = "SELECT * FROM `timesheets` WHERE AccID = ?";
        if (projectId != null && !projectId.equals("")) {
            sql += " and ProjectId = " + projectId;
        }
        if (process != null && !process.equals("")) {
            sql += " and ProcessID = " + process;
        }
        if (start != null && !start.equals("")) {
            sql += " and '" + start + "' < date ";
            System.out.println(start);
        }
        if (end != null && !end.equals("")) {
            sql += " and date < '" + end + "'";
            System.out.println(end);
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, acc.getId());
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Timesheet timesheet = new Timesheet();
                timesheet.setId(rs.getInt(1));
                timesheet.setAcc(accDao.getAccountByAccountId(rs.getInt(2)));
                timesheet.setDate(rs.getDate(3));
                timesheet.setTitle(rs.getString(4));
                timesheet.setProject(prjDao.getProjectById(rs.getInt(5)));
                timesheet.setProcess(rs.getString(6));
                timesheet.setDuration(rs.getDouble(7));
                timesheet.setWorkResult(rs.getString(8));
                timesheet.setRejectReason(rs.getString(9));
                timesheet.setStatus(rs.getString(10));
                timesheet.setProcess(rs.getString(11));
                list.add(timesheet);
            }

            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public void updateTimesheet(Timesheet timesheet) {
        String sql = "UPDATE `timesheets` "
                + "SET \n"
                + "`Date`= ?,\n"
                + "`Title`= ?,\n"
                + "`ProjectID`= ?,\n"
                + "`Duration`= ?,\n"
                + "`WorkResult`= ?,\n"
                + "`RejectionReason`= ?,\n"
                + "`StatusID`= ?,\n"
                + "`ProcessID`= ?\n"
                + " WHERE `ID`= ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, (java.sql.Date) timesheet.getDate());
            st.setString(2, timesheet.getTitle());
            st.setString(3, timesheet.getProject().getId());
            st.setDouble(4, timesheet.getDuration());
            st.setString(5, timesheet.getWorkResult());
            st.setString(6, timesheet.getRejectReason());
            st.setString(7, timesheet.getStatusId());
            st.setString(8, timesheet.getProcessId());
            st.setInt(9, timesheet.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void add(Timesheet timesheet) {
        String sql = "INSERT INTO `workflowbox`.`timesheets`\n"
                + "(\n"
                + "`Date`,\n"
                + "`Title`,\n"
                + "`ProjectID`,\n"
                + "`Duration`,\n"
                + "`WorkResult`,\n"
                + "`StatusID`,\n"
                + "`ProcessID`,\n"
                + "`AccID`)"
                + "VALUES\n"
                + "(?,?,?,?,?,?,?,?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setDate(1, (java.sql.Date) timesheet.getDate());
            st.setString(2, timesheet.getTitle());
            st.setString(3, timesheet.getProject().getId());
            st.setDouble(4, timesheet.getDuration());
            st.setString(5, timesheet.getWorkResult());
            st.setString(6, timesheet.getStatusId());
            st.setString(7, timesheet.getProcessId());
            st.setInt(8, timesheet.getAcc().getId());
            st.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int getGroupId(int id) {
        String sql = "SELECT GroupID FROM workflowbox.works \n"
                + "where StaffID = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
        }
        return 0;
    }

    public String getPrjRole(int id) {
        String sql = "SELECT ProjectRoleID FROM workflowbox.works \n"
                + "where StaffID = ?;";
        SettingDAO setdao = new SettingDAO();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return setdao.getSetting(rs.getInt(1));
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<String> getAccountIdByGroup(int id) {
        String sql = "select * from workflowbox.works \n"
                + "where GroupID = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            List<String> list = new ArrayList<>();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString(2));
            }
            return list;
        } catch (Exception e) {
        }
        return null;
    }

    public List<Timesheet> getListReview(Account acc) {
        try {
            TimesheetDAO dao = new TimesheetDAO();
            List<Timesheet> listT = new ArrayList<>();
            List<String> list = dao.getAccountIdByGroup(dao.getGroupId(acc.getId()));
            for (String string : list) {
                Account a = new Account();
                a.setId(Integer.parseInt(string));
                listT.addAll(dao.getByAcc(a, "", "", "", ""));
            }
            return listT;
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        TimesheetDAO dao = new TimesheetDAO();
        AccountDAO accdao = new AccountDAO();
        Account acc = new Account();
        acc.setId(1);

        ProjectDAO projectDao = new ProjectDAO();
        Timesheet timesheet = new Timesheet();
        List<Timesheet> listT = new ArrayList<>();
        System.out.println(dao.getGroupId(2));
        System.out.println(dao.getAccountIdByGroup(1).size());
        listT = dao.getListReview(acc);
        for (Timesheet timesheet1 : listT) {
            System.out.println(timesheet1.getAcc().getFullName() + "--" + timesheet1.toString()  );
        }
        System.out.println(listT.size());
    }
}
