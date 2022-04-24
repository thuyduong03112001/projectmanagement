/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Requirement;
import model.RequirementChanges;

/**
 *
 * @author Admin
 */
public class RequirementDAO extends DBContext {

    public List<Requirement> getAll() {
        ProjectDAO pdb = new ProjectDAO();
        GroupDAO gdb = new GroupDAO();
        SettingDAO sdb = new SettingDAO();
        AccountDAO adb = new AccountDAO();
        List<Requirement> list = new ArrayList<>();
        String sql = "SELECT * FROM `requirements`";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Requirement r = new Requirement();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setProject(pdb.getProjectByProjectId(rs.getInt("projectId")));
                r.setGroup(gdb.getGroupById(rs.getInt("groupid")));
                r.setOwner(adb.getAccountByAccountId(rs.getInt("OwnerId")));
                r.setJoinDate(rs.getString("updateddate"));
                r.setType(sdb.getById(rs.getInt("typeid")));
                r.setStatus(sdb.getById(rs.getInt("statusid")));
                list.add(r);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Requirement> getAllRequirementCanAccess(int aid) {
        ProjectDAO pdb = new ProjectDAO();
        GroupDAO gdb = new GroupDAO();
        SettingDAO sdb = new SettingDAO();
        AccountDAO adb = new AccountDAO();
        List<Requirement> list = new ArrayList<>();
        String sql = "SELECT r.*,ul.StaffID FROM workflowbox.userlist ul \n"
                + "inner join manages m on m.ManagerID = ul.ManagerID\n"
                + "inner join `groups` g on g.ID = m.GroupID\n"
                + "inner join `projects` p on g.ID = p.GroupID\n"
                + "inner join `requirements` r on p.ID = r.ProjectID\n"
                + "where ul.StaffID = ? and ul.StatusID = 10;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, aid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Requirement r = new Requirement();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setProject(pdb.getProjectByProjectId(rs.getInt("projectId")));
                r.setGroup(gdb.getGroupById(rs.getInt("groupid")));
                r.setOwner(adb.getAccountByAccountId(rs.getInt("OwnerId")));
                r.setJoinDate(rs.getString("updateddate"));
                r.setType(sdb.getById(rs.getInt("typeid")));
                r.setStatus(sdb.getById(rs.getInt("statusid")));
                list.add(r);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public List<Requirement> search(int aid, int groupId, int projectId, int ownerId, int typeId, int statusId, String value) {
        ProjectDAO pdb = new ProjectDAO();
        GroupDAO gdb = new GroupDAO();
        SettingDAO sdb = new SettingDAO();
        AccountDAO adb = new AccountDAO();
        List<Requirement> list = new ArrayList<>();
        String sql = "SELECT r.*,ul.StaffID FROM workflowbox.userlist ul \n"
                + "inner join manages m on m.ManagerID = ul.ManagerID\n"
                + "inner join `groups` g on g.ID = m.GroupID\n"
                + "inner join `projects` p on g.ID = p.GroupID\n"
                + "inner join `requirements` r on p.ID = r.ProjectID\n"
                + "where ul.StaffID = ? and ul.StatusID = 10";
        if (projectId > 0) {
            sql += " and r.ProjectID = " + projectId + " ";
        }
        if (groupId > 0) {
            sql += " and r.groupID = " + groupId + " ";
        }
        if (statusId > 0) {
            sql += " and r.StatusID = " + statusId + " ";
        }
        if (typeId > 0) {
            sql += " and r.TypeID = " + typeId + " ";
        }
        if (ownerId > 0) {
            sql += " and r.OwnerID = " + ownerId + " ";
        }
        if (value != null && !value.equals("")) {
            sql += " and r.`title` like '%" + value + "%' ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, aid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Requirement r = new Requirement();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setProject(pdb.getProjectByProjectId(rs.getInt("projectId")));
                r.setGroup(gdb.getGroupById(rs.getInt("groupid")));
                r.setOwner(adb.getAccountByAccountId(rs.getInt("OwnerId")));
                r.setJoinDate(rs.getString("updateddate"));
                r.setType(sdb.getById(rs.getInt("typeid")));
                r.setStatus(sdb.getById(rs.getInt("statusid")));
                list.add(r);
            }
        } catch (Exception e) {
        }
        return list;
    }

    //Requirement List
    public List<Requirement> getListByPage(List<Requirement> list, int start, int end) {
        ArrayList<Requirement> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    //Requirement Changes
    public List<RequirementChanges> getListByPage2(List<RequirementChanges> list, int start, int end) {
        ArrayList<RequirementChanges> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void addGroup(String title, int pid, int owner, int statusId, int typeId, int gid, String des) {
        String querry = "insert into requirements(`title`,`projectid`,`ownerid`,`updateddate`,`statusid`,`typeid`,`groupid`,`description`) \n"
                + "values (?,?,?,curdate(),?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setString(1, title);
            st.setInt(2, pid);
            st.setInt(3, owner);
            st.setInt(4, statusId);
            st.setInt(5, typeId);
            st.setInt(6, gid);
            st.setString(7, des);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateGroup(int id, String title, int pid, int owner, int statusId, int typeId, int gid, String des) {
        String querry = "Update workflowbox.requirements "
                + "set `Title` = ?, `ProjectID` = ?, `Ownerid` = ?, `StatusID` = ?, `TypeID` = ?, `UpdatedDate` = curdate(), `GroupId` = ?, `Description` = ? "
                + "where `id` = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setString(1, title);
            st.setInt(2, pid);
            st.setInt(3, owner);
            st.setInt(4, statusId);
            st.setInt(5, typeId);
            st.setInt(6, gid);
            st.setString(7, des);
            st.setInt(8, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Requirement getRequirementById(int rid) {
        ProjectDAO pdb = new ProjectDAO();
        GroupDAO gdb = new GroupDAO();
        SettingDAO sdb = new SettingDAO();
        AccountDAO adb = new AccountDAO();
        String sql = "SELECT * FROM `requirements` where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Requirement r = new Requirement();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setProject(pdb.getProjectByProjectId(rs.getInt("projectId")));
                r.setGroup(gdb.getGroupById(rs.getInt("groupid")));
                r.setOwner(adb.getAccountByAccountId(rs.getInt("OwnerId")));
                r.setJoinDate(rs.getString("updateddate"));
                r.setType(sdb.getById(rs.getInt("typeid")));
                r.setStatus(sdb.getById(rs.getInt("statusid")));
                return r;
            }
        } catch (SQLException e) {
        }
        return null;
    }

    public RequirementChanges getRequirementChangesByRequirementChangeId(int rcid) {
        List<RequirementChanges> list = new ArrayList<>();
        SettingDAO sdb = new SettingDAO();
        AccountDAO adb = new AccountDAO();
        String sql = "SELECT * FROM workflowbox.requirements_changes where Id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rcid);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                RequirementChanges r = new RequirementChanges();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setType(sdb.getById(rs.getInt("typeid")));
                r.setEditor(adb.getAccountByAccountId(rs.getInt("editorid")));
                r.setDetail(rs.getString("detail"));
                r.setRequirementId(rs.getInt("requirementId"));
                r.setVersion(rs.getString("version"));
                return r;
            }
        } catch (SQLException e) {
        }
        return null;

    }

    public void updateRequirementChange(int id, String title, int typeId, String destail, String version, int eid) {
        String querry = "Update workflowbox.requirements_changes "
                + "set `title` = ?, `typeid` = ?, `detail` = ?, `version` = ?, `editorid` = ? "
                + "where `id` = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setString(1, title);
            st.setInt(2, typeId);
            st.setString(3, destail);
            st.setString(4, version);
            st.setInt(5, eid);
            st.setInt(6, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addRequirementChange(int rid, String title, int typeId, String detail, String version, int eid) {
        String querry = "insERT INTO `requirements_changes`( `requirementid`, `title`, `typeid`, `detail`, `version`, `editorid`)"
                + " VALUES(?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, rid);
            st.setString(2, title);
            st.setInt(3, typeId);
            st.setString(4, detail);
            st.setString(5, version);
            st.setInt(6, eid);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<RequirementChanges> getAllRequirementChangesByRequirementId(int rid) {
        List<RequirementChanges> list = new ArrayList<>();
        SettingDAO sdb = new SettingDAO();
        AccountDAO adb = new AccountDAO();
        String sql = "SELECT * FROM workflowbox.requirements_changes where RequirementId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RequirementChanges r = new RequirementChanges();
                r.setId(rs.getInt("id"));
                r.setTitle(rs.getString("title"));
                r.setType(sdb.getById(rs.getInt("typeid")));
                r.setEditor(adb.getAccountByAccountId(rs.getInt("editorid")));
                r.setDetail(rs.getString("detail"));
                r.setRequirementId(rs.getInt("requirementId"));
                r.setVersion(rs.getString("version"));
                list.add(r);
            }
            return list;
        } catch (SQLException e) {
        }
        return list;

    }

    public static void main(String[] args) {
        RequirementDAO rdb = new RequirementDAO();
        System.out.println(rdb.getAll().get(0).getProject());
    }

}
