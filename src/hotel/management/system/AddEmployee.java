package hotel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEmployee extends JFrame implements ActionListener {
    private final JTextField tfName;
    private final JTextField tfAge;
    private final JTextField tfSalary;
    private final JTextField tfPhone;
    private final JTextField tfEmail;
    private final JRadioButton rbFemale;
    private final JRadioButton rbMale;
    private final JComboBox cbJob;
    private final JTextField tfAadhar;

    public AddEmployee() {
        setLayout(null);

        JLabel lblName = new JLabel("NAME");
        lblName.setBounds(60, 30, 120, 30);
        lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblName);

        tfName = new JTextField();
        tfName.setBounds(200, 30, 150, 30);
        add(tfName);

        JLabel lblAge = new JLabel("AGE");
        lblAge.setBounds(60, 80, 120, 30);
        lblAge.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblAge);

        tfAge = new JTextField();
        tfAge.setBounds(200, 80, 150, 30);
        add(tfAge);

        JLabel lblGender = new JLabel("GENDER");
        lblGender.setBounds(60, 130, 120, 30);
        lblGender.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblGender);

        rbMale = new JRadioButton("Male");
        rbMale.setBounds(200, 130, 70, 30);
        rbMale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbMale.setBackground(Color.WHITE);
        add(rbMale);

        rbFemale = new JRadioButton("Female");
        rbFemale.setBounds(280, 130, 70, 30);
        rbFemale.setFont(new Font("Tahoma", Font.PLAIN, 14));
        rbFemale.setBackground(Color.WHITE);
        add(rbFemale);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rbMale);
        bg.add(rbFemale);

        JLabel lblJob = new JLabel("JOB");
        lblJob.setBounds(60, 180, 120, 30);
        lblJob.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblJob);

        String[] str = {"Front Desk Clerk", "Porters", "Housekeeping",
                "Kitchen Staff", "Room Service", "Chefs", "Waiter/Waitress",
                "Manager", "Accountant"};
        cbJob = new JComboBox(str);
        cbJob.setBounds(200, 180, 150, 30);
        cbJob.setBackground(Color.WHITE);
        add(cbJob);

        JLabel lblSalary = new JLabel("SALARY");
        lblSalary.setBounds(60, 230, 120, 30);
        lblSalary.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblSalary);

        tfSalary = new JTextField();
        tfSalary.setBounds(200, 230, 150, 30);
        add(tfSalary);

        JLabel lblPhone = new JLabel("PHONE");
        lblPhone.setBounds(60, 280, 120, 30);
        lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblPhone);

        tfPhone = new JTextField();
        tfPhone.setBounds(200, 280, 150, 30);
        add(tfPhone);

        JLabel lblEmail = new JLabel("EMAIL");
        lblEmail.setBounds(60, 330, 120, 30);
        lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblEmail);

        tfEmail = new JTextField();
        tfEmail.setBounds(200, 330, 150, 30);
        add(tfEmail);

        JLabel lblAadhar = new JLabel("AADHAR");
        lblAadhar.setBounds(60, 380, 120, 30);
        lblAadhar.setFont(new Font("Tahoma", Font.PLAIN, 17));
        add(lblAadhar);

        tfAadhar = new JTextField();
        tfAadhar.setBounds(200, 380, 150, 30);
        add(tfAadhar);

        JButton submit = new JButton("SUBMIT");
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.setBounds(200, 440, 150, 30);
        submit.addActionListener(this);
        add(submit);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/tenth.jpg"));
        Image i2 = i1.getImage().getScaledInstance(450, 450, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(380, 60, 450, 370);
        add(image);

        setBounds(350, 200, 850, 540);
        getContentPane().setBackground(Color.WHITE);

        setVisible(true);
    }


    public static void main(String[] args) {
        new AddEmployee();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String name = tfName.getText();
        String age = tfAge.getText();
        String salary = tfSalary.getText();
        String phone = tfPhone.getText();
        String email = tfEmail.getText();
        String aadhar = tfAadhar.getText();
        String regex = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
        String gender = null;

        if (name.equals("")) {
            JOptionPane.showMessageDialog(null, "Name " +
                    "should not be empty");
            return;
        }

        if (age.equals("")) {
            JOptionPane.showMessageDialog(null, "Age " +
                    "should not be empty");
            return;
        }

        if (salary.equals("")) {
            JOptionPane.showMessageDialog(null, "Salary " +
                    "should not be empty");
            return;
        }

        if (phone.equals("")) {
            JOptionPane.showMessageDialog(null, "Phone " +
                    "should not be empty");
            return;
        }

        if (!email.matches(regex)) {
            JOptionPane.showMessageDialog(null, "Invalid Email");
            return;
        }

        if (aadhar.equals("")) {
            JOptionPane.showMessageDialog(null, "Aadhar " +
                    "should not be empty");
            return;
        }

        if (rbMale.isSelected()) {
            gender = "Male";
        } else if (rbFemale.isSelected()) {
            gender = "Female";
        }

        String job = (String) cbJob.getSelectedItem();

        if (gender == null) {
            JOptionPane.showMessageDialog(null, "Gender " +
                    "should not be empty");
            return;
        }

        if (job == null) {
            JOptionPane.showMessageDialog(null, "Job " +
                    "should not be empty");
            return;
        }

        try {
            Conn conn = new Conn();
            String query = String.format("INSERT INTO Employee VALUES('%s','%s'," +
                            "'%s','%s','%s','%s','%s','%s')",
                    name, age, gender, job, salary, phone, email, aadhar);

            conn.s.executeUpdate(query);

            JOptionPane.showMessageDialog(null,
                    "Employee added successfully");

            setVisible(false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
