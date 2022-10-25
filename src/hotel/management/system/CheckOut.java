package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class CheckOut extends JFrame implements ActionListener {
    private final Choice cCustomer;
    private final JLabel lblRoomNumber;
    private final JLabel lblCheckInTime;
    private final JButton checkOut;
    private final JButton back;
    private final JButton fetchDetails;

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

    public CheckOut() {
        setLayout(null);

        JLabel text = new JLabel("Check Out");
        text.setBounds(100, 20, 100, 30);
        text.setForeground(Color.BLUE);
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(text);

        JLabel lblId = new JLabel("Customer ID");
        lblId.setBounds(30, 80, 100, 30);
        add(lblId);

        cCustomer = new Choice();
        cCustomer.setBounds(150, 80, 150, 25);
        add(cCustomer);

        JLabel lblRoom = new JLabel("Room Number");
        lblRoom.setBounds(30, 130, 100, 30);
        add(lblRoom);

        lblRoomNumber = new JLabel();
        lblRoomNumber.setBounds(150, 130, 100, 30);
        add(lblRoomNumber);

        JLabel lblCheckIn = new JLabel("Check In Time");
        lblCheckIn.setBounds(30, 180, 100, 30);
        add(lblCheckIn);

        lblCheckInTime = new JLabel();
        lblCheckInTime.setBounds(150, 180, 100, 30);
        add(lblCheckInTime);

        JLabel lblCheckOut = new JLabel("Check Out Time");
        lblCheckOut.setBounds(30, 230, 100, 30);
        add(lblCheckOut);

        fetchData();

        Date date = new Date();
        JLabel lblCheckOutTime = new JLabel("" + date);
        lblCheckOutTime.setBounds(150, 230, 150, 30);
        add(lblCheckOutTime);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tick.png"));
        Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image1 = new JLabel(i3);
        image1.setBounds(310, 80, 20, 20);
        add(image1);

        fetchDetails = new JButton("Fetch Details");
        fetchDetails.setForeground(Color.WHITE);
        fetchDetails.setBackground(Color.BLACK);
        fetchDetails.setBounds(30, 280, 120, 30);
        fetchDetails.addActionListener(this);
        add(fetchDetails);

        checkOut = new JButton("Check Out");
        checkOut.setForeground(Color.WHITE);
        checkOut.setBackground(Color.BLACK);
        checkOut.setBounds(170, 280, 120, 30);
        checkOut.addActionListener(this);
        add(checkOut);

        back = new JButton("Back");
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.setBounds(310, 280, 120, 30);
        back.addActionListener(this);
        add(back);

        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/sixth.jpg"));
        Image i5 = i4.getImage().getScaledInstance(400, 250, Image.SCALE_DEFAULT);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel image2 = new JLabel(i6);
        image2.setBounds(450, 50, 400, 250);
        add(image2);

        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 1000, 400);
        setVisible(true);
    }

    public static void main(String[] args) {
        new CheckOut();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == fetchDetails) {
            try {
                String query = String.format("SELECT room_number, " +
                        "check_in_time FROM Customer WHERE document_number = '%s'",
                        cCustomer.getSelectedItem());
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                while (rs.next()) {
                    lblRoomNumber.setText(rs.getString("room_number"));
                    lblCheckInTime.setText(rs.getString("check_in_time"));
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }

        if (ae.getSource() == checkOut) {
            try {
                String roomNumber = lblRoomNumber.getText();
                String query1 = String.format("DELETE FROM Customer " +
                        "WHERE document_number = '%s'", cCustomer.getSelectedItem());
                String query2 = String.format("UPDATE Room SET " +
                        "availability = '%s' WHERE room_number = '%s'", "Available", roomNumber);
                Conn c = new Conn();

                c.s.executeUpdate(query1);
                c.s.executeUpdate(query2);

                JOptionPane.showMessageDialog(null, "Check Out completed successfully");
                setVisible(false);
                new Reception();
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
