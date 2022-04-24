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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Groups;

/**
 *
 * @author HP
 */
public class GroupDAO extends DBContext {

    public List<Groups> getName() {
        List<Groups> list = new ArrayList<>();
        String sql = "Select id,name from `groups`";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups G = new Groups();
                G.setId(rs.getInt("id"));
                G.setName(rs.getString("name"));
                list.add(G);
            }
            //connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public void insert(Account A, int groupID) {
        int result = 0;
        String sql = "insert into accounts(Fullname,Gender,Email,PhoneNumber,Account,Password,RoleID) values (?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, A.getFullName());
            ps.setInt(2, A.getGender());
            ps.setString(3, A.getEmail());
            ps.setString(4, A.getPhoneNumber());
            ps.setString(5, A.getAccount());
            ps.setString(6, A.getPassword());
            ps.setInt(7, A.getRoleId());
            result = ps.executeUpdate();
            //connection.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        try {
            String sql1 = "select * from accounts order by id desc limit 1";
            PreparedStatement ps1 = connection.prepareStatement(sql1);
            ResultSet rs = ps1.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                try {
                    String sql2 = "insert into works(staffID,groupid) values (?,?)";
                    PreparedStatement ps2 = connection.prepareStatement(sql2);
                    ps2.setInt(1, id);
                    ps2.setInt(2, groupID);
                    result = ps2.executeUpdate();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
            //connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public List<Groups> getAll() {
        AccountDAO adb = new AccountDAO();
        SettingDAO sdb = new SettingDAO();
        List<Groups> list = new ArrayList<>();
        String querry = "SELECT * FROM `groups`";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups g = new Groups();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setJoinDate(rs.getString("joindate"));
                g.setParent(getGroupById(rs.getInt("parentid")));
                g.setManagers(adb.getManagerListOfGroupByGroupId(rs.getInt("id")));
                g.setType(sdb.getById(rs.getInt("typeid")));
                g.setStatus(sdb.getById(rs.getInt("Statusid")));
                g.setDescription(rs.getString("description"));
                list.add(g);
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Groups> getAllParentGroup() {
        SettingDAO sdb = new SettingDAO();
        AccountDAO adb = new AccountDAO();
        List<Groups> list = new ArrayList<>();
        String querry = "select * FROM workflowbox.groups  \n"
                + "where Id in ( select distinct(ParentID) \n"
                + "FROM workflowbox.groups )";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups g = new Groups();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setType(sdb.getById(rs.getInt("typeid")));
                g.setStatus(sdb.getById(rs.getInt("Statusid")));
                g.setDescription(rs.getString("description"));
                g.setManagers(adb.getManagerListOfGroupByGroupId(rs.getInt("id")));
                g.setJoinDate(rs.getString("joindate"));
                g.setParent(getGroupById(rs.getInt("parentid")));
                list.add(g);
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Groups getGroupById(int id) {
        AccountDAO adb = new AccountDAO();
        SettingDAO sdb = new SettingDAO();
        Groups n = null;
        String querry = "select * FROM workflowbox.groups  \n"
                + "where workflowbox.groups.ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups g = new Groups();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setType(sdb.getById(rs.getInt("typeid")));
                g.setStatus(sdb.getById(rs.getInt("Statusid")));
                g.setDescription(rs.getString("description"));
                g.setManagers(adb.getManagerListOfGroupByGroupId(rs.getInt("id")));
                g.setJoinDate(rs.getString("joindate"));
                g.setParent(getGroupById(rs.getInt("parentid")));
                return g;
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public List<Groups> getAllGroupHasRequirement() {
        SettingDAO sdb = new SettingDAO();
        List<Groups> list = new ArrayList<>();
        String querry = "select distinct(GroupID) from requirements";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups g = getGroupById(rs.getInt("Groupid"));
                list.add(g);
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Groups> getGroupByName(String name) {
        AccountDAO adb = new AccountDAO();
        SettingDAO sdb = new SettingDAO();
        List<Groups> list = new ArrayList<>();
        String querry = "select * FROM workflowbox.groups  \n"
                + "where  workflowbox.groups.Name like '%" + name + "%'";
        System.out.println(querry);
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups g = new Groups();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setType(sdb.getById(rs.getInt("typeid")));
                g.setStatus(sdb.getById(rs.getInt("Statusid")));
                g.setManagers(adb.getManagerListOfGroupByGroupId(rs.getInt("id")));
                g.setDescription(rs.getString("description"));
                g.setJoinDate(rs.getString("joindate"));
                g.setParent(getGroupById(rs.getInt("parentid")));
                list.add(g);
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void deleteGroup(int id) {
        List<Groups> list = new ArrayList<>();
        String querry = "Update workflowbox.groups \n"
                + "set statusid = 24\n"
                + "where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, id);

            st.executeUpdate();
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void recoverGroup(int id) {
        List<Groups> list = new ArrayList<>();
        String querry = "Update workflowbox.groups \n"
                + "set statusid = 25\n"
                + "where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, id);

            st.executeUpdate();
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }


    public void addManager(String[] mid, int gid) {
        String querry2 = "SELECt * FROM workflowbox.manages order by id desc";
        try {
            PreparedStatement st2 = connection.prepareStatement(querry2);
            ResultSet rs2 = st2.executeQuery();
            int m = 1;
            if (rs2.next()) {
                m = rs2.getInt("id");
            }
            String querry3 = "insert into workflowbox.manages value (?,?,?)";
            try {
                PreparedStatement st3 = connection.prepareStatement(querry3);
                for (int i = 0; i < mid.length; i++) {
                    m++;
                    st3.setInt(1, m);
                    st3.setInt(2, Integer.parseInt(mid[i]));
                    st3.setInt(3, gid);
                    st3.executeUpdate();
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Groups getLastestGroup() {
        AccountDAO adb = new AccountDAO();
        SettingDAO sdb = new SettingDAO();
        Groups n = null;
        String querry = "SELECT *  FROM workflowbox.groups order by id desc;";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Groups g = new Groups();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setType(sdb.getById(rs.getInt("typeid")));
                g.setStatus(sdb.getById(rs.getInt("Statusid")));
                g.setDescription(rs.getString("description"));
                g.setManagers(adb.getManagerListOfGroupByGroupId(rs.getInt("id")));
                g.setJoinDate(rs.getString("joindate"));
                g.setParent(getGroupById(rs.getInt("parentid")));
                return g;
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }
    public void addGroup(String name, int pid, int typeId, int statusId, String des, String[] mid) {
        List<Groups> list = new ArrayList<>();
        String querry = "insert into workflowbox.groups(`name`,`joindate`,`parentid`,`statusid`,`description`,`typeid`) values (?,CURDATE(),?,?,?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setString(1, name);
            st.setInt(2, pid);
            st.setInt(3, statusId);
            st.setString(4, des);
            st.setInt(5, typeId);
            st.executeUpdate();
            addManager(mid, getLastestGroup().getId());
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    public void updateGroup(int id, String name, int pid, int type, int status, String des, String[] mid) {
        List<Groups> list = new ArrayList<>();
        String querry = "Update workflowbox.groups \n"
                + "set Name = ?, ParentID = ?, StatusId = ?, Description = ?, TypeId = ?\n"
                + "where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(6, id);
            st.setString(1, name);
            st.setInt(2, pid);
            st.setInt(3, status);
            st.setString(4, des);
            st.setInt(5, type);
            st.executeUpdate();
            try {
                System.out.println("check1");
                String querry2 = "DELETE FROm workflowbox.manages WHERE GroupID = ?";
                PreparedStatement st2 = connection.prepareStatement(querry2);
                st2.setInt(1, id);
                st2.executeUpdate();
            } catch (Exception e) {
                System.out.println(e);
            }
            addManager(mid, id);
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Groups> searchGroup( String value, int pid, int type, int status) {
        AccountDAO adb = new AccountDAO();
        SettingDAO sdb = new SettingDAO();
        List<Groups> list = new ArrayList<>();

        String querry = "select * FROM workflowbox.groups g where 1=1 \n";
        try {
            if (value != null && !value.equals("")) {
                querry += " and (Name like '%" + value + "%' or id = '" + value + "') ";
            }

            if (pid > 0) {
                querry += " and g.ParentID = " + pid;
            }
            if (type > 0) {
                querry += " and g.typeid = " + type;
            }
            if (status > 0) {
                querry += " and g.statusid = " + status;
            }
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups g = new Groups();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setType(sdb.getById(rs.getInt("typeid")));
                g.setStatus(sdb.getById(rs.getInt("Statusid")));
                g.setDescription(rs.getString("description"));
                g.setJoinDate(rs.getString("joindate"));
                g.setManagers(adb.getManagerListOfGroupByGroupId(rs.getInt("id")));
                g.setParent(getGroupById(rs.getInt("parentid")));
                list.add(g);
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public List<Groups> getListByPage(List<Groups> list, int start, int end) {
        ArrayList<Groups> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public Groups getGroupByProjectId(int projectId) {
        AccountDAO adb = new AccountDAO();
        SettingDAO sdb = new SettingDAO();
        String querry = "select g.* from `projects` p inner join `groups` g\n"
                + "on p.GroupID = g.ID where p.ID = ?";
        System.out.println(querry);
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, projectId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Groups g = new Groups();
                g.setId(rs.getInt("id"));
                g.setName(rs.getString("name"));
                g.setType(sdb.getById(rs.getInt("typeid")));
                g.setStatus(sdb.getById(rs.getInt("Statusid")));
                g.setManagers(adb.getManagerListOfGroupByGroupId(rs.getInt("id")));
                g.setDescription(rs.getString("description"));
                g.setJoinDate(rs.getString("joindate"));
                g.setParent(getGroupById(rs.getInt("parentid")));
                return g;
            }
            //connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        GroupDAO GD = new GroupDAO();

        //System.out.println(GD.searchGroup("", -1, 22, ).size());
    }
}
