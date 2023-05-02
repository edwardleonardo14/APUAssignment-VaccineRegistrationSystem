package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminProfileView extends JFrame implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            PageAdminProfileHub.viewAdmin = null;
            setVisible(false);
            dispose();
            PageAdminProfileHub adminProfileHub = new PageAdminProfileHub();
            adminProfileHub.setVisible(true);
        }
        else if (e.getSource() == modify){
            setVisible(false);
            dispose();
            PageAdminProfileMod adminProfileMod = new PageAdminProfileMod();
            adminProfileMod.setVisible(true);
        }
        else if (e.getSource() == delete){
            if(Main.currentAdmin.getUsername().equals(PageAdminProfileHub.viewAdmin.getUsername())){
                String a = JOptionPane.showInputDialog("Enter Verification Password to delete YOUR OWN Data!");
                if(a.equals(Main.currentAdmin.getPassword())){
                    DataIO.allAdmin.remove(Main.currentAdmin);
                    PageAdminProfileHub.viewAdmin = null;
                    Main.currentAdmin = null;
                    DataIO.write();
                    JOptionPane.showMessageDialog(delete,"Data Deleted! You will be logged out right now!");
                    setVisible(false);
                    dispose();
                    Main.login.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(delete,"Wrong Verification Password!");
                }
            }
            else{
                String a = JOptionPane.showInputDialog("Enter Verification Password to delete the Data!");
                if(a.equals(Main.currentAdmin.getPassword())){
                    DataIO.allAdmin.remove(PageAdminProfileHub.viewAdmin);
                    PageAdminProfileHub.viewAdmin = null;
                    DataIO.write();
                    JOptionPane.showMessageDialog(delete,"Data Deleted!");
                    setVisible(false);
                    dispose();
                    PageAdminProfileHub adminProfileHub = new PageAdminProfileHub();
                    adminProfileHub.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(delete,"Wrong Verification Password!");
                }
            }
        }
    }

    private TextField usernameField, passwordField, firstNameField, lastNameField, emailField, phoneField, ageField, genderField;
    private Label username, password, firstName, lastName, email, phone, age, gender;
    private Panel mainPanel, buttonPanel, u, p, f, l, e, ph, g, a,gep2,v;
    private Button modify, back,delete;
    private Panel titlePanel;
    private Label title, subtitle;


    public PageAdminProfileView(){
        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10,6));
        this.setLocation(300,300);
        this.setSize(800,700);
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
        mainPanel.setLayout(new GridLayout(4,1));
        buttonPanel = new Panel();
        buttonPanel.setPreferredSize(new Dimension(800,50));
        this.add(buttonPanel,BorderLayout.SOUTH);

        delete = new Button("Delete Admin");
        delete.addActionListener(this);
        modify = new Button("Modify");
        modify.addActionListener(this);
        back = new Button("Back");
        back.addActionListener(this);

        username = new Label("Username",Label.CENTER);
        username.setForeground(Color.WHITE);
        password = new Label("Password",Label.CENTER);
        password.setForeground(Color.WHITE);

        usernameField = new TextField(30);
        usernameField.setText(PageAdminProfileHub.viewAdmin.getUsername());
        usernameField.setEditable(false);
        passwordField = new TextField(30);
        passwordField.setText(PageAdminProfileHub.viewAdmin.getPassword());
        passwordField.setEchoChar('*');
        passwordField.setEditable(false);

        u = new Panel();
        u.add(usernameField);
        p = new Panel();
        p.add(passwordField);

        mainPanel.add(username);
        mainPanel.add(u);
        mainPanel.add(password);
        mainPanel.add(p);

        buttonPanel.add(modify);
        buttonPanel.add(delete);
        buttonPanel.add(back);

    }
}