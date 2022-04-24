package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.NewIssue;

/**
 *
 * @author Admin
 */
public class IssueDAO extends DBContext {

    public String getSetting(int id) {
        String sql = "SELECT * FROM workflowbox.settings where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String value = rs.getString(3);
                return value;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<NewIssue> getAll() {
        List<NewIssue> list = new ArrayList<>();
        String sql = "SELECT i.id, Title, p.Name as projectName, a.FullName as CreatedBy1, \n"
                + "s.Value as 'itype', CreatedDate, s1.Value as 'istatus', a1.FullName as Incharge1, UpdatedDate as UpdatedDate1\n"
                + "FROM workflowbox.issues i inner join accounts a on a.ID = i.CreatedBy\n"
                + "inner join accounts a1 on a1.ID = i.Incharge\n"
                + "inner join projects p on p.ID = i.ProjectID\n"
                + "inner join settings s on s.id = i.TypeID\n"
                + "inner join settings s1 on s1.id = i.StatusID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setId(rs.getInt("id"));
                i.setTitle(rs.getString("title"));
                i.setProjectName(rs.getString("projectName"));
                i.setItype(rs.getString("itype"));
                i.setCreatedDate(rs.getString("CreatedDate"));
                i.setIstatus(rs.getString("istatus"));
                i.setCreatedBy1(rs.getString("CreatedBy1"));
                i.setIncharge1(rs.getString("Incharge1"));
                i.setUpdatedDate1(rs.getString("UpdatedDate1"));
                list.add(i);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> FilterByType() {
        List<NewIssue> list = new ArrayList<>();
        try {
            String sql = "select distinct(s.Value) as 'itype' from issues i inner join settings s on s.id = i.TypeID";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setItype(rs.getString("itype"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> FilterByProjectName() {
        List<NewIssue> list = new ArrayList<>();
        String sql = "select distinct(p.Name) as 'projectName' from issues i\n"
                + "inner join projects p on p.ID = i.ProjectID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setProjectName(rs.getString("projectName"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> FilterByCreatedDate() {
        List<NewIssue> list = new ArrayList<>();
        String sql = "select distinct(CreatedDate) from issues;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setCreatedDate(rs.getString("CreatedDate"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> FilterByCreatedBy() {
        List<NewIssue> list = new ArrayList<>();
        String sql = "select distinct(a.FullName) as CreatedBy1 from issues i\n"
                + "inner join accounts a on a.ID = i.CreatedBy";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setCreatedBy1(rs.getString("CreatedBy1"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> FilterByIncharge() {
        List<NewIssue> list = new ArrayList<>();
        String sql = "select distinct(a1.FullName) as Incharge1 from issues i\n"
                + "inner join accounts a1 on a1.ID = i.Incharge";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setIncharge1(rs.getString("Incharge1"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> FilterByStatus() {
        List<NewIssue> list = new ArrayList<>();
        String sql = "select distinct(s1.Value) as 'iStatus' from issues i\n"
                + "inner join settings s1 on s1.id = i.StatusID;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setIstatus(rs.getString("istatus"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> searchByTitle(String title) {
        List<NewIssue> list = new ArrayList<>();
        String sql = "SELECT Title, p.Name as projectName, a.FullName as CreatedBy1, \n"
                + "s.Value as 'itype', CreatedDate, s1.Value as 'istatus', a1.FullName as Incharge1, UpdatedDate as UpdatedDate1\n"
                + "FROM workflowbox.issues i inner join accounts a on a.ID = i.CreatedBy\n"
                + "inner join accounts a1 on a1.ID = i.Incharge inner join projects p on p.ID = i.ProjectID\n"
                + "inner join settings s on s.id = i.StatusID inner join settings s1 on s1.id = i.StatusID where Title like ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, "%" + title + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setTitle(rs.getString("title"));
                i.setProjectName(rs.getString("projectName"));
                i.setItype(rs.getString("itype"));
                i.setCreatedDate(rs.getString("CreatedDate"));
                i.setIstatus(rs.getString("istatus"));
                i.setCreatedBy1(rs.getString("CreatedBy1"));
                i.setIncharge1(rs.getString("Incharge1"));
                i.setUpdatedDate1(rs.getString("UpdatedDate1"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> filter(String itype, String projectName, String CreatedDate, String CreatedBy1, String Incharge1, String istatus){
        List<NewIssue> list = new ArrayList<>();
        String sql = "SELECT Title, p.Name as projectName, a.FullName as CreatedBy1, \n"
                + "s.Value as 'iType', CreatedDate, s1.Value as 'iStatus', a1.FullName as Incharge1, UpdatedDate as UpdatedDate1\n"
                + "FROM workflowbox.issues i inner join accounts a on a.ID = i.CreatedBy\n"
                + "inner join accounts a1 on a1.ID = i.Incharge inner join projects p on p.ID = i.ProjectID\n"
                + "inner join settings s on s.id = i.StatusID inner join settings s1 on s1.id = i.StatusID where 1=1";
        try{
            if(itype != null && !itype.equals("")){
                sql += " and s.Value = '" + itype + "'";
            }
            if(projectName != null && !projectName.equals("")){
                sql += " and p.Name = '" + projectName + "'";
            }
            if(CreatedDate != null && !CreatedDate.equals("")){
                sql += " and CreatedDate = '" + CreatedDate + "'";
            }
            if(CreatedBy1 != null && !CreatedBy1.equals("")){
                sql += " and a.FullName = '" + CreatedBy1 + "'";
            }
            if(Incharge1 != null && !Incharge1.equals("")){
                sql += " and a1.FullName = '" + Incharge1 + "'";
            }
            if(istatus != null && !istatus.equals("")){
                sql += " and s1.Value = '" + istatus + "'";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NewIssue i = new NewIssue();
                i.setTitle(rs.getString("title"));
                i.setProjectName(rs.getString("projectName"));
                i.setItype(rs.getString("itype"));
                i.setCreatedDate(rs.getString("CreatedDate"));
                i.setIstatus(rs.getString("istatus"));
                i.setCreatedBy1(rs.getString("CreatedBy1"));
                i.setIncharge1(rs.getString("Incharge1"));
                i.setUpdatedDate1(rs.getString("UpdatedDate1"));
                list.add(i);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        return list;
    }

    public List<NewIssue> getListByPage(ArrayList<NewIssue> list, int start, int end) {
        ArrayList<NewIssue> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public void deleteIssue(int id) {
        String sql = "delete from issues where id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        IssueDAO dao = new IssueDAO();
        List i = dao.FilterByProjectName();
        System.out.println(i.toString());
    }

}
