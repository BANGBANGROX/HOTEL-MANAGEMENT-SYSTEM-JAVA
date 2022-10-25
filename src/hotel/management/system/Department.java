package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class Department extends JFrame implements ActionListener {
    private final JTable table;
    private final JButton back;

    private void fetchData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM Department";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public Department() {
        setLayout(null);

        JLabel l1 = new JLabel("Department");
        l1.setBounds(120, 10, 100, 20);
        add(l1);

        JLabel l2 = new JLabel("Budget");
        l2.setBounds(460, 10, 100, 20);
        add(l2);

        table = new JTable();
        table.setBounds(0, 50, 700, 350);
        add(table);

        fetchData();

        back = new JButton("Back");
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        back.setBounds(280, 400, 120, 30);
        back.addActionListener(this);
        add(back);

        getContentPane().setBackground(Color.WHITE);
        setBounds(400, 200, 700, 480);
        setVisible(true);

    }

    public static void main(String[] args) {
        new Department();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }
    }
}
