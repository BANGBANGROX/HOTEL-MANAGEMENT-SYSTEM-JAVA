package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdateRoom extends JFrame implements ActionListener {
    private final Choice cCustomer;
    private final JTextField tfRoom;
    private final JTextField tfAvailability;
    private final JTextField tfCleaningStatus;
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

    public UpdateRoom() {
        setLayout(null);

        JLabel heading = new JLabel("Update Room Status");
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        heading.setBounds(30, 20, 250, 30);
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
        lblRoom.setBounds(30, 130, 100, 20);
        add(lblRoom);

        tfRoom = new JTextField();
        tfRoom.setBounds(200, 130, 150, 25);
        add(tfRoom);

        JLabel lblAvailability = new JLabel("Availability");
        lblAvailability.setBounds(30, 180, 100, 20);
        add(lblAvailability);

        tfAvailability = new JTextField();
        tfAvailability.setBounds(200, 180, 150, 25);
        add(tfAvailability);

        JLabel lblCleaningStatus = new JLabel("Cleaning Status");
        lblCleaningStatus.setBounds(30, 230, 100, 20);
        add(lblCleaningStatus);

        tfCleaningStatus = new JTextField();
        tfCleaningStatus.setBounds(200, 230, 150, 25);
        add(tfCleaningStatus);

        check = new JButton("Check");
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        check.setBounds(30, 300, 100, 30);
        check.addActionListener(this);
        add(check);

        update = new JButton("Update");
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);
        update.setBounds(150, 300, 100, 30);
        update.addActionListener(this);
        add(update);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(270, 300, 100, 30);
        back.addActionListener(this);
        add(back);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/seventh.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 50, 500, 300);
        add(image);

        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 900, 450);
        setVisible(true);
    }

    public static void main(String[] args) {
        new UpdateRoom();
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
                    Conn c2 = new Conn();
                    ResultSet rs2 = c2.s.executeQuery(String.format("SELECT * FROM " +
                            "Room WHERE room_number = '%s'", roomNumber));

                    tfRoom.setText(roomNumber);

                    while (rs2.next()) {
                        String availability = rs2.getString("availability");
                        String cleaningStatus = rs2.getString("cleaning_status");
                        tfAvailability.setText(availability);
                        tfCleaningStatus.setText(cleaningStatus);
                    }
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == update) {
            try {
                String roomNumber = tfRoom.getText();
                String availability = tfAvailability.getText();
                String cleaningStatus = tfCleaningStatus.getText();
                Conn c = new Conn();

                c.s.executeUpdate(String.format("UPDATE Room " +
                        "SET availability = '%s', cleaning_status = '%s' " +
                        "WHERE room_number = '%s'", availability, cleaningStatus, roomNumber));

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
