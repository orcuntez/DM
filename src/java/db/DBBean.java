package db;

import java.sql.*;

/**
 *
 * @author Tuncay
 */
public class DBBean {

    private Connection conn = null;

    private final String db_user = "root";
    private final String db_pass = "";
    private final String db_driver = "com.mysql.jdbc.Driver";
    private final String db_url = "jdbc:mysql://localhost:3306/DonationManagerDB";

    private String id = "";
    private String firstname = "";
    private String lastname = "";
    private String username = "";
    private String password = "";
    private String gender = "";
    private String age = "";
    private String telephone = "";
    private String email = "";
    private String address = "";

    private String foundationname = "";

    private String dtype = "";
    private String dcategory = "";
    private String amount = "";
    private String description = "";

    private String rtype = "";
    private String rcategory = "";

    private PreparedStatement pstmt;
    ResultSet rs;

    //--------------------------------------------------------------------------

    /**
     *
     * @throws SQLException
     */
    public void initializeJdbc() throws SQLException {
        try {
            Class.forName(db_driver);
            conn = DriverManager.getConnection(db_url, db_user, db_pass);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @return
     * @throws SQLException
     */
    public boolean addDonator() throws SQLException {
        try {
            initializeJdbc();

            pstmt = conn.prepareStatement("insert into DonationManagerDB.donator "
                    + "(username, password, firstname, lastname, gender, email, age, "
                    + "telephone, address, status) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, firstname);
            pstmt.setString(4, lastname);
            pstmt.setString(5, gender);
            pstmt.setString(6, email);
            pstmt.setString(7, age);
            pstmt.setString(8, telephone);
            pstmt.setString(9, address);
            pstmt.setString(10, "idle");
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean editDonator(String username) throws SQLException {
        try {
            initializeJdbc();

            pstmt = conn.prepareStatement("update donator set password = ?, firstname = ?, lastname = ?, email = ?, age = ?, telephone = ?, address = ?  where username = ?");

            pstmt.setString(1, password);
            pstmt.setString(2, firstname);
            pstmt.setString(3, lastname);
            pstmt.setString(4, email);
            pstmt.setString(5, age);
            pstmt.setString(6, telephone);
            pstmt.setString(7, address);
            pstmt.setString(8, username);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean editFoundation(String username) throws SQLException {
        try {
            initializeJdbc();

            pstmt = conn.prepareStatement("update foundation set password = ?, telephone = ?, email = ?, address = ? where username = ?");

            pstmt.setString(1, password);
            pstmt.setString(2, telephone);
            pstmt.setString(3, email);
            pstmt.setString(4, address);
            pstmt.setString(5, username);

            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @return
     * @throws SQLException
     */
    public boolean addFoundation() throws SQLException {
        try {
            initializeJdbc();

            pstmt = conn.prepareStatement("insert into DonationManagerDB.foundation "
                    + "(username, password, foundationname, email, "
                    + "telephone, address, status) values (?, ?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, foundationname);
            pstmt.setString(4, email);
            pstmt.setString(5, telephone);
            pstmt.setString(6, address);
            pstmt.setString(7, "pending");
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean addDonation(String username) throws SQLException {
        try {
            initializeJdbc();

            int fk_uid = takeUidFromUsername(username, 2);

            pstmt = conn.prepareStatement("insert into DonationManagerDB.donation "
                    + "(dtype, dcategory, amount, description, status, FK_uid)"
                    + "values (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, dtype);
            pstmt.setString(2, dcategory);
            pstmt.setString(3, amount);
            pstmt.setString(4, description);
            pstmt.setString(5, "pending");
            pstmt.setInt(6, fk_uid);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public boolean addRequest(String username) throws SQLException {
        try {
            initializeJdbc();

            int fk_uid = takeUidFromUsername(username, 3);

            pstmt = conn.prepareStatement("insert into DonationManagerDB.request "
                    + "(rtype, rcategory, amount, description, status, FK_uid)"
                    + "values (?, ?, ?, ?, ?, ?)");

            pstmt.setString(1, rtype);
            pstmt.setString(2, rcategory);
            pstmt.setString(3, amount);
            pstmt.setString(4, description);
            pstmt.setString(5, "pending");
            pstmt.setInt(6, fk_uid);
            pstmt.executeUpdate();
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean checkDonator(String username, String password) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from donator where username = ? and password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        rs = pstmt.executeQuery();
        rs.first();

        if (rs.first()) {
            return true;
        } else {
            return false;
        }
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean checkFoundation(String username, String password) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from foundation where username = ? and password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        rs = pstmt.executeQuery();
        rs.first();

        if (rs.first()) {
            return true;
        } else {
            return false;
        }
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @param password
     * @return
     * @throws SQLException
     */
    public boolean checkAdmin(String username, String password) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from admin where username = ? and password = ?");
        pstmt.setString(1, username);
        pstmt.setString(2, password);

        rs = pstmt.executeQuery();
        rs.first();

        if (rs.first()) {
            return true;
        } else {
            return false;
        }
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param sql
     * @return
     * @throws SQLException
     */
    public ResultSet returnTable(String sql) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement(sql);
        rs = pstmt.executeQuery();
        rs.beforeFirst();
        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public ResultSet takeMyDonations(String username) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select uid from donator where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        rs.first();
        int id = Integer.parseInt(rs.getString("uid"));

        pstmt = conn.prepareStatement("select * from donation where donation.FK_uid = ?");
        pstmt.setInt(1, id);

        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public ResultSet takeMyRequests(String username) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select uid from foundation where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        rs.first();
        int id = Integer.parseInt(rs.getString("uid"));

        pstmt = conn.prepareStatement("select * from request where request.FK_uid = ?");
        pstmt.setInt(1, id);

        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public ResultSet takeMyReceiveds(String username) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select uid from foundation where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        rs.first();
        int id = Integer.parseInt(rs.getString("uid"));

        pstmt = conn.prepareStatement("select * from received where received.FK_uid = ?");
        pstmt.setInt(1, id);

        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @return
     * @throws SQLException
     */
    public ResultSet takeStockList() throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from stock");

        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param status
     * @return
     * @throws SQLException
     */
    public ResultSet takeDonations(String status) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from donation where status = ?");
        pstmt.setString(1, status);

        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param status
     * @return
     * @throws SQLException
     */
    public ResultSet takeFoundations(String status) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from foundation where status = ?");
        pstmt.setString(1, status);

        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param status
     * @return
     * @throws SQLException
     */
    public ResultSet takeRequests(String status) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from request where status = ?");
        pstmt.setString(1, status);

        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public String takeFirstname(String username) throws SQLException {
        initializeJdbc();
        pstmt = conn.prepareStatement("select firstname from donator where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        rs.first();

        return rs.getString("firstname");
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public String takeFoundationname(String username) throws SQLException {
        initializeJdbc();
        pstmt = conn.prepareStatement("select foundationname from foundation where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        rs.first();

        return rs.getString("foundationname");
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public String takeAdminname(String username) throws SQLException {
        initializeJdbc();
        pstmt = conn.prepareStatement("select firstname from admin where username = ?");
        pstmt.setString(1, username);
        rs = pstmt.executeQuery();
        rs.first();

        return rs.getString("firstname");
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param username
     * @param usertype
     * @return
     * @throws SQLException
     */
    public int takeUidFromUsername(String username, int usertype) throws SQLException {

        if (usertype == 1) {
            pstmt = conn.prepareStatement("select uid from admin where username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            rs.first();
            int id = Integer.parseInt(rs.getString("uid"));

            return id;

        } else if (usertype == 2) {
            pstmt = conn.prepareStatement("select uid from donator where username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            rs.first();
            int id = Integer.parseInt(rs.getString("uid"));

            return id;

        } else if (usertype == 3) {
            pstmt = conn.prepareStatement("select uid from foundation where username = ?");
            pstmt.setString(1, username);
            rs = pstmt.executeQuery();
            rs.first();
            int id = Integer.parseInt(rs.getString("uid"));

            return id;
        }
        return -1;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public String takeDonatorsUsername(String uid) throws SQLException {

        pstmt = conn.prepareStatement("select * from donator, donation where donator.uid=donation.FK_uid and donation.FK_uid = ?");
        int id = Integer.parseInt(uid);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        rs.first();
        String username = rs.getString("username");

        return username;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param uid
     * @return
     * @throws SQLException
     */
    public String takeFoundationsUsername(String uid) throws SQLException {

        pstmt = conn.prepareStatement("select * from foundation, request where foundation.uid=request.FK_uid and request.FK_uid = ?");
        int id = Integer.parseInt(uid);
        pstmt.setInt(1, id);
        rs = pstmt.executeQuery();
        rs.first();
        String username = rs.getString("username");

        return username;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param type
     * @param category
     * @return
     * @throws SQLException
     */
    public int takeStockAmount(String type, String category) throws SQLException {
        try {
            initializeJdbc();

            pstmt = conn.prepareStatement("select amount from stock where dtype = ? and dcategory = ?");
            pstmt.setString(1, type);
            pstmt.setString(2, category);

            rs = pstmt.executeQuery();
            rs.first();

            return rs.getInt("amount");

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return 0;
        }
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param rid
     * @return
     * @throws SQLException
     */
    public int takeAmountFromRid(int rid) throws SQLException {
        try {
            initializeJdbc();

            pstmt = conn.prepareStatement("select amount from request where rid = ?");
            pstmt.setInt(1, rid);
            rs = pstmt.executeQuery();
            rs.first();

            return rs.getInt("amount");

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return 0;
        }
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param rid
     * @return
     * @throws SQLException
     */
    public int takeStockAmountFromRid(int rid) throws SQLException {
        try {
            initializeJdbc();

            pstmt = conn.prepareStatement("select * from request where rid = ?");
            pstmt.setInt(1, rid);
            rs = pstmt.executeQuery();
            rs.first();

            String type = rs.getString("rtype");
            String category = rs.getString("rcategory");

            return takeStockAmount(type, category);

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return 0;
        }
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param status
     * @return
     * @throws SQLException
     */
    public ResultSet takeDonators(String status) throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from donator where status = ?");
        pstmt.setString(1, status);
        rs = pstmt.executeQuery();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param uid
     * @param status
     * @return
     * @throws SQLException
     */
    public boolean deletedonator(String uid, String status) throws SQLException {
        try {
            if (status.equals("idle")) {
                initializeJdbc();

                int id = Integer.parseInt(uid);

                pstmt = conn.prepareStatement("update donator set status = ? where uid = ?");
                pstmt.setString(1, status);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            } else {
                initializeJdbc();

                int id = Integer.parseInt(uid);

                pstmt = conn.prepareStatement("delete from donator where uid = ?");
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @return
     * @throws SQLException
     */
    public ResultSet takeAllDonations() throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from donation where status = ?");
        pstmt.setString(1, "approved");
        rs = pstmt.executeQuery();
        rs.first();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @return
     * @throws SQLException
     */
    public ResultSet takeAllReceiveds() throws SQLException {
        initializeJdbc();

        pstmt = conn.prepareStatement("select * from request where status = ?");
        pstmt.setString(1, "approved");
        rs = pstmt.executeQuery();
        rs.first();

        return rs;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param uid
     * @param status
     * @return
     */
    public boolean updateFoundationStatus(String uid, String status) {
        try {
            if (status.equals("approved")) {
                initializeJdbc();

                int id = Integer.parseInt(uid);

                pstmt = conn.prepareStatement("update foundation set status = ? where uid = ?");
                pstmt.setString(1, status);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            } else {
                initializeJdbc();

                int id = Integer.parseInt(uid);

                pstmt = conn.prepareStatement("delete from foundation where uid = ?");
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param did
     * @param status
     * @return
     */
    public boolean updateStatus(String did, String status) {
        try {
            if (status.equals("approved")) {
                initializeJdbc();

                int id = Integer.parseInt(did);

                pstmt = conn.prepareStatement("update donation set status = ? where did = ?");
                pstmt.setString(1, status);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();

                pstmt = conn.prepareStatement("select * from donation where did = ?");
                pstmt.setInt(1, id);
                rs = pstmt.executeQuery();
                rs.first();
                String dt = rs.getString("dtype");
                String dc = rs.getString("dcategory");
                int amou = rs.getInt("amount");

                pstmt = conn.prepareStatement("select * from stock where dtype = ? and dcategory = ?");
                pstmt.setString(1, dt);
                pstmt.setString(2, dc);
                rs = pstmt.executeQuery();
                rs.first();

                if (rs.first()) {
                    pstmt = conn.prepareStatement("update stock set amount = amount + ? where dtype = ? and dcategory = ?");
                    pstmt.setInt(1, amou);
                    pstmt.setString(2, dt);
                    pstmt.setString(3, dc);
                    pstmt.executeUpdate();
                } else {
                    pstmt = conn.prepareStatement("insert into stock (dtype, dcategory, amount) select dtype, dcategory, amount from donation where did = ?");
                    pstmt.setInt(1, id);
                    pstmt.executeUpdate();
                }
            } else {
                initializeJdbc();

                int id = Integer.parseInt(did);

                pstmt = conn.prepareStatement("update donation set status = ? where did = ?");
                pstmt.setString(1, status);
                pstmt.setInt(2, id);
                pstmt.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @param rid
     * @param status
     * @param amnt
     * @param stockAmnt
     * @return
     */
    public boolean answerRequest(int rid, String status, int amnt, int stockAmnt) {
        try {
            if (status.equals("approved")) {
                if (stockAmnt >= amnt) {
                    initializeJdbc();

                    pstmt = conn.prepareStatement("update request set status = ? where rid = ?");
                    pstmt.setString(1, status);
                    pstmt.setInt(2, rid);
                    pstmt.executeUpdate();

                    pstmt = conn.prepareStatement("select * from request where rid = ?");
                    pstmt.setInt(1, rid);
                    rs = pstmt.executeQuery();
                    rs.first();
                    String rt = rs.getString("rtype");
                    String rc = rs.getString("rcategory");
                    int fkuid = rs.getInt("FK_uid");

                    pstmt = conn.prepareStatement("update stock set amount = amount - ? where dtype = ? and dcategory = ?");
                    pstmt.setInt(1, amnt);
                    pstmt.setString(2, rt);
                    pstmt.setString(3, rc);
                    pstmt.executeUpdate();

                    pstmt = conn.prepareStatement("select * from received where rtype = ? and rcategory = ?");
                    pstmt.setString(1, rt);
                    pstmt.setString(2, rc);
                    rs = pstmt.executeQuery();
                    rs.first();

                    if (rs.first()) {
                        pstmt = conn.prepareStatement("update received set amount = amount + ? where rtype = ? and rcategory = ?");
                        pstmt.setInt(1, amnt);
                        pstmt.setString(2, rt);
                        pstmt.setString(3, rc);
                        pstmt.executeUpdate();
                    } else {
                        pstmt = conn.prepareStatement("insert into received (rtype, rcategory, amount, FK_uid) select rtype, rcategory, amount, FK_uid from request where rid = ?");
                        pstmt.setInt(1, rid);
                        pstmt.executeUpdate();
                    }

                } else if (stockAmnt == amnt) {

                    initializeJdbc();

                    pstmt = conn.prepareStatement("update request set status = ? where rid = ?");
                    pstmt.setString(1, status);
                    pstmt.setInt(2, rid);
                    pstmt.executeUpdate();

                    pstmt = conn.prepareStatement("select * from request where rid = ?");
                    pstmt.setInt(1, rid);
                    rs = pstmt.executeQuery();
                    rs.first();
                    String rt = rs.getString("rtype");
                    String rc = rs.getString("rcategory");
                    int fkuid = rs.getInt("FK_uid");

                    pstmt = conn.prepareStatement("update stock set amount = amount - ? where dtype = ? and dcategory = ?");
                    pstmt.setInt(1, amnt);
                    pstmt.setString(2, rt);
                    pstmt.setString(3, rc);
                    pstmt.executeUpdate();

                    pstmt = conn.prepareStatement("select * from received where rtype = ? and rcategory = ?");
                    pstmt.setString(1, rt);
                    pstmt.setString(2, rc);
                    rs = pstmt.executeQuery();
                    rs.first();

                    if (rs.first()) {
                        pstmt = conn.prepareStatement("update received set amount = amount + ? where rtype = ? and rcategory = ?");
                        pstmt.setInt(1, amnt);
                        pstmt.setString(2, rt);
                        pstmt.setString(3, rc);
                        pstmt.executeUpdate();
                    } else {
                        pstmt = conn.prepareStatement("insert into received (rtype, rcategory, amount, FK_uid) select rtype, rcategory, amount, FK_uid from request where rid = ?");
                        pstmt.setInt(1, rid);
                        pstmt.executeUpdate();
                    }

                    pstmt = conn.prepareStatement("delete from stock where dtype = ? and dcategory = ?");
                    pstmt.setString(1, rt);
                    pstmt.setString(1, rc);
                    pstmt.executeUpdate();

                } else {
                    return false;
                }
            } else {
                initializeJdbc();

                pstmt = conn.prepareStatement("update request set status = ? where rid = ?");
                pstmt.setString(1, status);
                pstmt.setInt(2, rid);
                pstmt.executeUpdate();
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            return false;
        }
        return true;
    }

    //--------------------------------------------------------------------------

    /**
     *
     * @return
     */
    public String getAddress() {
        return address;
    }

    /**
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     *
     * @return
     */
    public String getAge() {
        return age;
    }

    /**
     *
     * @param age
     */
    public void setAge(String age) {
        this.age = age;
    }

    /**
     *
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     *
     * @param firstname
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     *
     * @return
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    public String getLastname() {
        return lastname;
    }

    /**
     *
     * @param lastname
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getFoundationname() {
        return foundationname;
    }

    /**
     *
     * @param foundationname
     */
    public void setFoundationname(String foundationname) {
        this.foundationname = foundationname;
    }

    /**
     *
     * @return
     */
    public String getDtype() {
        return dtype;
    }

    /**
     *
     * @param dtype
     */
    public void setDtype(String dtype) {
        this.dtype = dtype;
    }

    /**
     *
     * @return
     */
    public String getDcategory() {
        return dcategory;
    }

    /**
     *
     * @param dcategory
     */
    public void setDcategory(String dcategory) {
        this.dcategory = dcategory;
    }

    /**
     *
     * @return
     */
    public String getAmount() {
        return amount;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(String amount) {
        this.amount = amount;
    }

    /**
     *
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return
     */
    public String getRtype() {
        return rtype;
    }

    /**
     *
     * @param rtype
     */
    public void setRtype(String rtype) {
        this.rtype = rtype;
    }

    /**
     *
     * @return
     */
    public String getRcategory() {
        return rcategory;
    }

    /**
     *
     * @param rcategory
     */
    public void setRcategory(String rcategory) {
        this.rcategory = rcategory;
    }

    //--------------------------------------------------------------------------
}
