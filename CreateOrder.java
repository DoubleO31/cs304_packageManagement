import java.sql.*;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

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
            con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug","","");

            System.out.println("\nConnected to Oracle!");
        }
        catch (SQLException ex)
        {
            System.out.println("Message: " + ex.getMessage());

        }

    }

    //return a list of all orders(all column)
    public List<Order> getAllOrders() throws Exception {
        List<Order> orderslist = new ArrayList<>();

        Statement stmt = null;
        ResultSet rs;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from CREATEORDER");



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

