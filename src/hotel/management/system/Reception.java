package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Reception extends JFrame implements ActionListener {
    private final JButton btnNewCustomer;
    private final JButton btnRooms;
    private final JButton btnDepartments;
    private final JButton btnAllEmployees;
    private final JButton btnLogout;
    private final JButton btnSearchRoom;
    private final JButton btnPickup;
    private final JButton btnUpdateRoomStatus;
    private final JButton btnUpdate;
    private final JButton btnCheckOut;
    private final JButton btnManagerInfo;
    private final JButton btnCustomers;

    public Reception() {
        setLayout(null);

        btnNewCustomer = new JButton("New Customer Form");
        btnNewCustomer.setBounds(10, 30, 200, 30);
        btnNewCustomer.setBackground(Color.BLACK);
        btnNewCustomer.setForeground(Color.WHITE);
        btnNewCustomer.addActionListener(this);
        add(btnNewCustomer);

        btnRooms = new JButton("Rooms");
        btnRooms.setBounds(10, 70, 200, 30);
        btnRooms.setBackground(Color.BLACK);
        btnRooms.setForeground(Color.WHITE);
        add(btnRooms);

        btnDepartments = new JButton("Departments");
        btnDepartments.setBounds(10, 110, 200, 30);
        btnDepartments.setBackground(Color.BLACK);
        btnDepartments.setForeground(Color.WHITE);
        add(btnDepartments);

        btnAllEmployees = new JButton("All Employees");
        btnAllEmployees.setBounds(10, 150, 200, 30);
        btnAllEmployees.setBackground(Color.BLACK);
        btnAllEmployees.setForeground(Color.WHITE);
        add(btnAllEmployees);

        btnCustomers = new JButton("Customers Info");
        btnCustomers.setBounds(10, 190, 200, 30);
        btnCustomers.setBackground(Color.BLACK);
        btnCustomers.setForeground(Color.WHITE);
        add(btnCustomers);

        btnManagerInfo = new JButton("Manager Info");
        btnManagerInfo.setBounds(10, 230, 200, 30);
        btnManagerInfo.setBackground(Color.BLACK);
        btnManagerInfo.setForeground(Color.WHITE);
        add(btnManagerInfo);

        btnCheckOut = new JButton("Check Out");
        btnCheckOut.setBounds(10, 270, 200, 30);
        btnCheckOut.setBackground(Color.BLACK);
        btnCheckOut.setForeground(Color.WHITE);
        add(btnCheckOut);

        btnUpdate = new JButton("Update Status");
        btnUpdate.setBounds(10, 310, 200, 30);
        btnUpdate.setBackground(Color.BLACK);
        btnUpdate.setForeground(Color.WHITE);
        add(btnUpdate);

        btnUpdateRoomStatus = new JButton("Update Room Status");
        btnUpdateRoomStatus.setBounds(10, 350, 200, 30);
        btnUpdateRoomStatus.setBackground(Color.BLACK);
        btnUpdateRoomStatus.setForeground(Color.WHITE);
        add(btnUpdateRoomStatus);

        btnPickup = new JButton("Pickup Service");
        btnPickup.setBounds(10, 390, 200, 30);
        btnPickup.setBackground(Color.BLACK);
        btnPickup.setForeground(Color.WHITE);
        add(btnPickup);

        btnSearchRoom = new JButton("Search Room");
        btnSearchRoom.setBounds(10, 430, 200, 30);
        btnSearchRoom.setBackground(Color.BLACK);
        btnSearchRoom.setForeground(Color.WHITE);
        add(btnSearchRoom);

        btnLogout = new JButton("Logout");
        btnLogout.setBounds(10, 470, 200, 30);
        btnLogout.setBackground(Color.BLACK);
        btnLogout.setForeground(Color.WHITE);
        add(btnLogout);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/fourth.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(250, 30, 500, 470);
        add(image);

        getContentPane().setBackground(Color.WHITE);
        setBounds(350, 200, 800, 570);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Reception();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);

        if (ae.getSource() == btnNewCustomer) {
            new AddCustomer();
        }
    }
}
