package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminProfileMod extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            setVisible(false);
            dispose();
            PageAdminProfileView adminProfileView = new PageAdminProfileView();
            adminProfileView.setVisible(true);
        }
        else if(e.getSource() == confirm){
            if(usernameField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Username cannot be empty!");
            }
            else if(passwordField.getText().isEmpty()){
                JOptionPane.showMessageDialog(confirm,"Password cannot be empty!");
            }
            else{
                String a = usernameField.getText();
                Admin check = DataIO.checkAdmin(a);
                if(check == null){
                    if(verificationField.getText().equals(Main.currentAdmin.getPassword())){
                        String c = usernameField.getText();
                        String d = passwordField.getText();
                        PageAdminProfileHub.viewAdmin.setUsername(c);
                        PageAdminProfileHub.viewAdmin.setPassword(d);
                        DataIO.write();
                        JOptionPane.showMessageDialog(confirm,"Modify Successful!");
                        setVisible(false);
                        dispose();
                        PageAdminProfileHub.viewAdmin = null;
                        PageAdminProfileHub adminProfileHub = new PageAdminProfileHub();
                        adminProfileHub.setVisible(true);
                    }
                    else{
                        JOptionPane.showMessageDialog(confirm,"Wrong Verification Password!");
                        verificationField.setText("");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(confirm,"Username has been Used!");
                    usernameField.setText("");
                    verificationField.setText("");
                }
            }

        }
    }

    private TextField usernameField, passwordField, firstNameField, lastNameField, emailField, phoneField, ageField,verificationField;
    private Label username, password, firstName, lastName, email, phone, age,verification;
    private Panel mainPanel, buttonPanel, u, p, f, l, e, ph, g, a,v1,v2;
    private Button confirm, cancel;
    private JRadioButton male, female;
    private ButtonGroup gender;
    private Panel titlePanel;
    private Label title, subtitle;

    public PageAdminProfileMod(){
        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,6));
        this.setLocation(300,300);
        this.setSize(800,600);
        this.getContentPane().setBackground(Color.DARK_GRAY);

        titlePanel = new Panel();
        titlePanel.setBackground(new Color(0xFF781F));
        titlePanel.setPreferredSize(new Dimension(1000,80));
        titlePanel.setLayout(new BorderLayout());
        this.add(titlePanel,BorderLayout.NORTH);

        title = new Label("Malaysia Vaccination Center Program",Label.CENTER);
        title.setFont(new Font("Helvetica",Font.BOLD,35));
        titlePanel.add(title,BorderLayout.NORTH);

        subtitle = new Label("Healthy Me, Healthy You",Label.CENTER);
        subtitle.setFont(new Font("Helvetica",Font.BOLD,20));
        titlePanel.add(subtitle,BorderLayout.CENTER);

        mainPanel = new Panel();
        this.add(mainPanel,BorderLayout.CENTER);
        mainPanel.setLayout(new GridLayout(6,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);

        confirm = new Button("Confirm");
        confirm.addActionListener(this);
        cancel = new Button("Cancel");
        cancel.addActionListener(this);

        username = new Label("Username",Label.CENTER);
        username.setForeground(Color.WHITE);
        password = new Label("Password",Label.CENTER);
        password.setForeground(Color.WHITE);
        verification = new Label("Admin Password (Verification)");
        verification.setForeground(Color.WHITE);

        gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);

        usernameField = new TextField(30);
        usernameField.setText(PageAdminProfileHub.viewAdmin.getUsername());
        passwordField = new TextField(30);
        passwordField.setText(PageAdminProfileHub.viewAdmin.getPassword());
        verificationField = new TextField(30);
        verificationField.setEchoChar('*');


        u = new Panel();
        u.add(usernameField);
        p = new Panel();
        p.add(passwordField);

        mainPanel.add(username);
        mainPanel.add(u);
        mainPanel.add(password);
        mainPanel.add(p);

        buttonPanel.add(confirm);
        buttonPanel.add(cancel);

        v1 = new Panel();
        v2 = new Panel();

        v1.add(verification);
        mainPanel.add(v1);
        v2.add(verificationField);
        mainPanel.add(v2);
    }
}