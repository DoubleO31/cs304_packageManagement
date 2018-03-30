import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;


public class MainGUI extends JFrame {
    private JPanel panel1;
    private JPanel NorthP;
    private JLabel SearchL;
    private JTextField SearchtextField;
    private JButton SearchB;
    private JButton AddB;
    private JButton UpdateB;
    private JButton DeleteB;
    private JButton View;
    private JButton SumB;
    protected JTable table1;
    private JScrollPane SouthS;
    private JTextField Package;
    private JButton maxPriceButton;
    private JButton returnToLogInButton;
    private int row = -1;
    private long custID;
    protected CreateOrder temp = new CreateOrder();


    public MainGUI() {
        setContentPane(panel1);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1500, 500);
        setVisible(true);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = table1.rowAtPoint(e.getPoint());
            }
        });
        AddB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] ag = new String[1];
                ag[0] = Long.toString(custID);
                CustomerAddUI.main(ag);
            }
        });
        UpdateB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerUpdateGUI.main(null);
            }
        });
        SearchB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Order> orderslist;

                    String oId = SearchtextField.getText();

                    if (oId != null && oId.trim().length() > 0) {

                        orderslist = MainGUI.this.temp.selectOrder(oId.trim(), Long.toString(custID));


                    } else {


                        orderslist = MainGUI.this.temp.getAllOrders(Long.toString(custID));

                    }
                    OrderTableModel model = new OrderTableModel(orderslist);


                    table1.setModel(model);


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }
            }
        });
        DeleteB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (row != -1) {
                    long orderID = (long) table1.getModel().getValueAt(row, 0);
                    try {
                        temp.deleteOrder(orderID);
                        List<Order> orderslist;

                        orderslist = MainGUI.this.temp.getAllOrders();

                        OrderTableModel model = new OrderTableModel(orderslist);

                        table1.setModel(model);


                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                        System.out.println("Message:?? " + ex.getMessage());
                    }
                }
                row = -1;
            }
        });

        SumB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JOptionPane.showMessageDialog(null, "Total cost is: " + Float.toString(MainGUI.this.temp.sumOfPrice(custID)));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }
            }
        });

        View.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<Packages> Packageslist = null;

                    String oId = Package.getText();

                    if (oId != null && oId.trim().length() > 0) {

                        Packageslist = MainGUI.this.temp.getPackages(Long.parseLong(oId.trim()));
                        PackageTableModel model = new PackageTableModel(Packageslist);
                        table1.setModel(model);
                    }


                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }

            }
        });
        maxPriceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String s = temp.groupBy(custID);
                    if (s != "") {
                        JOptionPane.showMessageDialog(null, s);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    System.out.println("Message:?? " + ex.getMessage());
                }
            }
        });
        returnToLogInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI.main(null);
            }
        });
    }

    public void setTable1() {
        List<Order> orderslist = null;
        try {
            orderslist = MainGUI.this.temp.getAllOrders();
        } catch (Exception e) {
            e.printStackTrace();
        }
        OrderTableModel model = new OrderTableModel(orderslist);
        table1.setModel(model);
    }


    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainGUI frame = new MainGUI();
                    if (args.length == 1) {
                        frame.custID = Long.parseLong(args[0]);
                    } else
                        frame.custID = 21367537;
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    //generate a random number or orderID from https://stackoverflow.com/questions/4655931/12-digit-unique-random-number-generation-in-java
    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        NorthP = new JPanel();
        NorthP.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 23, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(NorthP, BorderLayout.NORTH);
        SearchL = new JLabel();
        SearchL.setText("Search by Order ID");
        NorthP.add(SearchL, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SearchtextField = new JTextField();
        NorthP.add(SearchtextField, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        SearchB = new JButton();
        SearchB.setText("Search");
        NorthP.add(SearchB, new com.intellij.uiDesigner.core.GridConstraints(0, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        View = new JButton();
        View.setText("Check Package");
        NorthP.add(View, new com.intellij.uiDesigner.core.GridConstraints(1, 2, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setText("Check package by Order ID");
        NorthP.add(label1, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Package = new JTextField();
        NorthP.add(Package, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 1, false));
        maxPriceButton = new JButton();
        maxPriceButton.setText("Delivery Company with most expensive goods");
        NorthP.add(maxPriceButton, new com.intellij.uiDesigner.core.GridConstraints(1, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        AddB = new JButton();
        AddB.setText("Add Order");
        NorthP.add(AddB, new com.intellij.uiDesigner.core.GridConstraints(0, 3, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SumB = new JButton();
        SumB.setText("Total value of goods");
        NorthP.add(SumB, new com.intellij.uiDesigner.core.GridConstraints(1, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        UpdateB = new JButton();
        UpdateB.setText("Update Order");
        NorthP.add(UpdateB, new com.intellij.uiDesigner.core.GridConstraints(0, 4, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        DeleteB = new JButton();
        DeleteB.setText("Delete Order");
        NorthP.add(DeleteB, new com.intellij.uiDesigner.core.GridConstraints(0, 5, 1, 3, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        returnToLogInButton = new JButton();
        returnToLogInButton.setText("Return to Log in");
        NorthP.add(returnToLogInButton, new com.intellij.uiDesigner.core.GridConstraints(1, 5, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        SouthS = new JScrollPane();
        panel1.add(SouthS, BorderLayout.SOUTH);
        table1 = new JTable();
        SouthS.setViewportView(table1);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
