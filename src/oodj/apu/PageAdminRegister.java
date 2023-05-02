package oodj.apu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PageAdminRegister extends JFrame implements ActionListener {

    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == cancel){
            setVisible(false);
            dispose();
            PageAdminProfileHub adminProfileHub = new PageAdminProfileHub();
            adminProfileHub.setVisible(true);
        }
        else if(e.getSource() == registerAdmin){
            String a = usernameField.getText();
            Admin check = DataIO.checkAdmin(a);
            if (check == null){
                String b = passwordField.getText();
                String c = verificationField.getText();
                if(Main.currentAdmin.getPassword().equals(c)){
                    Admin d = new Admin(a,b);
                    DataIO.allAdmin.add(d);
                    DataIO.write();
                    usernameField.setText("");
                    passwordField.setText("");
                    JOptionPane.showMessageDialog(registerAdmin,"Register Successful!");
                    setVisible(false);
                    PageAdminProfileHub adminProfileHub = new PageAdminProfileHub();
                    adminProfileHub.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(registerAdmin,"Wrong Verification Password!");
                    verificationField.setText("");
                }
            }
            else{
                JOptionPane.showMessageDialog(registerAdmin,"Username has been Used!");
                usernameField.setText("");
            }
        }
    }


    private Panel titlePanel;
    private Label title, subtitle;

    //page 3
    private Label username, password, verification;
    private TextField usernameField, passwordField, verificationField;
    private Button registerAdmin, cancel;
    private Panel textPanel,p1,p2,p3,p4,p5,p6,p7,p8;

    public PageAdminRegister() {

        this.setTitle("COVID-19 VACCINATION SYSTEM");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout(10, 6));
        this.setLocation(300, 300);
        this.setSize(800, 400);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        titlePanel = new Panel();
        titlePanel.setBackground(new Color(0xFF781F));
        titlePanel.setPreferredSize(new Dimension(1000, 80));
        titlePanel.setLayout(new BorderLayout());
        this.add(titlePanel, BorderLayout.NORTH);
        title = new Label("Malaysia Vaccination Center Program", Label.CENTER);
        title.setFont(new Font("Helvetica", Font.BOLD, 35));
        titlePanel.add(title, BorderLayout.NORTH);
        subtitle = new Label("Healthy Me, Healthy You", Label.CENTER);
        subtitle.setFont(new Font("Helvetica", Font.BOLD, 20));
        titlePanel.add(subtitle, BorderLayout.CENTER);

        //Label
        username = new Label("New Admin Username",Label.CENTER);
        username.setForeground(Color.WHITE);

        password = new Label("New Admin Password",Label.CENTER);
        password.setForeground(Color.WHITE);

        verification = new Label("Current Admin Password (Verification)",Label.CENTER);
        verification.setForeground(Color.WHITE);

        //TextField
        usernameField = new TextField(30);
        passwordField= new TextField(30);
        verificationField = new TextField(30);
        verificationField.setEchoChar('*');

        //Button
        registerAdmin = new Button("Register Admin");
        cancel = new Button("Cancel");
        registerAdmin.addActionListener(this);
        cancel.addActionListener(this);

        //Pannel
        textPanel = new Panel();
        p1 = new Panel();
        p2 = new Panel();
        p3 = new Panel();
        p4 = new Panel();
        p5 = new Panel();
        p6 = new Panel();
        p7 = new Panel();
        p8 = new Panel();

        this.add(textPanel,BorderLayout.CENTER);
        textPanel.setLayout(new GridLayout(8,1));
        p1.add(username);
        textPanel.add(p1);
        p2.add(usernameField);
        textPanel.add(p2);
        p3.add(password);
        textPanel.add(p3);
        p4.add(passwordField);
        textPanel.add(p4);
        p5.add(verification);
        textPanel.add(p5);
        p6.add(verificationField);
        textPanel.add(p6);
        p7.add(registerAdmin);
        textPanel.add(p7);
        p8.add(cancel);
        textPanel.add(p8);
    }
}
