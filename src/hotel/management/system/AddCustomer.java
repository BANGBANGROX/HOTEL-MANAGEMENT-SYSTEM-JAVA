package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class AddCustomer extends JFrame implements ActionListener {
    private final JComboBox idCombo;
    private final JTextField tfNumber;
    private final JTextField tfName;
    private final JRadioButton rbMale;
    private final JRadioButton rbFemale;
    private final JTextField tfCountry;
    private final Choice cRoom;
    private final JLabel checkInTime;
    private final JTextField tfDeposit;
    private final JButton add;
    private final JButton cancel;

    public AddCustomer() {
        setLayout(null);

        JLabel text = new JLabel("NEW CUSTOMER FORM");
        text.setBounds(100, 20, 300, 30);
        text.setFont(new Font("Railway", Font.BOLD, 20));
        add(text);

        JLabel lblId = new JLabel("ID");
        lblId.setBounds(35, 80, 100, 20);
        lblId.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblId);

        String[] idValues = {"Aadhar Card", "Passport", "Driving License", "PAN Card",
                "Voter ID", "Rashan Card"};
        idCombo = new JComboBox(idValues);
        idCombo.setBounds(200, 80, 150, 25);
        idCombo.setBackground(Color.WHITE);
        add(idCombo);

        JLabel lblNumber = new JLabel("ID Number");
        lblNumber.setBounds(35, 120, 100, 20);
        lblNumber.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblNumber);

        tfNumber = new JTextField();
        tfNumber.setBounds(200, 120, 150, 25);
        add(tfNumber);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(35, 160, 100, 20);
        lblName.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(200, 160, 150, 25);
        add(tfName);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(35, 200, 100, 20);
        lblGender.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblGender);

        rbMale = new JRadioButton("Male");
        rbMale.setBackground(Color.WHITE);
        rbMale.setBounds(200, 200, 60, 25);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBackground(Color.WHITE);
        rbFemale.setBounds(270, 200, 100, 25);
        add(rbFemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        JLabel lblCountry = new JLabel("Country");
        lblCountry.setBounds(35, 240, 100, 20);
        lblCountry.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblCountry);

        tfCountry = new JTextField();
        tfCountry.setBounds(200, 240, 150, 25);
        add(tfCountry);

        JLabel lblRoom = new JLabel("Room Number");
        lblRoom.setBounds(35, 280, 150, 20);
        lblRoom.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblRoom);

        cRoom = new Choice();
        cRoom.setBounds(200, 280, 150, 25);
        add(cRoom);

        init();

        JLabel lblCheckInTime = new JLabel("Check In Time");
        lblCheckInTime.setBounds(35, 320, 150, 20);
        lblCheckInTime.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblCheckInTime);

        Date date = new Date();

        checkInTime = new JLabel(date.toString());
        checkInTime.setBounds(200, 320, 250, 20);
        checkInTime.setFont(new Font("Railway", Font.PLAIN, 15));
        add(checkInTime);

        JLabel lblDeposit = new JLabel("Deposit");
        lblDeposit.setBounds(35, 360, 100, 20);
        lblDeposit.setFont(new Font("Railway", Font.PLAIN, 15));
        add(lblDeposit);

        tfDeposit = new JTextField();
        tfDeposit.setBounds(200, 360, 150, 25);
        add(tfDeposit);

        add = new JButton("Add Customer");
        add.setBackground(Color.BLACK);
        add.setForeground(Color.WHITE);
        add.setBounds(50, 410, 120, 30);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(200, 410, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fifth.png"));
        Image i2 = i1.getImage().getScaledInstance(300, 400, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(420, 40, 300, 400);
        add(image);

        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 800, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
        new AddCustomer();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
          if (ae.getSource() == add) {
              String idType = (String) idCombo.getSelectedItem();
              String idNumber = tfNumber.getText();
              String name = tfName.getText();
              String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ?
                      "Female" : null;
              String country = tfCountry.getText();
              String roomNumber = cRoom.getSelectedItem();
              String time = checkInTime.getText();
              String deposit = tfDeposit.getText();

              if (idType == null || idType.equals("") || idNumber.equals("") || name.equals("") || gender == null
                      || country.equals("") || roomNumber.equals("")
                      || time.equals("") || deposit.equals("")) {
                  JOptionPane.showMessageDialog(null, "One or " +
                          "more fields are empty. Please check the again");
                  return;
              }

              try {
                  Conn conn = new Conn();
                  String query1 = String.format("INSERT INTO Customer VALUES('%s','%s'," +
                          "'%s','%s','%s','%s','%s','%s')", idType, idNumber,
                          name, gender, country, roomNumber, time, deposit);
                  String query2 = String.format("UPDATE Room SET availability = " +
                          "'Occupied' WHERE room_number = '%s'", roomNumber);

                  conn.s.executeUpdate(query1);
                  conn.s.executeUpdate(query2);

                  JOptionPane.showMessageDialog(null, "New Customer " +
                          "added successfully");
                  setVisible(false);
                  new Reception();
              }
              catch(Exception e) {
                  e.printStackTrace();
              }
          }
          else if (ae.getSource() == cancel) {
              setVisible(false);
              new Reception();
          }
    }

    private void init() {
        try {
            Conn conn = new Conn();
            String query = "SELECT * FROM Room WHERE availability = 'Available'";
            ResultSet rs = conn.s.executeQuery(query);

            while (rs.next()) {
                cRoom.add(rs.getString("room_number"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
