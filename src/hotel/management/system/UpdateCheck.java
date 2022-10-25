package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateCheck extends JFrame implements ActionListener {
    private final Choice cCustomer;
    private final JTextField tfRoom;
    private final JTextField tfPaid;
    private final JTextField tfCheckInTime;
    private final JTextField tfName;
    private final JTextField tfPending;
    private final JButton check;
    private final JButton update;
    private final JButton back;

    private void fetchData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM Customer";
            ResultSet rs = c.s.executeQuery(query);

            while (rs.next()) {
                cCustomer.add(rs.getString("document_number"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public UpdateCheck() {
        setLayout(null);

        JLabel heading = new JLabel("Update Status");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        heading.setBounds(90, 20, 200, 30);
        heading.setForeground(Color.BLUE);
        add(heading);

        JLabel lblId = new JLabel("Customer Id");
        lblId.setBounds(30, 80, 100, 20);
        add(lblId);

        cCustomer = new Choice();
        cCustomer.setBounds(200, 80, 150, 25);
        add(cCustomer);

        fetchData();

        JLabel lblRoom = new JLabel("Room Number");
        lblRoom.setBounds(30, 120, 100, 20);
        add(lblRoom);

        tfRoom = new JTextField();
        tfRoom.setBounds(200, 120, 150, 25);
        add(tfRoom);

        JLabel lblName = new JLabel("Name");
        lblName.setBounds(30, 160, 100, 20);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(200, 160, 150, 25);
        add(tfName);

        JLabel lblCheckInTime = new JLabel("Check In Time");
        lblCheckInTime.setBounds(30, 200, 100, 20);
        add(lblCheckInTime);

        tfCheckInTime = new JTextField();
        tfCheckInTime.setBounds(200, 200, 150, 25);
        add(tfCheckInTime);

        JLabel lblPaid = new JLabel("Amount Paid");
        lblPaid.setBounds(30, 240, 100, 20);
        add(lblPaid);

        tfPaid = new JTextField();
        tfPaid.setBounds(200, 240, 150, 25);
        add(tfPaid);

        JLabel lblPending = new JLabel("Amount Pending");
        lblPending.setBounds(30, 280, 100, 20);
        add(lblPending);

        tfPending = new JTextField();
        tfPending.setBounds(200, 280, 150, 25);
        add(tfPending);

        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 340, 100, 30);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150, 340, 100, 30);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(270, 340, 100, 30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/nine.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 50, 500, 300);
        add(image);

        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 900, 500);
        setVisible(true);
    }

    public static void main(String[] args) {
       new UpdateCheck();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == check) {
            try {
                Conn c1 = new Conn();
                String id = cCustomer.getSelectedItem();
                String query = String.format("SELECT * FROM Customer " +
                        "WHERE document_number = '%s'", id);

                ResultSet rs1 = c1.s.executeQuery(query);

                while (rs1.next()) {
                    String roomNumber = rs1.getString("room_number");
                    tfRoom.setText(roomNumber);
                    tfCheckInTime.setText(rs1.getString("check_in_time"));
                    tfName.setText(rs1.getString("name"));
                    tfPaid.setText(rs1.getString("deposit"));

                    Conn c2 = new Conn();
                    ResultSet rs2 = c2.s.executeQuery(String.format("SELECT * " +
                            "FROM Room WHERE room_number = '%s'", roomNumber));

                    while (rs2.next()) {
                        String price = rs2.getString("price");
                        int amountPaid = Integer.parseInt(tfPaid.getText());
                        int totalAmount = Integer.parseInt(price);
                        int amountLeft = totalAmount - amountPaid;
                        tfPending.setText(String.valueOf(amountLeft));
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == update) {
            try {
                String number = cCustomer.getSelectedItem();
                String roomNumber = tfRoom.getText();
                String name = tfName.getText();
                String checkInTime = tfCheckInTime.getText();
                String paid = tfPaid.getText();

                Conn c = new Conn();
                c.s.executeUpdate(String.format("UPDATE Customer " +
                        "SET room_number = '%s', name = '%s', check_in_time = '%s', " +
                        "deposit = '%s' WHERE document_number = '%s'", roomNumber,
                        name, checkInTime, paid, number));

                JOptionPane.showMessageDialog(null, "Data updated successfully");
                setVisible(false);

                new Reception();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == back) {
           setVisible(false);
           new Reception();
        }
    }
}
