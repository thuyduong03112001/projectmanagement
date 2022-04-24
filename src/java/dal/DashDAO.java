package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Dash;
import model.NewIssue;

public class DashDAO extends DBContext {

    public List<Dash> getTimesheetReport(int id, int projectId, int projectStatus) {
        List<Dash> list = new ArrayList<>();
        String sql = "SELECT DISTINCT date FROM timesheets ts inner join projects pr on ts.ProjectID = pr.id where accid = ? ";
        String sql2 = "SELECT date, ROUND(sum(process),3) as process , StatusID, ProjectID, pr.Status FROM timesheets ts "
                + "inner join projects pr on ts.ProjectID = pr.id where accid = ? and date = ? ";
        if (projectId != -1) {
            sql += " and projectid = " + projectId;
            sql2 += " and projectid = " + projectId;
        }
        if (projectStatus != -1) {
            sql += " and pr.Status = " + projectStatus;
            sql2 += " and pr.Status = " + projectStatus;
        }
        sql += " order by date";
        sql2 += " group by date, statusid, status, projectid order by date";
        List<String> date = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                date.add(rs.getString("date"));
            }
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, id);
            for (int i = 0; i < date.size(); i++) {
                st2.setString(2, date.get(i));
                ResultSet rs2 = st2.executeQuery();
                Dash c = new Dash();
                while (rs2.next()) {
                    c.setDate(date.get(i));
                    switch (rs2.getInt("statusid")) {
                        case 54:
                            c.setType1(rs2.getDouble("process"));
                            break;
                        case 55:
                            c.setType2(rs2.getDouble("process"));
                            break;
                        case 56:
                            c.setType3(rs2.getDouble("process"));
                            break;
                        default:
                            break;
                    }
                }
                list.add(c);
            }
//            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Dash> getIssuesReport(int id, int projectId, int projectStatus) {
        List<Dash> list = new ArrayList<>();
        String sql = "SELECT distinct CreatedDate FROM issues ts inner join projects pr "
                + "on ts.ProjectID = pr.id inner join works ws on pr.GroupID = ws.GroupID where staffid = ?";
        String sql2 = "SELECT CreatedDate, count(ts.id) as process , typeid, ProjectID, pr.Status FROM issues ts "
                + "inner join projects pr on ts.ProjectID = pr.id inner join works ws on pr.GroupID = ws.GroupID where staffid = ? and CreatedDate = ?";
        if (projectId != -1) {
            sql += " and projectid = " + projectId;
            sql2 += " and projectid = " + projectId;
        }
        if (projectStatus != -1) {
            sql += " and pr.Status = " + projectStatus;
            sql2 += " and pr.Status = " + projectStatus;
        }
        sql += " order by CreatedDate";
        sql2 += " group by CreatedDate, typeid, status, projectid order by CreatedDate";
        List<String> date = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                date.add(rs.getString("CreatedDate"));
            }
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, id);
            for (int i = 0; i < date.size(); i++) {
                st2.setString(2, date.get(i));
                ResultSet rs2 = st2.executeQuery();
                Dash c = new Dash();
                while (rs2.next()) {
                    c.setDate(date.get(i));
                    switch (rs2.getInt("typeid")) {
                        case 74:
                            c.setType1(rs2.getDouble("process"));
                            break;
                        case 75:
                            c.setType2(rs2.getDouble("process"));
                            break;
                        case 76:
                            c.setType3(rs2.getDouble("process"));
                            break;
                        case 77:
                            c.setType4(rs2.getDouble("process"));
                            break;
                        case 78:
                            c.setType5(rs2.getDouble("process"));
                            break;
                        default:
                            break;
                    }
                }
                list.add(c);
            }
//            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> getLastIssues(int id) {
        List<NewIssue> list = new ArrayList<>();
        String sql = "with t as ( SELECT iss.*, ac.FullName, st.value as status, pr.name as ProjectName FROM issues iss "
                + "inner join accounts ac on iss.CreatedBy = ac.id inner join settings st "
                + "on st.id = iss.StatusID inner join projects pr on iss.ProjectID = pr.id "
                + "where incharge = ? order by UpdatedDate desc LIMIT 10) select * from t order by UpdatedDate";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue c = new NewIssue();
                c.setTitle(rs.getString("title"));
                c.setProjectId(rs.getInt("projectid"));
                c.setProjectName(rs.getString("projectName"));
                c.setCreator(rs.getString("Fullname"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }
//            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> getLastRisks(int id) {
        List<NewIssue> list = new ArrayList<>();
        String sql = "with t as (SELECT rs.*, pr.name as ProjectName, st.value as status "
                + " FROM risks rs inner join projects pr on rs.projectId = pr.id "
                + " inner join settings st on statusid = st.id where owner = ? "
                + " order by UpdatedDate desc limit 10) select * from t order by UpdatedDate ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue c = new NewIssue();
                c.setTitle(rs.getString("title"));
                c.setProjectId(rs.getInt("projectid"));
                c.setProjectName(rs.getString("projectName"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }
//            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Dash> getTimesheetReportManager(int id, int projectId, int projectStatus) {
        List<Dash> list = new ArrayList<>();
        String sql = "SELECT DISTINCT date FROM timesheets ts inner join projects pr \n"
                + "on ts.ProjectID = pr.id \n"
                + "join manages m\n"
                + "on m.GroupID = pr.GroupID\n"
                + "where m.ID = ?";
        String sql2 = "SELECT date, ROUND(sum(process),3) as process , StatusID, ProjectID, pr.Status \n"
                + "FROM timesheets ts inner join projects pr \n"
                + "on ts.ProjectID = pr.id \n"
                + "join manages m\n"
                + "on m.GroupID = pr.GroupID\n"
                + "where m.ID = ? and date = ? ";
        if (projectId != -1) {
            sql += " and projectid = " + projectId;
            sql2 += " and projectid = " + projectId;
        }
        if (projectStatus != -1) {
            sql += " and pr.Status = " + projectStatus;
            sql2 += " and pr.Status = " + projectStatus;
        }
        sql += " order by date";
        sql2 += " group by date, statusid, status, projectid order by date";
        List<String> date = new ArrayList<>();
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                date.add(rs.getString("date"));
            }
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, id);
            for (int i = 0; i < date.size(); i++) {
                st2.setString(2, date.get(i));
                ResultSet rs2 = st2.executeQuery();
                Dash c = new Dash();
                while (rs2.next()) {
                    c.setDate(date.get(i));
                    switch (rs2.getInt("statusid")) {
                        case 54:
                            c.setType1(rs2.getDouble("process"));
                            break;
                        case 55:
                            c.setType2(rs2.getDouble("process"));
                            break;
                        case 56:
                            c.setType3(rs2.getDouble("process"));
                            break;
                        default:
                            break;
                    }
                }
                list.add(c);
            }
//            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> getLastIssuesByManager(int id) {
        List<NewIssue> list = new ArrayList<>();
        String sql = "with t as ( SELECT iss.*, ac.FullName, st.value as status, pr.name as ProjectName FROM issues iss\n"
                + "                inner join accounts ac on iss.CreatedBy = ac.id inner join settings st \n"
                + "                on st.id = iss.StatusID inner join projects pr on iss.ProjectID = pr.id \n"
                + "                inner join manages m on pr.GroupId = m.GroupID\n"
                + "                where m.ManagerID = ? order by UpdatedDate desc LIMIT 10) select * from t order by UpdatedDate";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue c = new NewIssue();
                c.setTitle(rs.getString("title"));
                c.setProjectId(rs.getInt("projectid"));
                c.setProjectName(rs.getString("projectName"));
                c.setCreator(rs.getString("Fullname"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }
//            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> getLastRisksByManager(int id) {
        List<NewIssue> list = new ArrayList<>();
        String sql = "with t as (SELECT rs.*, pr.name as ProjectName, st.value as status\n"
                + "                FROM risks rs inner join projects pr on rs.projectId = pr.id\n"
                + "                inner join manages m on pr.GroupId = m.GroupID\n"
                + "                inner join settings st on statusid = st.id \n"
                + "                where m.managerid = ?\n"
                + "                order by UpdatedDate desc limit 10) select * from t order by UpdatedDate";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue c = new NewIssue();
                c.setTitle(rs.getString("title"));
                c.setProjectId(rs.getInt("projectid"));
                c.setProjectName(rs.getString("projectName"));
                c.setStatus(rs.getString("status"));
                list.add(c);
            }
//            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String args[]) {
        DashDAO db = new DashDAO();
        List<Dash> list = db.getIssuesReport(1, -1, 1);
        System.out.println(list);
    }
}
