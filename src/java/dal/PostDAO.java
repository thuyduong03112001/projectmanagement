package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Post;

public class PostDAO extends DBContext {

    public List<Post> getAll() {
        List<Post> list = new ArrayList<>();
        String sql = "select p.*, a.FullName, st.value as Status, st1.value as Category from posts p \n"
                + "         inner join accounts a on p.hrid = a.id inner join settings st on p.statusid = st.id inner join settings st1 on p.CateID = st1.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post c = new Post();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setDate(rs.getString("date"));
                c.setContent(rs.getString("content"));
                c.setBrief(rs.getString("brief"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setFullName(rs.getString("fullname"));
                c.setStatus(rs.getString("status"));
                c.setCategory(rs.getString("category"));
                //for deeper
                c.setCateId(rs.getInt("p.cateid"));
                c.setHrId(rs.getInt("p.hrid"));
                c.setStatusId(rs.getInt("p.statusid"));
                c.setFlag(rs.getInt("p.flag"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public List<Post> getAllSlide() {
        List<Post> list = new ArrayList<>();
        String sql = "select p.*, a.FullName, st.value as Status, st1.value as Category from posts p \n"
                + "         inner join accounts a on p.hrid = a.id inner join settings st on p.statusid = st.id inner join settings st1 on p.CateID = st1.id where p.statusid = 3";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post c = new Post();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setDate(rs.getString("date"));
                c.setContent(rs.getString("content"));
                c.setBrief(rs.getString("brief"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setFullName(rs.getString("fullname"));
                c.setStatus(rs.getString("status"));
                c.setCategory(rs.getString("category"));
                //for deeper
                c.setCateId(rs.getInt("p.cateid"));
                c.setHrId(rs.getInt("p.hrid"));
                c.setStatusId(rs.getInt("p.statusid"));
                c.setFlag(rs.getInt("p.flag"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Post> getLastFeaturedN(int n) {
        List<Post> list = new ArrayList<>();
        String sql = "select p.*, a.FullName, st.value as Status, st1.value as Category from posts p \n"
                + "         inner join accounts a on p.hrid = a.id inner join settings st on p.statusid = st.id inner join settings st1 on p.CateID = st1.id "
                + "         where flag = 1 and p.statusid = 3 order by p.id desc limit ? ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, n);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post c = new Post();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setDate(rs.getString("date"));
                c.setContent(rs.getString("content"));
                c.setBrief(rs.getString("brief"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setFullName(rs.getString("fullname"));
                c.setStatus(rs.getString("status"));
                c.setCategory(rs.getString("category"));
                //for deeper
                c.setCateId(rs.getInt("p.cateid"));
                c.setHrId(rs.getInt("p.hrid"));
                c.setStatusId(rs.getInt("p.statusid"));
                c.setFlag(rs.getInt("p.flag"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Post getPostById(int id) {
        String sql = "select p.*, a.FullName, st.value as Status, st1.value as Category from posts p inner join accounts a "
                + "on p.hrid = a.id inner join settings st on p.statusid = st.id inner join settings st1 on p.CateID = st1.id where p.id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Post c = new Post();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setDate(rs.getString("date"));
                c.setContent(rs.getString("content"));
                c.setBrief(rs.getString("brief"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setFullName(rs.getString("fullname"));
                c.setStatus(rs.getString("status"));
                c.setCategory(rs.getString("category"));
                //for deeper
                c.setCateId(rs.getInt("p.cateid"));
                c.setHrId(rs.getInt("p.hrid"));
                c.setStatusId(rs.getInt("p.statusid"));
                c.setFlag(rs.getInt("p.flag"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void createPost(Post post) {
        String sql = "INSERT INTO posts (`Title`, `Date`, `Content`, `Brief`, `Thumbnail`, `HRID`, `StatusID`, `CateID`, `Flag`) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, post.getTitle());
            st.setString(2, post.getDate());
            st.setString(3, post.getContent());
            st.setString(4, post.getBrief());
            st.setString(5, post.getThumbnail());
            st.setInt(6, post.getHrId());
            st.setInt(7, post.getStatusId());
            st.setInt(8, post.getCateId());
            st.setInt(9, post.getFlag());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updatePost(Post post) {
        String sql = "UPDATE posts\n"
                + "SET `Title` = ?, `Date` = ?, `Content` = ?, `Brief` = ?, `Thumbnail` = ?, `StatusID` = ?, `CateID` = ?, `Flag` = ?\n"
                + "WHERE (`ID` = ?);";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, post.getTitle());
            st.setString(2, post.getDate());
            st.setString(3, post.getContent());
            st.setString(4, post.getBrief());
            st.setString(5, post.getThumbnail());
            st.setInt(6, post.getStatusId());
            st.setInt(7, post.getCateId());
            st.setInt(8, post.getFlag());
            st.setInt(9, post.getId());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    public void changeStatus(int id, int statusid) {
        String sql = "UPDATE posts SET StatusID = ? WHERE ID = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, statusid);
            st.setInt(2, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

//    public void deletePost(int id) {
//        String sql = "DELETE FROM posts WHERE (`ID` = ?);";
//        try {
//            PreparedStatement st = connection.prepareStatement(sql);
//            st.setInt(1, id);
//            st.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//    }

    public List<Post> filter(String title, List<Integer> categoryId, String brief, String author, int status, String sort) {
        List<Post> list = new ArrayList<>();
        String sql = "select p.*, a.FullName, st.value as Status, st1.value as Category from posts p \n"
                + "         inner join accounts a on p.hrid = a.id inner join settings st on p.statusid = st.id "
                + "         inner join settings st1 on p.CateID = st1.id where 1=1 ";
        sql += " and title like '%" + title + "%'";

        for (int i = 0; i < categoryId.size(); i++) {
            if (i == 0) {
                sql += " and (CateID = " + categoryId.get(i);
            } else {
                sql += " or CateID = " + categoryId.get(i);
            }
            if (i == categoryId.size() - 1) {
                sql += ") ";
            }
        }
        
        sql += " and brief like '%" + brief + "%'";
        sql += " and FullName like '%" + author + "%'";
        if (status != -1) {
            sql += " and StatusID = " + status;
        }
        sql += " " + sort;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Post c = new Post();
                c.setId(rs.getInt("id"));
                c.setTitle(rs.getString("title"));
                c.setDate(rs.getString("date"));
                c.setContent(rs.getString("content"));
                c.setBrief(rs.getString("brief"));
                c.setThumbnail(rs.getString("thumbnail"));
                c.setFullName(rs.getString("fullname"));
                c.setStatus(rs.getString("status"));
                c.setCategory(rs.getString("category"));
                //for deeper
                c.setCateId(rs.getInt("p.cateid"));
                c.setHrId(rs.getInt("p.hrid"));
                c.setStatusId(rs.getInt("p.statusid"));
                c.setFlag(rs.getInt("p.flag"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Post> getListByPage(List<Post> list, int start, int end) {
        ArrayList<Post> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }
    
    public int getMaxId() {
        String sql = "SELECT ID FROM posts WHERE ID = ( SELECT MAX(ID) FROM posts)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if(rs.next()) {
                return rs.getInt("ID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
}
