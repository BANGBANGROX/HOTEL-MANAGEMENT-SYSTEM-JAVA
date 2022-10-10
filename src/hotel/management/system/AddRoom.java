package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddRoom extends JFrame implements ActionListener {
    private final JButton add;
    private final JButton cancel;
    private final JComboBox typeCombo;
    private final JTextField tfPrice;
    private final JComboBox cleanCombo;
    private final JComboBox availableCombo;
    private final JTextField tfRoomNo;

    public AddRoom() {
        setLayout(null);

        setBounds(330, 220, 940, 470);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Add Room");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setBounds(150, 20, 200, 20);
        add(heading);

        JLabel lblRoomNo = new JLabel("Room No");
        lblRoomNo.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRoomNo.setBounds(60, 80, 120, 30);
        add(lblRoomNo);

        tfRoomNo = new JTextField();
        tfRoomNo.setBounds(200, 80, 150, 30);
        add(tfRoomNo);

        JLabel lblAvailable = new JLabel("Available");
        lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAvailable.setBounds(60, 130, 120, 30);
        add(lblAvailable);

        String[] availableOptions = {"Available", "Occupied"};
        availableCombo = new JComboBox(availableOptions);
        availableCombo.setBounds(200, 130, 150, 30);
        availableCombo.setBackground(Color.WHITE);
        add(availableCombo);

        JLabel lblClean = new JLabel("Cleaning Status");
        lblClean.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblClean.setBounds(60, 180, 120, 30);
        add(lblClean);

        String[] cleanOptions = {"Cleaned", "Dirty"};
        cleanCombo = new JComboBox(cleanOptions);
        cleanCombo.setBounds(200, 180, 150, 30);
        cleanCombo.setBackground(Color.WHITE);
        add(cleanCombo);

        JLabel lblPrice = new JLabel("Price");
        lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPrice.setBounds(60, 230, 120, 30);
        add(lblPrice);

        tfPrice = new JTextField();
        tfPrice.setBounds(200, 230, 150, 30);
        add(tfPrice);

        JLabel lblType = new JLabel("Bed Type");
        lblType.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblType.setBounds(60, 280, 120, 30);
        add(lblType);

        String[] typeOptions = {"Single Bed", "Double Bed"};
        typeCombo = new JComboBox(typeOptions);
        typeCombo.setBounds(200, 280, 150, 30);
        typeCombo.setBackground(Color.WHITE);
        add(typeCombo);

        add = new JButton("Add");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(60, 350, 130, 30);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(220, 350, 130, 30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource
                ("icons/twelve.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setVisible(true);
    }

    public static void main(String[] args) {
       new AddRoom();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         if (ae.getSource() == add) {
             String roomNo = tfRoomNo.getText();
             String available = (String) availableCombo.getSelectedItem();
             String status = (String) cleanCombo.getSelectedItem();
             String price = tfPrice.getText();
             String type = (String) typeCombo.getSelectedItem();

             if (roomNo.equals("")) {
                 JOptionPane.showMessageDialog(null, "Room " +
                         "Number should not be empty");
                 return;
             }

             assert available != null;
             if (available.equals("")) {
                 JOptionPane.showMessageDialog(null, "Available " +
                         "should not be empty");
                 return;
             }

             assert status != null;
             if (status.equals("")) {
                 JOptionPane.showMessageDialog(null, "Status " +
                         "should not be empty");
                 return;
             }

             if (price.equals("")) {
                 JOptionPane.showMessageDialog(null, "Price " +
                         "should not be empty");
                 return;
             }

             assert type != null;
             if (type.equals("")) {
                 JOptionPane.showMessageDialog(null, "Type " +
                         "should not be empty");
                 return;
             }

             try {
                 Conn conn = new Conn();
                 String query = String.format("INSERT INTO Room VALUES('%s','%s'," +
                         "'%s','%s','%s')", roomNo, available, status, price, type);
                 conn.s.executeUpdate(query);
                 JOptionPane.showMessageDialog(null, "Room " +
                         "added successfully");

                 setVisible(false);
             }
             catch(Exception e) {
                 e.printStackTrace();
             }
         }
         else if (ae.getSource() == cancel) {
             setVisible(false);
             new Dashboard();
         }
    }
}
