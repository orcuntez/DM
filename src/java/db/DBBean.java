package db;

import java.sql.*;

public class DBBean {
    
    private Connection conn = null;
    
    private final String db_user = "root";
    private final String db_pass = "";
    private final String db_driver = "com.mysql.jdbc.Driver";
    private final String db_url = "jdbc:mysql://localhost:3306/DonationManagerDB";
    
    private String id="";
    private String firstname="";
    private String lastname="";
    private String username="";
    private String password="";
    private String gender="";
    private String age="";
    private String telephone="";
    private String email="";
    private String address="";
    private String usertype="";
    
    private PreparedStatement pstmt;
    ResultSet rs;
    
    //--------------------------------------------------------------------------

    public void initializeJdbc() {
        try {
            Class.forName(db_driver);
            conn = DriverManager.getConnection(db_url, db_user, db_pass);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    //--------------------------------------------------------------------------
    
    public void addUser() {
        try {
            initializeJdbc();
            
            pstmt = conn.prepareStatement("insert into DonationManagerDB.user "
                    + "(username, password, firstname, lastname, gender, email, age, "
                    + "telephone, address, usertype) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
            pstmt.setString(5, gender);
            pstmt.setString(6, email);
            pstmt.setString(7, age);
            pstmt.setString(8, telephone);
            pstmt.setString(9, address);
            pstmt.setString(10, usertype);
            pstmt.executeUpdate();
        }
        catch (Exception ex){           
           ex.printStackTrace();
        }
    }
    
    //--------------------------------------------------------------------------
    
    public int checkUser( String username, String password ) throws SQLException{
        initializeJdbc();
        
        pstmt = conn.prepareStatement("select * from user where username = ? and password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        
        rs = pstmt.executeQuery();
        rs.first();
        
        if(rs.first()){
            String userType = rs.getString("usertype");
            int type = 0;
            if("admin".equals(userType))
                type = 1;
            if("donator".equals(userType))
                type = 2;
            if("foundation".equals(userType))
                type = 3;
            
            return type;
        } else {
            return -1;
        }
    }
    
    //--------------------------------------------------------------------------
    
    public ResultSet returnTable(String sql) throws SQLException{
        initializeJdbc();        

        pstmt = conn.prepareStatement(sql);        
        rs = pstmt.executeQuery();
        rs.beforeFirst();
        return rs;  
    }
    
    //--------------------------------------------------------------------------
    
    public ResultSet viewStatus(String username) throws SQLException{
        initializeJdbc();
        
        pstmt = conn.prepareStatement("select uid from user where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        rs.first();
        int id = Integer.parseInt(rs.getString("uid"));
        
        pstmt = conn.prepareStatement("");
        pstmt.setInt(1, id);
        
        rs = pstmt.executeQuery();
        rs.beforeFirst();        
        return rs;      
    }
    
    
    //--------------------------------------------------------------------------
    
    public String takeFirstname(String username) throws SQLException{
       initializeJdbc();
       pstmt = conn.prepareStatement("select firstname from user where username = ?");
       pstmt.setString(1, username);
       rs = pstmt.executeQuery();
       rs.first();
       
       return rs.getString("firstname");
   }
    
    //--------------------------------------------------------------------------

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    //--------------------------------------------------------------------------
    
}

