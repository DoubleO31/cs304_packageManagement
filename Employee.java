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
            con = DriverManager.getConnection("jdbc:oracle:thin:@dbhost.ugrad.cs.ubc.ca:1522:ug", "ora_t5g1b", "a83751157");

            System.out.println("\nConnected to Oracle!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

        }
    }

    public Map<Long,Company> divisionComp() throws Exception {
        Map<Long,Company> companies = new HashMap<>();
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = con.prepareStatement("SELECT * FROM  deliveryCompany C WHERE NOT EXISTS " +
                    "( SELECT * FROM CUSTOMER CU  WHERE NOT EXISTS " +
                    " (SELECT * FROM ORDERS R WHERE R.CustomerID = CU.CustomerID AND R.CompanyID = C.CompanyID )) ");
            rs = stmt.executeQuery();
            while (rs.next()) {
                //System.out.println("reached 41");
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
        PreparedStatement stmt = null;
        ResultSet rs;
        try {
            stmt = con.prepareStatement("SELECT * FROM  deliveryCompanyAddress C WHERE NOT EXISTS " +
                    "( SELECT * FROM CUSTOMER CU  WHERE NOT EXISTS " +
                    " (SELECT * FROM ORDERS R WHERE R.CustomerID = CU.CustomerID AND R.CompanyID = C.CompanyID )) ");
            rs = stmt.executeQuery();
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
