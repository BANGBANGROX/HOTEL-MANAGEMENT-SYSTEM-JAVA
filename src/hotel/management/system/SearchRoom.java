package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.*;

public class SearchRoom extends JFrame implements ActionListener {
    private final JTable table;
    private final JButton back;
    private final JButton submit;
    private final JComboBox bedType;
    private final JCheckBox available;

    private void fetchData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM Room";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public SearchRoom() {
        setLayout(null);

        JLabel text = new JLabel("Search for Room");
        text.setFont(new Font("Tahoma", Font.PLAIN, 20));
        text.setBounds(400, 30, 200, 30);
        add(text);

        JLabel lblBed = new JLabel("Bed Type");
        lblBed.setBounds(50, 100, 100, 20);
        add(lblBed);

        String[] bedOptions = {"Single Bed", "Double Bed"};
        bedType = new JComboBox(bedOptions);
        bedType.setBounds(150, 100, 150, 25);
        bedType.setBackground(Color.WHITE);
        add(bedType);

        available = new JCheckBox("Only Display Available");
        available.setBounds(650, 100, 150, 25);
        available.setBackground(Color.WHITE);
        add(available);

        JLabel l1 = new JLabel("Room Number");
        l1.setBounds(50, 160, 100, 20);
        add(l1);

        JLabel l2 = new JLabel("Availability");
        l2.setBounds(270, 160, 100, 20);
        add(l2);

        JLabel l3 = new JLabel("Cleaning Status");
        l3.setBounds(450, 160, 100, 20);
        add(l3);

        JLabel l4 = new JLabel("Price");
        l4.setBounds(670, 160, 100, 20);
        add(l4);

        JLabel l5 = new JLabel("Bed Type");
        l5.setBounds(870, 160, 100, 20);
        add(l5);

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
        new SearchRoom();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == back) {
            setVisible(false);
            new Reception();
        }

        if (ae.getSource() == submit) {
            try {
                String query1 = String.format("SELECT * FROM Room " +
                        "WHERE bed_type = '%s'", bedType.getSelectedItem());
                String query2 = String.format("SELECT * FROM Room " +
                        "WHERE bed_type = '%s' AND availability = '%s'",
                        bedType.getSelectedItem(), "Available");

                Conn c = new Conn();
                ResultSet rs;

                if (available.isSelected()) {
                    rs = c.s.executeQuery(query2);
                }
                else {
                    rs = c.s.executeQuery(query1);
                }

                table.setModel(DbUtils.resultSetToTableModel(rs));
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
