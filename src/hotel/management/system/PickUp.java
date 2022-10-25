package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class PickUp extends JFrame implements ActionListener {
    private final JTable table;
    private final JButton back;
    private final JButton submit;
    private final Choice carType;

    private void fetchData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM Driver";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void fetchCarType() {
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM Driver");
            while (rs.next()) {
                carType.add(rs.getString("model"));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public PickUp() {
        setLayout(null);

        JLabel text = new JLabel("Pick Up Service");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(text);

        JLabel lblType = new JLabel("Type of Car");
        lblType.setBounds(50, 100, 100, 20);
        add(lblType);

        carType = new Choice();
        carType.setBounds(150, 100, 200, 25);
        add(carType);

        fetchCarType();

        JLabel l1 = new JLabel("Name");
        l1.setBounds(50, 160, 100, 20);
        add(l1);

        JLabel l2 = new JLabel("Salary");
        l2.setBounds(180, 160, 100, 20);
        add(l2);

        JLabel l3 = new JLabel("Age");
        l3.setBounds(310, 160, 100, 20);
        add(l3);

        JLabel l4 = new JLabel("Model");
        l4.setBounds(420, 160, 100, 20);
        add(l4);

        JLabel l5 = new JLabel("Company");
        l5.setBounds(530, 160, 100, 20);
        add(l5);

        JLabel l6 = new JLabel("Location");
        l6.setBounds(680, 160, 100, 20);
        add(l6);

        JLabel l7 = new JLabel("Status");
        l7.setBounds(790, 160, 100, 20);
        add(l7);

        JLabel l8 = new JLabel("Gender");
        l8.setBounds(920, 160, 100, 20);
        add(l8);

        table = new JTable();
        table.setBounds(0, 200, 1050, 300);
        add(table);

        fetchData();

        submit = new JButton("Submit");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200, 500, 120, 30);
        submit.addActionListener(this);
        add(submit);

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(500, 500, 120, 30);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);
        setBounds(300, 200, 1050, 600);
        setVisible(true);

    }

    public static void main(String[] args) {
        new PickUp();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }

        if (ae.getSource() == submit) {
            try {
                String query = String.format("SELECT * " +
                        "FROM Driver WHERE model = '%s'", carType.getSelectedItem());
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
