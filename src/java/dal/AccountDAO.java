package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Account;

public class AccountDAO extends DBContext {

    public List<Account> getAll() {
        List<Account> list = new ArrayList<>();
        String sql = "select * from accounts";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account();
                c.setAccount(rs.getString("account"));
                c.setEmail(rs.getString("Email"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setFullName(rs.getString("FullName"));
                c.setId(rs.getInt("ID"));

                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Account> getAllUserCanAccessRequirementByRequirementId(int rid) {
        List<Account> list = new ArrayList<>();
        String sql = "select a.* from `requirements` r \n"
                + "inner join `projects` p on r.ProjectID = p.ID\n"
                + "inner join `groups` g on  p.GroupID = g.ID\n"
                + "inner join `works` w on g.ID = w.GroupID\n"
                + "inner join `accounts` a on w.StaffID = a.ID where r.id = ?;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account();
                c.setAccount(rs.getString("account"));
                c.setEmail(rs.getString("Email"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setFullName(rs.getString("FullName"));
                c.setId(rs.getInt("ID"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        sql = "select * from `requirements` r \n"
                + "inner join `projects` p on r.ProjectID = p.ID\n"
                + "inner join `groups` g on  p.GroupID = g.ID\n"
                + "inner join `manages` m on g.ID = m.GroupID\n"
                + "inner join `accounts` a on m.ManagerID = a.id where r.id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account();
                c.setAccount(rs.getString("account"));
                c.setEmail(rs.getString("Email"));
                c.setPhoneNumber(rs.getString("PhoneNumber"));
                c.setFullName(rs.getString("FullName"));
                c.setId(rs.getInt("ID"));
                if (!isAccountInList(list, c)) {
                    list.add(c);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean isAccountInList(List<Account> list, Account a) {
        for (Account account : list) {
            if (account.getId() == a.getId()) {
                return true;
            }
        }
        return false;
    }
    
    public List<Account> getAllAccountCanAccessRequirement(int rid) {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "SELECT r.*,ul.StaffID FROM workflowbox.userlist ul \n"
                    + "inner join manages m on m.ManagerID = ul.ManagerID\n"
                    + "inner join `groups` g on g.ID = m.GroupID\n"
                    + "inner join `projects` p on g.ID = p.GroupID\n"
                    + "inner join `requirements` r on p.ID = r.ProjectID\n"
                    + "where ul.StatusID = 10 and r.ID = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, rid);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = getAccountByAccountId(rs.getInt("staffid"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Account checkCookie(String user, String pass) {
        String sql = "select * from accounts where password = ? and account = ?";
        Account a = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, pass);
            st.setString(2, user);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                a = new Account();
                a.setAccount(rs.getString("Account"));
                a.setEmail(rs.getString("Email"));
                a.setPassword(rs.getString("Password"));
                a.setId(rs.getInt("id"));
                return a;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return a;
    }
    
    public Account getAcc(String acc, String pass) {
        String sql = "with t as (select * from accounts ac where (ac.account = ? or ac.email = ?) and ac.password = ?)\n"
                + "select t.*, st.Value as genderOfvalue, st2.Value as 'role', g.Name as GroupName, st4.Value as ProjectRole, pr.Name as projectname, \n"
                + "pr.StartDate, pr.EndDate, g.groupcode as GroupCode, pr.Description \n"
                + "from t inner join settings st on t.Gender = st.id \n"
                + "inner join settings st2 on t.RoleID = st2.id \n"
                + "inner join works w on t.ID = w.StaffID \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID \n"
                + "inner join settings st3 on g.TypeID = st3.id \n"
                + "inner join settings st4 on w.ProjectRoleID = st4.id\n"
                + "inner join projects pr on g.ID = pr.GroupID;";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc);
            st.setString(2, acc);
            st.setString(3, pass);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account c = new Account();
                c.setId(rs.getInt("id"));
                c.setFullName(rs.getString("fullname"));
                c.setDob(rs.getString("dob"));
                c.setGender(rs.getInt("gender"));
                c.setGenderOfvalue(rs.getString("genderOfvalue"));
                c.setEmail(rs.getString("email"));
                c.setPhoneNumber(rs.getString("phoneNumber"));
                c.setJoinDate(rs.getString("joindate"));
                c.setImage(rs.getString("image"));
                c.setRole(rs.getString("role"));
                c.setRoleId(rs.getInt("roleId"));
                c.setBio(rs.getString("bio"));
                c.setGroupCode(rs.getString("groupcode"));
                c.setGroupName(rs.getString("groupName"));
                c.setProjectRole(rs.getString("projectRole"));
                c.setProjectname(rs.getString("projectname"));
                c.setStartDate(rs.getString("StartDate"));
                c.setEndDate(rs.getString("EndDate"));
                c.setDescription(rs.getString("Description"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public void insert(Account A) {
        String query = "insert into accounts(Fullname,Gender,Email,PhoneNumber,Account,Password) values(?,?,?,?,?,?)";
        int result = 0;
        try {
            PreparedStatement st = connection.prepareStatement(query);
            st.setString(1, A.getFullName());
            st.setInt(2, A.getGender());
            st.setString(3, A.getEmail());
            st.setString(4, A.getPhoneNumber());
            st.setString(5, A.getAccount());
            st.setString(6, A.getPassword());
            result = st.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public String checkExits(String mobile, String email, String account) {
        List<Account> list = getAll();
        String text = "Accept";
        for (Account u : list) {
            if (mobile.equalsIgnoreCase(u.getPhoneNumber())) {
                text = "The PhoneNumber already exits";
            } else if (email.equalsIgnoreCase(u.getEmail())) {
                text = "The Email already exits";
            } else if (account.equalsIgnoreCase(u.getAccount())) {
                text = "The account already exits";
            }
        }
        return text;
    }

    public List<Account> getHRAllocationForHR() {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select g.id as GroupCode, p.Name as ProjectName, a.Account, a.FullName, g.Name as 'GroupName',\n"
                    + "st3.Value as ProjectRole, p.StartDate, p.EndDate, e.Effort as Effort from accounts a \n"
                    + "inner join works w on a.ID = w.StaffID \n"
                    + "inner join settings st3 on w.ProjectRoleID = st3.id \n"
                    + "inner join workflowbox.groups g on w.GroupID = g.ID \n"
                    + "inner join projects p on g.ID = p.GroupID inner join efforts e on e.AccID = a.ID \n"
                    + "inner join manages m on m.GroupID = g.ID";
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account();
                c.setGroupCode(rs.getString("groupcode"));
                c.setProjectname(rs.getString("projectname"));
                c.setAccount(rs.getString("account"));
                c.setFullName(rs.getString("fullname"));
                c.setGroupName(rs.getString("groupname"));
                c.setProjectRole(rs.getString("projectRole"));
                c.setStartDate(rs.getString("StartDate"));
                c.setEndDate(rs.getString("EndDate"));
                c.setEffort(rs.getInt("effort"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void newPass(String str, String newPass) {
        String sql = "select * from accounts\n"
                + "WHERE Account = ? or Email = ?";
        String sql2 = "UPDATE accounts\n"
                + "SET Password = ? WHERE id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, str);
            st.setString(2, str);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                PreparedStatement st2 = connection.prepareStatement(sql2);
                st2.setString(1, newPass);
                st2.setInt(2, rs.getInt("ID"));
                st2.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean check(String account, String email) {
        List<Account> list = getAll();
        for (Account o : list) {
            if (account.equalsIgnoreCase(o.getAccount()) && email.equalsIgnoreCase(o.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public Account getAccount(String email, String account) {
        String sql = "select * from accounts where email = ? and account = ?";
        Account A = null;
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, email);
            st.setString(2, account);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                A = new Account();
                A.setAccount(rs.getString("Account"));
                A.setEmail(rs.getString("Email"));
                A.setPassword(rs.getString("Password"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return A;
    }

    public String check1(String account, String email) {
        List<Account> list = getAll();
        String text = "";
        for (Account o : list) {
            if (!account.equalsIgnoreCase(o.getAccount())) {
                text = "Can't find account match with your username";
                return text;
            } else if (!email.equalsIgnoreCase(o.getEmail())) {
                text = "Can't find account match with your email";
                return text;
            }
        }
        return text;
    }

    public void changePass(String newP, String account, String email) throws SQLException {
        String sql = "update accounts set Password = ? where account = ? and email = ?";
        int result = 0;
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, newP);
        ps.setString(2, account);
        ps.setString(3, email);
        result = ps.executeUpdate();
    }

    public List<Account> getAll1() {
        List<Account> list = new ArrayList<>();
        String sql = "select *, s1.Value as genderOfvalue, g.groupcode as GroupCode, s.Value as StatusOfAccount \n"
                + "from accounts a inner join works w on a.ID = w.StaffID \n"
                + "inner join userlist u on u.staffID = a.id \n"
                + "inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on a.StatusID = s.id inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account();
                c.setId(rs.getInt("id"));
                c.setFullName(rs.getString("fullname"));
                c.setDob(rs.getString("dob"));
                c.setGenderOfvalue(rs.getString("genderOfvalue"));
                c.setEmail(rs.getString("email"));
                c.setPhoneNumber(rs.getString("phoneNumber"));
                c.setStatusId(rs.getInt("statusId"));
                c.setJoinDate(rs.getString("joindate"));
                c.setImage(rs.getString("image"));
                c.setBio(rs.getString("bio"));
                c.setGroupCode(rs.getString("groupcode"));
                c.setStatusOfAccount(rs.getString("StatusOfAccount"));
                c.setGroupID(rs.getInt("GroupID"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean updateProfile(Account acc) {

        boolean f = false;
        try {

            String sql = "update accounts set FullName = ?, PhoneNumber = ?, DOB = ?, Gender = ?, bio = ?, Image = ? where ID = ?";
            PreparedStatement p = connection.prepareStatement(sql);
            p.setString(1, acc.getFullName());
            p.setString(2, acc.getPhoneNumber());
            p.setString(3, acc.getDob());
            p.setInt(4, acc.getGender());
            p.setString(5, acc.getBio());
            p.setString(6, acc.getImage());
            p.setInt(7, acc.getId());

            p.executeUpdate();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f;
    }

    public List<Account> getHRAllocation(String groupcode) {
        List<Account> list = new ArrayList<>();
        try {
            String sql = "select a.id, r.RID, g.groupcode as GroupCode, p.Name as ProjectName, a.Account, a.FullName, a.Email, st4.Value as genderOfvalue, \n"
                    + "s.Value as StatusOfAccount, a.PhoneNumber, g.Name as 'GroupName',\n"
                    + "st3.Value as ProjectRole, r.StartDate, r.EndDate, r.Effort\n"
                    + "from resourceallocation r \n"
                    + "inner join accounts a on a.ID = r.StaffID \n"
                    + "inner join settings st3 on r.ProjectRoleID = st3.id\n"
                    + "inner join settings st4 on st4.id = a.Gender \n"
                    + "inner join userlist u on u.staffID = a.ID \n"
                    + "inner join settings s on u.StatusID = s.id\n"
                    + "inner join workflowbox.groups g on r.GroupID = g.ID \n"
                    + "inner join projects p on p.ID = r.ProjectId \n"
                    + "where g.groupcode = ? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, groupcode);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account();
                c.setId(rs.getInt("id"));
                c.setRID(rs.getInt("RID"));
                c.setGroupCode(rs.getString("groupcode"));
                c.setProjectname(rs.getString("projectname"));
                c.setAccount(rs.getString("account"));
                c.setFullName(rs.getString("fullname"));
                c.setGenderOfvalue(rs.getString("genderOfvalue"));
                c.setEmail(rs.getString("email"));
                c.setPhoneNumber(rs.getString("phoneNumber"));
                c.setStatusOfAccount(rs.getString("StatusOfAccount"));
                c.setGroupName(rs.getString("groupname"));
                c.setProjectRole(rs.getString("projectRole"));
                c.setStartDate(rs.getString("StartDate"));
                c.setEndDate(rs.getString("EndDate"));
                c.setEffort(rs.getInt("effort"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public int changePassword(Account acc) {
        int i = 0;
        String sql = "update accounts set password = ? where id=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, acc.getPassword());
            st.setInt(2, acc.getId());
            i = st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
        return i;
    }

    public List<Account> getListByPage(ArrayList<Account> list,
            int start, int end) {
        ArrayList<Account> arr = new ArrayList<>();
        for (int i = start; i < end; i++) {
            arr.add(list.get(i));
        }
        return arr;
    }

    public List<Account> getManagerListOfGroupByGroupId(int gid) {
        List<Account> list = new ArrayList<>();

        String sql = "select a.* from `groups` g  \n"
                + "inner join `manages` m on m.GroupID = g.ID \n"
                + "inner join `accounts` a on a.ID = m.ManagerID \n"
                + "where g.ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, gid);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                Account u = new Account();
                u.setId(r.getInt("ID"));
                u.setFullName(r.getString("FullName"));
                u.setDob(r.getString("dob"));
                u.setGender(r.getInt("gender"));
                u.setEmail(r.getString("email"));
                u.setPhoneNumber(r.getString("phoneNumber"));
                u.setAccount(r.getString("account"));
                u.setPassword(r.getString("password"));
                u.setJoinDate(r.getString("joinDate"));
                u.setImage(r.getString("image"));
                u.setBio(r.getString("bio"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public List<Account> getAllAccountOwnRequirementByProjectId(int groupId, int projectId) {
        List<Account> list = new ArrayList<>();

        String sql = "select distinct(OwnerID) from requirements where groupId=? and projectId = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, groupId);
            st.setInt(2, projectId);
            ResultSet r = st.executeQuery();
            while (r.next()) {
                Account u = getAccountByAccountId(r.getInt("OwnerId"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    public void editUser(int GroupID, String account, String fullName, String email, String phoneNumber, int gender, int roleId, int statusId, int id) {
        String sql = "update workflowbox.accounts a \n"
                + "inner join works w on a.ID = w.StaffID inner join userlist u on u.staffID = a.id \n"
                + "inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on a.StatusID = s.id \n"
                + "inner join settings st2 on a.RoleID = st2.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id\n"
                + "set w.GroupID = ?, a.Account = ?, a.FullName = ?, a.Email = ?, \n"
                + "a.PhoneNumber = ?, a.Gender = ?, a.RoleID = ?, a.StatusID = ? \n"
                + "where a.ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, GroupID);
            st.setString(2, account);
            st.setString(3, fullName);
            st.setString(4, email);
            st.setString(5, phoneNumber);
            st.setInt(6, gender);
            st.setInt(7, roleId);
            st.setInt(8, statusId);
            st.setInt(9, id);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    //ham cua LTA vi ham cua DTD ko dung duọc
    public Account getAccountByAccountId(int id) {
        Account n = null;
        String querry = "select * from accounts a \n"
                + "where a.id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            System.out.println(querry);
            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setFullName(rs.getString("fullname"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public void addResourceAllocation(int GroupID, int ProjectId, String account, String fullName, int ProjectRoleID, String StartDate, String EndDate, float effort, String notes) {
        String sql = "Insert into resourceallocation(GroupID, ProjectId, account, fullName, ProjectRoleID, StartDate, EndDate, effort, notes )\n"
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int add = 0;
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
            st.setString(9, notes);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

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

    public Account getAccountById(int id) {
        Account n = null;
        String querry = "select *, s1.Value as genderOfvalue, g.groupcode, s.Value as StatusOfAccount, s2.Value as 'Role', w.GroupID, p.Name as ProjectName,\n"
                + "g.Name as 'GroupName', p.id as ProjectId, p.Name as ProjectName, st3.Value as ProjectRole, p.StartDate, p.EndDate, e.Effort as Effort\n"
                + "from accounts a inner join works w on a.ID = w.StaffID \n"
                + "inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on a.StatusID = s.id \n"
                + "inner join settings s2 on a.RoleID = s2.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID \n"
                + "inner join projects p on g.ID = p.GroupID \n"
                + "inner join efforts e on e.AccID = a.ID \n"
                + "inner join manages m on m.GroupID = g.ID\n"
                + "inner join settings st3 on w.ProjectRoleID = st3.id\n"
                + "where a.ID = ?";
        try {
            PreparedStatement st = connection.prepareStatement(querry);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setFullName(rs.getString("fullname"));
                a.setDob(rs.getString("dob"));
                a.setGender(rs.getInt("gender"));
                a.setEmail(rs.getString("email"));
                a.setPhoneNumber(rs.getString("phonenumber"));
                a.setAccount(rs.getString("Account"));
                a.setPassword(rs.getString("password"));
                a.setStatusId(rs.getInt("statusId"));
                a.setJoinDate(rs.getString("joindate"));
                a.setImage(rs.getString("image"));
                a.setRoleId(rs.getInt("roleid"));
                a.setRole(rs.getString("role"));
                a.setGroupID(rs.getInt("GroupID"));
                a.setGenderOfvalue(rs.getString("genderOfvalue"));
                a.setBio(rs.getString("bio"));
                a.setProjectname(rs.getString("projectname"));
                a.setProjectRoleID(rs.getInt("ProjectRoleID"));
                a.setProjectId(rs.getInt("ProjectId"));
                a.setGroupCode(rs.getString("groupcode"));
                a.setStatusOfAccount(rs.getString("StatusOfAccount"));
                a.setGroupName(rs.getString("groupname"));
                a.setProjectRole(rs.getString("projectRole"));
                a.setStartDate(rs.getString("StartDate"));
                a.setEndDate(rs.getString("EndDate"));
                a.setEffort(rs.getInt("effort"));
                return a;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return n;
    }

    public List<Account> userList(String genderOfvalue, String groupCode, String StatusOfAccount) {
        List<Account> list = new ArrayList<>();
        String sql = "select *, s1.Value as genderOfvalue, g.id as GroupCode, s.Value as StatusOfAccount \n"
                + "from accounts a inner join works w on a.ID = w.StaffID\n"
                + "inner join userlist u on u.staffID = a.id \n"
                + "inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on u.StatusID = s.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id where 1 = 1";
        try {
            if (genderOfvalue != null && !genderOfvalue.equals("")) {
                sql += " and s1.Value = '" + genderOfvalue + "'";
            }
            if (groupCode != null && !groupCode.equals("")) {
                sql += " and g.id = '" + groupCode + "'";
            }
            if (StatusOfAccount != null && !StatusOfAccount.equals("")) {
                sql += " and s.Value = '" + StatusOfAccount + "'";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setId(rs.getInt("id"));
                a.setFullName(rs.getString("fullname"));
                a.setDob(rs.getString("dob"));
                a.setGenderOfvalue(rs.getString("genderOfvalue"));
                a.setEmail(rs.getString("email"));
                a.setPhoneNumber(rs.getString("phonenumber"));
                a.setImage(rs.getString("image"));
                a.setAccount(rs.getString("Account"));
                a.setGroupCode(rs.getString("groupCode"));
                a.setStatusOfAccount(rs.getString("StatusOfAccount"));
                a.setJoinDate(rs.getString("joinDate"));
                a.setBio(rs.getString("bio"));
                list.add(a);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Account> hrAllocation(String groupName, String projectname) {
        List<Account> list = new ArrayList<>();
        String sql = "select g.id as GroupCode, p.Name as ProjectName, a.Account, a.FullName, g.Name as 'GroupName',\n"
                + "st3.Value as ProjectRole, p.StartDate, p.EndDate, e.Effort as Effort\n"
                + "from accounts a \n"
                + "inner join works w on a.ID = w.StaffID \n"
                + "inner join settings st3 on w.ProjectRoleID = st3.id\n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join projects p on g.ID = p.GroupID\n"
                + "inner join efforts e on e.AccID = a.ID\n"
                + "inner join manages m on m.GroupID = g.ID where 1 = 1";
        try {
            if (groupName != null && !groupName.equals("")) {
                sql += " and g.Name = '" + groupName + "'";
            }
            if (projectname != null && !projectname.equals("")) {
                sql += " and p.Name = '" + projectname + "'";
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account c = new Account();
                c.setGroupCode(rs.getString("groupcode"));
                c.setProjectname(rs.getString("projectname"));
                c.setAccount(rs.getString("account"));
                c.setFullName(rs.getString("fullname"));
                c.setGroupName(rs.getString("groupname"));
                c.setProjectRole(rs.getString("projectRole"));
                c.setStartDate(rs.getString("StartDate"));
                c.setEndDate(rs.getString("EndDate"));
                c.setEffort(rs.getInt("effort"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Account> FilterByGender() {
        List<Account> list = new ArrayList<>();
        String sql = "select distinct(s1.Value) as genderOfvalue from accounts a inner join works w on a.ID = w.StaffID\n"
                + "inner join userlist u on u.staffID = a.id inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on u.StatusID = s.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setGenderOfvalue(rs.getString("genderOfvalue"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Account> FilterByStatus() {
        List<Account> list = new ArrayList<>();
        String sql = "select distinct(s.Value ) as StatusOfAccount from accounts a inner join works w on a.ID = w.StaffID\n"
                + "inner join userlist u on u.staffID = a.id inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on u.StatusID = s.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setStatusOfAccount(rs.getString("StatusOfAccount"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Account> FilterByGroupCode() {
        List<Account> list = new ArrayList<>();
        String sql = "select distinct(g.id) as GroupCode from accounts a inner join works w on a.ID = w.StaffID\n"
                + "inner join userlist u on u.staffID = a.id inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on u.StatusID = s.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setGroupCode(rs.getString("groupCode"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Account> FilterByProjectName() {
        List<Account> list = new ArrayList<>();
        String sql = "select distinct(p.Name) as ProjectName from accounts a \n"
                + "inner join works w on a.ID = w.StaffID\n"
                + "inner join userlist u on u.staffID = a.id \n"
                + "inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on u.StatusID = s.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id\n"
                + "inner join projects p on g.ID = p.GroupID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setProjectname(rs.getString("projectname"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Account> FilterByGroupName() {
        List<Account> list = new ArrayList<>();
        String sql = "select distinct(g.Name) as groupName from accounts a \n"
                + "inner join works w on a.ID = w.StaffID\n"
                + "inner join userlist u on u.staffID = a.id \n"
                + "inner join settings s1 on a.Gender = s1.id \n"
                + "inner join settings s on u.StatusID = s.id \n"
                + "inner join workflowbox.groups g on w.GroupID = g.ID\n"
                + "inner join settings st3 on g.TypeID = st3.id\n"
                + "inner join projects p on g.ID = p.GroupID";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setGroupName(rs.getString("groupName"));
                list.add(a);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static void main(String[] args) {
        AccountDAO dao = new AccountDAO();
        System.out.println(dao.getAllUserCanAccessRequirementByRequirementId(1).size());
    }
}
