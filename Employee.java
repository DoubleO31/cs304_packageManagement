import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee {
    private Connection con;

    public Employee() {
        try {
            // Load the Oracle JDBC driver
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            // may be oracle.jdbc.driver.OracleDriver as of Oracle 11g

        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
            System.exit(-1);
        }

        try {
            con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "", "");

            System.out.println("\nConnected to Oracle!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

        }
    }

    public Map<Long,Company> divisionComp() throws Exception {
        Map<Long,Company> companies = new HashMap<>();
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM  deliveryCompany C WHERE NOT EXISTS " +
                    "                    ( SELECT * FROM CUSTOMER CU  WHERE NOT EXISTS " +
                    "                     (SELECT * FROM ORDERS R WHERE R.CustomerID = CU.CustomerID AND R.CompanyID = C.CompanyID ))");
            //System.out.println(rs.isLast());
            while (rs.next()) {
                System.out.println("reached 41");
                Company comp = getComp(rs);
                companies.put(comp.getCompID(),comp);
            }
            return companies;
        } finally {
            assert stmt != null;
            stmt.close();
        }
    }

    public List<CompanyAddress> divisionAddr() throws Exception {
        List<CompanyAddress> companyAddresses = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM  deliveryCompanyAddress C WHERE NOT EXISTS " +
                    "( SELECT * FROM CUSTOMER CU  WHERE NOT EXISTS " +
                    " (SELECT * FROM ORDERS R WHERE R.CustomerID = CU.CustomerID AND R.CompanyID = C.CompanyID )) ");
            while (rs.next()) {
                //System.out.println("reached 62");
                CompanyAddress addr = getAddr(rs);
                companyAddresses.add(addr);
            }
            return companyAddresses;
        }
        finally {
            assert stmt != null;
            stmt.close();
        }
    }

    public String maxPrice() {
        String s = new String();
        Statement stmt;
        ResultSet rs;
        try {
            stmt = con.createStatement();
            rs =stmt.executeQuery("SELECT ORDERID, PRICE FROM ORDERS O WHERE O.price = (SELECT MAX (O2.price) FROM  ORDERS O2)");
            s = "The most expensive order is ";
            while (rs.next()) {
                s += rs.getString("ORDERID");
                s += " with price of " + rs.getString("PRICE") + "\n";
            }
        }catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());
            try {
                // undo the insert
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
        return s;
    }

    private static Company getComp(ResultSet rs) throws SQLException{
        long compID = Long.parseLong(rs.getString("companyID"));
        String cname = rs.getString("cname");
        return new Company(compID,cname);
    }

    private static  CompanyAddress getAddr(ResultSet rs) throws  SQLException {
        long compID = Long.parseLong(rs.getString("companyID"));
        int branch = Integer.parseInt(rs.getString("branch"));
        String addr = rs.getString("caddress");
        return new CompanyAddress(compID,branch,addr);
    }
}
