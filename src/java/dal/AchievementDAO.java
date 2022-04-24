package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Achievement;

public class AchievementDAO extends DBContext{
    public List<Achievement> getAll() {
        List<Achievement> list = new ArrayList<>();
        String sql="select * from Achievements";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()) {
                Achievement c = new Achievement();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setDescription(rs.getString("description"));
                c.setIcon(rs.getString("icon"));
                list.add(c);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return list;
    }
}
