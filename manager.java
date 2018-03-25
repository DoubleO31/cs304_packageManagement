
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

            System.out.print("\nCustomer Address");
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
            ps = con.prepareStatement("DELETE FROM Customer WHERE customer_id = ?");
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
            ps =con.prepareStatement("INSERT INTO manages VALUES (?,?,?,?,?)");

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
}