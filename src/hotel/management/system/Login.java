package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener {
    private final JTextField username;
    private final JPasswordField password;
    private final JButton login;
    private final JButton cancel;

    public Login() {
        getContentPane().setBackground(Color.WHITE);

        setLayout(null);
        setBounds(500, 200, 600, 300);

        JLabel user = new JLabel("Username");
        user.setBounds(40, 20, 100, 30);
        add(user);

        username = new JTextField();
        username.setBounds(150, 20, 150, 30);
        add(username);

        JLabel pass = new JLabel("Password");
        pass.setBounds(40, 70, 100, 20);
        add(pass);

        password = new JPasswordField();
        password.setBounds(150, 70, 150, 30);
        add(password);

        login = new JButton("Login");
        login.setBounds(40, 150, 120, 30);
        login.setBackground(Color.BLACK);
        login.setForeground(Color.WHITE);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 150, 120, 30);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/second.jpg"));
        Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(350, 10, 200, 200);
        add(image);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
              String user = username.getText();
              String pass = password.getText();

              try {
                   Conn c = new Conn();
                   String query = "SELECT * FROM Login WHERE username = '" + user +
                           "' AND password  = '" + pass + "'";


                   ResultSet rs = c.s.executeQuery(query);

                   if (rs.next()) {
                       setVisible(false);
                       new Dashboard();
                   }
                   else {
                       JOptionPane.showMessageDialog(null, "Invalid credentials");
                       setVisible(false);
                   }
              }
              catch(Exception e) {
                  e.printStackTrace();
              }
        }
        else if (ae.getSource() == cancel) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
       new Login();
    }
}
