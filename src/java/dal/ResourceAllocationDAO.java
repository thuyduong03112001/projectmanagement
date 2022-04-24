package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.ResourceAllocation;

public class ResourceAllocationDAO extends DBContext {

    public void updateHrAllocation(int GroupID, int ProjectId, String account, String fullName,
            int ProjectRoleID, String StartDate, String EndDate, float effort, int RID) {
        String sql = "Update resourceallocation r inner join settings s on r.ProjectRoleID = s.id\n"
                + "inner join workflowbox.groups g on r.GroupID = g.ID\n"
                + "inner join projects p on p.ID = r.ProjectId\n"
                + "set r.GroupID = ?, r.ProjectId = ?, r.account = ?, r.FullName = ?,\n"
                + "r.ProjectRoleID = ?, r.StartDate = ? , r.EndDate = ?, r.effort = ? where r.RID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, GroupID);
            st.setInt(2, ProjectId);
            st.setString(3, account);
            st.setString(4, fullName);
            st.setInt(5, ProjectRoleID);
            st.setString(6, StartDate);
            st.setString(7, EndDate);
            st.setFloat(8, effort);
            st.setInt(9, RID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addResourceAllocation(ResourceAllocation r) {
        String sql = "Insert into resourceallocation(GroupID, ProjectId, account, fullName, ProjectRoleID, StartDate, EndDate, effort, notes )\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, r.getGroupID());
            st.setInt(2, r.getProjectId());
            st.setString(3, r.getAccount());
            st.setString(4, r.getFullName());
            st.setInt(5, r.getProjectRoleID());
            st.setString(6, r.getStartDate());
            st.setString(7, r.getEndDate());
            st.setFloat(8, r.getEffort());
            st.setString(9, r.getNotes());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public ResourceAllocation getRID(int RID) {
        ResourceAllocation n = null;
        String sql = "SELECT r.*, g.groupcode as GroupCode, p.Name as projectname, g.Name as 'GroupName', s.Value as ProjectRole\n"
                + "FROM resourceallocation r inner join workflowbox.groups g on g.id = r.GroupID \n"
                + "inner join projects p on p.ID = r.ProjectId inner join settings s on r.ProjectRoleID = s.id where r.RID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, RID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                ResourceAllocation r = new ResourceAllocation();
                r.setRID(rs.getInt("RID"));
                r.setGroupID(rs.getInt("GroupID"));
                r.setProjectId(rs.getInt("ProjectId"));
                r.setProjectRoleID(rs.getInt("ProjectRoleID"));
                r.setNotes(rs.getString("notes"));
                r.setStaffID(rs.getInt("StaffID"));
                r.setGroupCode(rs.getString("GroupCode"));
                r.setProjectname(rs.getString("projectname"));
                r.setAccount(rs.getString("account"));
                r.setFullName(rs.getString("fullName"));
                r.setGroupName(rs.getString("GroupName"));
                r.setProjectRole(rs.getString("ProjectRole"));
                r.setStartDate(rs.getString("StartDate"));
                r.setEndDate(rs.getString("EndDate"));
                r.setEffort(rs.getInt("effort"));
                return r;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public List<ResourceAllocation> getHRAllocationForHR() {
        List<ResourceAllocation> list = new ArrayList<>();
        try {
            String sql = "SELECT r.RID, g.groupcode as GroupCode, p.Name as projectname, r.account, r.fullName, g.Name as 'GroupName', \n"
                    + "s.Value as ProjectRole, r.StartDate, r.EndDate, r.effort\n"
                    + "FROM resourceallocation r inner join workflowbox.groups g on g.id = r.GroupID\n"
                    + "inner join projects p on p.ID = r.ProjectId \n"
                    + "inner join settings s on r.ProjectRoleID = s.id";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ResourceAllocation r = new ResourceAllocation();
                r.setRID(rs.getInt("RID"));
                r.setGroupCode(rs.getString("GroupCode"));
                r.setProjectname(rs.getString("projectname"));
                r.setAccount(rs.getString("account"));
                r.setFullName(rs.getString("fullName"));
                r.setGroupName(rs.getString("GroupName"));
                r.setProjectRole(rs.getString("ProjectRole"));
                r.setStartDate(rs.getString("StartDate"));
                r.setEndDate(rs.getString("EndDate"));
                r.setEffort(rs.getInt("effort"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ResourceAllocation> getHRAllocation(String GroupCode) {
        List<ResourceAllocation> list = new ArrayList<>();
        try {
            String sql = "SELECT r.RID, g.groupcode as GroupCode, p.Name as projectname, r.account, r.fullName, g.Name as 'GroupName', \n"
                    + "s.Value as ProjectRole, r.StartDate, r.EndDate, r.effort \n"
                    + "FROM resourceallocation r inner join workflowbox.groups g on g.id = r.GroupID \n"
                    + "inner join projects p on p.ID = r.ProjectId inner join settings s on r.ProjectRoleID = s.id \n"
                    + "where GroupCode = ? and (not(r.ProjectRoleID = 22))";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, GroupCode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ResourceAllocation r = new ResourceAllocation();
                r.setRID(rs.getInt("RID"));
                r.setGroupCode(rs.getString("GroupCode"));
                r.setProjectname(rs.getString("projectname"));
                r.setAccount(rs.getString("account"));
                r.setFullName(rs.getString("fullName"));
                r.setGroupName(rs.getString("GroupName"));
                r.setProjectRole(rs.getString("ProjectRole"));
                r.setStartDate(rs.getString("StartDate"));
                r.setEndDate(rs.getString("EndDate"));
                r.setEffort(rs.getInt("effort"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ResourceAllocation> hrAllocation(String GroupName, String ProjectName) {
        List<ResourceAllocation> list = new ArrayList<>();
        String sql = "SELECT r.RID, g.groupcode as GroupCode, p.Name as projectname, r.account, r.fullName, g.Name as 'GroupName', \n"
                + "s.Value as ProjectRole, r.StartDate, r.EndDate, r.effort \n"
                + "FROM resourceallocation r inner join workflowbox.groups g on g.id = r.GroupID \n"
                + "inner join projects p on p.ID = r.ProjectId inner join settings s on r.ProjectRoleID = s.id where 1=1";
        try {
            if (GroupName != null && !GroupName.equals("")) {
                sql += " and g.Name = '" + GroupName + "'";
            }
            if (ProjectName != null && !ProjectName.equals("")) {
                sql += " and p.Name = '" + ProjectName + "'";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ResourceAllocation r = new ResourceAllocation();
                r.setRID(rs.getInt("RID"));
                r.setGroupCode(rs.getString("GroupCode"));
                r.setProjectname(rs.getString("projectname"));
                r.setAccount(rs.getString("account"));
                r.setFullName(rs.getString("fullName"));
                r.setGroupName(rs.getString("GroupName"));
                r.setProjectRole(rs.getString("ProjectRole"));
                r.setStartDate(rs.getString("StartDate"));
                r.setEndDate(rs.getString("EndDate"));
                r.setEffort(rs.getInt("effort"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ResourceAllocation> FilterByProjectName() {
        List<ResourceAllocation> list = new ArrayList<>();
        String sql = "select distinct(p.Name) as projectname from resourceallocation r inner join projects p on p.ID = r.ProjectId";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ResourceAllocation a = new ResourceAllocation();
                a.setProjectname(rs.getString("projectname"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ResourceAllocation> FilterByGroupName() {
        List<ResourceAllocation> list = new ArrayList<>();
        String sql = "select distinct(g.Name) as GroupName from resourceallocation r inner join workflowbox.groups g on g.ID = r.GroupID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ResourceAllocation a = new ResourceAllocation();
                a.setGroupName(rs.getString("GroupName"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<ResourceAllocation> getListByPage(ArrayList<ResourceAllocation> list,
            int start, int end) {
        ArrayList<ResourceAllocation> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {
        ResourceAllocationDAO r = new ResourceAllocationDAO();
        System.out.println("a");
    }
}
