package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Deliverable;

public class DeliverableDAO extends DBContext {

    public List<Deliverable> getAll() {
        List<Deliverable> list = new ArrayList<>();
        String sql = "SELECT pd.*, st.Value as Status, pr.Name, pr.GroupID FROM project_deliverables pd "
                + "inner join settings st on pd.StatusID = st.id inner join projects pr on pd.ProjectID = pr.id ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Deliverable c = new Deliverable();
                c.setId(rs.getInt("id"));
                c.setProjectId(rs.getInt("projectId"));
                c.setProjectName(rs.getString("name"));
                c.setGroupId(rs.getInt("groupid"));
                c.setDeliverable(rs.getString("deliverable"));
                c.setStatusId(rs.getInt("statusid"));
                c.setStatus(rs.getString("status"));
                c.setPlannedDate(rs.getString("Planned_date"));
                c.setPreviousDate(rs.getString("Previous_date"));
                c.setActualDate(rs.getString("Actual_date"));
                list.add(c);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Deliverable> getRelates(int id, boolean staff) {
        List<Deliverable> list = new ArrayList<>();
        String sql = "";
        if (staff == true) {
            sql = "SELECT pd.*, st.Value as Status, pr.Name, pr.GroupID, pr.status as Active, ws.StaffID FROM project_deliverables pd"
                    + "	inner join settings st on pd.StatusID = st.id inner join projects pr on pd.ProjectID = pr.id inner join `groups` gr on gr.id = pr.groupid"
                    + " inner join works ws on pr.groupid = ws.groupid where StaffID = ? GROUP BY pd.id, projectid, deliverable, statusid, "
                    + " planned_date, previous_date, actual_date, status, name, groupid, active, staffid";
        } else {
            sql = "SELECT pd.*, st.Value as Status, pr.Name, pr.GroupID, pr.status as Active, ws.managerid FROM project_deliverables pd"
                    + "	inner join settings st on pd.StatusID = st.id inner join projects pr on pd.ProjectID = pr.id inner join `groups` gr on gr.id = pr.groupid"
                    + " inner join manages ws on pr.groupid = ws.groupid where managerid = ? GROUP BY pd.id, projectid, deliverable, statusid, "
                    + " planned_date, previous_date, actual_date, status, name, groupid, active, managerid";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Deliverable c = new Deliverable();
                c.setId(rs.getInt("id"));
                c.setProjectId(rs.getInt("projectId"));
                c.setProjectName(rs.getString("name"));
                c.setGroupId(rs.getInt("groupid"));
                c.setDeliverable(rs.getString("deliverable"));
                c.setStatusId(rs.getInt("statusid"));
                c.setStatus(rs.getString("status"));
                c.setPlannedDate(rs.getString("Planned_date"));
                c.setPreviousDate(rs.getString("Previous_date"));
                c.setActualDate(rs.getString("Actual_date"));
                c.setActive(rs.getInt("active"));
                list.add(c);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void updateDeliverable(Deliverable deliverable) {
        String sql = "UPDATE project_deliverables SET Deliverable = ?, StatusID = ?, Planned_Date = ?, "
                + " Previous_Date = ?, Actual_Date = ? WHERE ID = ?";
        String sql2 = "SELECT pd.*, pr.status as Active FROM project_deliverables pd inner join projects pr on pd.projectid = pr.id where pd.id = ?";
        try {
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, deliverable.getId());
            ResultSet rs = st2.executeQuery();
            String oldPlan = "";
            String oldPre = "";
            if (rs.next()) {
                oldPlan = rs.getString("Planned_date");
                oldPre = rs.getString("previous_date");
                if (rs.getInt("active") != 1) {
                    return;
                }
            }
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, deliverable.getDeliverable());
            st.setInt(2, deliverable.getStatusId());
            st.setString(3, deliverable.getPlannedDate());
            if (oldPlan.equals(deliverable.getPlannedDate())) {
                st.setString(4, oldPre);
            } else {
                st.setString(4, oldPlan);
            }

            if (deliverable.getActualDate() == null || "".equals(deliverable.getActualDate())) {
                st.setString(5, null);
            } else {
                st.setString(5, deliverable.getActualDate());
            }
            st.setInt(6, deliverable.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void addDeliverable(Deliverable deliverable) {
        String sql = "INSERT INTO project_deliverables (`ProjectID`, `Deliverable`, `StatusID`, `Planned_Date`) "
                + " VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, deliverable.getProjectId());
            st.setString(2, deliverable.getDeliverable());
            st.setInt(3, deliverable.getStatusId());
            st.setString(4, deliverable.getPlannedDate());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Deliverable> filter(String name, int group, int project, int status, String from, String to) {
        List<Deliverable> list = new ArrayList<>();
        String sql = "SELECT pd.*, st.Value as Status, pr.Name, pr.GroupID FROM project_deliverables pd"
                + "		inner join settings st on pd.StatusID = st.id inner join projects pr on pd.ProjectID = pr.id where 1=1 ";
        sql += " and (Deliverable like '%" + name + "%') ";
        if (status != -1) {
            sql += " and StatusID = " + status;
        }
        if (group != -1) {
            sql += " and GroupID = " + group;
        }
        if (project != -1) {
            sql += " and ProjectID = " + project;
        }
        if (!"".equals(from)) { //planned
            sql += " and Planned_date >= '" + from + "' ";
        }
        if (!"".equals(to)) { //planned
            sql += " and Planned_date <= '" + to + "' ";
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Deliverable c = new Deliverable();
                c.setId(rs.getInt("id"));
                c.setProjectId(rs.getInt("projectId"));
                c.setProjectName(rs.getString("name"));
                c.setGroupId(rs.getInt("groupid"));
                c.setDeliverable(rs.getString("deliverable"));
                c.setStatusId(rs.getInt("statusid"));
                c.setStatus(rs.getString("status"));
                c.setPlannedDate(rs.getString("Planned_date"));
                c.setPreviousDate(rs.getString("Previous_date"));
                c.setActualDate(rs.getString("Actual_date"));
                list.add(c);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Deliverable> getListByPage(List<Deliverable> list, int start, int end) {
        ArrayList<Deliverable> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

     public List<Deliverable> filter(String name, int group, int project, int status, String from, String to, int id, int staff) {
        List<Deliverable> list = new ArrayList<>();
        String sql = "";
        switch (staff) {
            case 1:
                sql = "SELECT pd.*, st.Value as Status, pr.Name, pr.GroupID, pr.status as Active, ws.StaffID FROM project_deliverables pd"
                        + " inner join settings st on pd.StatusID = st.id inner join projects pr on pd.ProjectID = pr.id inner join `groups` gr on gr.id = pr.groupid"
                        + " inner join works ws on pr.groupid = ws.groupid where StaffID = " + id;
                break;
            case 0:
                // manager
                sql = "SELECT pd.*, st.Value as Status, pr.Name, pr.GroupID, pr.status as Active, ws.managerid FROM project_deliverables pd"
                        + "	inner join settings st on pd.StatusID = st.id inner join projects pr on pd.ProjectID = pr.id inner join `groups` gr on gr.id = pr.groupid"
                        + " inner join manages ws on pr.groupid = ws.groupid where managerid = "+ id;
                break;
            default:
                //hr
                sql = "SELECT pd.*, st.Value as Status, pr.Name, pr.GroupID, pr.status as active FROM project_deliverables pd "
                        + "inner join settings st on pd.StatusID = st.id inner join projects pr on pd.ProjectID = pr.id where 1=1";
                break;
        }

        sql += " and (Deliverable like '%" + name + "%') ";
        if (status != -1) {
            sql += " and pd.StatusID = " + status;
        }
        if (group != -1) {
            sql += " and gr.id = " + group;
        }
        if (project != -1) {
            sql += " and ProjectID = " + project;
        }
        if (!"".equals(from)) { //planned
            sql += " and Planned_date >= '" + from + "' ";
        }
        if (!"".equals(to)) { //planned
            sql += " and Planned_date <= '" + to + "' ";
        }
        if (staff==1) {
            sql += " GROUP BY pd.id, projectid, deliverable, statusid, planned_date, previous_date, actual_date, status, name, groupid, active, staffid";
        } else if (staff==0){
            sql += " GROUP BY pd.id, projectid, deliverable, statusid, planned_date, previous_date, actual_date, status, name, groupid, active, managerid";
        } 
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Deliverable c = new Deliverable();
                c.setId(rs.getInt("id"));
                c.setProjectId(rs.getInt("projectId"));
                c.setProjectName(rs.getString("name"));
                c.setGroupId(rs.getInt("groupid"));
                c.setDeliverable(rs.getString("deliverable"));
                c.setStatusId(rs.getInt("statusid"));
                c.setStatus(rs.getString("status"));
                c.setPlannedDate(rs.getString("Planned_date"));
                c.setPreviousDate(rs.getString("Previous_date"));
                c.setActualDate(rs.getString("Actual_date"));
                c.setActive(rs.getInt("active"));
                list.add(c);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public static void main(String args[]) {
        DeliverableDAO db = new DeliverableDAO();
//        System.out.println(db.filter("", -1, -1, -1, "", "", 2, true));
    }
}
