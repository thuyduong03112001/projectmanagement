/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;
import model.Issue;
import model.Project;
import model.Risk;

/**
 *
 * @author Admin
 */
public class ProjectDAO extends DBContext {

    SettingDAO settingdao = new SettingDAO();
    
    public List<Project> getAllByManager(int managerId, String groupId, String status) {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT p.*\n"
                + "FROM projects p inner join manages m\n"
                + "on p.GroupId = m.GroupID\n"
                + "where m.ManagerID = ?";
        if (groupId != null && !groupId.equals("")) {
            sql += " and p.GroupID = " + groupId;

        }
        if (status != null && !status.equals("")) {
            sql += " and p.status = " + status;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, managerId);
            System.out.println(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project prj = new Project();
                prj.setId(rs.getString("id"));
                prj.setName(rs.getString("name"));
                prj.setGroupId(rs.getString("groupid"));
                prj.setStartDate(rs.getString("startdate"));
                prj.setEndDate(rs.getString("enddate"));
                prj.setProgress(rs.getString("progess"));
                prj.setStatus(rs.getString("status"));
                prj.setDescription(rs.getString("description"));
                list.add(prj);
            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    public List<Project> getAllByStaff(int staffId) {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT p.*\n"
                + "FROM `projects` p inner join `groups` g\n"
                + "on p.GroupId = g.ID \n"
                + "join `works` w\n"
                + "on w.GroupID = g.ID\n"
                + "where w.StaffID = ?\n"
                + "group by p.ID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, staffId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project prj = new Project();
                prj.setId(rs.getString("id"));
                prj.setName(rs.getString("name"));
                prj.setGroupId(rs.getString("groupid"));
                prj.setStartDate(rs.getString("startdate"));
                prj.setEndDate(rs.getString("enddate"));
                prj.setProgress(rs.getString("progess"));
                prj.setStatus(rs.getString("status"));
                prj.setDescription(rs.getString("description"));
                list.add(prj);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Project> getAllProject(String accId, String groupId, String status) {
        List<Project> list = new ArrayList<>();
        String sql = "SELECT p.* FROM `projects` p where p.status = 1";
        if (groupId != null && !groupId.equals("")) {
            sql += " and p.GroupID = " + groupId;
        }
        if (groupId != null && !groupId.equals("")) {
            sql += " and p.GroupID = " + groupId;
        }
        if (status != null && !status.equals("")) {
            sql += " and p.status = " + status;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project prj = new Project();
                prj.setId(rs.getString("id"));
                prj.setName(rs.getString("name"));
                prj.setGroupId(rs.getString("groupid"));
                prj.setStartDate(rs.getString("startdate"));
                prj.setEndDate(rs.getString("enddate"));
                prj.setProgress(rs.getString("progess"));
                prj.setStatus(rs.getString("status"));
                prj.setDescription(rs.getString("description"));
                list.add(prj);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Project> getAllProjectHasRequirementByGroupId(int groupId) {
        List<Project> list = new ArrayList<>();
        String sql = "select distinct(ProjectId) from requirements where groupId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, groupId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project prj = getProjectById(rs.getInt("ProjectId"));
                list.add(prj);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Project getProjectByProjectId(int projectId) {
        String sql = "SELECT p.* FROM workflowbox.projects p \n"
                + "where p.ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, projectId);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Project prj = new Project();
                prj.setId(rs.getString("id"));
                prj.setName(rs.getString("name"));
                prj.setGroupId(rs.getString("groupid"));
                prj.setStartDate(rs.getString("startdate"));
                prj.setEndDate(rs.getString("enddate"));
                prj.setProgress(rs.getString("progess"));
                prj.setStatus(rs.getString("status"));
                prj.setDescription(rs.getString("description"));
                return prj;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Project getProjectById(int id) {
        String sql = "SELECT * FROM workflowbox.projects where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Project prj = new Project();
                prj.setId(rs.getString(1));
                prj.setName(rs.getString(2));
                prj.setGroupId(rs.getString(3));
                prj.setStartDate(rs.getString(4));
                prj.setEndDate(rs.getString(5));
                prj.setProgress(rs.getString(6));
                prj.setStatus(rs.getString(7));
                prj.setDescription(rs.getString(8));
                return prj;
            }

        } catch (Exception e) {
        }

        return null;

    }

    public List<Issue> getIssueByProject(int projectId) {
        List<Issue> list = new ArrayList<>();
        String sql = "SELECT * FROM workflowbox.issues where ProjectID = ? order by CreatedDate desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, projectId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Issue issue = new Issue();
                issue.setId(rs.getString(1));
                issue.setTitle(rs.getString(2));
                issue.setProjectId(rs.getString(3));
                issue.setCreatedBy(rs.getString(4));
                issue.setTypeId(rs.getString(5));
                issue.setCreatedDate(rs.getString(6));
                issue.setDeliverable(rs.getString(7));
                issue.setStatusId(rs.getString(8));
                list.add(issue);
            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    public List<Risk> getRiskByProject(int projectId) {
        List<Risk> list = new ArrayList<>();
        String sql = "SELECT * FROM workflowbox.risks where ProjectID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, projectId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Risk risk = new Risk();
//                risk.setId(rs.getString(1));
//                risk.setName(rs.getString(2));
//                risk.setCause(rs.getString(3));
//                risk.setImpact(rs.getString(4));
//                risk.setProjectId(rs.getString(5));
//                risk.setDescription(rs.getString(6));
//                risk.setStatusId(rs.getString(7));
//                risk.setCategoryId(rs.getString(7));
                list.add(risk);
            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    public List<String> getManagerByGroup(int groupId) {
        String sql = "SELECT acc.FullName\n"
                + "FROM workflowbox.manages m join workflowbox.groups gr \n"
                + "on m.GroupID = gr.ID\n"
                + "join accounts acc on m.ManagerID = acc.ID \n"
                + "where gr.id = ? ";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, groupId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("FullName"));
            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    public List<String> getListManager() {
        String sql = "SELECT acc.FullName\n"
                + "FROM workflowbox.manages m join workflowbox.groups gr \n"
                + "on m.GroupID = gr.ID\n"
                + "join accounts acc on m.ManagerID = acc.ID \n"
                + "where gr.id = ? ";
        List<String> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("FullName"));
            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    public List<Account> getResource(int projectid) {
        String sql = "SELECT acc.id,acc.FullName,w.ProjectRoleID,eff.Effort\n"
                + "FROM workflowbox.efforts eff join accounts acc\n"
                + "on eff.AccID = acc.id \n"
                + "join works w on w.StaffID = eff.AccID\n"
                + "join projects pr on w.GroupID = pr.GroupID\n"
                + "where pr.id = ?";
        List<Account> list = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, projectid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setFullName(rs.getString("fullname"));
                acc.setProjectRole(settingdao.getSetting(Integer.parseInt(rs.getString("projectroleid"))));
                acc.setEffort(rs.getInt("effort"));
                list.add(acc);
            }
            return list;
        } catch (Exception e) {
        }

        return null;
    }

    public static void main(String[] args) {
        ProjectDAO dao = new ProjectDAO();
        List<Account> list = dao.getResource(1);
        for (Account acc : list) {
            System.out.println(acc.getFullName() + "--" + acc.getProjectRole() + "--" + acc.getEffort());
        }

    }
}
