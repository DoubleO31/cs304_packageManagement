import java.sql.*;
import java.util.ArrayList;
import java.util.*;


public class CreateOrder {
    private Connection con;
    private long CustomerID = 21367537;


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
            con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug","ora_t5g1b","a83751157");

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
            ps.setLong(1,o.getOrderid());
            ps.setLong(2,CustomerID);
            ps.setLong(3,94237);
            ps.setString(4,o.getType());
            ps.setString(5,o.getSenderName());
            ps.setString(6,o.getSenderAddress());

            ps.setString(7,o.getReceiverAddress());
            ps.setString(8,o.getReceiverName());
            ps.setDouble(9,o.getPrice());
            ps.setDate(10,o.getDateCreated());
            ps.setDate(11, o.getExpectedArrival());

            ps.executeUpdate();

            con.commit();
            ps.close();
        } catch (SQLException ex) {
            System.out.println("Message:?? " + ex.getMessage());
            try {
                // undo the insert
                con.rollback();

            }
            catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
                throw ex;
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

    public void addEOrder(ExistingOrder o) throws Exception {

        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO EXISTINGORDERS VALUES (?,?,?,?,?,?)");
            ps.setLong(1,o.getOrderid());
            ps.setString(2,o.getLocation());
            ps.setString(3,o.getStatus());

            ps.setLong(4,94237);
            ps.setDate(5,o.getDateUpdated());
            ps.setString(6,o.getInstance());


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

    public List<ExistingOrder> selectEOrder(long oID) throws Exception{
        List<ExistingOrder> orderList = new ArrayList<>();
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = con.prepareStatement("select * from ORDERS NATURAL JOIN EXISTINGORDERS WHERE ORDERID = ?");
            stmt.setLong(1,oID);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ExistingOrder tempOrder = getEOrder(rs);
                orderList.add(tempOrder);
            }

            return orderList;
        }
        finally {
            assert stmt != null;
            stmt.close();
        }
    }

    public void updateOrderLoc(ExistingOrder o, String newLoc) throws Exception{
        o.setLocation(newLoc);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE ExistingOrders SET currentLocation = ? WHERE orderID = ?");
            ps.setString(1,newLoc);
            ps.setLong(2,o.getOrderid());

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex) {
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

    public void updateOrderSenderName(Order o, String newName) {
        o.setSenderName(newName);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE Orders SET SENDERNAME = ? WHERE ORDERID = ?");
            ps.setString(1,newName);
            ps.setLong(2,o.getOrderid());
            //System.out.print(o.getOrderid());

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex) {
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
    public void updateOrderSenderAddr(Order o, String newAddr) {
        o.setSenderAddress(newAddr);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE Orders SET SENDERADDRESS = ? WHERE ORDERID = ?");
            ps.setString(1,newAddr);
            ps.setLong(2,o.getOrderid());

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex) {
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
    public void updateOrderRecevName(Order o, String newName) {
        o.setReceiverName(newName);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE Orders SET RECEIVERNAME = ? WHERE ORDERID = ?");
            ps.setString(1,newName);
            ps.setLong(2,o.getOrderid());

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex) {
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
    public void updateOrderRecevAddr(Order o, String newAddr) {
        o.setReceiverAddress(newAddr);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE Orders SET RECEIVERADDRESS = ? WHERE ORDERID = ?");
            ps.setString(1,newAddr);
            ps.setLong(2,o.getOrderid());

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex) {
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

    public void updatePrice(Order o, float price) {
        o.setPrice(price);
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("UPDATE Orders SET PRICE = ? WHERE ORDERID = ?");
            ps.setFloat(1,price);
            ps.setLong(2,o.getOrderid());

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        catch (SQLException ex) {
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

    public void deleteOrder(long orderId) throws SQLException{
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement("DELETE FROM orders WHERE orderID = ?");
            ps.setLong(1, orderId);

            ps.executeUpdate();
            con.commit();
            ps.close();
        }
        finally {
            assert ps != null;
            ps.close();
        }
    }

    //return list of values in a selected column
    public List<Object> selectColumn(String colName) throws Exception{
        PreparedStatement stmt = null;
        List<Object> columnVal = new ArrayList<>();
        ResultSet rs;
        int i = 1;

        try {
            stmt = con.prepareStatement("select ? from Orders");
            stmt.setString(1, colName);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Object tempObj = rs.getObject(i);
                columnVal.add(tempObj);
                i++;
            }
            return columnVal;
        }
        finally{
            assert stmt != null;
            stmt.close();
        }

    }

    //return list of packages given an orderid
    public List<Packages> getPackages(Long orderID)throws Exception{
        PreparedStatement stmt = null;
        ResultSet rs;
        List<Packages> packlist = new ArrayList<>();

        try{
            stmt = con.prepareStatement("select p.packageNo, p.decription, p.weight " +
                    " From packageContained p" +
                    " INNER JOIN orders o ON p.orderID = o.orderID" +
                    " WHERE o.orderID = ?");
            stmt.setLong(1,orderID);
            rs = stmt.executeQuery();

            while (rs.next()) {
                int packageNo = rs.getInt("packageNo");
                String desc = rs.getString("description");
                Double weight = rs.getDouble("weight") ;
                Packages tempPack = new Packages(orderID,packageNo, desc, weight);
                packlist.add(tempPack);

            }
            return packlist;
        } finally {
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
        Long orderid =Long.parseLong(rs.getString("ORDERID")) ;
        Long compID = Long.parseLong(rs.getString("COMPANYID")) ;
        Long custID = Long.parseLong(rs.getString("CUSTOMERID")) ;
        String type = rs.getString("TYPENAME");
        String senderAddress = rs.getString("SENDERADDRESS");
        String senderName = rs.getString("SENDERNAME");
        String receiverAddress = rs.getString("RECEIVERADDRESS");
        String receiverName = rs.getString("RECEIVERNAME");
        float price = rs.getFloat("PRICE");
        java.sql.Date dateCreated = rs.getDate("DATECREATED");
        java.sql.Date expectedArrival = rs.getDate("EXPECTEDARRIVAL");




        return new Order(orderid,compID,custID,type, senderAddress, senderName, receiverAddress, receiverName, price, dateCreated, expectedArrival);
    }

    private static ExistingOrder getEOrder(ResultSet rs) throws SQLException {
        Long orderid =Long.parseLong(rs.getString("ORDERID")) ;
        Long compID = Long.parseLong(rs.getString("COMPANYID")) ;
        Long custID = Long.parseLong(rs.getString("CUSTOMERID")) ;
        String type = rs.getString("TYPENAME");
        String senderAddress = rs.getString("SENDERADDRESS");
        String senderName = rs.getString("SENDERNAME");
        String receiverAddress = rs.getString("RECEIVERADDRESS");
        String receiverName = rs.getString("RECEIVERNAME");
        float price = rs.getFloat("PRICE");
        java.sql.Date dateCreated = rs.getDate("DATECREATED");
        java.sql.Date expectedArrival = rs.getDate("EXPECTEDARRIVAL");
        String status = rs.getString("STATUS");
        String location = rs.getString("CURRENTLOCATION");
        java.sql.Date dateUpdated = rs.getDate("dateupdated");
        String instance = rs.getString("INSTANCE");



        return new ExistingOrder(orderid,compID,custID, type, senderAddress, senderName, receiverAddress, receiverName,
                price, dateCreated, expectedArrival,location,status,dateUpdated,instance);

    }




    public static void main(String[] args) {
        CreateOrder c = new CreateOrder();
    }
}




