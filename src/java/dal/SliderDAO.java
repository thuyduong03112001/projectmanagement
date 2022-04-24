package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Slider;

public class SliderDAO extends DBContext {

    public List<Slider> getAll() {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT sl.*, st.value as status FROM sliders sl inner join settings st on sl.statusid = st.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider c = new Slider();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setBacklink(rs.getString("backlink"));
                c.setImage(rs.getString("image"));
                c.setStatusId(rs.getInt("statusid"));
                c.setStatus(rs.getString("status"));
                c.setNote(rs.getString("note"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public Slider getSliderById(int id) {    
        String sql = "SELECT sl.*, st.value as status FROM sliders sl inner join settings st on sl.statusid = st.id where sl.id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider c = new Slider();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setBacklink(rs.getString("backlink"));
                c.setImage(rs.getString("image"));
                c.setStatusId(rs.getInt("statusid"));
                c.setStatus(rs.getString("status"));
                c.setNote(rs.getString("note"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void changeStatus(int id, int statusid) {
        String sql = "UPDATE sliders SET StatusID = ? WHERE ID = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, statusid);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
     public void updatePost(Slider slider) {
        String sql = "UPDATE sliders SET Title = ?, Image = ?, StatusID = ?, Note = ? WHERE ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, slider.getTitle());
            st.setString(2, slider.getImage());
            st.setInt(3, slider.getStatusId());
            st.setString(4, slider.getNote());
            st.setInt(5, slider.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List<Slider> filter(String search, int status) {
        List<Slider> list = new ArrayList<>();
        String sql = "SELECT sl.*, st.value as status FROM sliders sl inner join settings st on sl.statusid = st.id where 1=1 ";
        sql += " and (title like '%" + search + "%' or backlink like '%"  + search + "%')";
        if (status != -1) {
            sql += " and StatusID = " + status;
        }
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Slider c = new Slider();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setBacklink(rs.getString("backlink"));
                c.setImage(rs.getString("image"));
                c.setStatusId(rs.getInt("statusid"));
                c.setStatus(rs.getString("status"));
                c.setNote(rs.getString("note"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Slider> getListByPage(List<Slider> list, int start, int end) {
        ArrayList<Slider> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
}
