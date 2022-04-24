/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dal.SettingDAO;
import model.Setting;
import model.Type;

/**
 *
 * @author Admin
 */
public class SettingDetailDAO extends DBContext{
    
    public Type getTypeById(String id){
        
        String sql="select * from type_settings where id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt(1));
                type.setTypeName(rs.getString(2));
                return type;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public Setting findSetting(String id) {
        String sql="select * from settings where id = ?";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, id);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                Setting s = new Setting();
                s.setId(rs.getInt(1));
                s.setType(rs.getString(2));
                s.setValue(rs.getString(3));
                s.setOrder(rs.getInt(4));
                s.setDescription(rs.getString(5));
                s.setStatus(rs.getInt(6));
                return s;
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void editSetting(String id,String typeId,String value, String order, String description, String status){
        String sql = "UPDATE `workflowbox`.`settings`\n"
                + "SET\n"
                + "`TypeID` = ?,\n"
                + "`Value` = ?,\n"
                + "`Order` = ?,\n"
                + "`Description` = ?,\n"
                + "`Status` = ?\n"
                + "WHERE `id` = ?;";
        try{
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, typeId);
            st.setString(2, value);
            st.setString(3, order);
            st.setString(4, description);
            st.setString(5, status);
            st.setString(6, id);
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    
    
}

