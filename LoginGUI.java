// for the login window
//import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class LoginGUI implements ActionListener {

    // user is allowed 3 login attempts
    private int loginAttempts = 0;

    // components of the login window
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JFrame mainFrame;

    private LoginGUI()
    {
    mainFrame = new JFrame("User Login");

    JLabel usernameLabel = new JLabel("Enter username: ");
    JLabel passwordLabel = new JLabel("Enter password: ");

    usernameField = new JTextField(10);
    passwordField = new JPasswordField(10);
    passwordField.setEchoChar('*');

    JButton loginButton = new JButton("Log In");

    JPanel contentPane = new JPanel();
    mainFrame.setContentPane(contentPane);



    GridBagLayout gb = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();

    contentPane.setLayout(gb);
    contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // place the username label
    c.gridwidth = GridBagConstraints.RELATIVE;
    c.insets = new Insets(10, 10, 5, 0);
      gb.setConstraints(usernameLabel, c);
      contentPane.add(usernameLabel);

    // place the text field for the username
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = new Insets(10, 0, 5, 10);
      gb.setConstraints(usernameField, c);
      contentPane.add(usernameField);

    // place password label
    c.gridwidth = GridBagConstraints.RELATIVE;
    c.insets = new Insets(0, 10, 10, 0);
      gb.setConstraints(passwordLabel, c);
      contentPane.add(passwordLabel);

    // place the password field
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = new Insets(0, 0, 10, 10);
      gb.setConstraints(passwordField, c);
      contentPane.add(passwordField);

    // place the login button
    c.gridwidth = GridBagConstraints.REMAINDER;
    c.insets = new Insets(5, 10, 10, 10);
    c.anchor = GridBagConstraints.CENTER;
      gb.setConstraints(loginButton, c);
      contentPane.add(loginButton);

    // register password field and OK button with action event handler
      passwordField.addActionListener(this);
      loginButton.addActionListener(this);

    // anonymous inner class for closing the window
      mainFrame.addWindowListener(new WindowAdapter()
    {
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }
    });

    // size the window to obtain a best fit for the components
      mainFrame.pack();

    // center the frame
    Dimension d = mainFrame.getToolkit().getScreenSize();
    Rectangle r = mainFrame.getBounds();
      mainFrame.setLocation( (d.width - r.width)/2, (d.height - r.height)/2 );

    // make the window visible
      mainFrame.setVisible(true);

    // place the cursor in the text field for the username
      usernameField.requestFocus();

}


    /*
     * connects to packageManager database
     */
    private int connect(String username, String password)
    {
        if (custCheck(username) & password.equals("123456")){return 1;}
        if (username.equals("employee")& password.equals("123456")){return 2;}
        if (username.equals("delivery")& password.equals("123456")){return 3;}
        return 0;
    }
    
    private boolean custCheck(String user) {
        return  (user.equals("21367537") || user.equals("57384360")|| user.equals("34348957")
        || user.equals("45221778") || user.equals("77654321"));
    }


    /*
     * event handler for login window
     */
    public void actionPerformed(ActionEvent e)
    {
        if ( connect(usernameField.getText(), String.valueOf(passwordField.getPassword())) == 1 )
        {
            // if the username and password are valid,
            // remove the login window and display a text menu
            mainFrame.dispose();
            String[] a = new String[1];
            a[0] = usernameField.getText();
            MainGUI.main(a);
        }
        if ( connect(usernameField.getText(), String.valueOf(passwordField.getPassword())) == 2 )
        {
            // if the username and password are valid,
            // remove the login window and display a text menu
            mainFrame.dispose();
        }
        if ( connect(usernameField.getText(), String.valueOf(passwordField.getPassword())) == 3 )
        {
            // if the username and password are valid,
            // remove the login window and display a text menu
            mainFrame.dispose();
        }
        else
        {
            loginAttempts++;

            if (loginAttempts >= 3)
            {
                mainFrame.dispose();
                System.exit(-1);
            }
            else
            {
                // clear the password
                passwordField.setText("");
            }
        }

    }

    public static void main(String args[])
    {
        LoginGUI t = new LoginGUI();
    }
}
