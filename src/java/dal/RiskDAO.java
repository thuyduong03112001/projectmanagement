/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Risk;
import model.RiskResponse;

/**
 *
 * @author HP
 */
public class RiskDAO extends DBContext {

    public List<Risk> getAll() {
        List<Risk> list = new ArrayList<>();
        String sql = "select * from risks";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Risk r = new Risk();
                r.setID(rs.getInt("ID"));
                r.setCateID(rs.getInt("categoryID"));
                r.setCreatedDate(rs.getDate("CreatedDate"));
                r.setDescription(rs.getString("Description"));
                r.setImpact(rs.getString("Impact"));
                r.setCreatedBy(rs.getString("CreatedBy"));
                r.setOwner(rs.getString("Owner"));
                r.setPossibility(rs.getString("Possibility"));
                r.setProjectName(rs.getString("ProjectName"));
                r.setRank(rs.getInt("Rank"));
                r.setRootCause(rs.getString("RootCause"));
                r.setStatusID(rs.getInt("StatusID"));
                r.setTitle(rs.getString("Title"));
                r.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
       public List<Account> getInactivate() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from accounts where roleID not in ('11')";
        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account A = new Account();
                A.setFullName(rs.getString("FullName"));
                list.add(A);
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }
    public List<RiskResponse> getAllR() {
        List<RiskResponse> list = new ArrayList<>();
        String sql = "select * from riskR";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                RiskResponse RE = new RiskResponse();
                RE.setId(rs.getInt("id"));
                RE.setResID(rs.getInt("riskID"));
                RE.setAction(rs.getString("action"));
                RE.setNewPossibility(rs.getString("newPossibility"));
                RE.setNewImpact(rs.getString("newImpact"));
                RE.setFrom(rs.getDate("from"));
                RE.setTo(rs.getDate("to"));
                list.add(RE);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Risk> getbyTitle(String title) {
        List<Risk> list = new ArrayList<>();
        String sql = "select * from risks where title like '%?%'";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Risk r = new Risk();
                r.setID(rs.getInt("ID"));
                r.setCateID(rs.getInt("categoryID"));
                r.setCreatedDate(rs.getDate("CreatedDate"));
                r.setDescription(rs.getString("Description"));
                r.setImpact(rs.getString("Impact"));
                r.setCreatedBy(rs.getString("CreatedBy"));
                r.setOwner(rs.getString("Owner"));
                r.setPossibility(rs.getString("Possibility"));
                r.setProjectName(rs.getString("ProjectName"));
                r.setRank(rs.getInt("Rank"));
                r.setRootCause(rs.getString("RootCause"));
                r.setStatusID(rs.getInt("StatusID"));
                r.setTitle(rs.getString("Title"));
                r.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Risk> getbyFilter(String name, String createdBy, String cateID, String status, String owner, String from, String to) throws ParseException {
        List<Risk> list = new ArrayList<>();
        int x = 0;
        String sql = "select * from risks where ProjectName=?";
        if (createdBy != "") {
            sql += "and CreatedBy=?";
        }
        if (cateID != ("")) {
            sql += "and categoryID = ?";
        }
        if (status != ("")) {
            sql += "and statusID = ?";
        }
        if (owner != ("")) {
            sql += "and owner = ?";
        }
        if (from != ("")) {
            sql += "between ? ";
        }
        if (to != ("")) {
            sql += "and ?";
        }
// and CreatedBy=? and categoryID = ? and statusID = ? and owner =? between ? and ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            x++;
            st.setString(x, name);
            if (createdBy != "") {
                x++;
                st.setString(x, createdBy);
            }
            if (cateID != "") {
                x++;
                st.setString(x, cateID);

            }
            if (status != "") {
                x++;
                st.setString(x, status);
                
            }
            if (owner != "") {
                x++;
                st.setString(x, owner);
                
            }
            if (from != "") {
                x++;
                st.setString(x, from);
                
            }
            if (to != "") {
                x++;
                st.setString(x, to);
            }
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Risk r = new Risk();
                r.setID(rs.getInt("ID"));
                r.setCateID(rs.getInt("categoryID"));
                r.setCreatedDate(rs.getDate("CreatedDate"));
                r.setDescription(rs.getString("Description"));
                r.setImpact(rs.getString("Impact"));
                r.setCreatedBy(rs.getString("CreatedBy"));
                r.setOwner(rs.getString("Owner"));
                r.setPossibility(rs.getString("Possibility"));
                r.setProjectName(rs.getString("ProjectName"));
                r.setRank(rs.getInt("Rank"));
                r.setRootCause(rs.getString("RootCause"));
                r.setStatusID(rs.getInt("StatusID"));
                r.setTitle(rs.getString("Title"));
                r.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(r);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public void edit(Risk R) {
        int result = 0;
        String sql="update risks set projectName=?,title =?,categoryID = ?,createdBy = ?,`owner` = ?,`description` = ?,rootcause = ?,possibility = ?,impact = ?, `rank` = ?,statusID = ?, updatedDate = ? where ID = ?";
        try
        {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, R.getProjectName());
        ps.setString(2, R.getTitle());
        ps.setInt(3, R.getCateID());
        ps.setString(4, R.getCreatedBy());
        ps.setString(5, R.getOwner());
        ps.setString(6, R.getDescription());
        ps.setString(7, R.getRootCause());
        ps.setString(8, R.getPossibility());
        ps.setString(9, R.getImpact());
        ps.setInt(10, R.getRank());
        ps.setInt(11, R.getStatusID());
        ps.setString(12, R.getUpdatedDate1());
        ps.setInt(13, R.getID());
        result=ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
     public void editR(RiskResponse R) {
        int result = 0;
        String sql="update riskR set riskID=?,action=?,`from`=?,`to`=?,newPossibility=?,newImpact=? where id=?";
        try
        {
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, R.getResID());
        ps.setString(2, R.getAction());
        ps.setString(3, R.getDfrom());
        ps.setString(4, R.getDto());
        ps.setString(5, R.getNewPossibility());
        ps.setString(6, R.getNewImpact());
        ps.setInt(7, R.getId());
        result=ps.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    public void insert(Risk R) {
        int result = 0;
        String sql = "insert into risks(createdDate,ProjectName,Title,CategoryID,CreatedBy,`Owner`,`Description`,RootCause,Possibility,Impact,`Rank`,StatusID,UpdatedDate) values (now(),?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, R.getProjectName());
            ps.setString(2, R.getTitle());
            ps.setInt(3, R.getCateID());
            ps.setString(4, R.getCreatedBy());
            ps.setString(5, R.getOwner());
            ps.setString(6, R.getDescription());
            ps.setString(7, R.getRootCause());
            ps.setString(8, R.getPossibility());
            ps.setString(9, R.getImpact());
            ps.setInt(10, R.getRank());
            ps.setInt(11, R.getStatusID());
            ps.setString(12, R.getUpdatedDate1());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }
    public void insertR(RiskResponse R) {
        int result = 0;
        String sql = "insert into riskR(riskID,action,`from`,`to`,newPossibility,newImpact) values (?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, R.getResID());
            ps.setString(2, R.getAction());
            ps.setString(3, R.getDfrom());
            ps.setString(4, R.getDto());
            ps.setString(5, R.getNewPossibility());
            ps.setString(6, R.getNewImpact());
            result = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public int getTotalRisk() {
        String query = "Select count(*) from risks";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }
    public int getTotalRiskR() {
        String query = "Select count(*) from riskR";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<Risk> pagingProduct(int index) {
        List<Risk> list = new ArrayList<>();
        String query = "Select * from risks limit 6 OFFSET ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new Risk(
                        rs.getInt(1),
                        rs.getDate(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6),
                        rs.getString(7),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10),
                        rs.getString(11),
                        rs.getInt(12),
                        rs.getInt(13),
                        rs.getDate(14)));
            }
        } catch (Exception e) {
        }
        return list;
    }
    public List<RiskResponse> pagingProductR(int index) {
        List<RiskResponse> list = new ArrayList<>();
        String query = "Select * from riskR limit 6 OFFSET ?";
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setInt(1, (index - 1) * 6);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(new RiskResponse(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDate(4),
                        rs.getDate(5),
                        rs.getString(6),
                        rs.getString(7)));
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deletebyId(int id) {
        String sql = "delete from risks where id = ?";
        int x = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            x = ps.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public static void main(String[] args) throws ParseException {
        RiskDAO r = new RiskDAO();
        List<Risk> list = r.getbyTitle("Prj");
        for (Risk risk : list) {
            System.out.println(risk);
        }
    }
}
