import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Random;

public class CreateOrder {
    private Connection con;

    public CreateOrder(){

        try
        {
            // Load the Oracle JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            // may be oracle.jdbc.driver.OracleDriver as of Oracle 11g

        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());
            System.exit(-1);
        }

        try
        {
            con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug","ora_w7d1b","a28059146");

            System.out.println("\nConnected to Oracle!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());

        }

    }
    public void addOrder(Order o) throws Exception {

        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO ORDERS VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            ps.setString(1,o.getOrderid());
            ps.setNull(2,java.sql.Types.INTEGER);
            ps.setNull(3,java.sql.Types.INTEGER);
            ps.setNull(4, Types.CHAR);
            ps.setString(5,o.getSenderName());
            ps.setString(6,o.getSenderAddress());

            ps.setString(7,o.getReceiverAddress());
            ps.setString(8,o.getReceiverName());
            ps.setDouble(9,o.getPrice());
            ps.setDate(10,o.getDateCreated());
            ps.setDate(11,o.getExpectedArrival());

            ps.executeUpdate();

            con.commit();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
            try {
                // undo the insert
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    public  List<Order> selectOrder(String oID) throws Exception {

        List<Order> orderslist = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs;

        try {
            stmt = con.prepareStatement("select * from ORDERS WHERE ORDERID = ?");
            stmt.setString(1,oID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Order tempOrder = getOrder(rs);
                orderslist.add(tempOrder);
            }

            return orderslist;
        }
        finally {
            assert stmt != null;
            stmt.close();
        }
    }
    //return a list of all orders(all column)
    public List<Order> getAllOrders() throws Exception {
        List<Order> orderslist = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from ORDERS");



            while (rs.next()) {
                Order tempOrder = getOrder(rs);
                orderslist.add(tempOrder);
            }


            return orderslist;
        }
        finally {
            assert stmt != null;
            stmt.close();
        }
    }

    //helper function that return a single column for getAllOrders
    private static Order getOrder(ResultSet rs) throws SQLException {
        String orderid = rs.getString("ORDERID");
        String senderAddress = rs.getString("SENDERADDRESS");
        String senderName = rs.getString("SENDERNAME");
        String receiverAddress = rs.getString("RECEIVERADDRESS");
        String receiverName = rs.getString("RECEIVERNAME");
        float price = rs.getFloat("PRICE");
        Date dateCreated = rs.getDate("DATECREATED");
        Date expectedArrival = rs.getDate("EXPECTEDARRIVAL");




        return new Order(orderid, senderAddress, senderName, receiverAddress, receiverName, price, dateCreated, expectedArrival);
    }




    public static void main(String[] args) {
        CreateOrder c = new CreateOrder();
    }
}

