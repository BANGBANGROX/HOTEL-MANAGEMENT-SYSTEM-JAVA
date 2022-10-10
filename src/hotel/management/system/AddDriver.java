package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddDriver extends JFrame implements ActionListener {
    private final JTextField tfCompany;
    private final JTextField tfName;
    private final JTextField tfModel;
    private final JTextField tfAge;
    private final JTextField tfSalary;
    private final JTextField tfLocation;
    private final JRadioButton rbFemale;
    private final JRadioButton rbMale;
    private final JComboBox availableCombo;
    private final JButton add;
    private final JButton cancel;

    public AddDriver() {
        setLayout(null);

        setBounds(300, 200, 980, 600);
        getContentPane().setBackground(Color.WHITE);

        JLabel heading = new JLabel("Add Driver");
        heading.setFont(new Font("Tahoma", Font.BOLD, 22));
        heading.setBounds(150, 20, 200, 20);
        add(heading);

        JLabel lblName = new JLabel("Name");
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblName.setBounds(60, 80, 120, 30);
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(200, 80, 150, 30);
        add(tfName);

        JLabel lblAge = new JLabel("Age");
        lblAge.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAge.setBounds(60, 130, 120, 30);
        add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(200, 130, 150, 30);
        add(tfAge);

        JLabel lblGender = new JLabel("Gender");
        lblGender.setBounds(60, 180, 120, 30);
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblGender);

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(200, 180, 70, 30);
        rbMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbMale.setBackground(Color.WHITE);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(280, 180, 70, 30);
        rbFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbFemale.setBackground(Color.WHITE);
        add(rbFemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        JLabel lblCompany = new JLabel("Car Company");
        lblCompany.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblCompany.setBounds(60, 230, 120, 30);
        add(lblCompany);

        tfCompany = new JTextField();
        tfCompany.setBounds(200, 230, 150, 30);
        add(tfCompany);

        JLabel lblModel = new JLabel("Car Model");
        lblModel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblModel.setBounds(60, 280, 120, 30);
        add(lblModel);

        tfModel = new JTextField();
        tfModel.setBounds(200, 280, 150, 30);
        add(tfModel);

        JLabel lblAvailable = new JLabel("Available");
        lblAvailable.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblAvailable.setBounds(60, 330, 120, 30);
        add(lblAvailable);

        String[] driverOptions = {"Available", "Busy"};
        availableCombo = new JComboBox(driverOptions);
        availableCombo.setBounds(200, 330, 150, 30);
        availableCombo.setBackground(Color.WHITE);
        add(availableCombo);

        JLabel lblSalary = new JLabel("Salary");
        lblSalary.setBounds(60, 380, 120, 30);
        lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblSalary);

        tfSalary = new JTextField();
        tfSalary.setBounds(200, 380, 150, 30);
        add(tfSalary);

        JLabel lblLocation = new JLabel("Location");
        lblLocation.setBounds(60, 430, 120, 30);
        lblLocation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        add(lblLocation);

        tfLocation = new JTextField();
        tfLocation.setBounds(200, 430, 150, 30);
        add(tfLocation);

        add = new JButton("Add");
        add.setForeground(Color.WHITE);
        add.setBackground(Color.BLACK);
        add.setBounds(100, 500, 150, 30);
        add.addActionListener(this);
        add(add);

        cancel = new JButton("Cancel");
        cancel.setForeground(Color.WHITE);
        cancel.setBackground(Color.BLACK);
        cancel.setBounds(270, 500, 150, 30);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource
                ("icons/eleven.jpg"));
        Image i2 = i1.getImage().getScaledInstance(500, 300, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(400, 30, 500, 300);
        add(image);

        setVisible(true);
    }

    public static void main(String[] args) {
        new AddDriver();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == add) {
            String name = tfName.getText();
            String salary = tfSalary.getText();
            String age = tfAge.getText();
            String model = tfModel.getText();
            String company = tfCompany.getText();
            String location = tfLocation.getText();
            String status = (String) availableCombo.getSelectedItem();
            String gender = rbMale.isSelected() ? "Male" : rbFemale.isSelected() ? "Female" : null;

            if (name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name cannot be empty");
                return;
            }

            if (salary.equals("")) {
                JOptionPane.showMessageDialog(null, "Salary cannot be empty");
                return;
            }

            if (age.equals("")) {
                JOptionPane.showMessageDialog(null, "Age cannot be empty");
                return;
            }

            if (model.equals("")) {
                JOptionPane.showMessageDialog(null, "Model cannot be empty");
                return;
            }

            if (company.equals("")) {
                JOptionPane.showMessageDialog(null, "Company cannot be empty");
                return;
            }

            if (status == null) {
                JOptionPane.showMessageDialog(null, "Status cannot be empty");
                return;
            }

            if (gender == null) {
                JOptionPane.showMessageDialog(null, "Gender cannot be empty");
                return;
            }

            if (location.equals("")) {
                JOptionPane.showMessageDialog(null, "Location cannot be empty");
                return;
            }

            try {
                Conn conn = new Conn();
                String query = String.format("INSERT INTO Driver " +
                        "VALUES('%s','%s','%s','%s','%s','%s','%s','%s')",
                        name, salary, age, model, company, location, status, gender);

                conn.s.executeUpdate(query);

                setVisible(false);
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
        else if (ae.getSource() == cancel) {
            new Dashboard();
            setVisible(false);
        }
    }
}
