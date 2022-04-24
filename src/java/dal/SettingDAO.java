package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Setting;
import model.Type;

public class SettingDAO extends DBContext {

    public List<Setting> getAll() {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT s.*, Name FROM settings s inner join type_settings ts on s.TypeID = ts.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting c = new Setting();
                c.setId(rs.getInt("id"));
                c.setType(rs.getInt("typeid"));
                c.setValue(rs.getString("value"));
                c.setOrder(rs.getInt("order"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getInt("status"));
                //c.setName(rs.getString("name"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public String getSetting(int id) {
        String sql = "SELECT * FROM `settings` where ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String str = rs.getString(3);
                return str;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Setting> getAllByTypeId(int typeId) {
        List<Setting> list = new ArrayList<>();
        String querry = "SELECT * FROM workflowbox.settings where TypeID = ? and status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, typeId);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting g = new Setting();
                g.setId(rs.getInt("id"));
                g.setType(rs.getInt("typeid"));
                g.setValue(rs.getString("value"));
                g.setOrder(rs.getInt("order"));
                g.setDescription(rs.getString("description"));
                g.setStatus(rs.getInt("status"));
                list.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Setting> getAllGroupType() {
        List<Setting> list = new ArrayList<>();
        String querry = "SELECT * FROM workflowbox.settings where TypeID = 16 and status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting g = new Setting();
                g.setId(rs.getInt("id"));
                g.setValue(rs.getString("value"));
                list.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Setting> getAllGroupStatus() {
        List<Setting> list = new ArrayList<>();
        String querry = "SELECT * FROM workflowbox.settings where TypeID = 17 and status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting g = new Setting();
                g.setId(rs.getInt("id"));
                g.setValue(rs.getString("value"));
                list.add(g);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Setting getById(int id) {
        Setting n = null;
        String querry = "SELECT * FROM workflowbox.settings where ID = ? and status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Setting g = new Setting();
                g.setId(rs.getInt("id"));
                g.setType(rs.getInt("typeid"));
                g.setValue(rs.getString("value"));
                g.setOrder(rs.getInt("order"));
                g.setDescription(rs.getString("description"));
                g.setStatus(rs.getInt("status"));
                return g;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public List<Setting> getAllByType(String type) {
        List<Setting> list = new ArrayList<>();
        String sql = "SELECT s.*, Name FROM settings s inner join type_settings ts on s.TypeID = ts.id where name = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting c = new Setting();
                c.setId(rs.getInt("id"));
                c.setType(rs.getInt("typeid"));
                c.setValue(rs.getString("value"));
                c.setOrder(rs.getInt("order"));
                c.setDescription(rs.getString("description"));
                c.setStatus(rs.getInt("status"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Setting> getAllWithCondition(int id, int type, String value, int status, String sortby) {
        List<Setting> list = new ArrayList<>();

        String sql = "SELECT s.*, t.name as typename FROM workflowbox.settings s "
                + " left join workflowbox.type_settings t on s.TypeID = t.ID "
                + " where 1=1 ";
        try {
            if (id > 0) {
                sql += " and s.id = " + id;
            }
            if (type > 0) {
                sql += " and s.typeid = " + type;
            }
            if (value != null && !value.equals("")) {
                sql += " and s.value like '%" + value + "%'";
            }
            if (status > 0) {
                sql += " and s.status = " + status;
            }
            if (sortby != null && !sortby.equals("")) {
                sql += " order by `" + sortby + "` asc";
            }
            System.out.println(sql);
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt("id"));
                s.setOrder(rs.getInt("order"));
                s.setStatus(rs.getInt("status"));
                s.setType(rs.getString("typename"));
                s.setValue(rs.getString("value"));
                list.add(s);
            }
        } catch (Exception e) {
            System.out.println("getAllWithCondition: " + e);
        }
        return list;
    }

    public List<Type> getAllType() {
        List<Type> list = new ArrayList<>();
        String querry = "SELECT * FROM workflowbox.type_settings";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Type t = new Type();
                t.setId(rs.getInt("id"));
                t.setTypeName(rs.getString("name"));
                list.add(t);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Setting> getListByPage(List<Setting> list, int start, int end) {
        ArrayList<Setting> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public static void main(String[] args) {
        SettingDAO dao = new SettingDAO();
        System.out.println(dao.getAllWithCondition(-1, -1, "", -1, "").size());
    }
}
