
// We need to import the java.sql package to use JDBC
import java.sql.*;
import java.awt.*;
import java.awt.Event.*;
import javax.swing.*;
// for reading from the command line
import java.io.*;
import java.time.*;

public class manager implements ActionListener {
    // command line reader 
    private BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    private Connection con;

    // user is allowed 3 login attempts
    private int loginAttempts = 0;

    // components of the login window
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame mainFrame;

    /*
    insert a customer
    */
    private void insertCustomer() {
        int cID;
        String cName;
        String cAddr;
        String email;
        int cPhone;
        String methodOfPay;

        PreparedStatement ps;
        try {
            ps = con.prepareStatement("INSERT INTO customer VALUES (?,?,?,?,?,?)");

            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(1, cID);

            System.out.print("\nCustomer Name: ");
            cName = in.readLine();
            ps.setString(2, cName);

            System.out.print("\nCustomer Address: ");
            cAddr = in.readLine();

            if (cAddr.length() == 0) {
                ps.setString(3, null);
            } else {
                ps.setString(3, cAddr);
            }

            System.out.print("\nCustomer E-mail address: ");
            email = in.readLine();

            if (email.length() == 0) {
                ps.setString(4, null);
            } else {
                ps.setString(4, email);
            }

            System.out.print("\nCustomer phone number: ");
            cPhone = Integer.parseInt(in.readLine());
            ps.setInt(5, cPhone);

            System.out.print("\nMethod of Payment: ");
            methodOfPay = in.readLine();

            if (methodOfPay.length() == 0) {
                ps.setString(6, null);
            } else {
                ps.setString(6, methodOfPay);
            }

            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
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

    /* 
    delete a customer
    */
    private void deleteCustomer() {
        int cID;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM Customer WHERE customerID = ?");
            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(1, cID);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nCustomer " + cID + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    update the name of customer
    */
    private void cNameUpdate() {
        int cID;
        String cName;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("UPDATE customer SET name = ? WHERE customerID = ?");
            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(2, cID);

            System.out.print("\nCustomer Name: ");
            cName = in.readLine();
            ps.setString(1, cName);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("\nCustomer " + cID + " does not exist!");
            }

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
      update the address of customer
    */
    private void cAddrUpdate() {
        int cID;
        String cAddr;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("UPDATE customer SET address = ? WHERE customerID = ?");
            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(2, cID);

            System.out.print("\nCustomer Address: ");
            cAddr = in.readLine();
            ps.setString(1, cAddr);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("\nCustomer " + cID + " does not exist!");
            }

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    update the email of customer
    */
    private void emailUpdate() {
        int cID;
        String email;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("UPDATE customer SET email = ? WHERE customerID = ?");
            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(2, cID);

            System.out.print("\nCustomer email: ");
            email = in.readLine();
            ps.setString(1, email);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("\nCustomer " + cID + " does not exist!");
            }

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    update the name of customer
    */
    private void cPhoneUpdate() {
        int cID;
        int cPhone;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("UPDATE customer SET Phone# = ? WHERE customerID = ?");
            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(2, cID);

            System.out.print("\nCustomer Name: ");
            cPhone = Integer.parseInt(in.readLine());
            ps.setInt(1, cPhone);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("\nCustomer " + cID + " does not exist!");
            }

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    update the name of customer
    */
    private void methodUpdate() {
        int cID;
        String methodOfPay;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("UPDATE customer SET methodOfPayment = ? WHERE customerID = ?");
            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(2, cID);

            System.out.print("\nCustomer Name: ");
            methodOfPay = in.readLine();
            ps.setString(1, methodOfPay);

            int rowCount = ps.executeUpdate();
            if (rowCount == 0) {
                System.out.println("\nCustomer " + cID + " does not exist!");
            }

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    private void showCustomer() {
        int cID;
        String cName;
        String cAddr;
        String email;
        int cPhone;
        String methodOfPay;

        Statement stmt;
        ResultSet rs;

        try {
            stmt = con.createStatement();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    private void insertManage() {
        int cID;
        int eID;
        String sTime;
        String eTime;
        String instance;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO manages VALUES (?,?,?,?,?)");

            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(1, cID);

            System.out.print("\nEmployee ID: ");
            eID = Integer.parseInt(in.readLine());
            ps.setInt(2, eID);

            System.out.print("Start time: ");
            sTime = in.readLine();
            ps.setString(3, sTime);

            System.out.print("End time: ");
            eTime = in.readLine();
            ps.setString(4, eTime);

            System.out.print("Instance of Manage: ");
            instance = in.readLine();
            ps.setString(5, instance);

            ps.executeUpdate();

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /* 
    delete a Manage relation
    */
    private void deleteManage() {
        int cID;
        int eID;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM Manages WHERE customerID = ? AND employeeID = ?");
            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(1, cID);

            System.out.print("\nEmployee ID: ");
            eID = Integer.parseInt(in.readLine());
            ps.setInt(2, eID);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nManage with customer " + cID + " and employee " + eID + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }
    /*
    insert a customer service employee
    */

    private void insertCustomerService() {
        int eID;
        int compID;
        String name;
        String lang;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO CustomerService VALUES (?,?,?,?)");

            System.out.print("\nEmployee ID: ");
            eID = Integer.parseInt(in.readLine());
            ps.setInt(1, eID);

            System.out.print("\nCompany ID: ");
            compID = Integer.parseInt(in.readLine());
            ps.setInt(2, compID);

            System.out.print("Name: ");
            name = in.readLine();
            ps.setString(3, name);

            System.out.print("Language of Service: ");
            lang = in.readLine();
            ps.setString(4, lang);

            ps.executeUpdate();

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }

    }

    /*
    delete a customer service
    */
    private void deleteCustomerService() {
        int cID;
        int eID;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM CustomerService WHERE employeeID = ?");

            System.out.print("\nEmployee ID: ");
            eID = Integer.parseInt(in.readLine());
            ps.setInt(1, eID);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nCustomer Service " + eID + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    insert a delivery type
    */
    private void insertDeliveryType() {
        String name;
        double rate;
        String dTime;

        PreparedStatement ps;

        
        try {
            ps = con.prepareStatement("INSERT INTO DeliveryType VALUES (?,?,?)");

            System.out.print("\nName of delivery type: ");
            name = in.readLine();
            ps.setString(1, name);

            System.out.print("\nRate of delivery type: ");
            rate = Double.parseDouble(in.readLine());
            ps.setDouble(2, rate);

            System.out.print("\nDelivery time: ");
            dTime = in.readLine();
            ps.setString(3, dTime);

            ps.executeUpdate();

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    delete a delivery type
    */
    private void deleteDeliveryType() {
        String name;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM DeliveryType WHERE typename = ?");

            System.out.print("\nName of delivery type: ");
            name = in.readLine();
            ps.setString(1, name);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nDelivery Type " + name + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    insert a company
    */
    private void insertCompany() {
        int compID;
        String cname;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO DeliveryCompany VALUES (?,?)");

            System.out.print("\nCompany ID: ");
            compID = Integer.parseInt(in.readLine());
            ps.setDouble(1, compID);

            System.out.print("\nCompany Name: ");
            cname = in.readLine();
            ps.setString(2, cname);

            ps.executeUpdate();

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    delete a company
    */
    private void deleteCompany() {
        int compID;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM DeliveryCompany WHERE companyID = ?");

            System.out.print("\nName of delivery type: ");
            compID = Integer.parseInt(in.readLine()) ;
            ps.setInt(1, compID);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nCompany " + compID + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    Insert a company address
    */
    private void inserCompanyAddress() {
        int compID;
        int branch;
        String compAddr;

        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO DeliveryCompanyAddress VALUES (?,?,?)");

            System.out.print("\nCompany ID: ");
            compID =Integer.parseInt( in.readLine());
            ps.setInt(1, compID);

            System.out.print("\nBranch Number: ");
            rate = Integer.parseInt(in.readLine());
            ps.setInt(2, branch);

            System.out.print("\nAddress: ");
            compAddr = in.readLine();
            ps.setString(3, compAddr);

            ps.executeUpdate();

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }
    /*
    delete a company address
    */
    private void deleteCompanyAddress() {
        int compID;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM DeliveryCompanyAddress WHERE companyID = ?");

            System.out.print("\nCompany ID: ");
            compID = Integer.parseInt(in.readLine()) ;
            ps.setInt(1, compID);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nCompany " + compID + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    insert a order
    */
    private void insertCreateOrder() {
        int oID;
        int cID;
        int compID;
        String typename;
        String sName;
        String sAddr;
        String rName;
        String rAddr;
        double price;
        String date;
        String eArr;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO orders VALUES (?,?,?,?,?,?,?,?,?,?,?)");

            System.out.print("\nOrder ID: ");
            oID = Integer.parseInt(in.readLine());
            ps.setInt(1, oID);

            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(2, cID);

            System.out.print("\nCompany ID: ");
            compID = Integer.parseInt((in.readLine()));
            ps.setInt(3, compID);

            System.out.print("\ntype Name: ");
            typename = in.readLine();
            if (typename.length() == 0) {
                ps.setString(4, null);
            } else {
                ps.setString(4, typename);
            }

            System.out.print("\nSender Name: ");
            sName = in.readLine();
            ps.setString(5, sName);

            System.out.print("\nSender Address: ");
            sAddr = in.readLine();
            ps.setString(6, sAddr);          

            System.out.print("\nReceiver Name: ");
            rName = in.readLine();
            ps.setString(7, sName);

            System.out.print("\nReceiver Address: ");
            rAddr = in.readLine();
            ps.setString(8, sAddr);

            System.out.print("\nPrice of shipping: ");
            price = Double.parseDouble(in.readLine());
            ps.setDouble(9, price);

            System.out.print("\nDate of shipping: ");
            date = in.readLine();
            ps.setString(10, date);

            System.out.print("\nExpected Date of Delivery");
            eArr = in.readLine();
            ps.setString(11, eArr);

            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
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
    /* delete a order
    */
    private void deleteCreateOrder() {
        int oID;
        int cID;
        int compID;
        String typename;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM orders WHERE orderID = ? AND customerID = ? AND companyID = ? AND typename =?");

            System.out.print("\nOrder ID: ");
            oID = Integer.parseInt(in.readLine());
            ps.setInt(1, oID);

            System.out.print("\nCustomer ID: ");
            cID = Integer.parseInt(in.readLine());
            ps.setInt(2, cID);

            System.out.print("\nCompany ID: ");
            compID = Integer.parseInt(in.readLine());
            ps.setInt(3, compID);

            System.out.print("\nName of delivery type: ");
            typename = in.readLine();
            ps.setString(4, typename);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nCreateOrder with order " +oID+"and customer" + cID + " and company " + eID +"and delivery type" +" does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /* insert an update of existorder
    */

    private void insertExistOrder(){
        int oID;
        String loc;
        String status;
        int compID;
        String date;
        String inst;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO ExistingOrders VALUES (?,?,?,?,?,?)");

            System.out.print("\nOrder ID: ");
            oID = Integer.parseInt(in.readLine());
            ps.setInt(1, oID);

            System.out.print("\nLocation: ");
            loc = in.readLine();
            if (loc.length() == 0) {
                ps.setString(2, null);
            } else {
                ps.setString(2, loc);
            }

            System.out.print("\nStatus: ");
            status = in.readLine();

            if (status.length() == 0) {
                ps.setString(3, null);
            } else {
                ps.setString(3, status);
            }


            System.out.print("\nCompany ID: ");
            compID = Integer.parseInt(in.readLine());
            ps.setInt(4, compID);

            System.out.print("\nDate: ");
            date =in.readLine();
            ps.setString(5, date);

            System.out.print("\nInstance of update: ");
            inst = in.readLine();

            if (inst.length() == 0) {
                ps.setString(6, null);
            } else {
                ps.setString(6, inst);
            }

            ps.executeUpdate();
            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
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

    /*
    delete a existing order
    */
    private void deleteExistOrder() {
        int oID;
        PreparedStatement ps;
        try {
            ps = con.prepareStatement("DELETE FROM ExistingOrders WHERE orderID = ?");

            System.out.print("\nOrder ID: ");
            oID = Integer.parseInt(in.readLine());
            ps.setInt(1, oID);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nExisting order " + oID + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /* 
    insert a finished order
    */
    private void insertFinishOrder() {
        int oID;
        String fDate;
        String status;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO FinishedOrders VALUES (?,?,?)");

            System.out.print("\nOrder ID: ");
            oID =Integer.parseInt( in.readLine());
            ps.setInt(1, oID);

            System.out.print("\nFinished Date: ");
            fDate = in.readLine();
            ps.setString(2, fDate);

            System.out.print("\nStatus: ");
            status = in.readLine();
            ps.setString(3, status);

            ps.executeUpdate();

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    delete a finished order
    */
    private void deleteFinishedOrder() {
        int oID;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM FinishedOrders WHERE orderID = ?");

            System.out.print("\norder ID: ");
            oID =Integer.parseInt(in.readLine()) ;
            ps.setInt(1, oID);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nFinished Order " + oID + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    insert a package
    */
    private void insertPackage() {
        int oID;
        int pack;
        String desc;
        double weight;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("INSERT INTO CustomerService VALUES (?,?,?,?)");

            System.out.print("\nOrder ID: ");
            oID = Integer.parseInt(in.readLine());
            ps.setInt(1, oID);

            System.out.print("\nPackage Number: ");
            pack = Integer.parseInt(in.readLine());
            ps.setInt(2, pack);

            System.out.print("Description: ");
            desc = in.readLine();
            ps.setString(3, desc);

            System.out.print("Weight of package: ");
            weight =Double.parseDouble(in.readLine()) ;
            ps.setDouble(4, weight);

            ps.executeUpdate();

            con.commit();

            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }

    /*
    delete a package
    */
    private void deletePackage() {
        int oID;
        int pack;
        PreparedStatement ps;

        try {
            ps = con.prepareStatement("DELETE FROM Manages WHERE orderID = ? AND package# = ?");
            System.out.print("\nOrder ID: ");
            oID = Integer.parseInt(in.readLine());
            ps.setInt(1, oID);

            System.out.print("\nPackage number: ");
            pack = Integer.parseInt(in.readLine());
            ps.setInt(2, pack);

            int rowCount = ps.executeUpdate();

            if (rowCount == 0) {
                System.out.println("\nPackage with order ID " + oID + " and package number " +pack + " does not exist!");
            }

            con.commit();
            ps.close();
        } catch (IOException e) {
            System.out.println("IOException!");
        } catch (SQLException ex) {
            System.out.println("Message: " + ex.getMessage());

            try {
                con.rollback();
            } catch (SQLException ex2) {
                System.out.println("Message: " + ex2.getMessage());
                System.exit(-1);
            }
        }
    }
}
